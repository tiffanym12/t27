package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTourRequest {
    TourRequest request;
    Places places;
    Double response;

    @BeforeEach
    public void createConfigurationForTestCases() {
        places = new Places();
        response = 1.0;
        request = request = new TourRequest(places, response);
    }

    @Test
    @DisplayName("ewolving: test 0 response time")
    public void testZeroResponseTime() {
        response = 0.0;
        request = new TourRequest(places, response);
        request.buildResponse();
        assertEquals(request.places(), places);
        assertEquals(request.response(), 0.0);
    }
}
