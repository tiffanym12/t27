package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Exception;

public class TestDatabase {

    @Test
    @DisplayName("lwyip: test database connection")
    public void testDatabaseConnection(){
        try{
            String match = "ryp";
            Integer limit = 10;
            Integer found = Database.found(match);
            Places places = Database.places(match, limit);
            assertTrue(found == 4);
            assertEquals(4, places.size());
        }
        catch (Exception e){
            System.err.println("Exception yo " + e.getMessage());
        }
    }

    @Test
    @DisplayName("lwyip: test renameRegionOrCountry")
    public void testRenameRegionOrCountry(){
        String changeRegion = "region.name";
        String changeCountry = "country.name";
        String unChanged = "world.name";

        assertTrue(Database.renameRegionOrCountry(changeRegion).equals("region.region"));
        assertTrue(Database.renameRegionOrCountry(changeCountry).equals("country.country"));
        assertTrue(Database.renameRegionOrCountry(unChanged).equals("world.name"));
    }
    
}
