package com.example.librarymanagementsystem.engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.example.librarymanagementsystem.models.Book;
import com.example.librarymanagementsystem.models.User;

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

    // Method to borrow a book
    public void borrowBook(int userID, int bookID, Date borrowDate, Date dueDate) {
        String sql = "INSERT INTO Transactions (UserID, BookID, BorrowDate, DueDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setInt(2, bookID);
            pstmt.setDate(3, new java.sql.Date(borrowDate.getTime()));
            pstmt.setDate(4, new java.sql.Date(dueDate.getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to return a book
    public void returnBook(int transactionID, Date returnDate) {
        String sql = "UPDATE Transactions SET ReturnDate = ? WHERE TransactionID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(returnDate.getTime()));
            pstmt.setInt(2, transactionID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to view borrowed books
    public void viewBorrowedBooks() {
        String sql = "SELECT * FROM Transactions WHERE ReturnDate IS NULL";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("TransactionID: " + rs.getInt("TransactionID"));
                System.out.println("UserID: " + rs.getInt("UserID"));
                System.out.println("BookID: " + rs.getInt("BookID"));
                System.out.println("BorrowDate: " + rs.getDate("BorrowDate"));
                System.out.println("DueDate: " + rs.getDate("DueDate"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to view returned books
    public void viewReturnedBooks() {
        String sql = "SELECT * FROM Transactions WHERE ReturnDate IS NOT NULL";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("TransactionID: " + rs.getInt("TransactionID"));
                System.out.println("UserID: " + rs.getInt("UserID"));
                System.out.println("BookID: " + rs.getInt("BookID"));
                System.out.println("BorrowDate: " + rs.getDate("BorrowDate"));
                System.out.println("ReturnDate: " + rs.getDate("ReturnDate"));
                System.out.println("DueDate: " + rs.getDate("DueDate"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
