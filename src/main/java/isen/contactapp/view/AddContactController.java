package isen.contactapp.view;

import isen.contactapp.App;
import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;

public class AddContactController {
	// Create PersonDao object to add person to database.
	PersonDao personDao = new PersonDao();
	@FXML
	private TextField FnameTxt, LnameTxt, emailTxt, phoneTxt, cityTxt, NickTxt, streetAddressTxt, zipCodeTxt, countryTxt;

	@FXML
	private DatePicker dobPicker;

	public void HandleAddContactBtn() {
		// TODO Auto-generated constructor stub
		String Fname = FnameTxt != null ? FnameTxt.getText() : "";
		String Lname = LnameTxt!= null ? LnameTxt.getText() : "";
		String phone = phoneTxt != null ? phoneTxt.getText() : "";
		String email = emailTxt != null ? emailTxt.getText() : "";
		String nick = NickTxt != null ? NickTxt.getText() : "";
		String city = cityTxt != null ? cityTxt.getText() : "";
		String street = streetAddressTxt != null ? streetAddressTxt.getText() : "";
		String zipCode = zipCodeTxt != null ? zipCodeTxt.getText() : "";
		String country = countryTxt != null ? countryTxt.getText() : "";
		LocalDate dob = dobPicker != null ? dobPicker.getValue() : null;
		
		

		
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
<<<<<<< HEAD
					String contactAddress = street + "\n" + zipCode + "\n" + city + "\n" + country;
=======
					
					
					String contactAddress = String.format(
							"%s\n%s, %s %s",
							street,
							city,
							zipCode,
							country);
>>>>>>> saad-test
					contact.setAddress(contactAddress);
					contact.setDateOfBirth(dob);
					
					Person newContact = personDao.addPerson(contact);
					
					App.setRoot("MainLayout");
					
					System.out.print("Name"+contact);
					
					Alert alert = new Alert(AlertType.CONFIRMATION, newContact  + " has been added", ButtonType.FINISH);
					alert.showAndWait();

					if (alert.getResult() == ButtonType.FINISH) {
					    //do stuff
						Stage stage = (Stage) (phoneTxt).getScene().getWindow();
			            stage.close();
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
	}

}
