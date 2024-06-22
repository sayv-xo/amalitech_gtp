package com.example.librarymanagementsystem.models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Transaction {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty title;
    private final ObjectProperty<LocalDate> borrowDate;
    private final ObjectProperty<LocalDate> returnDate;


    public Transaction(int id, String name, String title, LocalDate borrowDate, LocalDate returnDate) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.title = new SimpleStringProperty(title);
        this.borrowDate = new SimpleObjectProperty<>(borrowDate);
        this.returnDate = new SimpleObjectProperty<>(returnDate);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public LocalDate getBorrowDate() {
        return borrowDate.get();
    }

    public ObjectProperty<LocalDate> borrowDateProperty() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate.set(borrowDate);
    }

    public LocalDate getReturnDate() {
        return returnDate.get();
    }

    public ObjectProperty<LocalDate> returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate.set(returnDate);
    }
}
