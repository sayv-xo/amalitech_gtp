package com.example.librarymanagementsystem;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.control.TableViewMatchers.hasTableCell;

import com.example.librarymanagementsystem.models.User;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import com.example.librarymanagementsystem.LibraryManagementSystem;

import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class LibraryManagementSystemTest {

    @Start
    public void start(Stage stage) {
        new LibraryManagementSystem().start(stage);
    }

    @Test
    public void shouldAddUser(FxRobot robot) {
        // Navigate to User Management section
        robot.clickOn("#btnManageUsers");

        // Enter user details
        robot.clickOn("#nameField").write("John Doe");
        robot.clickOn("#emailField").write("john.doe@example.com");

        // Click on Add User button
        robot.clickOn("#btnAddUser");

        robot.sleep(500);

        verifyThat(".table-view", hasTableCell("John Doe"));
        verifyThat(".table-view", hasTableCell("john.doe@example.com"));
    }

    @Test
    public void shouldDeleteUser(FxRobot robot) {
        // Navigate to User Management section
        robot.clickOn("#btnManageUsers");

        // Select the user in the TableView by clicking on the cell content
        TableView<?> usertableView = robot.lookup(".table-view").queryAs(TableView.class);
        int rowIndex = 0; // index of the row to select, adjust if needed
        robot.interact(() -> usertableView.getSelectionModel().select(rowIndex));
        robot.clickOn(".table-view .table-row-cell", MouseButton.PRIMARY);
        robot.clickOn("#btnDeleteUser");

        // Verify that the user was removed from the table
        verifyThat(".table-view", tableView -> !hasTableCell("John Doe").matches(tableView));
        verifyThat(".table-view", tableView -> !hasTableCell("john.doe@example.com").matches(tableView));
    }

    @Test
    public void shouldAddBook(FxRobot robot) {
        // Navigate to Book Management section
        robot.clickOn("#btnManageBooks");

        // Enter Book details
        robot.clickOn("#titleField").write("Effective Java");
        robot.clickOn("#authorField").write("Joshua Bloch");

        // Click on Add Book button
        robot.clickOn("#addBookButton");

        robot.sleep(500);

        verifyThat(".table-view", hasTableCell("Effective Java"));
        verifyThat(".table-view", hasTableCell("Joshua Bloch"));
    }

    @Test
    public void shouldDeleteBook(FxRobot robot) {
        // Navigate to Book Management section
        robot.clickOn("#btnManageBooks");

        // Select the book in the TableView by clicking on the cell content
        TableView<?> booktableView = robot.lookup(".table-view").queryAs(TableView.class);
        int rowIndex = 0; // index of the row to select, adjust if needed
        robot.interact(() -> booktableView.getSelectionModel().select(rowIndex));
        robot.clickOn(".table-view .table-row-cell", MouseButton.PRIMARY);
        robot.clickOn("#deleteBookButton");

        // Verify that the book was removed from the table
        verifyThat(".table-view", tableView -> !hasTableCell("Effective Java").matches(tableView));
        verifyThat(".table-view", tableView -> !hasTableCell("Joshua Bloch").matches(tableView));
    }
}
