package isen.contactapp.view;

import java.io.IOException;

import isen.contactapp.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainLayoutController {
    //@FXML
    //private Label welcomeText;

    //@FXML
    //protected void onHelloButtonClick() {
    //    welcomeText.setText("Welcome to JavaFX Application!");
    //}
	
	
	@FXML
	private Button viewallBtn;
	
	@FXML
	public void viewall() throws IOException {
		
	try {
		App.setRoot("ContactsListingView");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}