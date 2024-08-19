package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFindRequest {
    FindRequest request;
    Places places;
    Integer found;

    Integer limit = 20;
    String match = "johnny";

    @BeforeEach
    public void createConfigurationForTestCases() {
        places = new Places();
        request = new FindRequest(match, limit);
    }

    @Test
    @DisplayName("joseph32: search 'johnny'")
    public void testJohnny(){
        request.buildResponse();
        places = request.places();
        assertEquals(1, places.size());
        assertEquals(1, request.found());
        assertEquals(20, request.limit());

    }
  
}
