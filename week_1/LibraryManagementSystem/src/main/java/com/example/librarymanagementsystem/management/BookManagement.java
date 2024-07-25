package com.example.librarymanagementsystem.management;


import com.example.librarymanagementsystem.models.Book;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class BookManagement extends ManagementBase<Book> {

    public BookManagement() {
        super();
    }

    @Override
    public VBox getManagementPane() {
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
        titleField.setId("titleField");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");
        authorField.setId("authorField");
        Button addButton = new Button("Add Book");
        addButton.setId("addBookButton");
        Button deleteButton = new Button("Delete Book");
        deleteButton.setId("deleteBookButton");

        // Add elements to pane
        bookManagementPane.getChildren().addAll(
                new Label("Title:"), titleField,
                new Label("Author:"), authorField,
                addButton, deleteButton,
                new Label("Books:"), tableView
        );

        // Load books into table
        loadData();

        // Add functionality to buttons
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            if (title.isEmpty() || author.isEmpty()) {
                showAlert("Incomplete Data", "Please fill in all fields.");
            } else {
                dbManager.insertBook(new Book(0, title, author));
                loadData();
            }
        });

        deleteButton.setOnAction(e -> {
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                dbManager.deleteBook(selectedBook.getBookID());
                loadData();
            } else {
                showAlert("No book selected", "Please select a book to delete.");
            }
        });

        return bookManagementPane;
    }

    @Override
    protected void loadData() {
        data.clear();
        data.addAll(dbManager.getAllBooks());
        tableView.setItems(data);
    }
}
