package isen.contactapp.view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Person;

public class ContactsListingViewController{

    @FXML
    private ListView<String> myListView; // Assuming this is the VBox where you want to add labels
    
    @FXML
    private Label labelDetail;
    
    private PersonDao personDao = new PersonDao();
   
    List<Person> d = personDao.fetchAllPersons();
    
    List<String> myArrList = new ArrayList<>();
   
  
    @FXML
    public void initialize() {
    	
    	 
    	 for(int i=0;i<d.size();i++) {
    		 myArrList.add(d.get(i).getFirstName()+"  "+d.get(i).getLastName());
    		}
        // Initialize the labelsContainer
       myListView.getItems().addAll(myArrList);
       labelDetail.setText(myArrList.get(0));
       
       myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    	   @Override
    	   public void  changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
    		   labelDetail.setText(myListView.getSelectionModel().getSelectedItem());
    	   }
	});
    }

   
    
}
