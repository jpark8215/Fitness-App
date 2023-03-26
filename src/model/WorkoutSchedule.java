package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class WorkoutSchedule {
    private static Map<Workout, LocalDateTime> workoutReminders;

    public WorkoutSchedule() {
        workoutReminders = new HashMap<>();
    }

    //workoutReminders instance variable that stores the workouts and their corresponding reminder times as key-value pairs in a Map
//    public static void addWorkout(Workout workoutName) {
//        if (workoutReminders == null) {
//            workoutReminders = new HashMap<>();
//        }
//        workoutReminders.put(workoutName, null);
//    }

    public static void addWorkoutReminder(Workout workout, int reminderTimeMinutes) {
        LocalDateTime reminderTime = LocalDateTime.now().plus(Duration.ofMinutes(reminderTimeMinutes));
        workoutReminders.put(workout, reminderTime);
        System.out.println("Reminder set for workout \"" + workout.getName() + "\" at " + reminderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public static void setReminder(Workout workout, int reminderTimeMinutes) {
        if (workoutReminders.containsKey(workout)) {
            LocalDateTime reminderTime = LocalDateTime.now().plus(Duration.ofMinutes(reminderTimeMinutes));
            workoutReminders.put(workout, reminderTime);
            System.out.println("Reminder set for workout \"" + workout.getName() + "\" at " + reminderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } else {
            System.out.println("Workout not found");
        }
    }

    public void removeWorkout(Workout workout) {
        workoutReminders.remove(workout);
    }

    public void showWorkoutReminders() {
        System.out.println("Workout Reminders:");
        for (Map.Entry<Workout, LocalDateTime> entry : workoutReminders.entrySet()) {
            Workout workout = entry.getKey();
            LocalDateTime reminderTime = entry.getValue();
            System.out.print("- " + workout.getName());
            if (reminderTime != null) {
                System.out.print(" (reminder set for " + reminderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ")");
            }
            System.out.println();
        }
    }
}
