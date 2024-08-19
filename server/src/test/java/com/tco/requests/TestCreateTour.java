package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreateTour {
    CreateTour testCreateTour;
    
    Places places;
    double earthRadius;

    @BeforeEach
    public void createConfigurationForTestCases() {
        places = new Places();
        earthRadius = 3000.0;
        testCreateTour = new CreateTour(places, earthRadius);
    }

    @Test
    @DisplayName("ewolving: test that constructor is initializing things")
    public void testCreateTourConstructor(){
        Places temp = testCreateTour.getOptimizedPlaces();
        //Constructor initializes optimized places to the starting places. Some cases will already be optimal.
        assertEquals(temp, places);
    }

    @Test
    @DisplayName("ewolving: test Tour rotation method")
    public void testTourRotationMethod(){
        Tour unrotatedTour = new Tour(4);
        unrotatedTour.updateTour(3, 0);
        unrotatedTour.updateTour(0, 3);  
        //Tour = [3, 1, 2, 0]
        Tour rotated = new Tour(4);
        rotated = testCreateTour.rotateToStartingLocation(unrotatedTour);
        //Tour = [0, 3, 1, 2]
        assertEquals(rotated.tourIndexes[0], 0);
        assertEquals(rotated.tourIndexes[1], 3);
    }

    @Test
    @DisplayName("ewolving: test Place movement method")
    public void testPlaceReorganizationMethod(){
        Tour unrotatedTour = new Tour(4);
        unrotatedTour.updateTour(3, 0);
        unrotatedTour.updateTour(0, 3);  
        //Tour = [3, 1, 2, 0]
        Tour rotated = new Tour(4);
        rotated = testCreateTour.rotateToStartingLocation(unrotatedTour);
        //Tour = [0, 3, 1, 2]
        
        places.add(new Place("43.06","-89.41"));
        places.add(new Place("42.67", "-89.01"));
        places.add(new Place("43.04", "-87.90"));
        places.add(new Place("43.07", "-89.40"));

        testCreateTour = new CreateTour(places, earthRadius);
        Places temporaryPlaces = testCreateTour.reorderPlaces(rotated);

        Places correctPlaces = new Places();

        correctPlaces.add(new Place("43.06","-89.41"));
        correctPlaces.add(new Place("43.07", "-89.40"));
        correctPlaces.add(new Place("42.67", "-89.01"));
        correctPlaces.add(new Place("43.04", "-87.90"));

        assertEquals(temporaryPlaces, correctPlaces);
    }
}
