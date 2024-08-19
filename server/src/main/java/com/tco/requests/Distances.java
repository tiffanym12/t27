package com.tco.requests;

import java.util.ArrayList;

public class Distances extends ArrayList<Long> {
    public long total() {
        long sum = 0;
        for(long distances : this) {
            sum += distances;
        }
        return sum;
    }
}