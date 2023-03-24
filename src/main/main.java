/**
 * @author Jieun Park
 */
package main;
//import controller.LoginController;
//import database.DBConnection;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;


import model.workout;
import model.workoutSchedule;
import model.activityTracker;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Main class
 */

public class main {
    public static void main(String[] args) {

        // create a workout
        workout workout1 = new workout();
        workout1.setName();
        workout1.setStartTime(LocalTime.of(10, 0));

        // create a workout schedule
        workoutSchedule schedule = new workoutSchedule();

        // add the workout to the schedule
        schedule.addWorkout(workout1);

        // update the current time
        schedule.updateTime();

        // check for reminders
        schedule.checkForReminders();

    }
}




