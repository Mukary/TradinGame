package models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model representing an Comment. An Comment is composed by :
 * <ul>
 * 	<li>a service</li>
 *  <li>a user</li>
 * 	<li>a content</li>
 * </ul>
 * @author AyoubNas
 *
 */
public class Comment {
	
	private Service service;
	private User user;
	private StringProperty content;

	
	
	/**
	 * Constructor with all the attributes for a Comment
	 * @param service : the service this comment belongs to
	 * @param user :  the publisher of the comment
	 * @param content : the comment's content
	 */
	public Comment(Service service, User user, String content) {
		super();
		this.service= service;
		this.content = new SimpleStringProperty(content);

	}
	
	public final Service getService(){
		return this.service;
	}
	
	public final User getUser(){
		return this.user;
	}
	

	public final StringProperty contentProperty() {
		return this.content;
	}
	

	public final String getContent() {
		return this.contentProperty().get();
	}
	
	

	public final void setContent(final String content) {
		this.contentProperty().set(content);
	}
	
	public final void setService(Service service){
	this.service=service;
	}
	
	public final void setUser(User user){
		this.user=user;
	}


}
