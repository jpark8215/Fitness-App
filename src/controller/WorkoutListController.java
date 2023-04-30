package controller;

import com.gluonhq.charm.glisten.control.TimePicker;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Workout;
import model.WorkoutHub;
import model.WorkoutSchedule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class WorkoutListController {
    Stage stage;
    Parent scene;
    @FXML
    private TableColumn<Workout, Integer> calorieCol;

    @FXML
    private TableColumn<Workout, Integer> indexCol;

    @FXML
    private Button mainButton;

    @FXML
    private AnchorPane workoutField;

    @FXML
    private TableColumn<Workout, String> workoutNameCol;

    @FXML
    private TableView<Workout> workoutTable;

    @FXML
    void mainButtonHandler(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Main.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    @FXML
    void deleteButtonHandler(ActionEvent event) {
        Workout selectedWorkout = workoutTable.getSelectionModel().getSelectedItem();
        if (selectedWorkout != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this workout?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                // User selected delete, remove the workout
                WorkoutHub.removeWorkout(selectedWorkout);
                workoutTable.getItems().remove(selectedWorkout);
            }
        }
    }

    @FXML
    void addButtonHandler(ActionEvent event) {
        Workout selectedWorkout = workoutTable.getSelectionModel().getSelectedItem();

        if (selectedWorkout == null) {
            // No workout selected, display an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Workout Selected");
            alert.setContentText("Please select a workout from the table.");
            alert.showAndWait();
        } else {
            // Workout selected, show date and time picker
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Add to Schedule");

            // Set the button types for the dialog
            ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);

            // Create the content for the dialog
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));

            DatePicker datePicker = new DatePicker();
            TimePicker timePicker = new TimePicker();
            gridPane.add(new Label("Date:"), 0, 0);
            gridPane.add(datePicker, 1, 0);
            gridPane.add(new Label("Time:"), 0, 1);
            Group timePickerGroup = new Group((Collection<Node>) timePicker);
            gridPane.add(timePickerGroup, 1, 1);


            dialog.getDialogPane().setContent(gridPane);

            // Convert the date and time to a LocalDateTime object
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButton) {
                    LocalDate date = datePicker.getValue();
                    LocalTime time = timePicker.getTime();
                    if (date != null && time != null) {
                        WorkoutSchedule.addWorkoutReminder(selectedWorkout, date, time);
                    }
                }
                return null;
            });

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent()) {
                // Date and time selected, add to schedule
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Workout Added to Schedule");
                alert.setContentText("The selected workout has been added to your schedule.");
                alert.showAndWait();
            }
        }
    }



    @FXML
    void initialize() {
        // Create a new observable list and add all the workouts to it
        ObservableList<Workout> workoutObservableList = FXCollections.observableArrayList();
        workoutObservableList.addAll(WorkoutHub.getWorkoutObservableList());

        // Set the workoutTable items to the new observable list
        workoutTable.setItems(workoutObservableList);

        // Set the cell value factories for the columns
        calorieCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCalories()));
        workoutNameCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getWorkoutName()));
        indexCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getIndices()));

        // Add a listener to the table selection model to detect when a row is selected
        workoutTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Ask the user whether they want to delete or add a schedule
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Select Action");
                alert.setHeaderText("What do you want to do with this workout?");
                alert.setContentText("Choose your option.");

                ButtonType deleteButton = new ButtonType("Delete");
                ButtonType addButton = new ButtonType("Add to Schedule");

                alert.getButtonTypes().setAll(deleteButton, addButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == deleteButton){
                    // User selected delete, do something
                    System.out.println("Delete selected");
                } else if (result.get() == addButton) {
                    // User selected add to schedule, do something
                    System.out.println("Add to schedule selected");
                }
            }
        });



    }

}
