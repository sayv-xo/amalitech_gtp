package com.example.librarymanagementsystem.models;

import java.util.LinkedList;

public class Library {
    private LinkedList<User> users = new LinkedList<>();
    private LinkedList<Book> books = new LinkedList<>();

    // Methods to add and remove users and books
    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(int userID) {
        users.removeIf(user -> user.getUserID() == userID);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int bookID) {
        books.removeIf(book -> book.getBookID() == bookID);
    }

    // Getters for users and books
    public LinkedList<User> getUsers() {
        return users;
    }

    public LinkedList<Book> getBooks() {
        return books;
    }
}
