package isen.contactapp.view;

import isen.contactapp.App;
import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Person;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class AddContactController {
	
	@FXML
	private TextField FnameTxt,LnameTxt,emailTxt,phoneTxt,addressTxt,NickTxt;
	
	

	public void HandleAddContactBtn() {
		// TODO Auto-generated constructor stub
		String Fname = FnameTxt.getText();
		String Lname = LnameTxt.getText();
		String phone = phoneTxt.getText();
		String email = emailTxt.getText();		
		String nick = NickTxt.getText();		
		
		if(Fname.isEmpty() && Lname.isEmpty() && phone.isEmpty()) {
			try {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Fill the contact data First", ButtonType.OK);
				alert.showAndWait();
				if (alert.getResult() == ButtonType.OK) {
				    alert.close();
				}
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}		}else {
				
				try {
					Person contact = new Person();
					contact.setFirstName(Fname);
					contact.setLastName(Lname);
					contact.setPhoneNumber(phone);
					contact.setNickname(nick);
					contact.setEmailAddress(email);
					Person newContact = new PersonDao().addPerson(contact);
					
					App.setRoot("MainLayout");
					
					System.out.print("Name"+contact);
					
					Alert alert = new Alert(AlertType.CONFIRMATION, newContact  + " has been added", ButtonType.FINISH);
					alert.showAndWait();

					if (alert.getResult() == ButtonType.FINISH) {
					    //do stuff
						Stage stage = (Stage) ((Node) phoneTxt).getScene().getWindow();
			            stage.close();
					}
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
			}
	}
	

}
