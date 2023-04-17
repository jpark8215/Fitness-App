package model;


public class Workout {
    // Instance variables
    private int indices;
    private String workoutName;
    private int calories;

    // Constructor
    public Workout(String workoutName) {
        this.workoutName = workoutName;
        this.calories = 0; // default value for calories
    }

    // Getter and setter methods
    public int getIndices() {
        return indices;
    }

    public void setIndices(int indices) {
        this.indices = indices;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return workoutName + ", " + calories + " calories";
    }

}
