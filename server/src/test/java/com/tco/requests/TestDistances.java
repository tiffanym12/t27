package com.tco.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDistances {

    @Test
    @DisplayName("tmiao: distance with no places")
    public void testNoDistances() {
        Distances distances = new Distances();
        assertEquals(0, distances.size());
        assertEquals(0L, distances.total());
    }

    @Test
    @DisplayName("tmiao: distances with one place")
    public void testOneDistances() {
        Distances distances = new Distances();
        distances.add(12345L);
        assertEquals(1, distances.size());
        assertEquals(12345L, distances.total());
    }

    @Test
    @DisplayName("tmiao: distances with multiple places")
    public void testDistances() {
        Distances distances = new Distances();
        distances.add(5L);
        distances.add(40L);
        distances.add(300L);
        distances.add(2000L);
        distances.add(10000L);
        assertEquals(5, distances.size());
        assertEquals(12345L, distances.total());
    }
}