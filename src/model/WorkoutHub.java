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

//    public static List<Workout> getWorkouts() {
//        return workouts;
//    }

    public static List<String> getWorkoutsWithIndices() {
        List<String> workoutsWithIndices = new ArrayList<>();
        for (int i = 0; i < workouts.size(); i++) {
            workoutsWithIndices.add("[" + i + "] " + workouts.get(i).toString());
        }
        return workoutsWithIndices;
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