package com.example.librarymanagementsystem.models;

import javafx.beans.property.*;

public class User {
    private final IntegerProperty userID;
    private final StringProperty name;
    private final StringProperty email;

    public User(int userID, String name, String email) {
        this.userID = new SimpleIntegerProperty(userID);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
    }

    public int getUserID() {
        return userID.get();
    }

    public IntegerProperty idProperty() {
        return userID;
    }

    public void setUserId(int id) {
        this.userID.set(id);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
