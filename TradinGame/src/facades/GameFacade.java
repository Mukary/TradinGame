package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PostgresGameDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.Game;
import models.ServiceType;

/**
 * Facade to manage persistant datas about games
 * @author Max Cabourg - Polytech Montpellier
 *
 */
public class GameFacade {
	
	private static GameFacade gameFacade;
	private PostgresGameDAO gameDao;
	
	private GameFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates gameDAO object with factory;
		gameDao = (PostgresGameDAO) daoFactory.getDao(PostgresDAOFactory.GAME_DAO);
	}
	
	/**
	 * Method based on the singleton design pattern
	 * @return the unique instance of the class
	 */
	public static GameFacade getInstance(){
		if(gameFacade == null)
			gameFacade = new GameFacade();
		return gameFacade;
	}
	
	/**
	 * Returns all the game contained in the database
	 * @return an ArrayList of Game objects
	 * @throws SQLException if the query fails
	 */
	public ArrayList<Game> getAllGames() throws SQLException{
		return gameDao.getAll();
	}
	
	/**
	 * Returns all the games that can be concerned by a type of service
	 * @param serviceType the service type concerned
	 * @return an arrayList of game
	 * @throws SQLException if the query fails
	 */
	public ArrayList<Game> getAllGamesByServiceType(ServiceType serviceType) throws SQLException{
		return gameDao.getAllByServiceType(serviceType);
	}
	
	/**
	 * Tells if a game is compatible with a service type
	 * @param game the game concerned
	 * @param serviceType the service type concerned
	 * @return true if it is, false otherwise
	 * @throws SQLException if the query fails
	 */
	public boolean isCompatibleWithServiceType(Game game, ServiceType serviceType) throws SQLException{
		return gameDao.isCompatibleWithServiceType(game, serviceType);
	}
	
	/**
	 * Inserts a game in the database
	 * @param game The game to be inserted
	 * @return 1 if the query succeeded, 0 otherwise
	 * @throws SQLException if the query fails
	 */
	public int insertGame(Game game) throws SQLException{
		return gameDao.create(game);
	}

	/**
	 * Deletes a game on the database
	 * @param game the game to be deleted
	 * @return 1 if the query succeeded, 0 otherwise
	 * @throws SQLException if the query fails
	 */
	public int deleteGame(Game game) throws SQLException{
		return gameDao.delete(game);
	}
	
	/**
	 * Checks if a game already exists in the datase based on its name
	 * @param gameName the name of the game to be checked
	 * @return true if it already exists, false otherwise
	 * @throws SQLException if the query fails
	 */
	public boolean gameAlreadyExists(String gameName) throws SQLException{
		return gameDao.gameAlreadyExists(gameName);
	}

}
