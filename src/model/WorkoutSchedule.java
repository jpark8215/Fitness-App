package model;

import javafx.scene.control.Alert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WorkoutSchedule {

    // List to store workout reminders
    public static List<WorkoutSchedule> workoutReminders = new ArrayList<>();

    private Workout workout; // Workout associated with the reminder
    private LocalDateTime reminderDateTime; // Date and time for the reminder

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

    // Method to remove a workout reminder at the specified index
    public static void removeWorkoutReminder(int index) {
        if (index >= 0 && index < workoutReminders.size()) {
            WorkoutSchedule reminder = workoutReminders.remove(index);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Reminder Removed");
            alert.setHeaderText(null);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            alert.setContentText(reminder.getWorkout().getWorkoutName() + " @ "
                    + reminder.getReminderDateTime().format(formatter) +
                    " removed! " );
            alert.showAndWait();
        } else {
            throw new IndexOutOfBoundsException("Invalid index specified.");
        }
    }

    // Method to add a workout reminder with the selected workout and date/time
    public static void addWorkoutReminder(Workout selectedWorkout, LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            // Show a warning alert if the reminder time has already passed
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Reminder Time Passed");
            alert.setHeaderText(null);
            alert.setContentText("The reminder time has already passed. Please select a future date and time.");
            alert.showAndWait();
            return;
        }

        WorkoutSchedule reminder = new WorkoutSchedule(selectedWorkout, dateTime);

        workoutReminders.add(reminder);

        // Show a success alert after adding the workout reminder
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Workout Added to Schedule");
        String dateTimeFormatted = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        alert.setContentText("Reminder set for workout \"" + selectedWorkout.getWorkoutName() + "\" at " + dateTimeFormatted);
        alert.showAndWait();
    }

    // Method to update a workout reminder with the selected workout and date/time
    public static void updateWorkoutReminder(Workout selectedWorkout, LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            // Show a warning alert if the reminder time has already passed
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Reminder Time Passed");
            alert.setHeaderText(null);
            alert.setContentText("The reminder time has already passed. Please select a future date and time.");
            alert.showAndWait();
            return;
        }

        // Find the existing reminder for the selected workout, if it exists
        WorkoutSchedule existingReminder = null;
        for (WorkoutSchedule reminder : workoutReminders) {
            if (reminder.getWorkout().equals(selectedWorkout)) {
                existingReminder = reminder;
                break;
            }
        }

        if (existingReminder != null) {
            // Update the existing reminder with the new date and time
            existingReminder.setReminderDateTime(dateTime);

            // Show a success alert after updating the workout reminder
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Reminder Updated");
            String dateTimeFormatted = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            alert.setContentText("Reminder updated for workout \"" + selectedWorkout.getWorkoutName() + "\" to " + dateTimeFormatted);
            alert.showAndWait();
        } else {
            // If no existing reminder found, show an error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Reminder Found");
            alert.setContentText("No reminder found for the selected workout.");
            alert.showAndWait();
        }
    }

    // Method to check if a workout reminder exists for the given workout name
    public static boolean hasWorkoutReminder(String workoutName) {
        for (WorkoutSchedule reminder : workoutReminders) {
            if (reminder.getWorkout().getWorkoutName().equals(workoutName)) {
                return true;
            }
        }
        return false;
    }
}
