package views;

import java.io.IOException;
import java.sql.SQLException;

import application.Main;
import facades.ServiceFacade;
import facades.UserFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    
	/**
	 * Initializes the controller
	 */
    @FXML
    private void initialize() {
    	selectedService = null;
    	serviceFacade = ServiceFacade.getInstance();

    	// Services list
    	try {
			servicesList = FXCollections.observableList(serviceFacade.getAllServices());
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
    private void handleServiceDetailButton(){
        mainApp.showServiceDetailView(selectedService);
    }
    
    @FXML
    private void handleMyServiceDetailButton(){
    	mainApp.showMyServiceDetailView();
    }
    
    @FXML
    private void handleUnbookButton(){
    	Alert unbookAlert = new Alert(Alert.AlertType.CONFIRMATION);
    	unbookAlert.initOwner(stage);
    	unbookAlert.setTitle("Are you sure to unbook this service ?");
    	unbookAlert.showAndWait();
    	
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
