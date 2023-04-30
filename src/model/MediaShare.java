package model;
/**

 This class represents a media sharing platform where users can share their fitness activities on Instagram
 */
public class MediaShare {

    // Declare instance variables
    private String userName; // The user's name
    private String userGoal; // The user's fitness goal
    private String instagramAccount; // The user's Instagram account

    // Constructor
    public MediaShare(String userName, String userGoal, String instagramAccount) {
        this.userName = userName;
        this.userGoal = userGoal;
        this.instagramAccount = instagramAccount;
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getUserGoal() {
        return userGoal;
    }

    public String getInstagramAccount() {
        return instagramAccount;
    }

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserGoal(String userGoal) {
        this.userGoal = userGoal;
    }

    public void setInstagramAccount(String instagramAccount) {
        this.instagramAccount = instagramAccount;
    }

    /**

     This method shares the user's activity on Instagram if their calorie burn goal is reached

     @param activityCal The ActivityTracker object representing the user's fitness activity

     @param calorieBurnGoal The calorie burn goal set by the user
     */
    public void shareActivityIfGoalReached(ActivityTracker activityCal, int calorieBurnGoal) {
// Check if the calories burned during the activityCal is greater than or equal to the goal
        if (activityCal.getCaloriesBurned() >= calorieBurnGoal) {
// Construct the caption for the Instagram post
            String caption = "I just burned " + activityCal.getCaloriesBurned() + " calories during my " + activityCal.getWorkout() + " workout! #fitness #workout #calorieburn";

            // Share the activityCal on Instagram
            shareOnInstagram(caption);
        }
    }

    /**

     This method shares the given caption on Instagram
     @param caption The caption to be shared
     */
    private void shareOnInstagram(String caption) {
        System.out.println("Shared on Instagram: " + caption);
    }
}