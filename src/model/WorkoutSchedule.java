package model;

import javafx.scene.control.Alert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WorkoutSchedule {

    public static List<WorkoutSchedule> workoutReminders = new ArrayList<>();
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

    public static void removeWorkoutReminder(int index) {
        if (index >= 0 && index < workoutReminders.size()) {
            WorkoutSchedule reminder = workoutReminders.remove(index);
            System.out.println("Reminder removed for workout \"" + reminder.getWorkout().getWorkoutName() + "\"");
        } else {
            System.out.println("Invalid index specified.");
        }
    }

    public static void showWorkoutReminders() {
        System.out.println("Workout Reminders:");
        if (workoutReminders.isEmpty()) {
            System.out.println("No workout reminders found.");
        } else {
            for (int i = 0; i < workoutReminders.size(); i++) {
                WorkoutSchedule reminder = workoutReminders.get(i);
                String reminderDateTimeFormatted = reminder.getReminderDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                System.out.println((i + 1) + ". " + reminder.getWorkout().getWorkoutName() +
                        " (reminder set for " + reminderDateTimeFormatted + ")");
            }
        }
    }

    public static void addWorkoutReminder(Workout selectedWorkout, LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Reminder Time Passed");
            alert.setHeaderText(null);
            alert.setContentText("The reminder time has already passed. Please select a future date and time.");
            alert.showAndWait();
            return;
        }

        WorkoutSchedule reminder = new WorkoutSchedule(selectedWorkout, dateTime);

        workoutReminders.add(reminder);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Workout Added to Schedule");
        String dateTimeFormatted = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        alert.setContentText("Reminder set for workout \"" + selectedWorkout.getWorkoutName() + "\" at " + dateTimeFormatted);
        alert.showAndWait();
    }
    public static boolean hasWorkoutReminder(String workoutName) {
        for (WorkoutSchedule reminder : workoutReminders) {
            if (reminder.getWorkout().getWorkoutName().equals(workoutName)) {
                return true;
            }
        }
        return false;
    }



}
