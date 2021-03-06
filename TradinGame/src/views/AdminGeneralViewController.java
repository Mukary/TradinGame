package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import facades.GameFacade;
import facades.GameTypeFacade;
import facades.ReportFacade;
import facades.ServiceFacade;
import facades.UserFacade;
import facades.ServiceTypeFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import models.Game;
import models.GameType;
import models.Report;
import models.User;
import models.ServiceType;
import models.Service;

public class AdminGeneralViewController extends ViewController{
	
	@FXML
	private TableView<Game> gamesTableView;
	@FXML
	private TableColumn<Game, String> gameTitleColumn;
	@FXML
	private TableColumn<Game, String> editorColumn;
	@FXML
	private TableColumn<Game, String> genreColumn;
	@FXML
	private TableColumn<Game, String> releaseDateColumn;
	
	@FXML
	private TableView<User> usersTableView;
	@FXML
	private TableColumn<User, String> userNicknameColumn;
	@FXML
	private TableColumn<User, String> userFirstnameColumn;
	@FXML
	private TableColumn<User, String> userLastnameColumn;
	@FXML
	private TableColumn<User, String> userStatusColumn;
	
	@FXML
	private TableView<Report> reportsTable;
	@FXML
	private TableColumn<Report, String> reportTopicColumn;
	@FXML
	private TableColumn<Report, Number> serviceIDColumn;
	@FXML
	private TableColumn<Report, String> authorColumn;
	
	@FXML
	private TableView<Service> servicesTableView;
	@FXML
	private TableColumn<Service, String> serviceTitleColumn;
	@FXML
	private TableColumn<Service, String> serviceProviderColumn;
	@FXML
	private TableColumn<Service, String> serviceGameColumn;	
	
	@FXML
	private TableView<ServiceType> serviceTypesTable;
	@FXML
	private TableColumn<ServiceType, String> serviceTypeLabelColumn;
	@FXML
	private TableColumn<ServiceType, String> serviceTypeDescriptionColumn;
	
	@FXML
	private TableView<GameType> gameTypeTableView;
	@FXML
	private TableColumn<GameType, String> gameTypeNameColumn;
	
	
	
	
	
	public static ObservableList<Game> gamesList;
	public static ObservableList<GameType> gameTypesList;
	public static ObservableList<User> usersList;
	public static ObservableList<Report> reportsList;
	public static ObservableList<ServiceType> serviceTypesList;
	public static ObservableList<Service> serviceList;
	
	private GameFacade gameFacade;
	private GameTypeFacade gameTypeFacade;
	private UserFacade userFacade;
	private ReportFacade reportFacade;
	private ServiceTypeFacade serviceTypeFacade;
	private ServiceFacade serviceFacade;
	
	private Game selectedGame;
	private GameType selectedGameType;
	private User selectedUser;
	private Report selectedReport;
	private ServiceType selectedServiceType;
	private Service selectedService;

	@FXML
	public void initialize(){
		init();
	}
	
