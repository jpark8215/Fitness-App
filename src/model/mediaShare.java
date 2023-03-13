package model;

public class mediaShare {

    // Declare instance variables
    private String userName;
    private String userGoal;

    // Constructor
    public mediaShare(String userName, String userGoal) {
        this.userName = userName;
        this.userGoal = userGoal;
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getUserGoal() {
        return userGoal;
    }

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserGoal(String userGoal) {
        this.userGoal = userGoal;
    }
}

