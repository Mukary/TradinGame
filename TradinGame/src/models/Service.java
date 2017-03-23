package models;

import java.sql.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Service {
	
	private IntegerProperty idService;
	private StringProperty description;
	private Date expirationDate;
	private StringProperty sellerNickname;
	private StringProperty serviceTypeLabel;
	private StringProperty gameName;
	private StringProperty consumerNickname;
	
	
	/**
	 * Constructor with all the attributes for a Service
	 * @param idService
	 * @param description
	 * @param expirationDate
	 */
	public Service(int idService, String description, Date expirationDate, String sellerNickname, String serviceTypeLabel, String gameName, String consumerNickName){
		super();
		this.setIdService(idService);
		this.setDescription(description);
		this.setExpirationDate(expirationDate);
		this.setSellerNickName(sellerNickname);
		this.setServiceTypeLabel(serviceTypeLabel);
		this.setGameName(gameName);
		this.setConsumerNickname(consumerNickName);
	}

	public final IntegerProperty idServiceProperty() {
		return this.idService;
	}
	public final int getIdService() { return this.idServiceProperty().get(); }
	public final void setIdService(int idService) {
		this.idServiceProperty().set(idService);
	}

	public final StringProperty descriptionProperty() {
		return this.description;
	}
	public final String getDescription() { return this.descriptionProperty().get(); }
	public final void setDescription(String description) {
		this.descriptionProperty().set(description);
	}

	public final Date getExpirationDate() {
		return this.expirationDate;
	}
	public final void setExpirationDate(Date expirationDate) {this.expirationDate = expirationDate;	}

	public final StringProperty sellerNicknameProperty() {
		return this.sellerNickname;
	}
	public final String getSellerNickname() { return this.sellerNicknameProperty().get(); }
	public final void setSellerNickName(String sellerNickName) {
		this.sellerNicknameProperty().set(sellerNickName);
	}

	public final StringProperty serviceTypeLabelProperty() {
		return this.serviceTypeLabel;
	}
	public final String getServiceTypeLabel() { return this.serviceTypeLabelProperty().get(); }
	public final void setServiceTypeLabel(String serviceTypeLabel) {this.serviceTypeLabelProperty().set(serviceTypeLabel);}

	public final StringProperty gameNameProperty() {
		return this.gameName;
	}
	public final String getGameName() { return this.gameNameProperty().get(); }
	public final void setGameName(String gameName) {this.gameNameProperty().set(gameName);}

	public final StringProperty consumerNicknameProperty() {
		return this.consumerNickname;
	}
	public final String getConsumerNickname() { return this.consumerNicknameProperty().get(); }
	public final void setConsumerNickname(String consumerNickname) {this.consumerNicknameProperty().set(consumerNickname);}
	
}
