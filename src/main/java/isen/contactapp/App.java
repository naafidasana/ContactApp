package isen.contactapp;

import isen.contactapp.database.DataSourceFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import isen.contactapp.model.Person;


public class App extends Application {

    private static Scene scene;
    
    private static Person detailViewData;

    private static BorderPane mainLayout;

    @Override
    public void init() throws Exception {
        // Initialization logic that does not involve UI elements.
        // We create 'person' table if it does not exist already.
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
             try (Statement statement = connection.createStatement()) {

                 // Create `person` table if it does not exist already.
                 statement.executeUpdate("CREATE TABLE IF NOT EXISTS person (\r\n"
                         + "idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \r\n"
                         + "lastname VARCHAR(45) NOT NULL,  \r\n"
                         + "firstname VARCHAR(45) NOT NULL,\r\n"
                         + "nickname VARCHAR(45) NOT NULL,\r\n"
                         + "phone_number VARCHAR(15) NULL,\r\n"
                         + "address VARCHAR(200) NULL,\r\n"
                         + "email_address VARCHAR(150) NULL,\r\n"
                         + "birth_date DATE NULL);");

                 // Delete previously inserted data (from other tests most likely) from data
                 statement.executeUpdate("DELETE FROM person");

                 // Insert 3 Persons into table. We only insert the non-null values for now.
                 statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname) VALUES (1, 'IBRAHIM', 'Naafi', 'Prof')");
                 statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname) VALUES (2, 'KUMAH', 'Emmanuel', 'EasyBlend')");
                 statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname) VALUES (3, 'SAAD', 'Mohammad', 'Mo')");
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        mainLayout = loadFXML("MainLayout");
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static <T> T loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/contactapp/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void showView(String rootElement) {
        try {
            mainLayout.setCenter(loadFXML(rootElement));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
    
    
    // Getter for detailViewData
    @SuppressWarnings("exports")
	public static Person getDetailViewData() {
        return detailViewData;
    }

    // Setter for detailViewData
    public static void setDetailViewData(@SuppressWarnings("exports") Person data) {
        detailViewData = data;
    }

}