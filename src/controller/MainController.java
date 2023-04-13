package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.WorkoutHub;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    Stage stage;
    Parent scene;

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

    @FXML
    void addWorkout(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddWorkout.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void exit(ActionEvent event) {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void removeWorkout(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/WorkoutList.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void videoSearch(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/VideoSearch.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void workoutByIndex(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/WorkoutList.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void workoutSchedule(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ScheduleList.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
