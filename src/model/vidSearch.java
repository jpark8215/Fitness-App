package model;
//This code will allow users to access instructional videos via URL links from the fitness app Feather.

//Create a class for the fitness app
public class vidSearch {

    //Declare global variables
    private String videoURL;
    private String videoDescription;

    //Constructor
    public vidSearch(String url, String desc) {
        this.videoURL = url;
        this.videoDescription = desc;
    }

    //Getter and setter methods
    public String getVideoURL() {
        return this.videoURL;
    }

    public void setVideoURL(String url) {
        this.videoURL = url;
    }

    public String getVideoDescription() {
        return this.videoDescription;
    }

    public void setVideoDescription(String desc) {
        this.videoDescription = desc;
    }

    //Method to display instructional video
    public void displayVideo() {
        System.out.println("Playing instructional video from URL: " + this.videoURL);
        System.out.println("Video Description: " + this.videoDescription);
    }

}

