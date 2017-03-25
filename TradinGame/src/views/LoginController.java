package views;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import DAO.PostgresUserDAO;
import application.Main;
import facades.UserFacade;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import models.User;
import util.Util;

public class LoginController extends ViewController{

	@FXML
	private TextField nicknameField;
	@FXML
	private PasswordField passwordField;
	
	
	private UserFacade userFacade;
	
	/**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginController() {
    	userFacade = UserFacade.getInstance();
    }
    
    @FXML
    /**
     * Initializes the controller
     */
    private void initialize() {
    	
    }
    
    
    /**
     * Method triggered by a click on the sign in button
     */
    @FXML
    private void handleSignInButton(){
    	User userFound = null;
		try {
			userFound = userFacade.login(nicknameField.getText(), passwordField.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(userFound == null){
    		 Alert alert = new Alert(AlertType.ERROR);
             alert.initOwner(stage);
             alert.setTitle("Invalid Fields");
             alert.setHeaderText("Please correct invalid fields");
             alert.setContentText("Nickname or password incorrect");
             alert.showAndWait();
    	}
    	else{
    		UserFacade.setUserLogged(userFound);
    		mainApp.showUserGeneralViewDialog();		
    	}
    		
    	
    }
    
    /**
     * Method triggered by a cick on the sign up button
     */
    @FXML
    private void handleSignUpButton(){
    	mainApp.showSignupView();
    }
    
    

}
