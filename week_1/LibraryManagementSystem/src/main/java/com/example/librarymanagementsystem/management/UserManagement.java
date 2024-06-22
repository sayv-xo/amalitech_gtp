package com.example.librarymanagementsystem.management;



import com.example.librarymanagementsystem.models.User;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class UserManagement extends ManagementBase<User> {

    public UserManagement() {
        super();
    }

    @Override
    public VBox getManagementPane() {
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
        loadData();

        // Add functionality to buttons
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            if (name.isEmpty() || email.isEmpty()) {
                showAlert("Incomplete Data", "Please fill in all fields.");
            } else {
                dbManager.insertUser(new User(0, name, email));
                loadData();
            }
        });

        deleteButton.setOnAction(e -> {
            User selectedUser = tableView.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                dbManager.deleteUser(selectedUser.getUserID());
                loadData();
            } else {
                showAlert("No user selected", "Please select a user to delete.");
            }
        });

        return userManagementPane;
    }

    @Override
    protected void loadData() {
        data.clear();
        data.addAll(dbManager.getAllUsers());
        tableView.setItems(data);
    }
}
