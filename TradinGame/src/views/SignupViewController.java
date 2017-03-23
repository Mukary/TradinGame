package views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
			alert = new Alert(AlertType.INFORMATION);
	        alert.initOwner(stage);
	        alert.setHeaderText(null);
	        alert.setTitle("User registered");
	        alert.setContentText("You have been registered correctly !");
	        alert.showAndWait();
			//TODO : USE FACADE TO INSERT USER
	        mainApp.showLoginView();
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
