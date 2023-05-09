package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WorkoutSchedule {

    // Declare a static List that will store the workout reminders
    private static List<WorkoutSchedule> workoutReminders;
    private Workout workout;
    private LocalDateTime reminderDateTime;

    public WorkoutSchedule(Workout workout, LocalDateTime reminderDateTime) {
        this.workout = workout;
        this.reminderDateTime = reminderDateTime;
    }

    public LocalDateTime getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(LocalDateTime reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    // Method to remove a workout reminder from the workoutReminders List by index
    public static void removeWorkoutReminder(int index) {
        // Check if the index is within bounds of the workoutReminders List
        if (index >= 0 && index < workoutReminders.size()) {
            WorkoutSchedule reminder = workoutReminders.remove(index);
            System.out.println("Reminder removed for workout \"" + reminder.getWorkout().getWorkoutName() + "\"");
        } else {
            System.out.println("Invalid index specified.");
        }
    }

    public static void removeReminder(WorkoutSchedule workoutSchedule) {
        workoutReminders.remove(workoutSchedule);
    }


    // Method to show all workout reminders
    public static void showWorkoutReminders() {
        try {
            System.out.println("Workout Reminders:");
            if (workoutReminders != null) {
                for (int i = 0; i < workoutReminders.size(); i++) {
                    WorkoutSchedule reminder = workoutReminders.get(i);
                    System.out.print((i) + ". " + reminder.getWorkout().getWorkoutName());
                    if (reminder.getReminderDateTime() != null) {
                        System.out.print(" (reminder set for " + reminder.getReminderDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")");
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

        // Loop through all workouts in the WorkoutHub object and add them to the workoutObservableList
        for (int i = 0; i < workoutReminders.size(); i++) {
            WorkoutSchedule reminder = workoutReminders.get(i);
            String workoutNameWithIndex = String.format("%d. %s", i + 1, reminder.getWorkout().getWorkoutName());
            LocalDateTime reminderDateTime = reminder.getReminderDateTime();
            scheduleObservableList.add(new WorkoutSchedule(new Workout(workoutNameWithIndex), reminderDateTime));
        }
        return scheduleObservableList;
    }



    public static void addWorkoutReminder(Workout selectedWorkout, LocalDateTime dateTime) {
        // Check if the reminder time has already passed for today
        if (dateTime.isBefore(LocalDateTime.now())) {
            // If it has, add one day to the selected date to schedule the reminder for tomorrow
            dateTime = dateTime.plusDays(1);
        }

        // Create a new WorkoutSchedule object with the given workout and reminder time
        WorkoutSchedule reminder = new WorkoutSchedule(selectedWorkout, dateTime);

        // Initialize the workoutReminders List if it hasn't been created yet
        if (workoutReminders == null) {
            workoutReminders = new ArrayList<>();
        }

        // Add the WorkoutSchedule to the workoutReminders List
        workoutReminders.add(reminder);

        // Print a confirmation message to the console
        System.out.println("Reminder set for workout \"" + selectedWorkout.getWorkoutName() + "\" at " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }

}
