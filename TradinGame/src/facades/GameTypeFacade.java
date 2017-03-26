package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PostgresGameDAO;
import DAO.PostgresGameTypeDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.GameType;

public class GameTypeFacade {
	
	public static GameTypeFacade gameTypeFacade;
	private PostgresGameTypeDAO gameTypeDao;
	
	private GameTypeFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates gameDAO object with factory;
		gameTypeDao = (PostgresGameTypeDAO) daoFactory.getDao(PostgresDAOFactory.GAME_TYPE_DAO);
	}
	
	public static GameTypeFacade getInstance(){
		if(gameTypeFacade == null)
			gameTypeFacade = new GameTypeFacade();
		return gameTypeFacade;
	}
	
	public ArrayList<GameType> getAll() throws SQLException{
		return gameTypeDao.getAll();
	}
}
