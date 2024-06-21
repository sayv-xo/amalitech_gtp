package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.engine.DatabaseManager;
import com.example.librarymanagementsystem.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.*;

public class BookManagement {
    private TableView<Book> tableView;
    private ObservableList<Book> bookData;

    public BookManagement() {
        tableView = new TableView<>();
        bookData = FXCollections.observableArrayList();
    }

    public VBox getBookManagementPane() {
        VBox bookManagementPane = new VBox(10);

        // TableView setup
        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn);

        // Form elements
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");
        Button addButton = new Button("Add Book");
        Button deleteButton = new Button("Delete Book");

        // Add elements to pane
        bookManagementPane.getChildren().addAll(
                new Label("Title:"), titleField,
                new Label("Author:"), authorField,
                addButton, deleteButton,
                new Label("Books:"), tableView
        );

        // Load books into table
        loadBooks();

        // Add functionality to buttons
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            // Call method to add book to database
            DatabaseManager dbManager = new DatabaseManager();
            dbManager.insertBook(new Book(0, title, author));
            loadBooks(); // Refresh book list
        });

        deleteButton.setOnAction(e -> {
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                // Call method to delete book from database
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.deleteBook(selectedBook.getBookID());
                loadBooks(); // Refresh book list
            } else {
                showAlert("No book selected", "Please select a book to delete.");
            }
        });

        return bookManagementPane;
    }

    private void loadBooks() {
        bookData.clear();
        DatabaseManager dbManager = new DatabaseManager();
        try (Connection conn = dbManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Books")) {

            while (rs.next()) {
                int id = rs.getInt("BookID");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                bookData.add(new Book(id, title, author));
            }
            tableView.setItems(bookData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
