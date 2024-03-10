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


/**
 * The App class serves as the entry point for the Contact App application.
 */
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
                         + "birth_date DATE  NULL,\r\n"
                         +"City	VARCHAR(50) NULL,\r\n"
                     	+"Zip_Code	INTEGER NULL,\r\n"
                    	+"Street	VARCHAR(100));");
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the primary stage of the application.
     *
     * @param stage The primary stage.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        mainLayout = loadFXML("MainLayout");
        scene = new Scene(mainLayout);
        stage.setTitle("Contact App");
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
       
    }

    /**
     * The main method, serves as the entry point of the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets the root FXML file for the scene.
     *
     * @param fxml The name of the FXML file.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads the FXML file and returns the loaded object.
     *
     * @param fxml The name of the FXML file.
     * @param <T>  The type of the loaded object.
     * @return The loaded object.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    public static <T> T loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/contactapp/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Displays the specified view in the main layout.
     *
     * @param rootElement The FXML file representing the view.
     */
    public static void showView(String rootElement) {
        try {
            mainLayout.setCenter(loadFXML(rootElement));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Retrieves the detail view data.
     *
     * @return The detail view data.
     */
    // Getter for detailViewData
    @SuppressWarnings("exports")
	public static Person getDetailViewData() {
        return detailViewData;
    }

    // Setter for detailViewData
    /**
     * Sets the detail view data.
     *
     * @param data The detail view data to set.
     */
    public static void setDetailViewData(@SuppressWarnings("exports") Person data) {
        detailViewData = data;
    }

}