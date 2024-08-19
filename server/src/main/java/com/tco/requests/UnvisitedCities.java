package com.tco.requests;

public class UnvisitedCities {
    boolean[] citiesArray; 

    public void visitCity(int cityIndex){
        citiesArray[cityIndex] = true;
    }

    public void resetAllVisitStatus(){
        for(int i = 0; i<citiesArray.length; i++){
            citiesArray[i] = false;
        }
    }

    UnvisitedCities(){
        //Default constructor to avoid errors
        citiesArray = new boolean[1];
    }

    UnvisitedCities(int numberOfPlaces){
        citiesArray = new boolean[numberOfPlaces];
    }

    public boolean getVisitedStatus(int cityIndex){
        return citiesArray[cityIndex];
    }

    public boolean checkForAllVisited(){
        for (int i = 0; i < citiesArray.length; i++){
            if (!citiesArray[i]){
                return false;
            }
        }
        return true;
    }
}