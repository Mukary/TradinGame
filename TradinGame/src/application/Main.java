package application;
	
import java.io.IOException;

import database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.LoginController;
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
    public void showServiceDetailView(){
        try{
            //Load the service detail view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/ServiceDetailView.fxml"));
            AnchorPane serviceDetailView = (AnchorPane) loader.load();

            Scene scene = new Scene(serviceDetailView);
            primaryStage.setScene(scene);
            primaryStage.show();

            LoginController controller = loader.getController();
            controller.setMainApp(this);

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
	
	public static void main(String[] args) {
		launch(args);
	}
}
