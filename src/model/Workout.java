package model;


import java.util.ArrayList;
import java.util.List;

//Represents a workout object, which contains information about the name of the workout and the number of calories burned.
public class Workout {
    // Instance variables
    private int indices; // Index of the workout in the WorkoutHub list (not used in constructor)
    private String workoutName; // Name of the workout
    private int calories; // Number of calories burned during the workout


    //Constructor for a workout object, which sets the name of the workout and default value for calories burned to 0.
    //@param workoutName the name of the workout
    public Workout(String workoutName) {
        this.workoutName = workoutName;
        this.calories = 1; // default value for calories
    }

    public static List<Workout> getWorkouts() {
        // Create a list to store the workouts
        List<Workout> workouts = new ArrayList<>();

        // Add sample workouts to the list
        workouts.add(new Workout("Run"));
        workouts.add(new Workout("Walk"));
        workouts.add(new Workout("Swim"));

        return workouts;
    }

    //Getter method for the workout's index in the WorkoutHub list.
    //@return the index of the workout
    public int getIndices() {
        return indices;
    }


     //Setter method for the workout's index in the WorkoutHub list.
     //@param indices the index of the workout
    public void setIndices(int indices) {
        this.indices = indices;
    }


     //Getter method for the name of the workout.
     //@return the name of the workout
    public String getWorkoutName() {
        return workoutName;
    }

     //Setter method for the name of the workout.
     //@param workoutName the name of the workout
    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

     //Getter method for the number of calories burned during the workout.
     //@return the number of calories burned
    public int getCalories() {
        return calories;
    }


    //Setter method for the number of calories burned during the workout.
    //@param calories the number of calories burned
    public void setCalories(int calories) {
        this.calories = calories;
    }


    //Returns a string representation of the workout object, including the name of the workout and the number of calories burned.
    //@return a string representation of the workout object
    @Override
    public String toString() {
        return workoutName + "  (" + calories + " calories)";
    }
}