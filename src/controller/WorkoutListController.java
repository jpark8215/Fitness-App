package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
import java.time.format.DateTimeFormatter;
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
    private Button deleteButton;

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane workoutField;

    @FXML
    private TableColumn<Workout, String> workoutNameCol;

    @FXML
    private TableView<Workout> workoutTable;

    // Event handler for the main button
    @FXML
    void mainButtonHandler(ActionEvent event) throws IOException {
        // Load the main scene and display it
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Main.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    // Event handler for the delete button
    @FXML
    void deleteButtonHandler(ActionEvent event) {
        // Get the selected workout from the table
        Workout selectedWorkout = workoutTable.getSelectionModel().getSelectedItem();
        if (selectedWorkout != null) {
            // Display a confirmation dialog to confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this workout?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                // User selected delete, remove the workout
                WorkoutHub.removeWorkout(selectedWorkout);
                workoutTable.getItems().remove(selectedWorkout);
            }
        }
    }

    // Event handler for the add button
    @FXML
    void addButtonHandler(ActionEvent event) {
        // Get the selected workout from the table
        Workout selectedWorkout = workoutTable.getSelectionModel().getSelectedItem();

        if (selectedWorkout == null) {
            // No workout selected, display an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Workout Selected");
            alert.setContentText("Please select a workout from the table.");
            alert.showAndWait();
        } else {
            // Workout selected, show date and time picker in a dialog
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

            // Add a DatePicker, ComboBox for hour, ComboBox for minute, and ComboBox for AM/PM to the GridPane
            DatePicker datePicker = new DatePicker();
            ComboBox<Integer> hourComboBox = new ComboBox<>();
            ComboBox<String> minuteComboBox = new ComboBox<>();
            ComboBox<String> amPmComboBox = new ComboBox<>();

            // Populate the ComboBoxes with appropriate options
            // The hourComboBox shows values from 1-12
            for (int i = 1; i <= 12; i++) {
                hourComboBox.getItems().add(i);
            }
            // Select the current hour in the hourComboBox
            hourComboBox.getSelectionModel().select(LocalTime.now().getHour() % 12);

            // The minuteComboBox shows values from 0-55 in increments of 5
            for (int i = 0; i <= 55; i += 5) {
                String minute = String.format("%02d", i);
                minuteComboBox.getItems().add(minute);
            }
            // Select the current minute in the minuteComboBox
            minuteComboBox.getSelectionModel().select(LocalTime.now().format(DateTimeFormatter.ofPattern("mm")));

            // The amPmComboBox shows options for AM or PM
            amPmComboBox.getItems().addAll("AM", "PM");
            // Select the current AM/PM value in the amPmComboBox
            amPmComboBox.getSelectionModel().select(LocalTime.now().format(DateTimeFormatter.ofPattern("a")));

            // Add the labels and ComboBoxes to the GridPane
            gridPane.add(new Label("Date:"), 0, 0);
            gridPane.add(datePicker, 1, 0);
            gridPane.add(new Label("Time:"), 0, 1);
            gridPane.add(hourComboBox, 1, 1);
            gridPane.add(new Label(":"), 2, 1);
            gridPane.add(minuteComboBox, 3, 1);
            gridPane.add(amPmComboBox, 4, 1);

            // Add a result converter to the dialog to handle when the "Add" button is clicked
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButton) {
                    // Get the selected date and time from the DatePicker and ComboBoxes
                    LocalDate date = datePicker.getValue();
                    int hour = hourComboBox.getValue();
                    int minute = Integer.parseInt(minuteComboBox.getValue());
                    String amPm = amPmComboBox.getValue();
                    // If PM is selected, add 12 to the hour value
                    if (amPm.equals("PM")) {
                        hour += 12;
                    }
                    // Combine the date and time into a LocalDateTime object
                    LocalTime time = LocalTime.of(hour % 24, minute);
                    LocalDateTime dateTime = LocalDateTime.of(date, time);
                    WorkoutSchedule.addWorkoutReminder(selectedWorkout, dateTime);
                    }

                return null;
            });

            dialog.getDialogPane().setContent(gridPane);

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
                alert.setContentText("Choose your option below.");

            }
        });
    }
}