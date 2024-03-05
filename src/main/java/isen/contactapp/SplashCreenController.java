package isen.contactapp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import isen.contactapp.HelloApplication;

public class SplashCreenController {

	@FXML
	private Button viewAllButton;
	
	@FXML
	public void handleSplashCreen() {
		// TODO Auto-generated constructor stub
		try {	
			HelloApplication.setRoot("/isen/contactapp/view/view-all-contact");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
