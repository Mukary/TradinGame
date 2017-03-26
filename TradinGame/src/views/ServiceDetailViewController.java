package views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import models.Service;

public class ServiceDetailViewController extends ViewController{
	
	@FXML
	private Label descriptionLabel;
	@FXML
	private TextArea descriptionArea;

	private Service service;
	
	public void initialiaze(){
		
	}
	
	public void setService(Service service){
		this.service = service;
		descriptionLabel.setText("This service is proposed by "+service.getSellerNickname()+".\n It concerns "+service.getGameName()+".The theme of this service is : "+service.getServiceTypeLabel());
		descriptionArea.setText(service.getDescription());
	}
	
	@FXML
	private void handleBookButton(){
		
	}
	
	@FXML
	private void handleReportButton(){
		
	}
	
	@FXML
	private void handleCancelButton(){
		stage.close();
	}

}
