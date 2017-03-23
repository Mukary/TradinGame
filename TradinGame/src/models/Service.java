package models;

import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Service {
	
	private IntegerProperty idService;
	private StringProperty description;
	private Date expirationDate;
	private BooleanProperty isAvailable;
	
	
	
	/**
	 * Constructor with all the attributes for a Service
	 * @param idService
	 * @param description
	 * @param expirationDate
	 * @param isAvailable
	 */
	public Service(IntegerProperty idService, StringProperty description, Date expirationDate, BooleanProperty isAvailable){
		this.setIdService(idService);
		this.setDescription(description);
		this.setExpirationDate(expirationDate);
		this.setIsAvailable(isAvailable);
	}

	public IntegerProperty getIdService() {
		return idService;
	}



	public void setIdService(IntegerProperty idService) {
		this.idService = idService;
	}



	public StringProperty getDescription() {
		return description;
	}



	public void setDescription(StringProperty description) {
		this.description = description;
	}



	public BooleanProperty getIsAvailable() {
		return isAvailable;
	}



	public void setIsAvailable(BooleanProperty isAvailable) {
		this.isAvailable = isAvailable;
	}



	public Date getExpirationDate() {
		return expirationDate;
	}



	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	
	
	
	
}
