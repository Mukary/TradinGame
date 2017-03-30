package views;

import java.sql.SQLException;

import facades.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.User;
import javafx.scene.control.Alert.AlertType;
import util.Util;

public class SignupViewController extends ViewController{

	@FXML
	private TextField nicknameField;
	@FXML
	private TextField firstnameField;
	@FXML
	private TextField lastnameField;
	@FXML
	private TextField countryField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextField confirmPasswordField;
	
	@FXML
	private void handleRegisterButton(){
		Alert alert;
		if(!passwordMatches()){
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
	        alert.initOwner(stage);
	        alert.setTitle("Invalid Fields");
	        alert.setContentText("Password and confirmation don't match");
	        alert.showAndWait();
		}
		else if(!validInputs()){
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
	        alert.initOwner(stage);
	        alert.setTitle("Invalid Fields");
	        alert.setContentText("Please fill the mandatory fields");
	        alert.showAndWait();
		}
		else{
			//TODO : USE FACADE TO INSERT USER
	        String nickname = nicknameField.getText();
	        String firstname = firstnameField.getText();
	        String lastname = lastnameField.getText();
	        String country = countryField.getText();
	        String city = cityField.getText();
	        String address = addressField.getText();
	        String password = passwordField.getText();
	        User userToInsert = new User(nickname, firstname, lastname, password, country, city, address, false, false);
	        UserFacade userFacade = UserFacade.getInstance();
	        try {
				int res = userFacade.insertUser(userToInsert);
				if (res == 1) {
					Util.displayAlert(AlertType.INFORMATION, "User registered", "You have been registered correctly !");
	        		mainApp.showLoginView();
				}
				else
				{
					Util.displayAlert(AlertType.ERROR, "Failed to insert user", "This nickname is already used by another user.");
				}
				System.out.println(res);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
		        alert.initOwner(stage);
		        alert.setTitle("Failed to insert user");
		        alert.setContentText("There was a problem with the database. Try again later.");
		        alert.showAndWait();
				e.printStackTrace();
			}

		}
			
		
        
	}
	
	@FXML
	private void handleCancelButton(){
		mainApp.showLoginView();
	}
	
	/**
	 * Checks if all the mandatory inputs are filled
	 * @return true if they are, false otherwise
	 */
	private boolean validInputs(){
		if(nicknameField.getText().length() == 0 || firstnameField.getText().length() == 0 || lastnameField.getText().length() == 0 || passwordField.getText().length() == 0 || confirmPasswordField.getText().length() == 0){
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the password and its confirmation are the same
	 * @return true if it does, false otherwise
	 */
	private boolean passwordMatches(){
		return (passwordField.getText().equals(confirmPasswordField.getText()));
	}
}
