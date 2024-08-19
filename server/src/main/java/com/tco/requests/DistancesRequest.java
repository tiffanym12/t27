package com.tco.requests;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.GreatCircleDistance;

public class DistancesRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

    private Double earthRadius;
    private Places places;
    private Distances distances;

    @Override
    public void buildResponse() {
        distances = buildDistanceList();
        log.trace("distanceResponse -> {}", this);
    }

    private Distances buildDistanceList(){
        Distances distances = new Distances();
        
        if(places.size() == 1){
            distances.add(0L);
        }
        for(int i = 1; i < places.size(); i++){
            distances.add(GreatCircleDistance.calculate(places.get(i - 1), places.get(i), earthRadius));
        }
        if(places.size() > 1){
            distances.add(GreatCircleDistance.calculate(places.get(places.size() - 1), places.get(0), earthRadius));
        }

        return distances;
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public DistancesRequest(double earthRadius, Places places) {
        super();
        this.requestType = "distances";
        this. earthRadius = earthRadius;
        this.places = places;
    }

    public double earthRadius() {return this.earthRadius; }

    public Places places() {return this.places; }

    public Distances distances() { return this.distances; }
}