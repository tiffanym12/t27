package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

public class TestDistanceData {
    
    DistanceData distances;
    Places places;
    Double earthRadius;

    double actualRadiuskm = 6371.0;
    double actualRadiusmi = 3959.0;
    double smallRadius = 1.0;
    double bigRadius = 1000000.0;

    @BeforeEach
    public void createConfigurationForTestCases() {
        places = new Places();
    }

    @Test
    @DisplayName("lwyip: Rows equal Columns")
    public void testCtorRowsEqualColumnsMatrix(){
        places.add(new Place("0", "0"));
        places.add(new Place("0", "0"));
        places.add(new Place("0", "0"));
        places.add(new Place("0", "0"));
        distances = new DistanceData(places, smallRadius);

        assertTrue(distances.getRowLength() == distances.getColLength(0));
        assertTrue(distances.getRowLength() == distances.getColLength(1));
        assertTrue(distances.getRowLength() == distances.getColLength(2));
        assertTrue(distances.getRowLength() == distances.getColLength(3));
    }

    @Test
    @DisplayName("lwyip: Create Matrix of Four places")
    public void testCtorFourPlacesMatrix(){
        //Four place minimum to run algorithim
        places.add(new Place("0", "0"));
        places.add(new Place("0", "0"));
        places.add(new Place("0", "0"));
        places.add(new Place("0", "0"));

        distances = new DistanceData(places, smallRadius);
        assertEquals(4, distances.getRowLength());
        assertEquals(4, distances.getColLength(0));

    }
    @Test
    @DisplayName("lwyip: Create Matrix of 20 places")
    public void testCtor20PlacesMatrix(){
        for (int i = 0; i < 20; i++){
            places.add(new Place("0", "0"));
        }

        distances = new DistanceData(places, smallRadius);
        assertEquals(20, distances.getRowLength());
        assertEquals(20, distances.getColLength(0));
    }
    