	public void init() {
		initialiazeFacades();
		try {
			initializeLists();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initializeGamesTableView();
		initializeUsersTableView();
		initializeReportsTableView();
		initializeServiceTypesTableView();
		initializeServicesTableView();
		initializeGameTypeTableView();
	}
	
    @FXML
    private void handleLogoutButton(){
    	UserFacade.userLogged = null;
    	mainApp.showLoginView();
    }
	
	@FXML
	private void handleAddGameButton(){
		mainApp.showAddGameView();
	}
	
	@FXML
	private void handleAddUserButton(){
		try {
			mainApp.showAddUserDialogView();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles the code triggered by the delete game button
	 */
	@FXML
	private void handleDeleteGameButton(){
		Alert unbookAlert = new Alert(Alert.AlertType.CONFIRMATION);
		unbookAlert.initOwner(stage);
		unbookAlert.setTitle("Are you sure to delete this game ? ");
		Optional<ButtonType> result = unbookAlert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				gameFacade.deleteGame(selectedGame);
				AdminGeneralViewController.gamesList.remove(selectedGame);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Handles the delete report button
	 */
	@FXML
	private void handleDeleteReportButton(){
		Alert deleteReportAlert = new Alert(Alert.AlertType.CONFIRMATION);
		deleteReportAlert.initOwner(stage);
		deleteReportAlert.setTitle("Do you really want to delete this report ?");
		Optional<ButtonType> answer = deleteReportAlert.showAndWait();
		if(answer.get() == ButtonType.OK){
			try {
				reportFacade.deleteReport(selectedReport);
				AdminGeneralViewController.reportsList.remove(selectedReport);
			} catch (SQLException sql){
				sql.printStackTrace();
			}
		}
	}
	
	/**
	 * Handles the add service type button
	 */
	@FXML
	private void handleAddServiceTypeButton(){
		mainApp.showAddServiceTypeView();
	}
	
	/**
	 * Handles the edit service type button
	 */
	@FXML
	private void handleEditServiceTypeButton(){
		mainApp.showEditServiceTypeView(selectedServiceType);
	}
	
	/**
	 * Handles the detail service  button
	 */
	@FXML
	private void handleDetailServiceButton(){
		mainApp.showServiceDetailView(selectedService);
	}
	
	/**
	 * Handles the detail report button
	 */
	@FXML
	private void handleDetailReportButton(){
		Service associatedService;
		try {
			associatedService = serviceFacade.getServiceFromID(selectedReport.getServiceID());
			mainApp.showServiceDetailView(associatedService);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Handles the detail report button
	 */
	@FXML
	private void handleReportServiceDeleteButton(){
		Service associatedService;
		try {
			associatedService = serviceFacade.getServiceFromID(selectedReport.getServiceID());

			Alert deleteServiceTypeAlert = new Alert(Alert.AlertType.CONFIRMATION);
			deleteServiceTypeAlert.initOwner(stage);
			deleteServiceTypeAlert.setContentText("Do you really want to delete the associated ?");
			Optional<ButtonType> answer = deleteServiceTypeAlert.showAndWait();
			if(answer.get() == ButtonType.OK){
				try {
					serviceFacade.deleteService(associatedService);
					this.reportsList = FXCollections.observableList(reportFacade.getAllReports());
				} catch(SQLException sql){
					sql.printStackTrace();
				}
			}
			initializeReportsTableView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Handles the delete service type button
	 */
	@FXML
	private void handleDeleteServiceTypeButton(){
		Alert deleteServiceTypeAlert = new Alert(Alert.AlertType.CONFIRMATION);
		deleteServiceTypeAlert.initOwner(stage);
		deleteServiceTypeAlert.setTitle("Do you really want to delete this service type ?");
		Optional<ButtonType> answer = deleteServiceTypeAlert.showAndWait();
		if(answer.get() == ButtonType.OK){
			try {
				serviceTypeFacade.deleteServiceType(selectedServiceType);
				AdminGeneralViewController.serviceTypesList.remove(selectedServiceType);
			} catch(SQLException sql){
				sql.printStackTrace();
			}
		}
	}
	
    @FXML
    private void handleDeleteServiceButton(){
    	Alert unbookAlert = new Alert(Alert.AlertType.CONFIRMATION);
    	unbookAlert.initOwner(stage);
    	unbookAlert.setTitle("Are you sure to delete this service ? ");
		Optional<ButtonType> result = unbookAlert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				serviceFacade.deleteService(selectedService);
				AdminGeneralViewController.serviceList.remove(selectedService);
				// Should refresh services table
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	//TODO: call the delete method on the ServiceFacade.
    }
	
	/**
	 * Handles the ban  user type button
	 */
	@FXML
	private void handleBanUserButton(){
		Alert banUserAlert = new Alert(Alert.AlertType.CONFIRMATION);
		banUserAlert.initOwner(stage);
		banUserAlert.setTitle("Do you really want to ban this user ?");
		Optional<ButtonType> answer = banUserAlert.showAndWait();
		if(answer.get() == ButtonType.OK){
			selectedUser.setIsBanned(true);
			try {
				userFacade.updateUser(selectedUser);
				usersTableView.refresh();
			} catch(SQLException sql){
				sql.printStackTrace();
			}
		}
	}

	@FXML
	private void handleUnbanUserButton(){
		Alert unbanUserAlert = new Alert(Alert.AlertType.CONFIRMATION);
		unbanUserAlert.initOwner(stage);
		unbanUserAlert.setTitle("Do you really want to unban this user ?");
		Optional<ButtonType> answer = unbanUserAlert.showAndWait();
		if(answer.get() == ButtonType.OK){
			selectedUser.setIsBanned(false);
			try {
				userFacade.updateUser(selectedUser);
				usersTableView.refresh();
			} catch(SQLException sql){
				sql.printStackTrace();
			}
		}
	}

	/**
	 * Handles the delete game type button
	 */
	@FXML
	private void handleDeleteGameTypeButton(){
		Alert deleteGameType = new Alert(Alert.AlertType.CONFIRMATION);
		deleteGameType.initOwner(stage);
		deleteGameType.setTitle("Do you really want to delete this game type ?");
		Optional<ButtonType> answer = deleteGameType.showAndWait();
		if(answer.get() == ButtonType.OK){
			try{
				int res = 0;
				gameTypeFacade.delete(selectedGameType);
				if(res == 1){
					gameTypesList.remove(selectedGameType);
				}
				
			} catch (SQLException sql){
				sql.printStackTrace();
			}
		}
	}
	
	/**
	 * Initialize the games table view
	 */
	private void initializeGamesTableView(){
		gamesTableView.setItems(gamesList);
		gameTitleColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		editorColumn.setCellValueFactory(cellData -> cellData.getValue().editorProperty());
		releaseDateColumn.setCellValueFactory(cellData -> cellData.getValue().yearReleaseProperty());
		genreColumn.setCellValueFactory(cellData -> cellData.getValue().gameTypeLabelProperty());
		gamesTableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectedGame = newValue);
	}
	
	private void initializeReportsTableView(){
		reportsTable.setItems(reportsList);
		reportTopicColumn.setCellValueFactory(cellData -> cellData.getValue().topicProperty());
		serviceIDColumn.setCellValueFactory(cellData -> cellData.getValue().serviceIDProperty());
		authorColumn.setCellValueFactory(cellData -> cellData.getValue().nicknameP());
		
		reportsTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectedReport = newValue);
	}
	
	/**
	 * Initialiazes the different facades
	 */
	private void initialiazeFacades(){
		gameFacade = GameFacade.getInstance();
		gameTypeFacade = GameTypeFacade.getInstance();
		userFacade = UserFacade.getInstance();
		reportFacade = ReportFacade.getInstance();
		serviceTypeFacade = ServiceTypeFacade.getInstance();
		serviceFacade = ServiceFacade.getInstance();
	}
	
	/**
	 * Initializes the different lists used for the table views
	 * @throws SQLException if the queries fail
	 */
	private void initializeLists() throws SQLException{
		gamesList = FXCollections.observableList(gameFacade.getAllGames());
		gameTypesList = FXCollections.observableList(gameTypeFacade.getAll());
		usersList = FXCollections.observableList(userFacade.getAll());
		reportsList = FXCollections.observableList(reportFacade.getAllReports());
		serviceTypesList = FXCollections.observableList(serviceTypeFacade.getAllServiceTypes());
		serviceList = FXCollections.observableList(serviceFacade.getAllServices());
	}
	
	/**
	 * Initializes the users table view
	 */
	private void initializeUsersTableView(){
		usersTableView.setItems(usersList);
		userNicknameColumn.setCellValueFactory(cellData -> cellData.getValue().nicknameProperty());
		userFirstnameColumn.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
		userLastnameColumn.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
		userStatusColumn.setCellValueFactory(cellData -> cellData.getValue().getIsbannedString());
		usersTableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectedUser = newValue);
	}
	
	/**
	 * Initializes the service types table view
	 */
	private void initializeServiceTypesTableView(){
		serviceTypesTable.setItems(serviceTypesList);
		serviceTypeLabelColumn.setCellValueFactory(cellData -> cellData.getValue().labelProperty());
		serviceTypeDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		
		serviceTypesTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue)-> selectedServiceType = newValue);
	}
	
	/**
	 * Initializes the services table view
	 */
	private void initializeServicesTableView(){
		servicesTableView.setItems(serviceList);
		serviceTitleColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		serviceProviderColumn.setCellValueFactory(cellData -> cellData.getValue().sellerNicknameProperty());
		serviceGameColumn.setCellValueFactory(cellData -> cellData.getValue().gameNameProperty());

		servicesTableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue)-> selectedService = newValue);
	}
	/**
	 * Initializes the game type table view
	 */
	private void initializeGameTypeTableView(){
		gameTypeTableView.setItems(gameTypesList);
		gameTypeNameColumn.setCellValueFactory(cellData -> cellData.getValue().labelProperty());
		
		gameTypeTableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectedGameType = newValue);
	}
	
}
