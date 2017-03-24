package views;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import facades.GameFacade;
import facades.ServiceTypeFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import models.Game;
import models.ServiceType;
import javafx.scene.control.Alert.AlertType;

public class AddServiceViewController extends ViewController{
	
	@FXML
	private TextArea descriptionArea;	
	@FXML
	private ComboBox gameBox;
	@FXML
	private ComboBox serviceTypeBox;
	@FXML
	private DatePicker datePicker;
	
	private ArrayList<Game> proposableGames;
	private ArrayList<ServiceType> proposableServiceTypes;
	private GameFacade gameFacade;
	private ServiceTypeFacade serviceTypeFacade;
	
	@FXML
	private void initialize(){
		gameFacade = GameFacade.getInstance();
		serviceTypeFacade = ServiceTypeFacade.getInstance();
		try {
			proposableGames = gameFacade.getAllGames();
			proposableServiceTypes = serviceTypeFacade.getAllServiceTypes();
			//proposableServiceTypes = 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleAddServiceButton(){
		Alert alert;
		if(!validInputs()){
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
	        alert.initOwner(stage);
	        alert.setTitle("Error");
	        alert.setContentText("Please fill the required fields");
	        alert.showAndWait();
		}
		else if(pickedDateBeforeNow()){
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
	        alert.initOwner(stage);
	        alert.setTitle("Error");
	        alert.setContentText("Please pick a date after the current date");
	        alert.showAndWait();
		}
		else{
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
	        alert.initOwner(stage);
	        alert.setTitle("Service added");
	        alert.setContentText("Your service has been well added");
	        alert.showAndWait();
		}
			
		
	}
	
	@FXML
	private void handleCancelButton(){
		stage.close();
	}

	private boolean validInputs(){	
		LocalDate date = datePicker.getValue();
		if(descriptionArea.getText().length() == 0 || date == null)
			return false;
		return true;
	}
	
	private boolean pickedDateBeforeNow(){
		LocalDate now = LocalDate.now();
		return now.isAfter(datePicker.getValue());
	}
}
