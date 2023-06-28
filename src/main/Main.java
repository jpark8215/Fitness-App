package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ActivityTracker;
import model.MediaShare;
import model.Workout;
import model.WorkoutHub;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Main extends Application {

    public static void main(String[] args) {

        launch(args);

        Scanner scanner = new Scanner(System.in);

        // Create a WorkoutHub object with the list of workouts
        WorkoutHub workoutHubList = new WorkoutHub();

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Activity Track and Share");
            System.out.println("2. Exit");

            int option = scanner.nextInt();

            switch (option) {

                case 1 -> {
                    // Get the list of workouts from the Workout class
                    List<Workout> workoutList = WorkoutHub.getWorkoutObservableList();

                    // Display the available workouts
                    System.out.println("Select a workout:");
                    for (int i = 0; i < workoutList.size(); i++) {
                        System.out.println((i + 1) + ". " + workoutList.get(i).getWorkoutName());
                    }

                    int workoutOption = scanner.nextInt();

                    // Perform the desired action based on the selected workout option
                    if (workoutOption >= 1 && workoutOption <= workoutList.size()) {
                        Workout selectedWorkout = workoutList.get(workoutOption - 1);
                        // Handle the selected workout (e.g., track and share)
                        System.out.println("Selected workout: " + selectedWorkout.getWorkoutName());

                        // Ask the user if they want to set a goal
                        System.out.println("Do you want to set a calorie burn goal? (Y/N)");
                        String response = scanner.next();

                        if (response.equalsIgnoreCase("Y")) {
                            // Ask the user to enter the calorie burn goal
                            System.out.println("Enter the calorie burn goal: ");
                            int calorieBurnGoal = scanner.nextInt();
                        }

                        // Ask the user to enter the duration in minutes
                        System.out.println("Enter the duration in minutes:");
                        int duration = scanner.nextInt();

                        // Calculate the calories burned based on the selected workout and duration
                        int caloriesBurned = selectedWorkout.getCalories() * duration;


                        // Create an instance of ActivityTracker with the selected workout and calculated calories burned
                        ActivityTracker activity = new ActivityTracker(selectedWorkout, duration, caloriesBurned);
                        System.out.println(" You've burned " + activity.getCaloriesBurned() + " calories!");
                        // Check if the activity's calories burned reach the goal
                        MediaShare.shareActivityIfGoalReached(activity, activity.getCaloriesBurned());


                    } else {
                        System.out.println("Invalid workout option");
                    }

                }

                case 2 -> {
                    System.out.println("See you soon again!");
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


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
        primaryStage.setTitle("Fit");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }
}