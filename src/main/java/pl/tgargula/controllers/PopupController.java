package pl.tgargula.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupController {

    @FXML
    public Text feedback;

    private Stage primaryStage;
    private Stage popupStage;
    private String pathToDirectory;

    @FXML
    private void closePopup() {
        popupStage.close();
    }

    @FXML
    private void closeApp() {
        primaryStage.close();
        closePopup();
    }

    @FXML
    private void openDirectory() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("xdg-open", pathToDirectory);
        processBuilder.start();
        closeApp();
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setPopupStage(Stage stage) {
        this.popupStage = stage;
    }

    public void setDirectory(String pathToDirectory) {
        this.pathToDirectory = pathToDirectory;
    }

    public void setWarningMessage(ResponseType responseType) {
        responseType.setWarningMessage(feedback);
    }

}
