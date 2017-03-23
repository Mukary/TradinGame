package models;

import javafx.beans.property.StringProperty;

public class Report {
	
	private User user;
	private Service service;
	private StringProperty content;
	
	/**
	 * Constructor with all the attributes for a Report
	 * @param user
	 * @param service
	 * @param content
	 */
	public Report(User user, Service service, StringProperty content){
		this.user = user;
		this.service = service;
		this.content = content;
	}
	
	public StringProperty getContent() {
		return content;
	}
	public void setContent(StringProperty content) {
		this.content = content;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