    @Test
    @DisplayName("lwyip: create distance matrix with zero distances")
    public void testMatrixZeroDistances(){
        for (int i = 0; i < 4; i++){
            places.add(new Place("0", "0"));
        }
        distances = new DistanceData(places, smallRadius);
        distances.fillDistances();
        distances.printMatrix();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                assertTrue(0 == distances.getDistanceValue(i,j));
            }
        }
    }

    @Test
    @DisplayName("lwyip: create distance matrix with big radius")
    public void testMatrixBigRadius(){
        places.add(new Place("90.0", "179.9"));
        places.add(new Place("-90.0", "-180.0"));
        places.add(new Place("-89.9", "-179.9"));
        places.add(new Place("-89.8", "-179.8"));

        distances = new DistanceData(places, bigRadius);
        distances.fillDistances();
        distances.printMatrix();
        for (int i = 0; i < 4; i++){
            assertTrue(0 == distances.getDistanceValue(i,i));
        }
        assertTrue(3141593 == distances.getDistanceValue(1,0));
        assertTrue(3139847 == distances.getDistanceValue(2,0));
        assertTrue(1745 == distances.getDistanceValue(2,1));
        assertTrue(3138102 == distances.getDistanceValue(3,0));
        assertTrue(3491 == distances.getDistanceValue(3,1));
        assertTrue(1745 == distances.getDistanceValue(3,2));

        assertTrue(distances.getDistanceValue(1,0) == distances.getDistanceValue(0,1));
        assertTrue(distances.getDistanceValue(2,0) == distances.getDistanceValue(0,2));
        assertTrue(distances.getDistanceValue(2,1) == distances.getDistanceValue(1,2));
        assertTrue(distances.getDistanceValue(3,0) == distances.getDistanceValue(0,3));
        assertTrue(distances.getDistanceValue(3,1) == distances.getDistanceValue(1,3));
        assertTrue(distances.getDistanceValue(3,2) == distances.getDistanceValue(2,3));
    }

    @Test
    @DisplayName("lwyip: test wisc original total trip Distance")
    public void testWiscOrigTotalTripDistance(){
        places.add(new Place("43.06","-89.41"));
        places.add(new Place("42.67", "-89.01"));
        places.add(new Place("43.04", "-87.90"));
        places.add(new Place("43.07", "-89.40"));
        places.add(new Place("42.72", "-88.96"));
        places.add(new Place("43.03", "-88.02"));
        places.add(new Place("42.95", "-87.90"));
        distances = new DistanceData(places, actualRadiusmi);
        distances.fillDistances();
        System.out.println("printing Madison Trip");
        distances.printMatrix();
        System.out.println("----");

        Tour defaultTour = new Tour(places.size());
        assertEquals(342, distances.getTotalTripDistance(defaultTour));
    }

    @Test
    @DisplayName("lwyip: test wisc modified total trip distance")
    public void testWiscModifiedTotalTripDistance(){
        places.add(new Place("43.06","-89.41"));
        places.add(new Place("42.67", "-89.01"));
        places.add(new Place("43.04", "-87.90"));
        places.add(new Place("43.07", "-89.40"));
        places.add(new Place("42.72", "-88.96"));
        places.add(new Place("43.03", "-88.02"));
        places.add(new Place("42.95", "-87.90"));
        distances = new DistanceData(places, actualRadiusmi);
        distances.fillDistances();
        System.out.println("printing Madison Trip");
        distances.printMatrix();
        System.out.println("----");

        Tour defaultTour = new Tour(places.size());
        System.out.println("Before Tour update");
        System.out.println(Arrays.toString(defaultTour.getTour()));
        //change tour from [0,1,2,3,4,5,6] to [0,3,4,1,2,5,6] = 191 miles
        defaultTour.updateTour(3,1);
        defaultTour.updateTour(4,2);
        defaultTour.updateTour(1,3);
        defaultTour.updateTour(2,4);
        System.out.println("After Tour update");
        System.out.println(Arrays.toString(defaultTour.getTour()));
        assertEquals(191, distances.getTotalTripDistance(defaultTour));
    }

    @Test
    @DisplayName("ewolving: test basic getNextTripLocation method")
    public void testGetNextTripLocation(){
        places.add(new Place("90.0", "179.9"));
        places.add(new Place("-90.0", "-180.0"));
        places.add(new Place("-89.9", "-179.9"));
        places.add(new Place("-89.8", "-179.8"));

        distances = new DistanceData(places, bigRadius);
        distances.fillDistances();
        UnvisitedCities testCities = new UnvisitedCities(4);
        testCities.visitCity(0);

        int testNext = distances.getNextLegTripLocation(0, testCities);
        assertEquals(testNext, 3);
        
    }

    @Test
    @DisplayName("ewolving: test last iteration of getNextTripLocation method")
    public void testLastTripLocation(){
        places.add(new Place("90.0", "179.9"));
        places.add(new Place("-90.0", "-180.0"));
        places.add(new Place("-89.9", "-179.9"));
        places.add(new Place("-89.8", "-179.8"));

        distances = new DistanceData(places, bigRadius);
        distances.fillDistances();
        UnvisitedCities testCities = new UnvisitedCities(4);
        testCities.visitCity(1);
        testCities.visitCity(2);
        testCities.visitCity(3);

        int testNext = distances.getNextLegTripLocation(2, testCities);
        assertEquals(testNext, 0);       
    }

    @Test
    @DisplayName("ewolving: test a middle iteration of getNextTripLocation method")
    public void testMiddleTripLocation(){
        places.add(new Place("90.0", "179.9"));
        places.add(new Place("-90.0", "-180.0"));
        places.add(new Place("-89.9", "-179.9"));
        places.add(new Place("-89.8", "-179.8"));

        distances = new DistanceData(places, bigRadius);
        distances.fillDistances();
        UnvisitedCities testCities = new UnvisitedCities(4);
        testCities.visitCity(0);
        testCities.visitCity(3);

        int testNext = distances.getNextLegTripLocation(3, testCities);
        assertEquals(testNext, 2);       
    }
}
