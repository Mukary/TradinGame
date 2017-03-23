package models;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model representing a Game. A Game is composed by :
 * <ul>
 * 	<li>a name</li>
 *  <li>an editor</li>
 * 	<li>a release date</li>
 * 	<li>a game type</li>
 * </ul>
 * @author Rahim Nouraly
 *
 */
public class Game {

	private StringProperty name;
	private StringProperty editor;
	private StringProperty gameTypeLabel;
	public Date releaseDate;
	
	
	/**
	 * Constructor with all the attributes for a game
	 * @param name
	 * @param editor
	 * @param releaseDate
	 */
	public Game(String name, String editor, Date releaseDate, String gameTypeLabel){
		super();
		this.name = new SimpleStringProperty(name);
		this.editor = new SimpleStringProperty(editor);
		this.releaseDate = releaseDate;
		this.gameTypeLabel = new SimpleStringProperty(gameTypeLabel);
	}
	
	public final StringProperty nameProperty() {
		return this.nameProperty();
	}
	public final String getName() { return this.nameProperty().get(); }
	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final StringProperty editorProperty() {
		return this.editorProperty();
	}
	public final String getEditor() { return this.editorProperty().get(); }
	public final void setEditor(final String editor) {this.editorProperty().set(editor);}

	public final StringProperty gameTypeLabelProperty() {
		return this.gameTypeLabelProperty();
	}
	public final String getGameTypeLabel() { return this.gameTypeLabelProperty().get(); }
	public final void setGameTypeLabel(final String gameTypeLabel) {this.gameTypeLabelProperty().set(gameTypeLabel);}

	public final Date getReleaseDate() { return this.releaseDate; }
	public final void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
	
	
	
}
