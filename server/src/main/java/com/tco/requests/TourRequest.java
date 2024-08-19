package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TourRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

    private Places places;
    private Double response;
    private Double earthRadius;

    @Override
    public void buildResponse() {
        places = buildTourList();
        log.trace("findResponse -> {}", this);
    }

    public Places buildTourList(){
        //TODO: call the tour class(es) here, and perform nearestNeighbor calculations/comparisons until the response time is up. These do not exist at the time of writing this.
        return places;
    }

    /* The following methods are for testing purposes, and are not used otherwise */

    public TourRequest(Places places, Double response){
        super();
        this.requestType = "tour";
        this.places = places;
        this.response = response;
        this.earthRadius = 3959.0;
    }

    public Places places(){ return this.places; }

    public Double response(){ return this.response; }

}
