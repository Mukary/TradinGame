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
		if(!passwordMatches()){
			Alert alert = new Alert(AlertType.ERROR);
	        alert.initOwner(stage);
	        alert.setTitle("Invalid Fields");
	        alert.setContentText("Password and confirmation don't match");
	        alert.showAndWait();
		}
		else if(!validInputs()){
			Alert alert = new Alert(AlertType.ERROR);
	        alert.initOwner(stage);
	        alert.setTitle("Invalid Fields");
	        alert.setContentText("Please fill the mandatory fields");
	        alert.showAndWait();
		}
		else
			System.out.println("C'est pas bon");
		
        
	}
	
	@FXML
	private void handleCancelButton(){
		
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
