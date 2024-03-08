package isen.contactapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import isen.contactapp.model.Person;


public class App extends Application {

    private static Scene scene;
    
    private static Person detailViewData;


    private static BorderPane mainLayout;
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