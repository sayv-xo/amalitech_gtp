package com.example.tps.textprocessingtool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Map;

public class HistoryController {

    @FXML
    private ListView<String> historyListView;

    private Map<String, String> history;

    public void setHistory(Map<String, String> history) {
        this.history = history;
        historyListView.getItems().clear();
        history.forEach((pattern, text) -> historyListView.getItems().add(pattern + " -> " + text));
    }

    @FXML
    protected void deleteSelected(ActionEvent event) {
        String selectedItem = historyListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String[] parts = selectedItem.split(" -> ");
            String pattern = parts[0];
            String text = parts[1];

            history.remove(pattern);
            historyListView.getItems().remove(selectedItem);
        } else {
            showAlert("No Selection", "No pattern-text pair selected", "Please select a pattern-text pair to delete.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
