package views;

import facades.UserFacade;
import facades.ServiceFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import models.Service;
import models.User;
import util.Util;

import java.sql.SQLException;
import java.util.Optional;

public class ServiceDetailViewController extends ViewController{
	
	@FXML
	private Label descriptionLabel;
	@FXML
	private TextArea descriptionArea;
	@FXML
	private Button bookButton;
	@FXML 
	private Button reportServiceButton;
	@FXML
	private Button unbookButton;
	
	private Service service;
	
	@FXML
	public void initialize(){
		
	}
	
	
	
	public void setService(Service service){
		this.service = service;
		descriptionLabel.setText("This service is proposed by "+service.getSellerNickname()+".\n It concerns "+service.getGameName()+".The theme of this service is : "+service.getServiceTypeLabel());
		descriptionArea.setText(service.getDescription());
		if (UserFacade.userLogged.isIsAdmin()) {
			bookButton.setVisible(false);
			reportServiceButton.setVisible(false);
		} else {
			if (UserFacade.userLogged.getNickname().equals(service.getSellerNickname())) {
				bookButton.setVisible(false);
				unbookButton.setVisible(false);
			} else {
				if (UserFacade.userLogged.getNickname().equals(service.getConsumerNickname())) {
					bookButton.setVisible(false);
					unbookButton.setVisible(true);
				} else {
					bookButton.setVisible(true);
					unbookButton.setVisible(false);
				}
			}
			reportServiceButton.setVisible(true);
		}
	}
	
	@FXML
	private void handleBookButton(){
		UserFacade userFacade = UserFacade.getInstance();
		ServiceFacade serviceFacade = ServiceFacade.getInstance();
		User user = UserFacade.userLogged;
		this.service.setConsumerNickname(user.getNickname());
		try {
			serviceFacade.bookService(service);
			UserGeneralViewController.myServicesList.add(this.service);
			bookButton.setVisible(false);
			unbookButton.setVisible(true);
			util.Util.displayAlert(AlertType.INFORMATION, "Success", "Service booked");
		

		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleUnbookButton(){
		ServiceFacade serviceFacade = ServiceFacade.getInstance();
		this.service.setConsumerNickname(null);
		try {
			serviceFacade.bookService(service);
			UserGeneralViewController.myServicesList.remove(this.service);
			bookButton.setVisible(true);
			unbookButton.setVisible(false);
			util.Util.displayAlert(AlertType.INFORMATION, "Success", "Service unbooked");

		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleDeleteButton(){
		ServiceFacade serviceFacade = ServiceFacade.getInstance();
		Alert unbookAlert = new Alert(Alert.AlertType.CONFIRMATION);
		unbookAlert.initOwner(stage);
		unbookAlert.setTitle("Are you sure to delete this service ? ");
		Optional<ButtonType> result = unbookAlert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				serviceFacade.deleteService(service);
				AdminGeneralViewController.reportsList.remove(service);
				stage.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
