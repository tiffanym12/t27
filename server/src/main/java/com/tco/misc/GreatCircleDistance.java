package com.tco.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.GeographicCoordinate;
import java.lang.Long;
import java.lang.Double;
import static java.lang.Double.parseDouble;
import static java.lang.Math.toRadians;



public final class GreatCircleDistance {

    final static boolean useRandom = false;

    private GreatCircleDistance() {}
    
    public static Long calculate(
        GeographicCoordinate from,
        GeographicCoordinate to,
        double earthRadius) {

        double lat1 = from.latRadians();
        double lon1 = from.lonRadians();
        double lat2 = to.latRadians();
        double lon2 = to.lonRadians();

        double numerator1 = Math.pow((Math.cos(lat2) * Math.sin(Math.abs(lon1-lon2))) , 2 );
        double numerator2 = Math.pow( ((Math.cos(lat1)*Math.sin(lat2)) 
                            - ( Math.sin(lat1)*Math.cos(lat2)*Math.cos(Math.abs(lon1-lon2))) ) , 2 );
        double numerator3 = Math.sqrt(numerator1 + numerator2);

        double denominator1 = Math.sin(lat1)*Math.sin(lat2);
        double denominator2 = Math.cos(lat1)*Math.cos(lat2)*Math.cos(Math.abs(lon1-lon2));
        double denominator3 = denominator1 + denominator2;
        double theta = Math.atan2(numerator3, denominator3);
        double vicentyNumber = earthRadius * theta;

        return Math.round(vicentyNumber);
    }

}
