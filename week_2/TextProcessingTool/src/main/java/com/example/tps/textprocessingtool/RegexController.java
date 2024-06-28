package com.example.tps.textprocessingtool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexController {

    @FXML
    private TextArea resultInput;

    @FXML
    private TextField regexInput;

    @FXML
    private TextArea textInput;

    @FXML
    private TextField replaceInput;

    private String regex;

    private String text;

    private String replacement;

    private Map<String, String> history = new HashMap<>();

    @FXML
    protected void initialize() {
        regexInput.textProperty().addListener((observable, oldValue, newValue) -> regex = newValue);
        textInput.textProperty().addListener((observable, oldValue, newValue) -> text = newValue);
        replaceInput.textProperty().addListener((observable, oldValue, newValue) -> replacement = newValue);
    }

    @FXML
    protected void matchRegex(ActionEvent event) {

        try {
            if (regex.isEmpty() || text.isEmpty()) handleError();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            clearIO();

            if (matcher.matches()) {
                String result = String.format("Full match found: '%s'.", matcher.group());
                resultInput.appendText(result);
            } else {
                resultInput.appendText("No full match found.");
            }
        } catch (Exception e) {
            handleError();
        }
    }

    @FXML
    protected void findRegex(ActionEvent event) {
        try {
            if (regex.isEmpty() || text.isEmpty()) handleError();
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            clearIO();
            boolean found = false;

            while (matcher.find()) {
                found = true;
                String result = String.format("Found: '%s' at index %d to %d.%n",
                        matcher.group(), matcher.start(), matcher.end());
                resultInput.appendText(result);
            }

            if (!found) {
                resultInput.appendText("No matches found.");
            }
        } catch (Exception e) {
            handleError();
        }
    }

    @FXML
    protected void replaceRegex(ActionEvent event) {
        try {
            if (regex.isEmpty() || text.isEmpty() || replacement.isEmpty()) handleError();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            String replacedText = matcher.replaceAll(replacement);
            resultInput.setText(replacedText);
        } catch (Exception e) {
            handleError();
        }
    }

    @FXML
    protected void addToHistory(ActionEvent event) {
        if (regex != null && !regex.isEmpty() && text != null && !text.isEmpty()) {
            history.put(regex, text);
            System.out.println("Pattern and text added to history: " + regex + " -> " + text);
        }
    }

    @FXML
    protected void showHistory(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history_view.fxml"));
            Parent root = loader.load();
            HistoryController historyController = loader.getController();
            historyController.setHistory(history);

            Stage stage = new Stage();
            stage.setTitle("History");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Sorry, There was an error");
            alert.showAndWait();
        }
    }

    private void handleError() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Error: One or more fields empty");
        alert.showAndWait();
    }

    private void clearIO() {
        resultInput.clear();
        replaceInput.clear();
    }
}
