package views;

import facades.UserFacade;
import facades.ServiceFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import models.Service;
import models.User;

import java.sql.SQLException;

public class ServiceDetailViewController extends ViewController{
	
	@FXML
	private Label descriptionLabel;
	@FXML
	private TextArea descriptionArea;

	@FXML
	private Button bookButton;

	private Service service;
	
	public void initialiaze(){
		System.out.println("intialize");

		// bookButton should be hidden when coming from my services
		//bookButton.setVisible(false);
	}
	
	public void setService(Service service){
		this.service = service;
		descriptionLabel.setText("This service is proposed by "+service.getSellerNickname()+".\n It concerns "+service.getGameName()+".The theme of this service is : "+service.getServiceTypeLabel());
		descriptionArea.setText(service.getDescription());
	}
	
	@FXML
	private void handleBookButton(){
		UserFacade userFacade = UserFacade.getInstance();
		ServiceFacade serviceFacade = ServiceFacade.getInstance();
		User user = UserFacade.userLogged;
		this.service.setConsumerNickname(user.getNickname());
		try {

			serviceFacade.bookService(service);

		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleReportButton(){
		
	}
	
	@FXML
	private void handleCancelButton(){
		stage.close();
	}

}
