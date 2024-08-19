package com.tco.requests;

public class CreateTour {
    Places originalPlaces;
    Places optimizedPlaces;
    double earthRadius;
    NearestNeighbor nearestNeighbor;

    public CreateTour(Places originalPlaces, double earthRadius){
        this.originalPlaces = originalPlaces;
        this.earthRadius = earthRadius;
        this.optimizedPlaces = originalPlaces;
        nearestNeighbor = new NearestNeighbor(originalPlaces, earthRadius);
    }
    
    public Places performOptimization(){
        Tour shortestTour = nearestNeighbor.startNearestNeighbor();
        shortestTour = rotateToStartingLocation(shortestTour);
        Places reorganizedPlaces = reorderPlaces(shortestTour);
        return reorganizedPlaces;
    }
    
    public Tour rotateToStartingLocation(Tour unrotated){
        if(unrotated.tourIndexes[0] == 0) return unrotated;
        
        int startIndex = 1;
        for(int i = 1; i<unrotated.getSize(); i++){
            if(unrotated.tourIndexes[i] == 0){
                startIndex = i;
            } 
        }

        Tour rotatedTour = new Tour(unrotated.getSize());
        int currentRotatedIndex = 0;
        //unrotated locations AFTER the starting index
        while(currentRotatedIndex < unrotated.getSize() - startIndex){
            rotatedTour.updateTour(unrotated.tourIndexes[startIndex + currentRotatedIndex], currentRotatedIndex);
            currentRotatedIndex++;
        }
        //unrotated locations BEFORE the starting index
        for(int i = 0; i < startIndex; i++){
            rotatedTour.updateTour(unrotated.tourIndexes[i], currentRotatedIndex);
            currentRotatedIndex++;
        }
        return rotatedTour;
    }

    public Places reorderPlaces(Tour newOrder){
        Places newPlacesList = new Places();
        for(int i = 0; i < newOrder.getSize(); i++){
            newPlacesList.add(originalPlaces.get(newOrder.tourIndexes[i]));
        }
        return newPlacesList;
    }

    //Methods for testing purposes

    public Places getOptimizedPlaces(){
        return optimizedPlaces;
    }
}
