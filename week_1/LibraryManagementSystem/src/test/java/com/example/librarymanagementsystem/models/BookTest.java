package com.example.librarymanagementsystem.models;

import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookTest {
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book(1, "Effective Java", "Joshua Bloch");
    }

    @Test
    public void testGetBookID() {
        assertEquals(1, book.getBookID());
    }

    @Test
    public void testSetBookID() {
        book.setId(2);
        assertEquals(2, book.getBookID());
    }

    @Test
    public void testGetTitle() {
        assertEquals("Effective Java", book.getTitle());
    }

    @Test
    public void testSetTitle() {
        book.setTitle("Java Concurrency in Practice");
        assertEquals("Java Concurrency in Practice", book.getTitle());
    }

    @Test
    public void testGetAuthor() {
        assertEquals("Joshua Bloch", book.getAuthor());
    }

    @Test
    public void testSetAuthor() {
        book.setAuthor("Brian Goetz");
        assertEquals("Brian Goetz", book.getAuthor());
    }

    @ParameterizedTest
    @CsvSource({
            "1, Effective Java, Joshua Bloch",
            "2, Java Concurrency in Practice, Brian Goetz"
    })
    public void testBookConstructor(int id, String title, String author) {
        Book newBook = new Book(id, title, author);
        assertEquals(id, newBook.getBookID());
        assertEquals(title, newBook.getTitle());
        assertEquals(author, newBook.getAuthor());
    }

    @Test
    public void testToString() {
        assertEquals("Effective Java", book.toString());
    }

    @Test
    public void testTitleProperty() {
        StringProperty titleProperty = book.titleProperty();
        assertNotNull(titleProperty);
        titleProperty.set("Clean Code");
        assertEquals("Clean Code", book.getTitle());
    }

    @Test
    public void testAuthorProperty() {
        StringProperty authorProperty = book.authorProperty();
        assertNotNull(authorProperty);
        authorProperty.set("Robert C. Martin");
        assertEquals("Robert C. Martin", book.getAuthor());
    }
}
