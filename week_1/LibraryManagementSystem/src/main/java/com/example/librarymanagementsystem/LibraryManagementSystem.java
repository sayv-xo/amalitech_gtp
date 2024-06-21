package com.example.librarymanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryManagementSystem extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        // Layouts
        BorderPane root = new BorderPane();
        VBox leftMenu = new VBox(10);
        VBox centerContent = new VBox(10);

        // Menu Buttons
        Button btnManageUsers = new Button("Manage Users");
        Button btnManageBooks = new Button("Manage Books");
        Button btnManageTransactions = new Button("Manage Transactions");

        leftMenu.getChildren().addAll(btnManageUsers, btnManageBooks, btnManageTransactions);

        // Add layouts to root
        root.setLeft(leftMenu);
        root.setCenter(centerContent);

        // Scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event Handling
        btnManageUsers.setOnAction(e -> {
            UserManagement userManagement = new UserManagement();
            centerContent.getChildren().clear();
            centerContent.getChildren().add(userManagement.getUserManagementPane());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
