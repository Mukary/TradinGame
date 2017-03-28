package models;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServiceType {
	
	private StringProperty label;
	private StringProperty description;
	private ArrayList<Service> services;
	
	/**
	 * Constructor with all attributes for a ServiceType
	 * @param label
	 * @param description
	 */
	public ServiceType(String label, String description){
		this.label = new SimpleStringProperty(label);
		this.description = new SimpleStringProperty(description);
	}

	public StringProperty labelProperty() { return label;}
	public final String getLabel() {return this.labelProperty().get();}
	public void setLabel(StringProperty label) {
		this.label = label;
	}

	public StringProperty descriptionProperty() { return description;}
	public final String getDescription() {return this.descriptionProperty().get();}
	public void setDescription(StringProperty description) {
		this.description = description;
	}


	public ArrayList<Service> getServices() {
		return services;
	}

	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}
	
}
