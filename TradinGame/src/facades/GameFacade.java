package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PostgresGameDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.Game;
import models.ServiceType;

public class GameFacade {
	
	private static GameFacade gameFacade;
	private PostgresGameDAO gameDao;
	
	private GameFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates gameDAO object with factory;
		gameDao = (PostgresGameDAO) daoFactory.getDao(PostgresDAOFactory.GAME_DAO);
	}
	
	public static GameFacade getInstance(){
		if(gameFacade == null)
			gameFacade = new GameFacade();
		return gameFacade;
	}
	
	public ArrayList<Game> getAllGames() throws SQLException{
		return gameDao.getAll();
	}
	
	public ArrayList<Game> getAllGamesByServiceType(ServiceType serviceType) throws SQLException{
		return gameDao.getAllByServiceType(serviceType);
	}
	
	public boolean isCompatibleWithServiceType(Game game, ServiceType serviceType) throws SQLException{
		return gameDao.isCompatibleWithServiceType(game, serviceType);
	}
	
	public int insertGame(Game game) throws SQLException{
		return gameDao.create(game);
	}

	public int deleteGame(Game game) throws SQLException{
		return gameDao.delete(game);
	}
	
	public boolean gameAlreadyExists(String gameName) throws SQLException{
		return gameDao.gameAlreadyExists(gameName);
	}

}
