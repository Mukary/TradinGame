package views;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import DAO.UserDAO;
import application.Main;
import facades.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import models.User;
import util.Util;

public class LoginController {

	@FXML
	private TextField nicknameField;
	@FXML
	private PasswordField passwordField;
	
	private Stage stage;
	
	private Main mainApp;
	private UserFacade uf;
	
	/**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginController() {
    	uf = new UserFacade();
    }
    
    @FXML
    private void initialize() {
    
    }
    
    public void setStage(Stage stage){
    	this.stage = stage;
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Method triggered by a click on the sign in button
     */
    @FXML
    private void handleSignInButton(){
    	User u = null;
		try {
			u = uf.login(nicknameField.getText(), passwordField.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(u == null){
    		 Alert alert = new Alert(AlertType.ERROR);
             alert.initOwner(stage);
             alert.setTitle("Invalid Fields");
             alert.setHeaderText("Please correct invalid fields");
             alert.setContentText("Nickname or password incorrect");
             alert.showAndWait();
    	}
    	else
    		System.out.println("oui");
    	
    }
    
    /**
     * Method triggered by a cick on the sign up button
     */
    @FXML
    private void handleSignUpButton(){
    	System.out.println("Clicked on sign up button");
    }
    
    

}
