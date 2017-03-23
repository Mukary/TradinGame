package views;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class AddServiceViewController extends ViewController{
	
	@FXML
	private TextArea descriptionArea;	
	@FXML
	private ComboBox gameBox;
	@FXML
	private ComboBox serviceTypeBox;
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private void handleAddServiceButton(){
		
	}
	
	@FXML
	private void handleCancelButton(){
		mainApp.showUserGeneralViewDialog();
	}

}
