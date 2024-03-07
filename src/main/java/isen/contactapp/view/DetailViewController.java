package isen.contactapp.view;

import isen.contactapp.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DetailViewController {

	
	@FXML
	private TextField firstNameTxt,lastNameTxt,phoneTxt,emailTxt,nickTxt;
	
	@FXML
	private Button returnBtn;
	
	@FXML
	private Label title;



	

	@FXML
	public void initialize() {
		System.out.println("Selected item: " +App.getDetailViewData());
		
		title.setText(App.getDetailViewData().getFirstName()+" - "+App.getDetailViewData().getLastName());
		
		// TODO Auto-generated constructor stub
		try {
			firstNameTxt.setText(App.getDetailViewData().getFirstName());
			
			lastNameTxt.setText(App.getDetailViewData().getLastName());
			emailTxt.setText(App.getDetailViewData().getEmailAddress());
			phoneTxt.setText(App.getDetailViewData().getPhoneNumber());
			nickTxt.setText(App.getDetailViewData().getNickname());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void handleReturnBtn() {
		try {
//			App.setRoot("ContactsListingView");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
