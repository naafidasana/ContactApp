package isen.contactapp.view;

import java.io.IOException;

import isen.contactapp.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * The MainLayoutController class controls the main layout of the application.
 */
public class MainLayoutController {
	
	@FXML
	private Button viewallBtn;

	/**
	 * Handles the event when the "View All" button is clicked.
	 * Navigates to the ContactsListingView.
	 *
	 * @throws IOException if an error occurs while loading the FXML file.
	 */
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