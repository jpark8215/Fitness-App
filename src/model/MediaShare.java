package model;

public class MediaShare {

    // Declare instance variables
    private String userName;
    private String userGoal;
    private String instagramAccount;

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
}

//    MediaShare mediaShare = new MediaShare("John", "Gain 5KG", "@johndoe");
