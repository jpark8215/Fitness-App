package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Workout;
import model.WorkoutHub;

import java.io.IOException;
import java.util.Objects;


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

//        TableView<String> workoutTable = new TableView<>();

        // Create a new ObservableList from the list of workouts with indices
        ObservableList<String> items = FXCollections.observableArrayList(WorkoutHub.getWorkoutsWithIndices());

        // Set the items of the TableView to the new ObservableList
        workoutTable.setItems(items);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Main.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();


    }

}