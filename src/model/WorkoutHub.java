package model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHub {

    private static List<Workout> workouts;

    public WorkoutHub(List<Workout> workouts) {
        WorkoutHub.workouts = new ArrayList<>();
    }

    public static void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public void removeWorkout(Workout workout) {
        workouts.remove(workout);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void clearWorkouts() {
        workouts.clear();
    }

    public int getNumberOfWorkouts() {
        return workouts.size();
    }

    public Workout getWorkout(int index) {
        return workouts.get(index);
    }

}