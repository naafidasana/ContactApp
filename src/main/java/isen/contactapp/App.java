package isen.contactapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    private static BorderPane mainLayout;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Contacts");
        mainLayout = loadFXML("MainLayout");
        scene = new Scene(mainLayout, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
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
}