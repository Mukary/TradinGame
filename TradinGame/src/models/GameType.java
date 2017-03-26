package models;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameType {
	
	public StringProperty label;
	
	/**
	 * Constructor with all the attributes of a GameType
	 * @param label
	 */
	public GameType(String label){
		super();
		this.label = new SimpleStringProperty(label);
	}

	public final StringProperty labelProperty() {
		return this.label;
	}


	public final String getLabel() {
		return this.labelProperty().get();
	}


	public final void setLabel(final String label) {
		this.labelProperty().set(label);
	}
}
