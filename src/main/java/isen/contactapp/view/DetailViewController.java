package isen.contactapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DetailViewController {

	
	@FXML
	private TextField firstNameTxt,lastNameTxt,phoneTxt,emailTxt;
	

String firstName;

	

	@FXML
	public void initialize() {
		// TODO Auto-generated constructor stub
		
		firstNameTxt.setText(firstName);
		
	}

}
