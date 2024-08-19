package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNearestNeighbor {
    NearestNeighbor testNearestNeighbor;

    Places places;
    double earthRadius;

    @BeforeEach
    public void createConfigurationForTestCases() {
        places = new Places();
        // places.add(new Place("43.06","-89.41"));
        // places.add(new Place("42.67", "-89.01"));
        // places.add(new Place("43.04", "-87.90"));
        // places.add(new Place("43.07", "-89.40"));
        // places.add(new Place("42.72", "-88.96"));
        // places.add(new Place("43.03", "-88.02"));
        // places.add(new Place("42.95", "-87.90"));
        testNearestNeighbor = new NearestNeighbor(places, 1);
    }

    @Test
    @DisplayName("ewolving: test a two place tour (no optimization possible)")
    public void testTwoPlaces(){
        earthRadius = 1;
        places.add(new Place("0","0"));
        places.add(new Place("0","0"));
        testNearestNeighbor = new NearestNeighbor(places, earthRadius);
        Tour result = testNearestNeighbor.startNearestNeighbor();
        assertEquals(0, result.tourIndexes[0]);
    }

    @Test
    @DisplayName("lwyip: test startNearestNeighbor")
    public void testStartNearestNeighbor(){
        Tour temporaryBest;
        earthRadius = 3959;
        places.add(new Place("43.06","-89.41"));
        places.add(new Place("42.67", "-89.01"));
        places.add(new Place("43.04", "-87.90"));
        places.add(new Place("43.07", "-89.40"));
        places.add(new Place("42.72", "-88.96"));
        places.add(new Place("43.03", "-88.02"));
        places.add(new Place("42.95", "-87.90"));
        testNearestNeighbor = new NearestNeighbor(places, earthRadius);
        temporaryBest = testNearestNeighbor.startNearestNeighbor();
        temporaryBest.printTour();

        Tour actualBest = new Tour(7);
        actualBest.updateTour(4,0);
        actualBest.updateTour(1,1);
        actualBest.updateTour(3,2);
        actualBest.updateTour(0,3);
        actualBest.updateTour(5,4);
        actualBest.updateTour(2,5);
        actualBest.updateTour(6,6);
        int[] actualBestArray = actualBest.getTour();
        int[] temporaryBestArray = temporaryBest.getTour();
        for (int i = 0; i < 7; i++){
            assertEquals(actualBestArray[i], temporaryBestArray[i]);
        }

    }



}