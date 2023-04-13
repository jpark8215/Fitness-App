package model;


public class Workout {
    // Instance variables
    private int indices;
    private String names;
    private int calories;

    // Constructor
    public Workout(String workoutName) {
        names = workoutName;
    }

    // Getter and setter methods
    public int getIndices() {
        return indices;
    }

    public void setIndices(int indices) {
        this.indices = indices;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
            return names;
        }


}
