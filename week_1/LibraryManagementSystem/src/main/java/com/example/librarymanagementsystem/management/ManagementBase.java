package com.example.librarymanagementsystem.management;
import com.example.librarymanagementsystem.engine.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public abstract class ManagementBase<T> {
    protected TableView<T> tableView;
    protected ObservableList<T> data;
    protected DatabaseManager dbManager;

    public ManagementBase() {
        tableView = new TableView<>();
        data = FXCollections.observableArrayList();
        dbManager = new DatabaseManager();
    }

    public abstract VBox getManagementPane();

    protected abstract void loadData();

    protected void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
