package models;

import java.util.ArrayList;

import javafx.beans.property.StringProperty;

public class GameType {
	
	public StringProperty label;
	private ArrayList<Game> games;
	private ArrayList<ServiceType> serviceTypes;
	
	
	/**
	 * Constructor with all the attributes of a GameType
	 * @param label
	 * @param games
	 */
	public GameType(StringProperty label, ArrayList<Game> games, ArrayList<ServiceType> serviceTypes){
		this.label = label;
		this.setGames(games);
	}


	public ArrayList<Game> getGames() {
		return games;
	}


	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}
	
	
}
