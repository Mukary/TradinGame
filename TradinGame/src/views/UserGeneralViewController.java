package views;

import application.Main;
import facades.UserFacade;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class UserGeneralViewController {
	
	private Main mainApp;
	
	@FXML
	private ComboBox sortingChoiceBox = new ComboBox(FXCollections.observableArrayList("A - Z", "Date (ASC)", "Date (DESC)", "Game", "Provider"));
	
	public UserGeneralViewController() {
		
    }
    
    @FXML
    private void initialize() {
    	sortingChoiceBox.getItems().removeAll(sortingChoiceBox.getItems());
        sortingChoiceBox.getItems().addAll("Option A", "Option B", "Option C");
    	sortingChoiceBox.setPromptText("test");
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
