package pl.tgargula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.tgargula.controllers.AppController;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/app.fxml"));

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
        ((AppController) loader.getController()).setStage(stage);

        stage.setScene(scene);
        stage.setTitle("PDF Encrypter");
        stage.show();
    }

}
