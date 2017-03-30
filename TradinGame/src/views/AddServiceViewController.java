package views;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import facades.GameFacade;
import facades.ServiceFacade;
import facades.ServiceTypeFacade;
import facades.UserFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;
import models.Game;
import models.Service;
import models.ServiceType;
import util.Util;
import javafx.scene.control.Alert.AlertType;

public class AddServiceViewController extends ViewController{
	
	@FXML
	private TextArea descriptionArea;	
	@FXML
	private ComboBox<Game> gameBox;
	@FXML
	private ComboBox<ServiceType> serviceTypeBox;
	@FXML
	private DatePicker datePicker;
	
	private ObservableList<Game> proposableGames;
	private ObservableList<ServiceType> proposableServiceTypes;
	private GameFacade gameFacade;
	private ServiceTypeFacade serviceTypeFacade;
	private ServiceFacade serviceFacade;
	private Game selectedGame;
	private ServiceType selectedServiceType;
	
	@FXML
	private void initialize(){
		gameFacade = GameFacade.getInstance();
		serviceTypeFacade = ServiceTypeFacade.getInstance();
		serviceFacade = ServiceFacade.getInstance();
		selectedGame = null;
		selectedServiceType = null;
		try {
			proposableGames = FXCollections.observableList(gameFacade.getAllGames());
			proposableServiceTypes = FXCollections.observableList(serviceTypeFacade.getAllServiceTypes());
			for(Game g: proposableGames){
				gameBox.getItems().add(g);
			}
			//Ca ressemble a du NodeJS :p
			//Comment afficher les items de la liste deroulante
			gameBox.setCellFactory((comboBox) -> {
				return new ListCell<Game>(){
					@Override
					protected void updateItem(Game item, boolean empty){
						super.updateItem(item, empty);
						
						if(item == null || empty){
							setText(null);
						}
						else{
							setText(item.getName());
						}
					}
				};
			});
			
			//Comment afficher l'item selectionné
			gameBox.setConverter(new StringConverter<Game>() {
			    @Override
			    public String toString(Game game) {
			        if (game == null) {
			            return null;
			        } else {
			            return game.getName();
			        }
			    }

			    @Override
			    public Game fromString(String gameString) {
			        return null; // No conversion fromString needed.
			    }
			});
			
			gameBox.setOnAction((event) -> {
			    selectedGame = gameBox.getSelectionModel().getSelectedItem();
			});
			
			for(ServiceType st: proposableServiceTypes){
				serviceTypeBox.getItems().add(st);
			}
			
			serviceTypeBox.setCellFactory((comboBox) -> {
				return new ListCell<ServiceType>(){
					@Override
					protected void updateItem(ServiceType item, boolean empty){
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
			
			//Comment afficher l'item selectionné
			serviceTypeBox.setConverter(new StringConverter<ServiceType>() {
			    @Override
			    public String toString(ServiceType serviceType) {
			        if (serviceType == null) {
			            return null;
			        } else {
			            return serviceType.getLabel();
			        }
			    }

			    @Override
			    public ServiceType fromString(String serviceTypeString) {
			        return null; // No conversion fromString needed.
			    }
			});
			
			serviceTypeBox.setOnAction((event) -> {
			    selectedServiceType = serviceTypeBox.getSelectionModel().getSelectedItem();
			});
			//proposableServiceTypes = 
		} catch (SQLException e) {}
	}
	
	@FXML
	private void handleAddServiceButton(){
		Alert alert;
		if(!validInputs()){
			Util.displayAlert(AlertType.ERROR, "Error", "Please fill the required fields");
		}
		else if(pickedDateBeforeNow()){
			Util.displayAlert(AlertType.ERROR, "Error", "Please choose an expiration date after the current date");
		} else
			try {
				if(!gameFacade.isCompatibleWithServiceType(selectedGame, selectedServiceType)){
			        Util.displayAlert(AlertType.ERROR, "Error", "You cannot associated the game "+selectedGame.getName()+" to the service type : "+selectedServiceType.getLabel());
				}
				else{
					Date convertedDate = Date.valueOf(datePicker.getValue()); //Convert LocalDate into Date
					Service service = new Service(1, descriptionArea.getText(), convertedDate, UserFacade.userLogged.getNickname(),
							selectedServiceType.getLabel(), selectedGame.getName(), null);
					serviceFacade.insertService(service);
					UserGeneralViewController.servicesList.add(service);
					UserGeneralViewController.myServicesList.add(service);
					Util.displayAlert(AlertType.INFORMATION, "Service added", "Your service has been well added !");
					stage.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	@FXML
	private void handleCancelButton(){
		stage.close();
	}

	private boolean validInputs(){	
		LocalDate date = datePicker.getValue();
		if(descriptionArea.getText().length() == 0 || date == null)
			return false;
		return true;
	}
	
	private boolean pickedDateBeforeNow(){
		LocalDate now = LocalDate.now();
		return now.isAfter(datePicker.getValue());
	}
}
