package model;

// Class to store fitness activity data
public class ActivityTracker {

    // Instance variables
    private Workout workout;
    private int duration;
    private int caloriesBurned;

    public ActivityTracker(Workout workout, int duration, int caloriesBurned) {
        this.workout = workout;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    // Getters
    public Workout getWorkout() {
        return workout;
    }

    public int getDuration() {
        return duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    // Setters
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        this.caloriesBurned = calculateCaloriesBurned();
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    // Calculate the calories burned based on the workout's calorie value and duration
    private int calculateCaloriesBurned() {
        if (workout != null) {
            int caloriePerMinute = workout.getCalories();
            return caloriePerMinute * duration;
        }
        return 0;
    }
}
