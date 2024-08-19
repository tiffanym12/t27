package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

public class TestTour {
    Tour testTour;


    @BeforeEach
    public void createConfigurationForTestCases() {
        testTour = new Tour(10);
    }

    @Test
    @DisplayName("ewolving: test that Tour constructor is working")
    public void testTourConstructor(){
        assertEquals(3, testTour.tourIndexes[3]);
    }

    @Test
    @DisplayName("lwyip: tour test getter")
    public void testTourGetter(){
        int[] tour = testTour.getTour();
        assertTrue(tour != null);
        System.out.println(Arrays.toString(tour));
    }

    @Test
    @DisplayName("ewolving: test updateTour method")
    public void testUpdateTour(){
        testTour.updateTour(3, 0);
        testTour.updateTour(0, 3);
        assertEquals(3, testTour.tourIndexes[0]);
    }

    @Test
    @DisplayName("ewolving: test that Tour constructor is working")
    public void testGetSize(){
        assertEquals(10, testTour.getSize());
    }
}
