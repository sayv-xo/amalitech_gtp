package com.example.librarymanagementsystem.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        User user = new User(1, "John Doe", "john@example.com");
        assertEquals(1, user.getUserID());
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void testSetters() {
        User user = new User(1, "John Doe", "john@example.com");
        user.setUserId(2);
        user.setName("Jane Doe");
        user.setEmail("jane@example.com");

        assertEquals(2, user.getUserID());
        assertEquals("Jane Doe", user.getName());
        assertEquals("jane@example.com", user.getEmail());
    }

    @Test
    void testProperties() {
        User user = new User(1, "John Doe", "john@example.com");
        assertEquals(1, user.idProperty().get());
        assertEquals("John Doe", user.nameProperty().get());
        assertEquals("john@example.com", user.emailProperty().get());
    }

    @Test
    void testToString() {
        User user = new User(1, "John Doe", "john@example.com");
        assertEquals("John Doe", user.toString());
    }

    @Test
    void testNullValues() {
        User user = new User(1, null, null);
        assertNull(user.getName());
        assertNull(user.getEmail());
    }

    @Test
    void testEmptyStrings() {
        User user = new User(1, "", "");
        assertEquals("", user.getName());
        assertEquals("", user.getEmail());
    }

    @Test
    void testNegativeUserID() {
        User user = new User(-1, "John Doe", "john@example.com");
        assertEquals(-1, user.getUserID());
    }
}