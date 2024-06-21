package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.engine.DatabaseManager;
import com.example.librarymanagementsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.*;

public class UserManagement {
    private TableView<User> tableView;
    private ObservableList<User> userData;

    public UserManagement() {
        tableView = new TableView<>();
        userData = FXCollections.observableArrayList();
    }

    public VBox getUserManagementPane() {
        VBox userManagementPane = new VBox(10);

        // TableView setup
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        tableView.getColumns().addAll(idColumn, nameColumn, emailColumn);

        // Form elements
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        Button addButton = new Button("Add User");
        Button deleteButton = new Button("Delete User");

        // Add elements to pane
        userManagementPane.getChildren().addAll(
            new Label("Name:"), nameField,
            new Label("Email:"), emailField,
            addButton, deleteButton,
            new Label("Users:"), tableView
        );

        // Load users into table
        loadUsers();

        // Add functionality to buttons
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            // Call method to add user to database
            DatabaseManager dbManager = new DatabaseManager();
            dbManager.insertUser(new User(0, name, email));
            loadUsers(); // Refresh user list
        });

        deleteButton.setOnAction(e -> {
            User selectedUser = tableView.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                // Call method to delete user from database
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.deleteUser(selectedUser.getUserID());
                loadUsers(); // Refresh user list
            } else {
                showAlert("No user selected", "Please select a user to delete.");
            }
        });

        return userManagementPane;
    }

    private void loadUsers() {
        userData.clear();
        DatabaseManager dbManager = new DatabaseManager();
        try (Connection conn = dbManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Users")) {

            while (rs.next()) {
                int id = rs.getInt("UserID");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                userData.add(new User(id, name, email));
            }
            tableView.setItems(userData);
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
