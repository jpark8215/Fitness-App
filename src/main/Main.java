package main;

import model.Workout;
import model.WorkoutHub;
import model.WorkoutSchedule;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an empty list of workouts
        List<Workout> workouts = new ArrayList<>();

        // Create a WorkoutHub object with the list of workouts
        WorkoutHub workoutHub = new WorkoutHub(workouts);

        System.out.println("\nWelcome to your Workout Manager!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add workout");
            System.out.println("2. Remove workout");
            System.out.println("3. Get workout by index");
            System.out.println("4. Show workout schedule");
            System.out.println("5. Video Search");
            System.out.println("6. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    // Prompt user to enter the name of the workout
                    System.out.println("Enter workout name:");
                    String workoutName = scanner.next();

                    // Create a new Workout object with the given name
                    Workout workout = new Workout(workoutName);

                    // Prompt user to set a reminder for the workout
                    System.out.println("Do you want to set a reminder for this workout? Enter 'yes' or 'no'");
                    String option1 = scanner.next();

                    try {
                        if (option1.equals("yes")) {
                            // If user wants to set a reminder, prompt user to enter the reminder time
                            System.out.println("Enter reminder time (in minutes from now):");
                            int reminderTime = scanner.nextInt();

                            // Add the workout and its reminder to the WorkoutSchedule object
                            WorkoutSchedule.addWorkoutReminder(workout, reminderTime);
                            WorkoutHub.addWorkout(workout);
                        } else {
                            // If user doesn't want to set a reminder, just add the workout to the WorkoutHub object
                            WorkoutHub.addWorkout(workout);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid input format. Please enter a valid number.");
                    } catch (NullPointerException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: An unexpected error occurred. Please try again.");
                    }

                    // Print a message indicating the workout was added
                    System.out.println("Workout added: " + workoutName);
                }
                case 2 -> {
                    // Print the list of workouts with their indices
                    System.out.println(WorkoutHub.getWorkoutsWithIndices());

                    // Prompt user to enter the index of the workout to remove
                    System.out.println("Enter workout index to remove:");
                    int indexToRemove = scanner.nextInt();

                    // If the index is valid, remove the workout and print a message indicating the workout was removed
                    if (indexToRemove >= 0 && indexToRemove < workoutHub.getNumberOfWorkouts()) {
                        Workout removedWorkout = workoutHub.getWorkout(indexToRemove);
                        workoutHub.removeWorkout(removedWorkout);
                        System.out.println("Workout removed: " + removedWorkout);
                    } else {
                        // If the index is invalid, print an error message
                        System.out.println("Invalid index");
                    }
                }
                case 3 -> {
                    // Print the list of workouts with
                    System.out.println(WorkoutHub.getWorkoutsWithIndices());

                    // Prompt the user to enter the index of the workout they want to get
                    System.out.println("Enter workout index to get:");
                    int indexToGet = scanner.nextInt();

                    // Check if the entered index is valid
                    if (indexToGet >= 0 && indexToGet < workoutHub.getNumberOfWorkouts()) {
                        // Get the workout at the entered index
                        Workout gottenWorkout = workoutHub.getWorkout(indexToGet);

                        // Print the gotten workout
                        System.out.println("Workout at index " + indexToGet + ": " + gottenWorkout);

                        // Prompt the user to set a reminder for the gotten workout
                        System.out.println("Do you want to set a reminder for this workout? Enter 'yes' or 'no'");
                        String option2 = scanner.next();

                        // If the user chooses to set a reminder, prompt them to enter the reminder time
                        if (option2.equals("yes")) {
                            System.out.println("Enter reminder time (in minutes from now):");
                            int reminderTime = scanner.nextInt();

                            // Set a reminder for the gotten workout with the entered reminder time
                            WorkoutSchedule.addWorkoutReminder(gottenWorkout, reminderTime);
                        }
                    } else {
                        System.out.println("Invalid index");
                    }
                }
                case 4 -> {
                    // Show all workout reminders
                    WorkoutSchedule.showWorkoutReminders();

                    // Ask user if they want to delete a reminder
                    System.out.println("Do you want to delete a reminder? Enter 'yes' or 'no'");
                    String option3 = scanner.next();

                    if (option3.equalsIgnoreCase("yes")) {
                        // Ask user which reminder to delete
                        System.out.println("Enter the index of the reminder you want to delete:");
                        int index = scanner.nextInt();
                        scanner.nextLine(); // consume the newline character

                        // Delete the reminder
                        WorkoutSchedule.removeWorkoutReminder(index);
                        } else {
                            System.out.println("No workout found \"");
                        }
                    }

                case 5 -> {
                    // Prompt the user to enter a keyword to search for on YouTube
                    System.out.print("Enter keyword to search for video: ");
                    String keyword = scanner.next();

                    try {
                        // Encode the keyword for URL
                        String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);

                        // Open the default browser with the search result page
                        String url = "https://www.youtube.com/results?search_query=" + encodedKeyword;
                        Desktop.getDesktop().browse(new URI(url));
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
                case 6 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }

                default -> {
                    try {
                        throw new IllegalArgumentException("Invalid option");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        }
    }
}