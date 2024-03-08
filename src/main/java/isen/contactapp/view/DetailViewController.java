package isen.contactapp.view;

import isen.contactapp.App;
import isen.contactapp.database.PersonDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailViewController {

	
	@FXML
	private TextField firstNameTxt,lastNameTxt,phoneTxt,emailTxt,nickTxt;
	
	@FXML
	private Button returnBtn,deleteBtn1;
	
	@FXML
	private Label title;



	

	
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
	

	public void handleReturnBtn() {
		try {
			App.setRoot("ContactsListingView");
//			Stage stages = new Stage();
//			Parent root = FXMLLoader.load(getClass().getResource("/isen/contactapp/ContactsListingView.fxml"));
//			stages.setTitle("All Contacts");
//			stages.setScene(new Scene(root));
//			stages.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	

	public void handleDeleteBtn() {
		try {
			int result = new PersonDao().deletePerson(App.getDetailViewData());
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + App.getDetailViewData().getFirstName() + " ?", ButtonType.YES, ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
			    //do stuff
			}
		App.setRoot("ContactsListingView");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
