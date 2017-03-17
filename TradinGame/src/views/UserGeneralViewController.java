package views;

import application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class UserGeneralViewController {
	
	private Main mainApp;


	private Stage stage;
	
	@FXML
	private ComboBox sortingChoiceBox = new ComboBox(FXCollections.observableArrayList("A - Z", "Date (ASC)", "Date (DESC)", "Game", "Provider"));
	

    
	/**
	 * Initializes the controller
	 */
    @FXML
    private void initialize() {

    }

    @FXML
    private void handleServiceDetailButton(){

//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//        alert.initOwner(stage);
//        alert.setTitle("Service details");
//        alert.showAndWait();

        mainApp.showServiceDetailView();
    }
    
    @FXML
    private void handleMyServiceDetailButton(){
    	mainApp.showMyServiceDetailView();
    }
    
    @FXML
    private void handleUnbookButton(){
    	Alert unbookAlert = new Alert(Alert.AlertType.CONFIRMATION);
    	unbookAlert.initOwner(stage);
    	unbookAlert.setTitle("Are you sure to unbook this service ?");
    	unbookAlert.showAndWait();
    	
    	//TODO: call the delete method on the ServiceFacade.
    }
    

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

}
