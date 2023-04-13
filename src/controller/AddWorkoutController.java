package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Workout;
import model.WorkoutHub;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class AddWorkoutController {

    Stage stage;
    Parent scene;


    @FXML
    private TableColumn<Workout, Integer> calorieCol;

    @FXML
    private TableColumn<Workout, Integer> indexCol;

    @FXML
    private Button saveButton;

    @FXML
    private AnchorPane workoutField;

    @FXML
    private TableColumn<Workout, String> workoutNameCol;

    @FXML
    private TableView<String> workoutTable;

    @FXML
    private TextField workoutTextField;

    @FXML
    void saveHandler(ActionEvent event) throws IOException {

        String workoutName = workoutTextField.getText();
        Workout workout = new Workout(workoutName);
        // Add the new workout to the workouts list in the WorkoutHub
        WorkoutHub.addWorkout(workout);
        workoutTextField.clear();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Main.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void initialize(URL location, ResourceBundle resources) {

        // Initialize the workout table
        ObservableList<String> workoutsWithIndices = FXCollections.observableArrayList(WorkoutHub.getWorkoutsWithIndices());
        workoutTable.setItems(workoutsWithIndices);
        workoutNameCol.setCellValueFactory(cellData -> new SimpleStringProperty());

    }

}