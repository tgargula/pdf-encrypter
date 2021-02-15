package pl.tgargula;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AppController {

    @FXML
    private VBox root;

    @FXML
    private TextField inputFilePath;

    @FXML
    private TextField password;

    @FXML
    private TextField outputFileName;

    private final FileChooser fileChooser = new FileChooser();
    private Stage stage;

    @FXML
    private void initialize() {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("All files", "*")
        );
    }

    @FXML
    private void chooseFile() {
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null)
            inputFilePath.setText(selectedFile.toString());
    }

    @FXML
    private void closeWindow() {
        stage.close();
    }

    @FXML
    private void reset() {
        inputFilePath.setText("");
        password.setText("");
        outputFileName.setText("");
    }

    @FXML
    private void encrypt() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
