package views;


import models.ServiceType;
import util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.property.SimpleStringProperty;
import java.sql.SQLException;
import facades.ServiceTypeFacade;

public class EditServiceTypeViewController extends ViewController{
	
	private ServiceType serviceType;
	private ServiceTypeFacade serviceTypeFacade;
	
	public void setServiceType(ServiceType serviceType){
		this.serviceType = serviceType;
	}
	
	@FXML
	private TextField editSTLabel;
	@FXML
	private TextArea editSTDescription;
	
	/**
	 * Handles the save service type button
	 */
	@FXML
	private void handleSaveServiceTypeButton(){
		serviceType.setDescription(new SimpleStringProperty(editSTDescription.getText()));

		try{
			int res = 0;
			serviceTypeFacade = ServiceTypeFacade.getInstance();
			res = serviceTypeFacade.updateServiceType(serviceType);
			if(res == 1){
				AdminGeneralViewController.serviceTypesList.add(serviceType);
				Util.displayAlert(AlertType.INFORMATION, "Service type updated", "The service type has been updated !");
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
	 * Cancel action handler
	 */
	@FXML
	private void handleCancelButton(){
		stage.close();
	}
}
