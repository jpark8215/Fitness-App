package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    // Declare instance variables
    Stage stage;
    Parent scene;

    // FXML components
    @FXML
    private Button addWorkoutButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button removeWorkoutButton;

    @FXML
    private Button videoSearchButton;

    @FXML
    private Button workoutByIndexButton;

    @FXML
    private Button workoutScheduleButton;

    // Method to handle add workout button click
    @FXML
    void addWorkout(ActionEvent event) throws IOException {
        // Get the stage and load the AddWorkout.fxml view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddWorkout.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    // Method to handle exit button click
    @FXML
    void exit(ActionEvent event) {
        // Get the stage and close the window
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Method to handle remove workout button click
    @FXML
    void removeWorkout(ActionEvent event) throws IOException {
        // Get the stage and load the WorkoutList.fxml view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/WorkoutList.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    // Method to handle video search button click
    @FXML
    void videoSearch(ActionEvent event) throws IOException {
        // Get the stage and load the VideoSearch.fxml view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/VideoSearch.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    // Method to handle workout by index button click
    @FXML
    void workoutByIndex(ActionEvent event) throws IOException {
        // Get the stage and load the WorkoutList.fxml view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/WorkoutList.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    // Method to handle workout schedule button click
    @FXML
    void workoutSchedule(ActionEvent event) throws IOException {
        // Get the stage and load the ScheduleList.fxml view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ScheduleList.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
