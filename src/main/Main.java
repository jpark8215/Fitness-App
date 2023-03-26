/**
 * @author Jieun Park
 */

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
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //create a Scanner object to get input from the user, and an empty list of Workout objects. We then create a WorkoutHub object using the list of workouts
        Scanner scanner = new Scanner(System.in);
        List<Workout> workouts = new ArrayList<>();
        WorkoutHub workoutHub = new WorkoutHub(workouts);

        System.out.println("Welcome to the Workout Manager!");

        //create a menu system using a while loop and a switch statement. The user can select an option by entering a number, and the program will perform the corresponding action.
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add workout");
            System.out.println("2. Remove workout");
            System.out.println("3. Get workout by index");
            System.out.println("4. Video Search");
            System.out.println("5. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println("Enter workout name:");
                    String workoutName = scanner.next();
//                    System.out.println("Enter workout calories:");
//                    int workoutCalories = scanner.nextInt();
                    Workout workout = new Workout(workoutName);
                    WorkoutHub.addWorkout(workout);
                    System.out.println("Workout added: " + workoutName);
                    System.out.println("Do you want to set a reminder for this workout? (y/n)");
                    String option1 = scanner.next();

                    if (option1.equals("y")) {
                        System.out.println("Enter reminder time (in minutes from now):");
                        int reminderTime = scanner.nextInt();
                        WorkoutSchedule.addWorkoutReminder(workout, reminderTime);
                    } else {
                        WorkoutHub.addWorkout(workout);
                    }
                }
                case 2 -> {
                    System.out.println("Enter workout index to remove:");
                    int indexToRemove = scanner.nextInt();
                    if (indexToRemove >= 0 && indexToRemove < workoutHub.getNumberOfWorkouts()) {
                        Workout removedWorkout = workoutHub.getWorkout(indexToRemove);
                        workoutHub.removeWorkout(removedWorkout);
                        System.out.println("Workout removed: " + removedWorkout);
                    } else {
                        System.out.println("Invalid index");
                    }
                }
                case 3 -> {
                    System.out.println(workoutHub.getWorkouts());
                    System.out.println("Enter workout index to get:");
                    int indexToGet = scanner.nextInt();
                    if (indexToGet >= 0 && indexToGet < workoutHub.getNumberOfWorkouts()) {
                        Workout gottenWorkout = workoutHub.getWorkout(indexToGet);
                        System.out.println("Workout at index " + indexToGet + ": " + gottenWorkout);
                        System.out.println("Do you want to set a reminder for this workout? (y/n)");
                        String option2 = scanner.next();

                        if (option2.equals("y")) {
                            System.out.println("Enter reminder time (in minutes from now):");
                            int reminderTime = scanner.nextInt();
                            WorkoutSchedule.setReminder(gottenWorkout, reminderTime);
                        }
                    } else {
                        System.out.println("Invalid index");
                    }
                }
                case 4 -> {
                    System.out.print("Enter keyword to search for video: ");
                    String keyword = scanner.nextLine();

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
                case 5 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}