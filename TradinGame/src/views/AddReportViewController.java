package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import application.Main;
import facades.ReportFacade;
import facades.ServiceFacade;
import facades.UserFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Report;
import models.Service;

public class AddReportViewController extends ViewController {
	
	private Service reportedService;
	private ReportFacade reportFacade;
	
	@FXML
	private TextField topicTextField;
	@FXML
	private TextArea descriptionTextArea;
		
	@FXML
	private void initialize(){
	}
	
	public AddReportViewController(){
		reportFacade = ReportFacade.getInstance();
		
	}
	
	public void setReportedService(Service service){
		this.reportedService = service;
	}
	
	@FXML
	private void handleSendButton(){		
		Report report = new Report(1, topicTextField.getText(), descriptionTextArea.getText(), reportedService.getIdService(), UserFacade.userLogged.getNickname());
		try {
			reportFacade.insertReport(report);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Give the topic and description text to the facade + the reported service
		
	}
	
	@FXML
	private void handleCancelButton(){
		stage.close();
	}
	
	
	
	
}
