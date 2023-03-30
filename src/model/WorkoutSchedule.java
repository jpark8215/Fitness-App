package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class WorkoutSchedule {
    // Declare a static Map that will store the workout reminders and their corresponding reminder times
    private static Map<Workout, LocalDateTime> workoutReminders;

    // Constructor that initializes the workoutReminders Map
    public WorkoutSchedule() {
        workoutReminders = new HashMap<>();
    }

    // Method to add a workout reminder to the workoutReminders Map
    public static void addWorkoutReminder(Workout workout, int reminderTimeMinutes) {
        // Create a new HashMap to store the workout reminder
        workoutReminders = new HashMap<>();

        // Calculate the reminder time based on the current time and the given reminder time in minutes
        LocalDateTime reminderTime = LocalDateTime.now().plus(Duration.ofMinutes(reminderTimeMinutes));

        // Add the workout and its reminder time to the workoutReminders Map
        workoutReminders.put(workout, reminderTime);

        // Print a confirmation message to the console
        System.out.println("Reminder set for workout \"" + workout.getName() + "\" at " + reminderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    // Method to set a workout reminder for a previously added workout
    public static void setReminder(Workout workout, int reminderTimeMinutes) {
        // Create a new HashMap to store the updated workout reminder
        workoutReminders = new HashMap<>();

        // Calculate the updated reminder time based on the current time and the given reminder time in minutes
        LocalDateTime reminderTime = LocalDateTime.now().plus(Duration.ofMinutes(reminderTimeMinutes));

        // Update the workout reminder in the workoutReminders Map
        workoutReminders.put(workout, reminderTime);

        // Print a confirmation message to the console
        System.out.println("Reminder set for workout \"" + workout.getName() + "\" at " + reminderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    // Method to remove a workout and its corresponding reminder from the workoutReminders Map
    public void removeWorkout(Workout workout) {
        workoutReminders.remove(workout);
    }

    // Method to display all the workout reminders and their corresponding reminder times in the workoutReminders Map
    public static void showWorkoutReminders() {
        try {
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
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
