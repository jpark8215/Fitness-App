package model;

public class MediaShare {

    // Declare instance variables
    private String userName; // The user's name
    private int userGoal; // The user's fitness goal
    private String instagramAccount; // The user's Instagram account

    // Constructor
    public MediaShare(String userName, int userGoal, String instagramAccount) {
        this.userName = userName;
        this.userGoal = userGoal;
        this.instagramAccount = instagramAccount;
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public int getUserGoal() {
        return userGoal;
    }

    public String getInstagramAccount() {
        return instagramAccount;
    }

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserGoal(int userGoal) {
        this.userGoal = userGoal;
    }

    public void setInstagramAccount(String instagramAccount) {
        this.instagramAccount = instagramAccount;
    }


    public static void shareActivityIfGoalReached(ActivityTracker activityCal, int calorieBurnGoal) {
        // Check if the calories burned during the activityCal is greater than or equal to the goal
        if (activityCal.getCaloriesBurned() >= calorieBurnGoal) {
            // Construct the caption for the Instagram post
            String caption = "I just burned " + activityCal.getCaloriesBurned() + " calories! #fitness #workout #calorieburn";

            // Share the activityCal on Instagram
            shareOnInstagram(caption);
        }
    }


    private static void shareOnInstagram(String caption) {
        System.out.println("Shared on Instagram: " + caption);
    }
}