module isen.contactapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;


    opens isen.contactapp to javafx.fxml;
    opens isen.contactapp.view to javafx.fxml;
    exports isen.contactapp;
    exports isen.contactapp.view;
}