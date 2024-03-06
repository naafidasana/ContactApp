module isen.contactapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;


    opens isen.contactapp to javafx.fxml;
    exports isen.contactapp;
}