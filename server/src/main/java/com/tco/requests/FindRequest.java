package com.tco.requests;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(FindRequest.class);

    private String match;
    private Integer limit;
    private Integer found;
    private Places places;

    @Override
    public void buildResponse() {
        found = buildFoundList();
        places = buildFindList();
        log.trace("findResponse -> {}", this);
    }

    public Places buildFindList() {
        try {
            places = Database.places(match, limit);
        }
        catch(Exception e) {
            places = new Places();
        }
    return places;
    }

    public Integer buildFoundList() {
        try {
            found = Database.found(match);
        }
        catch (Exception e) {
            found = 0;
        }
        return found;
    }
    
/* The following methods exist only for testing purposes and are not used during normal execution, including the constructor. */
    public FindRequest(String match, Integer limit) {
        super();
        this.requestType = "find";
        this.match = match;
        this.limit = limit;
    }

    public Integer limit() {return this.limit; }

    public Places places() {return this.places; }

    public Integer found() { return this.found; }
}