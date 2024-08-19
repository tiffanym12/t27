package com.tco.requests;

import com.tco.misc.GreatCircleDistance;


public class DistanceData {
    private Places places;
    private long[][] distanceMatrix;
    private double earthRadius;

    public DistanceData(Places places, double earthRadius){
        this.places = places;
        this.earthRadius = earthRadius;
        distanceMatrix = new long[places.size()][places.size()];
        fillDistances();
    }

    public void fillDistances(){
        fillZeros();
        fillNonZeros();
    }
    private void fillZeros(){
        for (int i = 0; i < places.size(); i++){
            distanceMatrix[i][i] = 0;
        }
    }

    private void fillNonZeros(){
        for (int i = 0; i < places.size(); i++){
            for (int j = i + 1; j < places.size(); j++){
                long calculatedDistance = GreatCircleDistance.calculate(places.get(i), places.get(j), earthRadius);
                distanceMatrix[i][j] = calculatedDistance;
                distanceMatrix[j][i] = calculatedDistance;
            }
        }
    }

    public int getRowLength(){
        return distanceMatrix.length;
    }

    public int getColLength(int column){
        return distanceMatrix[column].length;
    }

    public long getDistanceValue(int row, int col){
        return distanceMatrix[row][col];
    }

    public long getTotalTripDistance(Tour currentTour){
        int[] tempTour = currentTour.getTour();
        long totalDistance = 0;
        for (int i = 0; i < places.size(); i++){
            int row = tempTour[i];
            int col = tempTour[(i+1) % places.size()];
            totalDistance += getDistanceValue(row, col);
        }
        return totalDistance;
    }

    public int getNextLegTripLocation(int currentLocation, UnvisitedCities unvisited){
        // printMatrix();
        // System.out.println();
        int nextLegLocation = 0; 
        long shortestDistance = setFirstDistance(currentLocation, unvisited);

        for (int location = 0; location < places.size(); location++){ //iterate through size of tour/places
            if(location == currentLocation){
                continue; //don't want the zero distance
            }
            long tempDistance = getDistanceValue(currentLocation, location);
            // System.out.println("Currently at: " + currentLocation + ", Column: " + location);
            // System.out.println("Distance: " + tempDistance);
            if((tempDistance < shortestDistance || tempDistance == shortestDistance) && !unvisited.getVisitedStatus(location)){
                shortestDistance = tempDistance;
                nextLegLocation = location;
            }
        }
        return nextLegLocation; //return this location
     }

     private long setFirstDistance(int currentLocation, UnvisitedCities unvisited){
        int firstLocation = 0;   
        while(unvisited.getVisitedStatus(firstLocation)){ //while the location is visited, iterate to next location
            ++firstLocation;
        }
        return getDistanceValue(currentLocation, firstLocation);
     }

     

    //The following methods are used for testing

    public void printMatrix(){
        for (int i = 0; i < places.size(); i++){
            for(int j = 0; j < places.size(); j++){
                System.out.print(distanceMatrix[i][j] + " | ");
            }
            System.out.println();
        }
    }

}
