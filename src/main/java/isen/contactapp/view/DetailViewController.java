package isen.contactapp.view;

import isen.contactapp.App;
import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;

public class DetailViewController {

    PersonDao personDao = new PersonDao();
	
	@FXML
	private TextField firstNameTxt,lastNameTxt,phoneTxt,emailAddressTxt,NickTxt, countryTxt, cityTxt,streetAddressTxt,zipCodeTxt;

    @FXML
    private DatePicker dobPicker;
	
	@FXML
	private Button returnBtn,deleteBtn1, updateBtn;

    private boolean isModified = false;
	
	public void initialize() {
		
		try {
			firstNameTxt.setText(App.getDetailViewData().getFirstName());
			lastNameTxt.setText(App.getDetailViewData().getLastName());
			emailAddressTxt.setText(App.getDetailViewData().getEmailAddress());
			phoneTxt.setText(App.getDetailViewData().getPhoneNumber());
			NickTxt.setText(App.getDetailViewData().getNickname());
            dobPicker.setValue(App.getDetailViewData().getDateOfBirth());

            // Get and split address string into respective fields
            String fullAddress = App.getDetailViewData().getAddress();
            String[] addressComponents = fullAddress != null ? fullAddress.split("\n") : new String[]{"", "", "", ""};
            // street address, zip code, city, country
            streetAddressTxt.setText(addressComponents[0]);
            zipCodeTxt.setText(addressComponents[1]);
            cityTxt.setText(addressComponents[2]);
            countryTxt.setText(addressComponents[3]);


			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
        // Add change listeners to text fields
        firstNameTxt.textProperty().addListener(new TextFieldChangeListener());
        lastNameTxt.textProperty().addListener(new TextFieldChangeListener());
        emailAddressTxt.textProperty().addListener(new TextFieldChangeListener());
        phoneTxt.textProperty().addListener(new TextFieldChangeListener());
        NickTxt.textProperty().addListener(new TextFieldChangeListener());
        countryTxt.textProperty().addListener(new TextFieldChangeListener());
        cityTxt.textProperty().addListener(new TextFieldChangeListener());
        streetAddressTxt.textProperty().addListener(new TextFieldChangeListener());
        zipCodeTxt.textProperty().addListener(new TextFieldChangeListener());
        dobPicker.valueProperty().addListener(new DateFieldChangeListener());

		
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
				 Integer rowsDeleted = personDao.deletePerson(App.getDetailViewData());
			}
		App.setRoot("ContactsListingView");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

    public void handleUpdateBtn() {
        // Retrieve modified data from the text fields
        String newFirstName = firstNameTxt.getText();
        String newLastName = lastNameTxt.getText();
        String newEmail = emailAddressTxt.getText();
        String newPhone = phoneTxt.getText();
        String newNickName = NickTxt.getText();
        String newCountry = countryTxt.getText();
        String newCity = cityTxt.getText();
        String newStreet = streetAddressTxt.getText();
        String newZip = zipCodeTxt.getText();
        LocalDate newDateOfBirth = dobPicker.getValue();
        

        // Get the Person object to update
        Person personToUpdate = App.getDetailViewData();

        // Update the Person object with the modified data
        personToUpdate.setFirstName(newFirstName);
        personToUpdate.setLastName(newLastName);
        personToUpdate.setEmailAddress(newEmail);
        personToUpdate.setPhoneNumber(newPhone);
        personToUpdate.setNickname(newNickName);

        // Concatenate address components into string before setting.
        String address = newStreet + "\n" + newZip + "\n" + newCity + "\n" + newCountry;
        personToUpdate.setAddress(address);
        personToUpdate.setDateOfBirth(newDateOfBirth);
        

        try {
            // Update the Person in the database
            Person updatedPerson = personDao.updatePerson(personToUpdate, personToUpdate.getId());
            
            // Display a success message
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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

    // Listener to detect changes in date picker
    private class DateFieldChangeListener implements  ChangeListener<LocalDate> {
        @Override
        public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate oldValue, LocalDate newValue) {
            isModified=true;
            updateBtn.setDisable(false);
        }
    }

}
