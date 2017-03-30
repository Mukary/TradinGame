package views;

import java.sql.SQLException;

import facades.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import models.User;
import javafx.scene.control.Alert.AlertType;
import util.Util;

public class AddUserViewController extends ViewController{

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
	private CheckBox isAdminCheckbox;
	
	private UserFacade userFacade;
	
	@FXML
	public void initialize(){
		userFacade = UserFacade.getInstance();
	}
	
	@FXML
	public void handleCancelButton(){
		stage.close();
	}
	
	@FXML
	public void handleAddUserButton(){
		if(!validInputs()){
			Util.displayAlert(AlertType.ERROR, "Error", "Please fill all mandatory fields.");
		}
		else if(!passwordAndConfirmMatch()){
			Util.displayAlert(AlertType.ERROR, "Error", "The password and the confirmation don't match.");
		}
		else{
			User user = new User(nicknameField.getText(), firstnameField.getText(), lastnameField.getText(), passwordField.getText(), countryField.getText(), cityField.getText(), addressField.getText(), false, isAdminCheckbox.isSelected());
			try {
				int res = userFacade.insertUser(user);
				if (res == 1) {
					Util.displayAlert(AlertType.INFORMATION, "User add", "The user has been well added !");
					stage.close();
					AdminGeneralViewController.usersList.add(user);
				}
				else {
					Util.displayAlert(AlertType.ERROR, "Error", "This nickname is already used by another user.");
				}
			} catch (SQLException e) {
				Util.displayAlert(AlertType.ERROR, "Error", "It seems there is a problem with the database. Try again later");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Checks if the obligatories fields are filled
	 * @return
	 */
	private boolean validInputs(){
		if(nicknameField.getText().length() == 0 || firstnameField.getText().length() == 0 || lastnameField.getText().length() == 0 || passwordField.getText().length() == 0 || confirmPasswordField.getText().length() == 0)
			return false;
		return true;
	}
	
	private boolean passwordAndConfirmMatch(){
		return passwordField.getText().equals(confirmPasswordField.getText());
	}
}
