package com.example.librarymanagementsystem.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionTest {
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        transaction = new Transaction(1, "John Doe", "Effective Java", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 15));
    }

    @Test
    public void testGetId() {
        assertEquals(1, transaction.getId());
    }

    @Test
    public void testSetId() {
        transaction.setId(2);
        assertEquals(2, transaction.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", transaction.getName());
    }

    @Test
    public void testSetName() {
        transaction.setName("Jane Doe");
        assertEquals("Jane Doe", transaction.getName());
    }

    @Test
    public void testGetTitle() {
        assertEquals("Effective Java", transaction.getTitle());
    }

    @Test
    public void testSetTitle() {
        transaction.setTitle("Java Concurrency in Practice");
        assertEquals("Java Concurrency in Practice", transaction.getTitle());
    }

    @Test
    public void testGetBorrowDate() {
        assertEquals(LocalDate.of(2023, 1, 1), transaction.getBorrowDate());
    }

    @Test
    public void testSetBorrowDate() {
        transaction.setBorrowDate(LocalDate.of(2023, 2, 1));
        assertEquals(LocalDate.of(2023, 2, 1), transaction.getBorrowDate());
    }

    @Test
    public void testGetReturnDate() {
        assertEquals(LocalDate.of(2023, 1, 15), transaction.getReturnDate());
    }

    @Test
    public void testSetReturnDate() {
        transaction.setReturnDate(LocalDate.of(2023, 2, 15));
        assertEquals(LocalDate.of(2023, 2, 15), transaction.getReturnDate());
    }

    @ParameterizedTest
    @CsvSource({
            "1, John Doe, Effective Java, 2023-01-01, 2023-01-15",
            "2, Jane Doe, Java Concurrency in Practice, 2023-02-01, 2023-02-15"
    })
    public void testTransactionConstructor(int id, String name, String title, LocalDate borrowDate, LocalDate returnDate) {
        Transaction newTransaction = new Transaction(id, name, title, borrowDate, returnDate);
        assertEquals(id, newTransaction.getId());
        assertEquals(name, newTransaction.getName());
        assertEquals(title, newTransaction.getTitle());
        assertEquals(borrowDate, newTransaction.getBorrowDate());
        assertEquals(returnDate, newTransaction.getReturnDate());
    }

    @Test
    public void testNameProperty() {
        StringProperty nameProperty = transaction.nameProperty();
        assertNotNull(nameProperty);
        nameProperty.set("Jack Doe");
        assertEquals("Jack Doe", transaction.getName());
    }

    @Test
    public void testTitleProperty() {
        StringProperty titleProperty = transaction.titleProperty();
        assertNotNull(titleProperty);
        titleProperty.set("Clean Code");
        assertEquals("Clean Code", transaction.getTitle());
    }

    @Test
    public void testBorrowDateProperty() {
        ObjectProperty<LocalDate> borrowDateProperty = transaction.borrowDateProperty();
        assertNotNull(borrowDateProperty);
        borrowDateProperty.set(LocalDate.of(2023, 3, 1));
        assertEquals(LocalDate.of(2023, 3, 1), transaction.getBorrowDate());
    }

    @Test
    public void testReturnDateProperty() {
        ObjectProperty<LocalDate> returnDateProperty = transaction.returnDateProperty();
        assertNotNull(returnDateProperty);
        returnDateProperty.set(LocalDate.of(2023, 3, 15));
        assertEquals(LocalDate.of(2023, 3, 15), transaction.getReturnDate());
    }

    @Test
    public void testIdProperty() {
        IntegerProperty idProperty = transaction.idProperty();
        assertNotNull(idProperty);
        idProperty.set(2);
        assertEquals(2, transaction.getId());
    }
}
