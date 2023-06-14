package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.WorkoutSchedule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import static model.WorkoutSchedule.workoutReminders;


public class ScheduleListController {
    Stage stage;
    Parent scene;
    @FXML
    private Button mainButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button updateButton;

    @FXML
    private AnchorPane scheduleField;

    @FXML
    private TableView<WorkoutSchedule> scheduleTable;

    @FXML
    private TableColumn<WorkoutSchedule, LocalDateTime> timeCol;

    @FXML
    private TableColumn<WorkoutSchedule, String> workoutNameCol;

    @FXML
    void mainButtonHandler(ActionEvent event) throws IOException {
        // Handle the event when the mainButton is clicked
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Main.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void removeButtonHandler(ActionEvent event) {
        // Handle the event when the removeButton is clicked
        // Get the selected workout from the table
        WorkoutSchedule selectedSchedule = scheduleTable.getSelectionModel().getSelectedItem();
        if (selectedSchedule != null) {
            // Display a confirmation dialog to confirm deletion
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete this workout reminder?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                // Remove the schedule from the observable list
                ObservableList<WorkoutSchedule> scheduleObservableList = scheduleTable.getItems();
                scheduleObservableList.remove(selectedSchedule);

                // Remove the schedule from the workoutReminders list
                WorkoutSchedule.removeWorkoutReminder(selectedSchedule.getWorkout().getIndices());
            }
        }
    }

    @FXML
    void updateButtonHandler(ActionEvent event) {
        WorkoutSchedule selectedSchedule = scheduleTable.getSelectionModel().getSelectedItem();

        if (selectedSchedule != null) {
            // Workout selected, show date and time picker in a dialog
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update Schedule");

            // Set the button types for the dialog
            ButtonType updateButton = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(updateButton, cancelButton);

            // Create the content for the dialog
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));

            // Add a DatePicker, ComboBox for hour, ComboBox for minute, and ComboBox for AM/PM to the GridPane
            DatePicker datePicker = new DatePicker(LocalDate.now());
            ComboBox<Integer> hourComboBox = new ComboBox<>();
            ComboBox<Integer> minuteComboBox = new ComboBox<>();
            ComboBox<String> amPmComboBox = new ComboBox<>();

            // Populate the ComboBoxes with appropriate options
            // The hourComboBox shows values from 1-12
            for (int i = 1; i <= 12; i++) {
                hourComboBox.getItems().add(i);
            }

            // Set the current hour in the hourComboBox based on the application's time zone
            LocalTime currentTime = LocalTime.now(ZoneId.systemDefault());
            hourComboBox.getSelectionModel().select(currentTime.getHour() % 12);

            // The minuteComboBox shows values from 0-55 in increments of 5
            for (int i = 0; i <= 55; i += 5) {
                minuteComboBox.getItems().add(i);
            }

            // Set the 0 minute in the minuteComboBox based on the application's time zone
            minuteComboBox.getSelectionModel().select(0);

            // The amPmComboBox shows options for AM or PM
            amPmComboBox.getItems().addAll("AM", "PM");

            // Set the current AM/PM value in the amPmComboBox based on the application's time zone
            amPmComboBox.getSelectionModel().select(currentTime.format(DateTimeFormatter.ofPattern("a")));

            // Add the labels and ComboBoxes to the GridPane
            gridPane.add(new Label("Date:"), 0, 0);
            gridPane.add(datePicker, 1, 0);
            gridPane.add(new Label("Time:"), 0, 1);
            gridPane.add(hourComboBox, 1, 1);
            gridPane.add(new Label(":"), 2, 1);
            gridPane.add(minuteComboBox, 3, 1);
            gridPane.add(amPmComboBox, 4, 1);

            // Set the content of the dialog pane
            dialog.getDialogPane().setContent(gridPane);

            // Add a result converter to the dialog to handle when the "Update" button is clicked
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == updateButton) {
                    // Check if any of the ComboBoxes is empty
                    if (hourComboBox.getValue() == null || minuteComboBox.getValue() == null || amPmComboBox.getValue() == null) {
                        // Display an alert message if any ComboBox is empty
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Incomplete Time Selection");
                        alert.setContentText("Please select a value for each time component.");
                        alert.showAndWait();
                    } else {
                        // Get the selected date and time from the DatePicker and ComboBoxes
                        LocalDate date = datePicker.getValue();
                        int hour = hourComboBox.getValue();
                        int minute = minuteComboBox.getValue();
                        String amPm = amPmComboBox.getValue();
                        // If PM is selected, add 12 to the hour value
                        if (amPm.equals("PM")) {
                            hour += 12;
                        }
                        // Combine the date and time into a LocalDateTime object
                        LocalTime time = LocalTime.of(hour % 24, minute);
                        LocalDateTime dateTime = LocalDateTime.of(date, time);
                        WorkoutSchedule.updateWorkoutReminder(selectedSchedule.getWorkout(), dateTime);

                        // Refresh the table after updating
                        scheduleTable.refresh();
                    }
                }
                return dialogButton;
            });

            // Show the dialog and wait for user input
            dialog.showAndWait();
        }
    }



    @FXML
    void initialize () {
        // Initialize the Schedule List Controller
        // Create an empty observable list
        ObservableList<WorkoutSchedule> scheduleObservableList = FXCollections.observableArrayList();

        // Add all workout reminders to the observable list
        scheduleObservableList.addAll(workoutReminders);

        // Check if the schedule list is empty
        if (scheduleObservableList.isEmpty()) {
            // Show an alert message if there are no scheduled workouts
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "There are no scheduled workouts.");
            alert.showAndWait();
        } else {
            // Set up the table with the schedule list
            scheduleTable.setItems(scheduleObservableList);

            // Set up the columns in the table
            workoutNameCol.setCellValueFactory(new PropertyValueFactory<>("workout"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
            timeCol.setCellValueFactory(new PropertyValueFactory<>("reminderDateTime"));
            timeCol.setCellFactory(col -> new TableCell<WorkoutSchedule, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.format(formatter));
                    }
                }
            });
        }
    }
}