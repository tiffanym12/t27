package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDistancesRequest {
    DistancesRequest request;
    Places places;
    Distances distances;

    static final long bigRadius = 1000000L;
    static final long bigPi = Math.round(Math.PI * bigRadius);
    static final long bigPiHalf = Math.round(Math.PI / 2.0 * bigRadius);

    @BeforeEach
    public void createConfigurationForTestCases() {
        places = new Places();
        request = new DistancesRequest(bigRadius, places);
    }

    @Test
    @DisplayName("oppgp: empty places")
    public void testEmptyPlaces(){
        request.buildResponse();

        distances = request.distances();
        assertEquals(0, distances.size());
        assertEquals(0L, distances.total());

        assertEquals(0, request.places().size());
        assertEquals(bigRadius, request.earthRadius());

    }

    @Test
    @DisplayName("lwyip: one place")
    public void testOnePlaces(){
        places.add(new Place("0", "0"));
        request.buildResponse();
        //check distances
        distances = request.distances();
        assertEquals(1, distances.size());
        assertEquals(0L, distances.total());
        assertEquals(0L, distances.get(0));
        //check original values
        assertEquals(1, request.places().size());
        assertEquals(bigRadius, request.earthRadius());

    }

    @Test
    @DisplayName("lwyip: two places")
    public void testTwoPlaces(){
        places.add(new Place("0","0"));
        places.add(new Place("0","0"));
        request.buildResponse();

        distances = request.distances();
        assertEquals(2, distances.size());
        assertEquals(0L, distances.total());
        assertEquals(0L, distances.get(0));

        assertEquals(2, request.places().size());
        assertEquals(bigRadius, request.earthRadius());
    }

    @Test
    @DisplayName("lwyip: five places")
    public void testFivePlaces(){
        places.add(new Place("0","0"));
        places.add(new Place("0","0"));
        places.add(new Place("0","0"));
        places.add(new Place("0","0"));
        places.add(new Place("0","0"));
        request.buildResponse();

        distances = request.distances();
        assertEquals(5, distances.size());
        assertEquals(0L, distances.total());
        assertEquals(0L, distances.get(0));

        assertEquals(5, request.places().size());
        assertEquals(bigRadius, request.earthRadius());
    }
}