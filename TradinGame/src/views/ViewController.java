package views;

import application.Main;
import javafx.stage.Stage;

public class ViewController {

	protected Main mainApp;
	protected Stage stage;
	
	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	
}
