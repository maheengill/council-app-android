package com.example.myapplication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

    // asserts the constructor sets the firstName correctly
    @Test
    void checkFirstNameConstructor() {
        String firstName = "firstName";
        User user = new User(firstName, "lastName", "Username");
        assertEquals(firstName, user.getFirstName());
    }

    @Test
    void checkLastNameConstructor() {

        String lastName = "lastName";
        User user = new User("firstName", lastName, "Username");
        assertEquals(lastName, user.getLastName());
    }

    @Test
    void checkUsernameConstructor() {
        String username = "username";
        User user = new User("firstName", "lastName", username);
        assertEquals(username, user.getUsername());
    }

}