package application;
	
import java.io.IOException;

import database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Service;
import views.AddGameViewController;
import views.AddReportViewController;
import views.AddServiceViewController;
import views.AddUserViewController;
import views.AdminGeneralViewController;
import views.LoginController;
import views.ServiceDetailViewController;
import views.SignupViewController;
import views.UserGeneralViewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	@Override
	
	/**
	 * Entry point of the application
	 */
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Welcome !");
			showLoginView();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showLoginView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/LoginView.fxml"));
            AnchorPane loginView = (AnchorPane) loader.load();

            Scene scene = new Scene(loginView);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Shows the details of the selected service.
     */
    public void showServiceDetailView(Service service){
        try{
            //Load the service detail view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/ServiceDetailView.fxml"));
            AnchorPane serviceDetailView = (AnchorPane) loader.load();

            Scene scene = new Scene(serviceDetailView);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Service details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);

            ServiceDetailViewController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setService(service);
            dialogStage.showAndWait();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showAddReportView(Service service){
    	try{
            //Load the report form detail view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/AddReportView.fxml"));
            AnchorPane addReportView = (AnchorPane) loader.load();

            Scene scene = new Scene(addReportView);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Report service");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);

            AddReportViewController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setReportedService(service);
            dialogStage.showAndWait();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showAddGameView(){
    	try{
            //Load the service detail view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/AddGameView.fxml"));
            AnchorPane addGameView = (AnchorPane) loader.load();

            Scene scene = new Scene(addGameView);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add game");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);

            AddGameViewController controller = loader.getController();
            controller.setStage(dialogStage);
            dialogStage.showAndWait();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showMyServiceDetailView(){
    	try {
    		//Load the booked service detail view
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("/views/MyServiceDetailView.fxml"));
    		AnchorPane myServiceDetailView = (AnchorPane) loader.load();
    		
    		Scene scene = new Scene(myServiceDetailView);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    		
    		
    	} catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    
    /**
     * Displays the user's main view (market etc..)
     */
    public void showUserGeneralViewDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/UserGeneralView.fxml"));
            AnchorPane userGeneralView = (AnchorPane) loader.load();

            // Create the dialog Stage.
            primaryStage.setTitle("TradinGame");
            Scene scene = new Scene(userGeneralView);
            primaryStage.setScene(scene);

            // Set the person into the controller.
            UserGeneralViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showAdminGenereralView(){
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/AdminGeneralView.fxml"));
            AnchorPane adminGeneralView = (AnchorPane) loader.load();

            // Create the dialog Stage.
            primaryStage.setTitle("TradinGame");
            Scene scene = new Scene(adminGeneralView);
            primaryStage.setScene(scene);

            // Set the person into the controller.
            AdminGeneralViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void showSignupView(){
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/SignupView.fxml"));
            AnchorPane signupView = (AnchorPane) loader.load();

            // Create the dialog Stage.
            primaryStage.setTitle("Register form");
            Scene scene = new Scene(signupView);
            primaryStage.setScene(scene);

            // Set the person into the controller.
            SignupViewController controller = loader.getController();
            controller.setMainApp(this);

        	} catch (IOException e) {
        		e.printStackTrace();
        	}
    }
    
    public void showAddServiceDialogView() throws IOException{
    	// Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/addServiceView.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add service");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        AddServiceViewController controller = loader.getController();
        controller.setStage(dialogStage);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }
    
    public void showAddUserDialogView() throws IOException{
    	// Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/AddUserView.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add an user");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        AddUserViewController controller = loader.getController();
        controller.setStage(dialogStage);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }
	
    /**
     * Starting point of the application
     * @param args
     */
	public static void main(String[] args) {
		launch(args);
	}
}
