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

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

}
