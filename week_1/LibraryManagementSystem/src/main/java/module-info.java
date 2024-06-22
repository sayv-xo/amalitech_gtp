module com.example.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.librarymanagementsystem to javafx.fxml;
    exports com.example.librarymanagementsystem;
    exports com.example.librarymanagementsystem.management;
    opens com.example.librarymanagementsystem.management to javafx.fxml;
}