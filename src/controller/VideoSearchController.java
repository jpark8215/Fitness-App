package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VideoSearchController {
    Stage stage;
    Parent scene;

    @FXML
    private TextField keywordTextField;


    @FXML
    private Button mainButton;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<String> videoListView;

    @FXML
    void mainButtonHandler(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Main.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void searchButtonHandler(ActionEvent event) {
        String keywordTextFieldText = keywordTextField.getText();

        try {
            // Encode the keyword for URL
            String encodedKeyword = URLEncoder.encode(keywordTextFieldText, StandardCharsets.UTF_8);

            // Open the default browser with the search result page
            String url = "https://www.youtube.com/results?search_query=" + encodedKeyword + "%20workout";

            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Add default video URLs to the list view
        List<String> defaultVideoUrls = Arrays.asList(
                "https://www.youtube.com/channel/UCJyIC2zqJ-i9R84Ba29tjWA",
                "https://www.youtube.com/shorts/L2BgrNn44qE",
                "https://www.youtube.com/watch?v=AfUvp0AGdYE"
        );
        videoListView.getItems().addAll(defaultVideoUrls);
    }

    public void videoMouseClickHandler(javafx.scene.input.MouseEvent mouseEvent) {
        // Add event handler for clicking on a list view item
        videoListView.setOnMouseClicked(event -> {
            if (mouseEvent.getClickCount() == 2) { // double-clicked
                String selectedUrl = videoListView.getSelectionModel().getSelectedItem();
                try {
                    Desktop.getDesktop().browse(new URI(selectedUrl));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


