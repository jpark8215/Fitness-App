package model;

// movementTracker Class
public class movementTracker {

    // instance variables
    private int totalDistance;
    private int totalTime;
    private int totalCalories;

    // constructor
    public movementTracker() {
        totalDistance = 0;
        totalTime = 0;
        totalCalories = 0;
    }

    // methods
    public void startMove() {
        // start tracking distance, time, and calories
    }

    public void stopMove() {
        // stop tracking distance, time, and calories
    }

    public void resetMove() {
        // reset total distance, time, and calories
        totalDistance = 0;
        totalTime = 0;
        totalCalories = 0;
    }

    public int getTotalDistance() {
        // returns total distance traveled
        return totalDistance;
    }

    public int getTotalTime() {
        // returns total time traveled
        return totalTime;
    }

    public int getTotalCalories() {
        // returns total calories burned
        return totalCalories;
    }
}

