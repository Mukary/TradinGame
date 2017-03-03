package application;
	
import java.io.IOException;

import database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	@Override
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
            /*PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
