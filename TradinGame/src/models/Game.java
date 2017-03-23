package models;

import java.util.Date;

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
	public Date releaseDate;
	
	
	/**
	 * Constructor with all the attributes for a game
	 * @param name
	 * @param editor
	 * @param releaseDate
	 */
	public Game(StringProperty name, StringProperty editor, Date releaseDate){
		this.name = name;
		this.editor = editor;
		this.releaseDate = releaseDate;
	}
	
	
	public StringProperty getName() {
		return name;
	}
	public void setName(StringProperty name) {
		this.name = name;
	}
	public StringProperty getEditor() {
		return editor;
	}
	public void setEditor(StringProperty editor) {
		this.editor = editor;
	}
	
	
	
	
	
}
