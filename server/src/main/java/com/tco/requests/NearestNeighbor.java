package com.tco.requests;

public class NearestNeighbor {
    Tour bestTour;
    DistanceData tourDistances;
    UnvisitedCities unvisitedCities;

    NearestNeighbor(Places places, double earthRadius){
        bestTour =  new Tour(places.size());
        tourDistances = new DistanceData(places, earthRadius);
        unvisitedCities = new UnvisitedCities(places.size());
    }

    public Tour startNearestNeighbor() {
        for(int i = 0; i < bestTour.getSize(); i++){
            Tour temporaryTour = new Tour(bestTour.getSize());
            temporaryTour.updateTour(i, 0);
            unvisitedCities.visitCity(i);
            int counter = 1;
            int currentLocation = i;
            while((!unvisitedCities.checkForAllVisited())){
                currentLocation = calculateTripLeg(counter, currentLocation, temporaryTour); //
                counter++;
            }
            // System.out.println("Iteration: " + i); //FOR TESTING
            // temporaryTour.printTour(); //FOR TESTING
            bestTour = compareTours(temporaryTour);
            unvisitedCities.resetAllVisitStatus();
        }
        return bestTour;
    }

    private int calculateTripLeg(int counter, int currentLocation, Tour temporaryTour){
        currentLocation = tourDistances.getNextLegTripLocation(currentLocation, unvisitedCities);
        temporaryTour.updateTour(currentLocation, counter); //set index counter to the index Location
        unvisitedCities.visitCity(currentLocation); //set that location to True(visited)
        return currentLocation;
    }

    private Tour compareTours(Tour temporaryTour){
        long currentBestTourTotalDistance = tourDistances.getTotalTripDistance(bestTour);
        long temporaryTourTotalDistance = tourDistances.getTotalTripDistance(temporaryTour);
        // System.out.println(temporaryTourTotalDistance); //FOR TESTING
        if (temporaryTourTotalDistance < currentBestTourTotalDistance){
            return temporaryTour;
        }
        else{
            return bestTour;
        }
    }


}