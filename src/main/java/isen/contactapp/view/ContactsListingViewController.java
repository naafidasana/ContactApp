package isen.contactapp.view;


import java.util.ArrayList;
import java.util.List;

import isen.contactapp.App;
import isen.contactapp.database.PersonDao;
import isen.contactapp.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ContactsListingViewController{

    @FXML
    private ListView<String> myListView; // Assuming this is the VBox where you want to add labels
    
    @FXML
    private Label labelDetail;
    
    @FXML
    private Button detailButton;
    
   
  
    @FXML
    public void initialize() {
    	

        PersonDao personDao = new PersonDao();
       
        List<Person> d = personDao.fetchAllPersons();
        
        List<String> myArrList = new ArrayList<>();
    	
        if(d.isEmpty()) {
        	return ;
        }else {
        for(int i = 0; i < d.size(); i++) {
            myArrList.add(d.get(i).getFirstName());
        }
        
        // Initialize the labelsContainer
        myListView.getItems().addAll(myArrList);
        labelDetail.setText(myArrList.get(0));
        
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                // Update labelDetail when an item is selected
                labelDetail.setText(myListView.getSelectionModel().getSelectedItem());
                
                // Update DetailViewData when an item is selected
                String selectedItem = myListView.getSelectionModel().getSelectedItem();
                for (Person person : d) {
                    if (person.getFirstName().equals(selectedItem)) {
                        App.setDetailViewData(person);
                        break;
                    }
                }
            }
        });
    }
    }
    @FXML
    public void handleButtonClick() {
        try {
            // Get the selected item from the list view
            
            // Pass the selected item to the next view
            
            
            // Navigate to the next view
            App.setRoot("DetailView");
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

   
    
}
