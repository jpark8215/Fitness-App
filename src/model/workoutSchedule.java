package model;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

public class workoutSchedule {
        private final List<workout> workouts;
        private LocalDateTime currentTime;

        public workoutSchedule() {
            this.workouts = new ArrayList<>();
            this.currentTime = LocalDateTime.now();
        }

        public void addWorkout(workout workout) {
            workouts.add(workout);
        }

        public void removeWorkout(workout workout) {
            workouts.remove(workout);
        }

        public void updateTime() {
            this.currentTime = LocalDateTime.now();
        }

        public void checkForReminders() {
            for (model.workout workout : workouts) {
                if (workout.getStartTime().isBefore(LocalTime.from(currentTime))) {
                    System.out.println("It's time to workout! " + workout.getName());
                }
            }
        }
    }

