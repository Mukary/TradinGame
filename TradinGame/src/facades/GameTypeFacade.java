package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PostgresGameDAO;
import DAO.PostgresGameTypeDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.GameType;

/**
 * Facade used to manage persistant datas about game types
 * @author MAX
 *
 */
public class GameTypeFacade {
	
	public static GameTypeFacade gameTypeFacade;
	private PostgresGameTypeDAO gameTypeDao;
	
	private GameTypeFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates gameDAO object with factory;
		gameTypeDao = (PostgresGameTypeDAO) daoFactory.getDao(PostgresDAOFactory.GAME_TYPE_DAO);
	}
	
	/**
	 * Method based on the singleton design pattern
	 * @return the unique instance of the class
	 */
	public static GameTypeFacade getInstance(){
		if(gameTypeFacade == null)
			gameTypeFacade = new GameTypeFacade();
		return gameTypeFacade;
	}
	
	/**
	 * Gets all the game types contained in the database
	 * @return an arrayList of game types
	 * @throws SQLException if the query fails
	 */
	public ArrayList<GameType> getAll() throws SQLException{
		return gameTypeDao.getAll();
	}
}
