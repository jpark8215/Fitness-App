package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
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
    private Button mainButton;

    @FXML
    private AnchorPane workoutField;

    @FXML
    private TableColumn<Workout, String> workoutNameCol;

    @FXML
    private TableView<Workout> workoutTable;

    @FXML
    private TextField workoutTextField;

    @FXML
    void mainButtonHandler(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Main.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void saveButtonHandler(ActionEvent event) throws IOException {

        // Get workout Name and feed into constructor
        String workoutName = workoutTextField.getText();
        Workout workout = new Workout(workoutName);

        // Set the index of the new workout to be equal to the size of the workouts list
        int workoutIndex = WorkoutHub.getWorkoutList().size();
        workout.setIndices(workoutIndex);

        // Add the new workout to the workouts list in the WorkoutHub
        WorkoutHub.addWorkout(workout);
        workoutTextField.clear();

        // Create a new observable list and add all the workouts to it
        ObservableList<Workout> workoutObservableList = FXCollections.observableArrayList();
        workoutObservableList.addAll(WorkoutHub.getWorkoutObservableList());

        // Set the workoutTable items to the new observable list
        workoutTable.setItems(workoutObservableList);

        // Set the cell value factories for the columns
        calorieCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCalories()));
        workoutNameCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getWorkoutName()));
        indexCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getIndices()));

    }

}