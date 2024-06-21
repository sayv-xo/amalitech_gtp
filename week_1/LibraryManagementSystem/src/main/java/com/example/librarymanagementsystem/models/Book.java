package com.example.librarymanagementsystem.models;

import javafx.beans.property.*;

public class Book {
    private final IntegerProperty bookID;
    private final StringProperty title;
    private final StringProperty author;

    public Book(int id, String title, String author) {
        this.bookID = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
    }

    public int getBookID() {
        return bookID.get();
    }

    public IntegerProperty idProperty() {
        return bookID;
    }

    public void setId(int id) {
        this.bookID.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }
}
