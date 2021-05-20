package pl.tgargula.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.tgargula.FileParser;

import java.io.File;
import java.io.IOException;

public class AppController {

    private final FileChooser fileChooser = new FileChooser();
    @FXML
    private TextField inputFilePath;
    @FXML
    private TextField password;
    @FXML
    private TextField outputFileName;

    private Stage stage;
    private File selectedFile;

    @FXML
    private void initialize() {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("All files", "*")
        );
    }

    @FXML
    private void chooseFile() {
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            inputFilePath.setText(selectedFile.getAbsolutePath());
            outputFileName.setText(FileParser.getEncryptedFileName(selectedFile));
        }
    }

    @FXML
    private void closeWindow() {
        stage.close();
    }

    @FXML
    public void reset() {
        inputFilePath.setText("");
        password.setText("");
        outputFileName.setText("");
    }

    @FXML
    private void encrypt() throws IOException {
        if (!isCorrectData()) return;
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(
                "pdftk", inputFilePath.getText(),
                "output", FileParser.getOutputPath(selectedFile, outputFileName.getText()),
                "user_pw", password.getText()
        );
        try {
            Process process = processBuilder.start();
            int exitValue = process.waitFor();

            if (exitValue == 0)
                openPopup(ResponseType.SUCCESS);
            else
                openPopup(ResponseType.FAILURE);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private boolean isCorrectData() throws IOException {
        if (inputFilePath.getText().isEmpty()) {
            openPopup(ResponseType.FILE_NOT_SELECTED_WARNING);
            return false;
        }

        if (password.getText().isEmpty()) {
            openPopup(ResponseType.EMPTY_PASSWORD_WARNING);
            return false;
        }

        if (outputFileName.getText().isEmpty()) {
            openPopup(ResponseType.EMPTY_OUTPUT_WARNING);
            return false;
        }

        if (!(password.getText().matches("[A-Za-z0-9]+"))) {
            openPopup(ResponseType.INCORRECT_PASSWORD_WARNING);
            return false;
        }

        return true;
    }

    private void openPopup(ResponseType responseType) throws IOException {
        Stage popup = new Stage();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(responseType.getFxmlFile()));
        Parent root = loader.load();
        root.getStylesheets().add(this.getClass().getResource(responseType.getStyleClass()).toExternalForm());
        PopupController popupController = loader.getController();
        popupController.setPrimaryStage(stage);
        popupController.setPopupStage(popup);
        popupController.setDirectory(selectedFile != null ? selectedFile.getParent() : null);
        popupController.setWarningMessage(responseType);
        popup.setScene(new Scene(root));
        popup.initOwner(stage);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initStyle(StageStyle.UNDECORATED);
        popup.show();
    }

}
