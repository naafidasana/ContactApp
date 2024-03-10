package isen.contactapp.view;


import java.util.ArrayList;
import java.util.List;

import isen.contactapp.App;
import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ContactsListingViewController{

    @FXML
    private ListView<String> myListView; // Vbox in which we'll be adding the labels.
    
    @FXML
    private Label labelDetail;
    
    @FXML
    private Button detailButton, addButton, homeButton;

    PersonDao personDao = new PersonDao();
    
    List<Person> d = personDao.fetchAllPersons();

    List<String> myArrList = new ArrayList<>();
    
    Boolean selected = false;
    
    public void setList() {
        if(d.isEmpty()) {
        	return ;
        }else {
        for(int i = 0; i < d.size(); i++) {
            myArrList.add(d.get(i).getFirstName()+" "+d.get(i).getLastName());
        }
        
        // Initialize the labelsContainer
        
        	 myListView.getItems().addAll(myArrList);
             labelDetail.setText(myArrList.get(0));
             myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                 @Override
                 public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                     // Update labelDetail when an item is selected
                     labelDetail.setText(myListView.getSelectionModel().getSelectedItem());
                     selected = true;
                     // Update DetailViewData when an item is selected
                     String selectedItem = myListView.getSelectionModel().getSelectedItem();
                     for (Person person : d) {
                         if (person.getFullName().equals(selectedItem)) {
                             App.setDetailViewData(person);
                             break;
                         }
                     }
                 }
             });
         
         }
    }
  
   @FXML
   public void initialize() {
       if(d.isEmpty()) {
           labelDetail.setText("You have No Contacts!");
           detailButton.setVisible(false);
       } else {
           setList();
       }
   }

   public void handleAddButtonClick() {
		   try {
			   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddContact.fxml"));
		   	Parent root1 = (Parent)fxmlLoader.load();
		   	Stage stages = new Stage();
		   	stages.setTitle("All Contacts");
		   	stages.setScene(new Scene(root1));
		   	stages.show();
		   } catch (Exception e) {
		   	// TODO: handle exception
		   	e.printStackTrace();
		   }
   }

    public void handleButtonClick() {
        try {
           if(selected)
            // Navigate to the next view
            App.setRoot("DetailView");
           else 
           {Alert alert = new Alert(AlertType.ERROR, "Select a contact to view Details", ButtonType.OK);
			alert.setHeaderText("No contact selected!");
			alert.showAndWait();}
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

    public void handleHomeButtonClick() {
    	try {
			App.setRoot("MainLayout");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
}
