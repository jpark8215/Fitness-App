package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHub {

    // Define a static List of Workout objects
    private static final List<Workout> workouts = new ArrayList<>();

    // Method to get the number of workouts in the list
    public int getNumberOfWorkouts() {
        return workouts.size();
    }

    // Method to get a Workout object at a specific index in the list
    public Workout getWorkoutByIndex(int index) {
        return workouts.get(index);
    }

    // Method to add a Workout object to the list of workouts
    public static void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    // Method to remove a Workout object from the list of workouts
    public static void removeWorkout(Workout workout) {
        workouts.remove(workout);
    }


    // Method to get a list of all workouts, with each workout's index displayed
    public static List<String> getWorkoutList() {
        // Create a new List to store the information of all workouts
        List<String> workoutList = new ArrayList<>();

        // Loop through all workouts in the workouts List
        for (int i = 0; i < workouts.size(); i++) {
            Workout workout = workouts.get(i);
            String workoutInfo = "\n" + i + ". " + workout.getWorkoutName() + " : " + workout.getCalories() + " calories";
            workoutList.add(workoutInfo);
        }
        return workoutList ;
    }

    // Method to get an observable list of all workouts
    public static ObservableList<Workout> getWorkoutObservableList() {
        // Create a new ObservableList to store all workouts
        ObservableList<Workout> workoutObservableList = FXCollections.observableArrayList();
        workoutObservableList.addAll(workouts);

        return workoutObservableList;
    }

}
