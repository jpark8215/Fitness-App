package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class WorkoutSchedule {

    // Declare a static List that will store the workout reminders
    private static List<WorkoutSchedule> workoutReminders;

    // Declare instance variables for workout and reminder time
    private final Workout workout;
    private final LocalDateTime reminderDateTime;

    // Constructor that initializes the instance variables for workout and reminder time
    public WorkoutSchedule(Workout workout, LocalDateTime reminderDateTime) {
        this.workout = workout;
        this.reminderDateTime = reminderDateTime;
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

    // Method to show all workout reminders
    public static void showWorkoutReminders() {
        try {
            System.out.println("Workout Reminders:");
            if (workoutReminders != null) {
                for (int i = 0; i < workoutReminders.size(); i++) {
                    WorkoutSchedule reminder = workoutReminders.get(i);
                    System.out.print((i) + ". " + reminder.workout.getWorkoutName());
                    if (reminder.reminderDateTime!= null) {
                        System.out.print(" (reminder set for " + reminder.reminderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ")");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No workout reminders found.");
            }
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to get an observable list of all schedules
    public static ObservableList<WorkoutSchedule> getScheduleObservableList() {
        // Create a new ObservableList to store all workouts
        ObservableList<WorkoutSchedule> scheduleObservableList = FXCollections.observableArrayList();

        // Loop through the workoutReminders list and set an index for each WorkoutSchedule object
        if (workoutReminders != null) {
            for (int i = 0; i < workoutReminders.size(); i++) {
                WorkoutSchedule reminder = workoutReminders.get(i);
                System.out.print((i) + ". " + reminder.workout.getWorkoutName());
                if (reminder.reminderDateTime!= null) {
                    System.out.print(" (reminder set for " + reminder.reminderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ")");
                }
                System.out.println();
            }
        } else {
            System.out.println("No workout reminders found.");
        }

        return scheduleObservableList;
    }



    public static void addWorkoutReminder(Workout selectedWorkout, LocalDateTime dateTime) {
        // Check if the reminder time has already passed for today
        if (dateTime.isBefore(LocalDateTime.now())) {
            // If it has, add one day to the selected date to schedule the reminder for tomorrow
            dateTime = dateTime.plusDays(1);
        }

        // Create a new WorkoutReminder object with the given workout and reminder time
        WorkoutSchedule reminder = new WorkoutSchedule(selectedWorkout, dateTime);

        // Initialize the workoutReminders List if it hasn't been created yet
        if (workoutReminders == null) {
            workoutReminders = new ArrayList<>();
        }

        // Add the WorkoutReminder to the workoutReminders List
        workoutReminders.add(reminder);

        // Print a confirmation message to the console
        System.out.println("Reminder set for workout \"" + selectedWorkout.getWorkoutName() + "\" at " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }

    // Non FXML codes
    public static void addWorkoutReminder(Workout workout, LocalDate reminderDate, LocalTime reminderTime) {
        // Combine the selected date and time to create a LocalDateTime object
        LocalDateTime reminderDateTime = LocalDateTime.of(reminderDate, reminderTime);

        // Check if the reminder time has already passed for today
        if (reminderDateTime.isBefore(LocalDateTime.now())) {
            // If it has, add one day to the selected date to schedule the reminder for tomorrow
            reminderDateTime = reminderDateTime.plusDays(1);
        }

        // Create a new WorkoutReminder object with the given workout and reminder time
        WorkoutSchedule reminder = new WorkoutSchedule(workout, reminderDateTime);

        // Initialize the workoutReminders List if it hasn't been created yet
        if (workoutReminders == null) {
            workoutReminders = new ArrayList<>();
        }

        // Add the WorkoutReminder to the workoutReminders List
        workoutReminders.add(reminder);

        // Print a confirmation message to the console
        System.out.println("Reminder set for workout \"" + workout.getWorkoutName() + "\" at " + reminderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
