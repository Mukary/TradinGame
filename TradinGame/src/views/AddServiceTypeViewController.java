package views;

import models.ServiceType;
import util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;
import facades.ServiceTypeFacade;


public class AddServiceTypeViewController extends ViewController{
	
	private ServiceTypeFacade serviceTypeFacade;
	
	@FXML
	private TextField labelServiceType;
	@FXML
	private TextArea textAreaServiceTypeDescription;
	
	
	/**
	 * Handles the create service type button
	 */
	@FXML
	private void handleCreateServiceTypeButton(){
		ServiceType serviceType = new ServiceType(labelServiceType.getText(), textAreaServiceTypeDescription.getText());
		try{
			int res = 0;
			serviceTypeFacade = ServiceTypeFacade.getInstance();
			res = serviceTypeFacade.createServiceType(serviceType);
			if(res == 1){
				AdminGeneralViewController.serviceTypesList.add(serviceType);
				Util.displayAlert(AlertType.CONFIRMATION, "Service type added", "The game has been added !");
				stage.close();
			}
			else {
				Util.displayAlert(AlertType.ERROR, "Error", "It seems the database has a problem. Try again later.");
			}
		} catch (SQLException sql){
			sql.printStackTrace();
		}
		
		
	}
	
	/**
	 * Handles the cancel service type button
	 */
	@FXML
	private void handleCancelServiceTypeButton(){
		stage.close();
	}
}
