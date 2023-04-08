package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FrontPageNoLoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onFrontPageButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}