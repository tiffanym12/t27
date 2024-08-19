package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCredential {

    String correctUser = "cs314-db";
    String correctPassword = "eiK5liet1uej";
    
    @Test
    @DisplayName("lwyip: Test user credentials")
    public void testUserCredentials(){
        assertEquals(Credential.getUser(), correctUser);
    }

    @Test
    @DisplayName("lwyip: Test password credentials")
    public void testPasswordCredentials(){
        assertEquals(Credential.getPassword(), correctPassword);
    }
}
