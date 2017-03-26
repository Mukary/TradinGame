package views;

import java.sql.SQLException;

import facades.GameFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Game;

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
	private GameFacade gameFacade;

	@FXML
	public void initialize(){
		gameFacade = GameFacade.getInstance();
		try {
			gamesList = FXCollections.observableList(gameFacade.getAllGames());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initializeGamesTableView();
	}
	
	private void initializeGamesTableView(){
		gamesTableView.setItems(gamesList);
		gameTitleColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		editorColumn.setCellValueFactory(cellData -> cellData.getValue().editorProperty());
		releaseDateColumn.setCellValueFactory(cellData -> cellData.getValue().yearReleaseProperty());
		genreColumn.setCellValueFactory(cellData -> cellData.getValue().gameTypeLabelProperty());
	}
}
