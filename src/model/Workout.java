package model;

public class Workout {
    // Instance variables
    private String name;
    private int calories;

    // Constructor
    public Workout(String workoutName) {
        name = workoutName;
//        this.calories = calories;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
            return name;
        }
}
