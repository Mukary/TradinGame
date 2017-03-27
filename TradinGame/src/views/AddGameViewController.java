package views;

import java.sql.Date;
import java.sql.SQLException;

import facades.GameFacade;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
import models.Game;
import models.GameType;
import util.Util;

public class AddGameViewController extends ViewController{
	
	@FXML
	private TextField titleField;
	@FXML
	private TextField editorField;
	@FXML
	private ComboBox<GameType> gameTypeBox;
	@FXML
	private DatePicker releaseDatePicker;
	
	private GameType selectedGameType;
	private GameFacade gameFacade;
	
	@FXML
	public void initialize(){
		gameFacade = GameFacade.getInstance();
		selectedGameType = null;
		initializeComboBox();
	}
	
	@FXML
	private void handleAddGameButton(){
		if(!validInputs()){
			Util.displayAlert(AlertType.ERROR, "Error", "Please fill the title and editor fields plus the game type");
		}
		else{
			try {
				if(gameFacade.gameAlreadyExists(titleField.getText())){
					Util.displayAlert(AlertType.ERROR, "Error", "The game you try to add already exists in the database.");
					return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Date convertedDate = Date.valueOf(releaseDatePicker.getValue());
			Game game = new Game(titleField.getText(), editorField.getText(), convertedDate, selectedGameType.getLabel());
			int res = 0;
			try {
				res = gameFacade.insertGame(game);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(res == 1){
				AdminGeneralViewController.gamesList.add(game);
				Util.displayAlert(AlertType.CONFIRMATION, "Game added", "The game has been well added !");
				stage.close();
			}
				
			else
				Util.displayAlert(AlertType.ERROR, "Error", "It seems the database has a problem. Try again later.");
		}
	}
	
	@FXML
	private void cancelButton(){
		stage.close();
	}
	
	private boolean validInputs(){
		if(titleField.getText().length() == 0 || editorField.getText().length() == 0 || selectedGameType == null || releaseDatePicker.getValue() == null)
			return false;
		return true;
	}
	
	private void initializeComboBox(){
		for(GameType gameType: AdminGeneralViewController.gameTypesList){
			gameTypeBox.getItems().add(gameType);
		}
		
		gameTypeBox.setCellFactory((comboBox) -> {
			return new ListCell<GameType>(){
				@Override
				protected void updateItem(GameType item, boolean empty){
					super.updateItem(item, empty);

					if(item == null || empty){
						setText(null);
					}
					else{
						setText(item.getLabel());
					}
				}
			};
		});
		
		gameTypeBox.setConverter(new StringConverter<GameType>() {
		    @Override
		    public String toString(GameType gameType) {
		        if (gameType == null) {
		            return null;
		        } else {
		            return gameType.getLabel();
		        }
		    }

		    @Override
		    public GameType fromString(String gameString) {
		        return null; // No conversion fromString needed.
		    }
		});
		
		gameTypeBox.setOnAction((event) -> {
		    selectedGameType = gameTypeBox.getSelectionModel().getSelectedItem();
		});
	}

}
