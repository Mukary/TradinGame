package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	
	private StringProperty nickname;
	private StringProperty firstname;
	private StringProperty lastname;
	private StringProperty password;
	private StringProperty country;
	private StringProperty city;
	private StringProperty address;
	private BooleanProperty isBanned;
	private BooleanProperty isAdmin;
	
	public User(String nickname, String firstname, String lastname, String password,
			String country, String city, String address, Boolean isBanned,
			Boolean isAdmin) {
		super();
		this.nickname = new SimpleStringProperty(nickname);
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.password = new SimpleStringProperty(password);
		this.country = new SimpleStringProperty(country);
		this.city = new SimpleStringProperty(city);
		this.address = new SimpleStringProperty(address);
		this.isBanned = new SimpleBooleanProperty(isBanned);
		this.isAdmin = new SimpleBooleanProperty(isAdmin);
	}

	public final StringProperty nicknameProperty() {
		return this.nickname;
	}
	

	public final String getNickname() {
		return this.nicknameProperty().get();
	}
	

	public final void setNickname(final String nickname) {
		this.nicknameProperty().set(nickname);
	}
	

	public final StringProperty firstnameProperty() {
		return this.firstname;
	}
	

	public final String getFirstname() {
		return this.firstnameProperty().get();
	}
	

	public final void setFirstname(final String firstname) {
		this.firstnameProperty().set(firstname);
	}
	

	public final StringProperty lastnameProperty() {
		return this.lastname;
	}
	

	public final String getLastname() {
		return this.lastnameProperty().get();
	}
	

	public final void setLastname(final String lastname) {
		this.lastnameProperty().set(lastname);
	}
	

	public final StringProperty passwordProperty() {
		return this.password;
	}
	

	public final String getPassword() {
		return this.passwordProperty().get();
	}
	

	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	

	public final StringProperty countryProperty() {
		return this.country;
	}
	

	public final String getCountry() {
		return this.countryProperty().get();
	}
	

	public final void setCountry(final String country) {
		this.countryProperty().set(country);
	}
	

	public final StringProperty cityProperty() {
		return this.city;
	}
	

	public final String getCity() {
		return this.cityProperty().get();
	}
	

	public final void setCity(final String city) {
		this.cityProperty().set(city);
	}
	

	public final StringProperty addressProperty() {
		return this.address;
	}
	

	public final String getAddress() {
		return this.addressProperty().get();
	}
	

	public final void setAddress(final String address) {
		this.addressProperty().set(address);
	}
	

	public final BooleanProperty isBannedProperty() {
		return this.isBanned;
	}
	

	public final boolean isIsBanned() {
		return this.isBannedProperty().get();
	}
	

	public final void setIsBanned(final boolean isBanned) {
		this.isBannedProperty().set(isBanned);
	}
	

	public final BooleanProperty isAdminProperty() {
		return this.isAdmin;
	}
	

	public final boolean isIsAdmin() {
		return this.isAdminProperty().get();
	}
	

	public final void setIsAdmin(final boolean isAdmin) {
		this.isAdminProperty().set(isAdmin);
	}
	
	
	
	

}
