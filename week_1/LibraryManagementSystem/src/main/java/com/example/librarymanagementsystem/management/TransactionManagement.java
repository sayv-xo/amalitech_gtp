package com.example.librarymanagementsystem.management;


import com.example.librarymanagementsystem.engine.DatabaseManager;
import com.example.librarymanagementsystem.models.Book;
import com.example.librarymanagementsystem.models.Transaction;
import com.example.librarymanagementsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class TransactionManagement extends ManagementBase<Transaction> {
    private ObservableList<User> userData;
    private ObservableList<Book> bookData;
    private ObservableList<Transaction> transactionData;

    public TransactionManagement() {
        super();
        userData = FXCollections.observableArrayList();
        bookData = FXCollections.observableArrayList();
        transactionData = FXCollections.observableArrayList();

    }

    @Override
    public VBox getManagementPane() {
        VBox transactionManagementPane = new VBox(10);

        // Clear any existing columns to prevent duplication
        tableView.getColumns().clear();

        // TableView setup
        TableColumn<Transaction, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<Transaction, String> nameColumn = new TableColumn<>("User");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Transaction, String> titleColumn = new TableColumn<>("Book Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Transaction, LocalDate> borrowDateColumn = new TableColumn<>("Borrow Date");
        borrowDateColumn.setCellValueFactory(cellData -> cellData.getValue().borrowDateProperty());

        TableColumn<Transaction, LocalDate> returnDateColumn = new TableColumn<>("Return Date");
        returnDateColumn.setCellValueFactory(cellData -> cellData.getValue().returnDateProperty());

        tableView.getColumns().addAll(idColumn, nameColumn, titleColumn, borrowDateColumn, returnDateColumn);

        // Load users and books into lists
        loadUsers();
        loadBooks();

        // Form elements
        ComboBox<User> userComboBox = new ComboBox<>(userData);
        userComboBox.setPromptText("Select User");
        userComboBox.setId("userComboBox");
        ComboBox<Book> bookComboBox = new ComboBox<>(bookData);
        bookComboBox.setPromptText("Select Book");
        bookComboBox.setId("bookComboBox");
        DatePicker borrowDatePicker = new DatePicker();
        borrowDatePicker.setPromptText("Borrow Date");
        borrowDatePicker.setId("borrowDatePicker");
        DatePicker returnDatePicker = new DatePicker();
        returnDatePicker.setPromptText("Return Date");
        returnDatePicker.setId("returnDatePicker");
        Button borrowButton = new Button("Borrow Book");
        borrowButton.setId("borrowButton");
        Button returnButton = new Button("Return Book");
        returnButton.setId("returnButton");

        // Add elements to pane
        transactionManagementPane.getChildren().addAll(
                new Label("User:"), userComboBox,
                new Label("Book:"), bookComboBox,
                new Label("Borrow Date:"), borrowDatePicker,
                new Label("Return Date:"), returnDatePicker,
                borrowButton, returnButton,
                new Label("Transactions:"), tableView
        );

        // Load transactions into table
        loadData();

        // Add functionality to buttons
        borrowButton.setOnAction(e -> {
            User selectedUser = userComboBox.getValue();
            Book selectedBook = bookComboBox.getValue();
            LocalDate borrowDate = borrowDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            if (selectedUser == null || selectedBook == null || borrowDate == null || returnDate == null) {
                showAlert("Incomplete Data", "Please fill in all fields.");
            } else {
                if (isBookAlreadyBorrowed(selectedUser, selectedBook)) {
                    showAlert("Duplicate Borrow", "User has already borrowed that book.");
                } else {
                    // Call method to add transaction to database
                    DatabaseManager dbManager = new DatabaseManager();
                    dbManager.insertTransaction(new Transaction(0, selectedUser.getName(), selectedBook.getTitle(), borrowDate, returnDate));
                    loadTransactions(); // Refresh transaction list
                }
            }
        });

        returnButton.setOnAction(e -> {
            Transaction selectedTransaction = tableView.getSelectionModel().getSelectedItem();
            if (selectedTransaction != null) {
                dbManager.deleteTransaction(selectedTransaction.getId());
                loadData();
            } else {
                showAlert("No transaction selected", "Please select a transaction to return.");
            }
        });

        return transactionManagementPane;
    }

    @Override
    protected void loadData() {
        data.clear();
        data.addAll(dbManager.getAllTransactions());
        tableView.setItems(data);
    }

    private void loadUsers() {
        userData.clear();
        userData.addAll(dbManager.getAllUsers());
    }

    private void loadBooks() {
        bookData.clear();
        bookData.addAll(dbManager.getAllBooks());
    }

    // Method to check if a book is already borrowed by user
    private boolean isBookAlreadyBorrowed(User user, Book book) {
        for (Transaction transaction : transactionData) {
            if (transaction.getName().equals(user.getName()) && transaction.getTitle().equals(book.getTitle())) {
                return true;
            }
        }
        return false;
    }

    private void loadTransactions() {
        transactionData.clear();
        DatabaseManager dbManager = new DatabaseManager();
        transactionData.addAll(dbManager.getAllTransactions());
        tableView.setItems(transactionData);
    }
}
