package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report {
	
	private IntegerProperty reportID;
	private StringProperty  userNickname;
	private IntegerProperty serviceID;
	private StringProperty description;
	private StringProperty topic;
	
	/**
	 * Constructor with all the attributes for a Report
	 * @param user : the user who emitted the report
	 * @param service : the service concerned by the report
	 * @param content : what the report is about
	 */
	public Report(int reportID, String topic, String description, int serviceID, String userNickname){
		this.reportID = new SimpleIntegerProperty(reportID);
		this.topic = new SimpleStringProperty(topic);
		this.description = new SimpleStringProperty(description);
		this.serviceID = new SimpleIntegerProperty(serviceID);
		this.userNickname = new SimpleStringProperty(userNickname);
	}
	
	public String getDescription() {
		return description.get();
	}
	public void setDescription(String description) {
		this.description.set(description);;
	}
	public int getServiceID() {
		return serviceID.get();
	}
	public void setServiceID(int serviceID) {
		this.serviceID.set(serviceID);;
	}
	public String getUserNickname() {
		return userNickname.get();
	}
	public void setUserNickname(String userNickname) {
		this.userNickname.set(userNickname);
	}

	public String getTopic() {
		return topic.get();
	}

	public void setTopic(String topic) {
		this.topic.set(topic);
	}

	public int getReportID() {
		return reportID.get();
	}

	public void setReportID(int reportID) {
		this.reportID.set(reportID);
	}
	

}
