package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PostgresGameDAO;
import DAO.PostgresServiceTypeDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.Game;
import models.ServiceType;

/**
 * Facade used to manage persistant datas about service types
 * @author Max Cabourg - Polytech Montpellier
 *
 */
public class ServiceTypeFacade {

	private static ServiceTypeFacade serviceTypeFacade;
	private PostgresServiceTypeDAO postgresServiceTypeDao;
	
	private ServiceTypeFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates userDAO object with factory;
		postgresServiceTypeDao = (PostgresServiceTypeDAO) daoFactory.getDao(PostgresDAOFactory.SERVICE_TYPE_DAO);
		
	}
	
	/**
	 * Method based on the singleton design pattern
	 * @return the unique instance of the class
	 */
	public static ServiceTypeFacade getInstance(){
		if(serviceTypeFacade == null)
			serviceTypeFacade = new ServiceTypeFacade();
		return serviceTypeFacade;
	}
	
	/**
	 * Gets all the service types contained in the database
	 * @return an ArrayList of service types
	 * @throws SQLException if the query fails
	 */
	public ArrayList<ServiceType> getAllServiceTypes() throws SQLException{
		return postgresServiceTypeDao.getAll();
	}
	
	/**
	 * Gets all the service types concerned by a game
	 * @param game the concerned game
	 * @return an ArrayList of games
	 * @throws SQLException if the query fails
	 */
	public ArrayList<ServiceType> getAllByGame(Game game) throws SQLException{
		return postgresServiceTypeDao.getAllByGame(game);
	}
	
	/**
	 * Creates a new service type
	 */
	public int createServiceType(ServiceType serviceType) throws SQLException{
		return postgresServiceTypeDao.create(serviceType);
	}
	
	/**
	 * Deletes a service type from the admin panel
	 */
	public int deleteServiceType(ServiceType serviceType) throws SQLException{
		return postgresServiceTypeDao.delete(serviceType);
	}
}
