package model;

// Class to store fitness activity data
public class ActivityTracker {

    // Instance variables
    private String workout; // Activity name
    private int duration; // Duration of activity in minutes
    private int intensity; // Intensity of activity on a scale of 1-10
    private int caloriesBurned; // Calories burned during activity

    // Constructor
    public ActivityTracker(String n, int d, int i, int c) {
        workout = n;
        duration = d;
        intensity = i;
        caloriesBurned = c;
    }

    // Getters
    public String getWorkout() {
        return workout;
    }

    public int getDuration() {
        return duration;
    }

    public int getIntensity() {
        return intensity;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    // Setters
    public void setWorkout(String workout) {
        this.workout = workout;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

}
