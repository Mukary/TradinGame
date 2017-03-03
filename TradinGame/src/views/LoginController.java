package views;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField nicknameField;
	@FXML
	private PasswordField passwordField;
	
	private Main mainApp;
	
	/**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginController() {
    	
    }
    
    @FXML
    private void initialize() {
    
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
    	System.out.println("Clicked on sign in button");
    }
    
    /**
     * Method triggered by a cick on the sign up button
     */
    @FXML
    private void handleSignUpButton(){
    	System.out.println("Clicked on sign up button");
    }
    
    

}
