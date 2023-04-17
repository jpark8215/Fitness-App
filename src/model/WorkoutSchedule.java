package model;

import main.Main;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WorkoutSchedule {
    // Declare a static List that will store the workout reminders
    private static List<WorkoutSchedule> workoutReminders;

    // Declare instance variables for workout and reminder time
    private final Workout workout;
    private final LocalDateTime reminderTime;

    // Constructor that initializes the instance variables for workout and reminder time
    public WorkoutSchedule(Workout workout, LocalDateTime reminderTime) {
        this.workout = workout;
        this.reminderTime = reminderTime;
    }

    // Method to add a workout reminder to the workoutReminders List
    public static void addWorkoutReminder(Workout workout, int reminderTimeMinutes) {
        // Calculate the reminder time based on the current time and the given reminder time in minutes
        LocalDateTime reminderTime = LocalDateTime.now().plus(Duration.ofMinutes(reminderTimeMinutes));

        // Create a new WorkoutReminder object with the given workout and reminder time
        WorkoutSchedule reminder = new WorkoutSchedule(workout, reminderTime);

        // Initialize the workoutReminders List if it hasn't been created yet
        if (workoutReminders == null) {
            workoutReminders = new ArrayList<>();
        }

        // Add the WorkoutReminder to the workoutReminders List
        workoutReminders.add(reminder);

        // Print a confirmation message to the console
        System.out.println("Reminder set for workout \"" + workout.getWorkoutName() + "\" at " + reminderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    // Method to remove a workout reminder from the workoutReminders List by index
    public static void removeWorkoutReminder(int index) {
        // Check if the index is within bounds of the workoutReminders List
        if (index >= 0 && index < workoutReminders.size()) {
            WorkoutSchedule reminder = workoutReminders.remove(index);
            System.out.println("Reminder removed for workout \"" + reminder.workout.getWorkoutName() + "\"");
        } else {
            System.out.println("Invalid index specified.");
        }
    }

    // Getter methods for the instance variables
    public Workout getWorkout() {
        return workout;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }


    public static void showWorkoutReminders() {
        try {
            System.out.println("Workout Reminders:");
            if (workoutReminders != null) {
                for (int i = 0; i < workoutReminders.size(); i++) {
                    WorkoutSchedule reminder = workoutReminders.get(i);
                    System.out.print((i) + ". " + reminder.getWorkout().getWorkoutName());
                    if (reminder.getReminderTime() != null) {
                        System.out.print(" (reminder set for " + reminder.getReminderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ")");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No workout found.");
            }
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
