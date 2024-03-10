package isen.contactapp.view;

import isen.contactapp.App;
import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DetailViewController {

	
	@FXML
	private TextField firstNameTxt,lastNameTxt,phoneTxt,emailTxt,nickTxt, countryTxt, cityTxt,streetTxt,zipTxt;
	
	@FXML
	private Button returnBtn,deleteBtn1, updateBtn;
	
	@FXML
	private Label title;

    private boolean isModified = false;



	

	
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
			countryTxt.setText(App.getDetailViewData().getCountry());
			cityTxt.setText(App.getDetailViewData().getCity());
			streetTxt.setText(App.getDetailViewData().getStreet());
			zipTxt.setText(App.getDetailViewData().getZip());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
        // Add change listeners to text fields
        firstNameTxt.textProperty().addListener(new TextFieldChangeListener());
        lastNameTxt.textProperty().addListener(new TextFieldChangeListener());
        emailTxt.textProperty().addListener(new TextFieldChangeListener());
        phoneTxt.textProperty().addListener(new TextFieldChangeListener());
        nickTxt.textProperty().addListener(new TextFieldChangeListener());
        countryTxt.textProperty().addListener(new TextFieldChangeListener());
        cityTxt.textProperty().addListener(new TextFieldChangeListener());
        streetTxt.textProperty().addListener(new TextFieldChangeListener());
        zipTxt.textProperty().addListener(new TextFieldChangeListener());

		
        // Disable update button initially
        updateBtn.setDisable(true);
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
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + App.getDetailViewData().getFirstName() + " ?", ButtonType.YES, ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
			    //do stuff
				 new PersonDao().deletePerson(App.getDetailViewData());
			}
		App.setRoot("ContactsListingView");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
//    public void handleUpdateBtn() {
//        // Implement update functionality here
//        System.out.println("Update button clicked");
//    }

    public void handleUpdateBtn() {
        // Retrieve modified data from the text fields
        String newFirstName = firstNameTxt.getText();
        String newLastName = lastNameTxt.getText();
        String newEmail = emailTxt.getText();
        String newPhone = phoneTxt.getText();
        String newNickName = nickTxt.getText();
        String newCountry = countryTxt.getText();
        String newCity = cityTxt.getText();
        String newStreet = streetTxt.getText();
        String newZip = zipTxt.getText();
        

        // Get the Person object to update
        Person personToUpdate = App.getDetailViewData();

        // Update the Person object with the modified data
        personToUpdate.setFirstName(newFirstName);
        personToUpdate.setLastName(newLastName);
        personToUpdate.setEmailAddress(newEmail);
        personToUpdate.setPhoneNumber(newPhone);
        personToUpdate.setNickname(newNickName);
        personToUpdate.setCountry(newCountry);
        personToUpdate.setCity(newCity);
        personToUpdate.setStreet(newStreet);
        personToUpdate.setZip(newZip);
        

        try {
            // Update the Person in the database
            new PersonDao().updatePerson(personToUpdate);
            
            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setHeaderText(null);
            alert.setContentText("Contact details updated successfully.");
            alert.showAndWait();

            // Navigate back to the contacts listing view
            App.setRoot("ContactsListingView");
        } catch (Exception e) {
            // Handle any exceptions or errors
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Failed");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update contact details. Please try again.");
            alert.showAndWait();
        }
    }
    
    // Listener to detect changes in text fields
    private class TextFieldChangeListener implements ChangeListener<String> {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            isModified = true;
            updateBtn.setDisable(false);
        }
    }

}
