package com.example.librarymanagementsystem.engine;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.librarymanagementsystem.models.Book;
import com.example.librarymanagementsystem.models.Transaction;
import com.example.librarymanagementsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseManager {
    public Connection connect() {
        // Connect to the database
        String url = "jdbc:mysql://localhost:3306/lms_db";
        String user = "root";
        String password = "xoxo";
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection to the database failed.");
            e.printStackTrace();
        }
        return conn;
    }

    // Method to insert a user
    public void insertUser(User user) {
        String sql = "INSERT INTO Users (Name, Email) VALUES (?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to delete a user
    public void deleteUser(int userID) {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to insert a book
    public void insertBook(Book book) {
        String sql = "INSERT INTO Books (Title, Author) VALUES (?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to delete a book
    public void deleteBook(int bookID) {
        String sql = "DELETE FROM Books WHERE BookID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("UserID");
                String name = rs.getString("name");
                String email = rs.getString("email");
                users.add(new User(id, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("BookID");
                String title = rs.getString("title");
                String author = rs.getString("author");
                books.add(new Book(id, title, author));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


    // Method to add a borrowed book
    public void insertTransaction(Transaction transaction) {
        String query = "INSERT INTO Transactions (Name, Title, BorrowDate, ReturnDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, transaction.getName());
            pstmt.setString(2, transaction.getTitle());
            pstmt.setDate(3, Date.valueOf(transaction.getBorrowDate()));
            pstmt.setDate(4, transaction.getReturnDate() != null ? Date.valueOf(transaction.getReturnDate()) : null);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to remove a borrowed book
    public void deleteTransaction(int transactionId) {
        String sql = "DELETE FROM Transactions WHERE TransactionID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transactionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all transactions in a table
    public ObservableList<Transaction> getAllTransactions() {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        String query = "SELECT * FROM Transactions";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("TransactionID");
                String name = rs.getString("Name");
               String title = rs.getString("Title");
                LocalDate borrowDate = rs.getDate("BorrowDate").toLocalDate();
                LocalDate returnDate = rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null;
                transactions.add(new Transaction(id, name, title, borrowDate, returnDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
