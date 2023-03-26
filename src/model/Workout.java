package model;

public class Workout {
    private String name;
    private int calories;

    public Workout(String workoutName) {
        name = workoutName;
//        this.calories = calories;
    }

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

}