package models;

import java.util.ArrayList;

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
	public ServiceType(StringProperty label, StringProperty description, ArrayList<Service> services){
		this.setLabel(label);
		this.setDescription(description);
	}

	public StringProperty getLabel() {
		return label;
	}

	public void setLabel(StringProperty label) {
		this.label = label;
	}

	public StringProperty getDescription() {
		return description;
	}

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
