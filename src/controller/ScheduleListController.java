package controller;

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
import model.WorkoutSchedule;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class ScheduleListController {
    Stage stage;
    Parent scene;
    @FXML
    private Button mainButton;

    @FXML
    private Button removeButton;

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
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Main.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void removeButtonHandler(ActionEvent event) {
        // Get the selected workout from the table
        WorkoutSchedule selectedSchedule = scheduleTable.getSelectionModel().getSelectedItem();
        if (selectedSchedule != null) {
            // Display a confirmation dialog to confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this workout reminder?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                // Remove the schedule from the observable list
                ObservableList<WorkoutSchedule> scheduleObservableList = scheduleTable.getItems();
                scheduleObservableList.remove(selectedSchedule);

                // Remove the schedule from the workoutReminders list
                WorkoutSchedule.removeReminder(selectedSchedule);
            }
        }
    }

    @FXML
    void initialize() {
        // Create a new observable list and add all the workouts to it
        ObservableList<WorkoutSchedule> scheduleObservableList = FXCollections.observableArrayList();
        scheduleObservableList.addAll(WorkoutSchedule.getScheduleObservableList());

        // Check if the schedule list is empty
        if (scheduleObservableList.isEmpty()) {
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

