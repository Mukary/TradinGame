package views;

import java.sql.SQLException;

import facades.GameFacade;
import facades.GameTypeFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Game;
import models.GameType;

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
	
	public static ObservableList<Game> gamesList;
	public static ObservableList<GameType> gameTypesList;
	private GameFacade gameFacade;
	private GameTypeFacade gameTypeFacade;

	@FXML
	public void initialize(){
		gameFacade = GameFacade.getInstance();
		gameTypeFacade = GameTypeFacade.getInstance();
		try {
			gamesList = FXCollections.observableList(gameFacade.getAllGames());
			gameTypesList = FXCollections.observableList(gameTypeFacade.getAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initializeGamesTableView();
	}
	
	@FXML
	private void handleAddGameButton(){
		mainApp.showAddGameView();
	}
	
	private void initializeGamesTableView(){
		gamesTableView.setItems(gamesList);
		gameTitleColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		editorColumn.setCellValueFactory(cellData -> cellData.getValue().editorProperty());
		releaseDateColumn.setCellValueFactory(cellData -> cellData.getValue().yearReleaseProperty());
		genreColumn.setCellValueFactory(cellData -> cellData.getValue().gameTypeLabelProperty());
	}
}
