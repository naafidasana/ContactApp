package isen.contactapp.view;

import isen.contactapp.App;
import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Address;
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
	private TextField FnameTxt,LnameTxt,emailTxt,phoneTxt,adressTxt1,NickTxt,adressTxt2,adressTxt3;
	
	

	public void HandleAddContactBtn() {
		// TODO Auto-generated constructor stub
		String Fname = FnameTxt.getText();
		String Lname = LnameTxt.getText();
		String phone = phoneTxt.getText();
		String email = emailTxt.getText();		
		String nick = NickTxt.getText();
		String city = adressTxt1.getText();
		String street = adressTxt2.getText();
		String zipCode = adressTxt3.getText();
		
		if(Fname.isEmpty() && Lname.isEmpty() && phone.isEmpty()) {
			try {
				Alert alert = new Alert(AlertType.ERROR, "Fill the contact data First", ButtonType.OK);
				alert.setHeaderText("MISSING FIELDS");
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
					contact.setAddress(new Address(city, street, zipCode));
					
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
