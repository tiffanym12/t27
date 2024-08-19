package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUnvisitedCities {
    UnvisitedCities testCities;

    @BeforeEach
    public void createConfigurationForTestCases() {
        testCities = new UnvisitedCities();
    }

    @Test
    @DisplayName("ewolving: test UnvisitedCities constructor")
    public void testNonDefaultConstructor() {
        testCities = new UnvisitedCities(10);
        assertEquals(testCities.getVisitedStatus(3), false); //constructor should initialize all to false
    }

    @Test
    @DisplayName("ewolving: test visitCity method")
    public void testVisitCity() {
        testCities = new UnvisitedCities(10);
        testCities.visitCity(3);
        assertEquals(testCities.getVisitedStatus(3), true); 
    }

    @Test
    @DisplayName("ewolving: test resetAll method")
    public void testResetAllVisits() {
        testCities = new UnvisitedCities(10);
        testCities.visitCity(3);
        testCities.visitCity(7);
        testCities.resetAllVisitStatus();
        assertEquals(testCities.getVisitedStatus(3), false); 
        assertEquals(testCities.getVisitedStatus(7), false); 
    }

    @Test
    @DisplayName("lwyip: test checkForAllVisited False")
    public void testCheckForAllVisitedFalse(){
        testCities = new UnvisitedCities(2);
        testCities.visitCity(0);
        assertEquals(testCities.checkForAllVisited(), false);
    }

    @Test
    @DisplayName("lwyip test checkForAllVisited True")
    public void testCheckForAllVisitedTrue(){
        testCities = new UnvisitedCities(2);
        testCities.visitCity(0);
        testCities.visitCity(1);
        assertEquals(testCities.checkForAllVisited(), true);
    }
    

}