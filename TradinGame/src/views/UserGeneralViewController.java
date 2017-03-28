package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import application.Main;
import facades.ServiceFacade;
import facades.UserFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Service;

public class UserGeneralViewController extends ViewController{
	
	@FXML
	private ComboBox sortingChoiceBox = new ComboBox(FXCollections.observableArrayList("A - Z", "Date (ASC)", "Date (DESC)", "Game", "Provider"));
	@FXML
	private TableView<Service> services;
	@FXML
    private TableColumn<Service, String> descriptionServiceColumn;
    @FXML
    private TableColumn<Service, String> providerNameColumn;
    @FXML
    private TableColumn<Service, String> gameNameColumn;
    @FXML
    private TableColumn<Service, String> serviceTypeColumn;

	public static ObservableList<Service> servicesList;

	@FXML
	private TableView<Service> myServices;
    @FXML
	private TableColumn<Service, String> descriptionMyServicesColumn;
    @FXML
	private TableColumn<Service, String> gameMyServicesColumn;
    @FXML
	private TableColumn<Service, String> providerMyServicesColumn;
	
	private ServiceFacade serviceFacade;

	public static ObservableList<Service> myServicesList;

	
	private Service selectedService;
	private Service selectedMyService;

    
	/**
	 * Initializes the controller
	 */
    @FXML
    private void initialize() {
    	selectedService = null;
    	serviceFacade = ServiceFacade.getInstance();

    	// Services list
    	try {
			servicesList = FXCollections.observableList(serviceFacade.getUnbookedServices());
			services.setItems(servicesList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.initializeServicesListTable();

    	// My Services list
		try {
			myServicesList = FXCollections.observableList(serviceFacade.getAllServicesByUser(UserFacade.userLogged));
			myServices.setItems(myServicesList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.initializeMyServicesListTable();

    }
    
    @FXML
    private void handleLogoutButton(){
    	UserFacade.userLogged = null;
    	mainApp.showLoginView();
    }
    
    @FXML
    private void handleServiceDetailButton(){
        mainApp.showServiceDetailView(selectedService);
    }
    
    @FXML
    private void handleMyServiceDetailButton(){
    	mainApp.showServiceDetailView(selectedMyService);
    }
    
    @FXML
    private void handleDeleteButton(){
    	Alert unbookAlert = new Alert(Alert.AlertType.CONFIRMATION);
    	unbookAlert.initOwner(stage);
    	unbookAlert.setTitle("Are you sure to delete this service ? ");
		Optional<ButtonType> result = unbookAlert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				serviceFacade.deleteService(selectedMyService);
				UserGeneralViewController.myServicesList.remove(selectedMyService);
				// Should refresh services table
				UserGeneralViewController.servicesList.remove(selectedMyService);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

    	//TODO: call the delete method on the ServiceFacade.
    }
    
    @FXML
    private void addNewServiceButton(){
    	try {
			mainApp.showAddServiceDialogView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void initializeServicesListTable(){
		descriptionServiceColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		providerNameColumn.setCellValueFactory(cellData -> cellData.getValue().sellerNicknameProperty());
		gameNameColumn.setCellValueFactory(cellData -> cellData.getValue().gameNameProperty());
		serviceTypeColumn.setCellValueFactory(cellData -> cellData.getValue().serviceTypeLabelProperty());
		services.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectedService = newValue);
	}

	private void initializeMyServicesListTable(){
		descriptionMyServicesColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		gameMyServicesColumn.setCellValueFactory(cellData -> cellData.getValue().gameNameProperty());
		providerMyServicesColumn.setCellValueFactory(cellData -> cellData.getValue().sellerNicknameProperty());
		myServices.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectedMyService = newValue);
	}


    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    /*public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        
    }*/

}
