package com.tco.requests;
import java.util.Arrays;


public class Tour {
    int[] tourIndexes;

    public Tour(int placesSize){
        tourIndexes = new int[placesSize];
        for (int i = 0; i<placesSize; i++){
            tourIndexes[i]= i;
        }
    }

    public int[] getTour(){
        return tourIndexes;
    }

    public void updateTour(int originalValue, int newIndex){
        tourIndexes[newIndex] = originalValue;
        //TODO: Be careful with duplicaiton of entries
    }

    public int getSize(){
        return tourIndexes.length;
    }

    //For Testing
    public void printTour(){
        System.out.println(Arrays.toString(tourIndexes));
    }
}
