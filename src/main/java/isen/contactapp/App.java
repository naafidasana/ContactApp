package isen.contactapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    private static Scene scene;

    private static BorderPane mainLayout;
    @Override
    ///WHAT IS THIS ????
//    public void start(Stage stage) throws IOException {
//        stage.setTitle("Contacts");
//        mainLayout = loadFXML("MainLayout");
//        scene = new Scene(mainLayout, 640, 480);
//        stage.setScene(scene);
//        stage.show();
//    }
    
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/isen/contactapp/view/MainLayout"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    //Added the set root. why do you create a mainapp class without the setroot function???????????????????????????
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    
    //Why do you have a list return type for the loadFFXML function!!!!!!!!! WHYYYY
//    public static <T> T loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/contactapp/view/" + fxml + ".fxml"));
//        return fxmlLoader.load();
//    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
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