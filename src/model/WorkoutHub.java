package model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHub {

    // Define a static List of Workout objects
    private static List<Workout> workouts;

    // Constructor that initializes the list of workouts
    public WorkoutHub(List<Workout> workouts) {
        // Initialize the workouts list with a new ArrayList object
        // Note that this creates a new empty list and discards the provided workouts parameter
        WorkoutHub.workouts = new ArrayList<>();
    }

    // Method to add a Workout object to the list of workouts
    public static void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    // Method to remove a Workout object from the list of workouts
    public void removeWorkout(Workout workout) {
        workouts.remove(workout);
    }

    // Method to get a list of all workouts, with each workout's index displayed
    public static List<String> getWorkoutsWithIndices() {
        List<String> workoutsWithIndices = new ArrayList<>();
        // Iterate through each workout in the workouts list and add its index and name to the result list
        for (int i = 0; i < workouts.size(); i++) {
            workoutsWithIndices.add("[" + i + "] " + workouts.get(i).toString());
        }
        return workoutsWithIndices;
    }

    // Method to clear all workouts from the list
    public void clearWorkouts() {
        workouts.clear();
    }

    // Method to get the number of workouts in the list
    public int getNumberOfWorkouts() {
        return workouts.size();
    }

    // Method to get a Workout object at a specific index in the list
    public Workout getWorkout(int index) {
        return workouts.get(index);
    }

}
