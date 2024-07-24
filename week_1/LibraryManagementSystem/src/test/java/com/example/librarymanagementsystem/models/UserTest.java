package com.example.librarymanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1, "Tim Cook", "tcook@example.com");
    }
    
    
}
