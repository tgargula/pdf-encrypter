package pl.tgargula.controllers;

import javafx.scene.text.Text;

public enum ResponseType {
    SUCCESS, FAILURE, EMPTY_PASSWORD_WARNING, FILE_NOT_SELECTED_WARNING, EMPTY_OUTPUT_WARNING, INCORRECT_PASSWORD_WARNING;

    public String getStyleClass() {
        return getPath() + ".css";
    }

    public String getFxmlFile() {
        return getPath() + ".fxml";
    }

    public void setWarningMessage(Text feedback) {
        switch (this) {
            case FILE_NOT_SELECTED_WARNING:
                feedback.setText("Nie został wybrany żaden plik!");
                break;
            case EMPTY_PASSWORD_WARNING:
                feedback.setText("Nie zostało nadane hasło!");
                break;
            case EMPTY_OUTPUT_WARNING:
                feedback.setText("Nie została nadana nazwa pliku wyjściowego!");
                break;
            case INCORRECT_PASSWORD_WARNING:
                feedback.setText("Hasło może zawierać małe i duże litery oraz liczby (bez polskich znaków)!");
                break;
        }
    }

    private String getPath() {
        String result;
        switch (this) {
            case SUCCESS:
                result = "/popups/success";
                break;
            case FAILURE:
                result = "/popups/failure";
                break;
            default:
                result = "/popups/warning";
        }
        return result;
    }
}
