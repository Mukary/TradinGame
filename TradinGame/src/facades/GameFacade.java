package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.GameDAO;
import DAO.PostgresUserDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.Game;

public class GameFacade {
	
	private static GameFacade gameFacade;
	private GameDAO gameDao;
	
	private GameFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates userDAO object with factory;
		gameDao = (GameDAO) daoFactory.getDao(PostgresDAOFactory.GAME_DAO);
	}
	
	public static GameFacade getInstance(){
		if(gameFacade == null)
			gameFacade = new GameFacade();
		return gameFacade;
	}
	
	public ArrayList<Game> getAllGames() throws SQLException{
		return gameDao.getAll();
	}

}
