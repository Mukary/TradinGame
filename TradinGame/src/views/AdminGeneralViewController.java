package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import facades.GameFacade;
import facades.GameTypeFacade;
import facades.ReportFacade;
import facades.UserFacade;
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
	private TableColumn<Report, String> sellerColumn;
	@FXML
	private TableColumn<Report, String> authorColumn;
	
	
	
	
	public static ObservableList<Game> gamesList;
	public static ObservableList<GameType> gameTypesList;
	public static ObservableList<User> usersList;
	public static ObservableList<Report> reportsList;
	
	private GameFacade gameFacade;
	private GameTypeFacade gameTypeFacade;
	private UserFacade userFacade;
	private ReportFacade reportFacade;
	
	private Game selectedGame;
	private User selectedUser;
	private Report selectedReport;

	@FXML
	public void initialize(){
		initialiazeFacades();
		try {
			initializeLists();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initializeGamesTableView();
		initializeUsersTableView();
		initializeReportsTableView();
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
	 * Handles the delete report button action
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
		//sellerColumn.setCellValueFactory(cellData -> cellData.getValue().)
		authorColumn.setCellValueFactory(cellData -> cellData.getValue().nicknameP());
		
		reportsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedReport = newValue);
	}
	
	/**
	 * Initialiazes the different facades
	 */
	private void initialiazeFacades(){
		gameFacade = GameFacade.getInstance();
		gameTypeFacade = GameTypeFacade.getInstance();
		userFacade = UserFacade.getInstance();
		reportFacade = ReportFacade.getInstance();
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
	}
	
	/**
	 * Initializes the users table view
	 */
	private void initializeUsersTableView(){
		usersTableView.setItems(usersList);
		userNicknameColumn.setCellValueFactory(cellData -> cellData.getValue().nicknameProperty());
		userFirstnameColumn.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
		userLastnameColumn.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
		usersTableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectedUser = newValue);
	}
}
