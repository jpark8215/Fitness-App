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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Workout;
import model.WorkoutHub;
import model.WorkoutSchedule;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ScheduleListController {
    Stage stage;
    Parent scene;
    @FXML
    private TableColumn<WorkoutSchedule, Integer> indexCol;

    @FXML
    private Button mainButton;

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
    void initialize() {
        // Create a new observable list and add all the workouts to it
        ObservableList<WorkoutSchedule> scheduleObservableList = FXCollections.observableArrayList();
        scheduleObservableList.addAll(WorkoutSchedule.getScheduleObservableList());
        scheduleTable.setItems(scheduleObservableList);

        // Set up the columns in the table
        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        workoutNameCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().equals(workoutNameCol)).asString());

    }


}
