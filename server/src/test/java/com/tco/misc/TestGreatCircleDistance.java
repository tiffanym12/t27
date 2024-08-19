package com.tco.misc;

import static com.tco.misc.GreatCircleDistance.calculate;
import static com.tco.misc.GreatCircleDistance.useRandom;
import com.tco.misc.GeographicCoordinate;
import static java.lang.Math.toRadians;
import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



public class TestGreatCircleDistance {

    //to test minimum earth radius value
    final static long small = 1L;
    final static long piSmall = Math.round(Math.PI * small);
    final static long piSmallHalf = Math.round(Math.PI / 2.0 * small);

    //to test large values with significant digits to verify double/long
    final static long big = 1000000000000L;
    final static long piBig = Math.round(Math.PI * big);
    final static long piBigHalf = Math.round(Math.PI / 2.0 * big);

    final static double actualRadiusKm = 6371.0;
    
    static class Geo implements GeographicCoordinate{
        Double degreesLatitude;
        Double degreesLongtitude;

        public Geo(Double lat, Double lon){
            this.degreesLatitude = lat;
            this.degreesLongtitude = lon;
        }

        public Double latRadians() {
            return toRadians(degreesLatitude);
        }
        public Double lonRadians() {
            return toRadians(degreesLongtitude);
        }
    }

    final Geo originGeo = new Geo(0., 0.);
    Geo northPole = new Geo(90.0, 0.);
    Geo southPole = new Geo(-90.0, 0.);

    @Test
    @DisplayName("lwyip: distance to self should be zero")
    public void testGeoDistanceToSelf() throws Exception{
        assertEquals(0L, GreatCircleDistance.calculate(originGeo, originGeo, big));
        assertEquals(0L, GreatCircleDistance.calculate(originGeo, originGeo, small));
    }

    @Test
    @DisplayName("lwyip: northPole to southPole distance")
    public void testNPoleSPole() throws Exception{
        assertEquals(20015L, GreatCircleDistance.calculate(northPole, southPole, actualRadiusKm));
    }

    @Test
    @DisplayName("lwyip: excel spreadsheet coordinate check")
    public void testExcelSS() throws Exception{
        Geo to = new Geo(-33.8696, 151.206965);
        Geo from = new Geo(-37.81753, 144.96715);
        assertEquals(713L, GreatCircleDistance.calculate(to, from, actualRadiusKm));
    }

    @Test
    @DisplayName("lwyip: big radius with small distances")
    public void testBigRadiusSmallDistance() throws Exception{
        Geo to = new Geo(90.0, 179.9);
        Geo from = new Geo(-90.0, -180.0);
        assertEquals(3141592653590L, GreatCircleDistance.calculate(to, from, big));
    }

    @Test
    @DisplayName("lwyip: small radius with extremes")
    public void testSmallRadiusWithExtremes() throws Exception{
        assertEquals(3L, GreatCircleDistance.calculate(northPole, southPole, small));
    }
    
    @Test
    @DisplayName("lwyip: actual radius with small Distances")
    public void testActualRadiusWithSmallDistances() throws Exception{
        Geo to = new Geo(40.57, -105.11);
        Geo from = new Geo(40.58, -105.11);
        assertEquals(1L, GreatCircleDistance.calculate(to, from, actualRadiusKm));
    }
    
}
