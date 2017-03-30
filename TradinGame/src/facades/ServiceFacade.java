package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PostgresGameDAO;
import DAO.PostgresServiceDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.Service;
import models.User;

/**
 * Facade used to manage persistant datas about services
 * @author Max Cabourg - Polytech Montpellier
 *
 */
public class ServiceFacade {
	
	private static ServiceFacade serviceFacade;
	private PostgresServiceDAO serviceDao;
	
	private ServiceFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates userDAO object with factory;
		serviceDao = (PostgresServiceDAO) daoFactory.getDao(PostgresDAOFactory.SERVICE_DAO);

	}
	
	/**
	 * Method based on the singleton design pattern
	 * @return the unique instance of the class
	 */
	public static ServiceFacade getInstance(){
		if(serviceFacade == null)
			serviceFacade = new ServiceFacade();
		return serviceFacade;
	}
	
	/**
	 * Inserts a service in the database
	 * @param service the service to be inserted
	 * @return 1 if the query succeeded, 0 otherwise
	 * @throws SQLException if the query fails
	 */
	public int insertService(Service service) throws SQLException{
		return serviceDao.create(service);
	}
	
	/**
	 * Returns a service based on its ID
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Service getServiceFromID(int id) throws SQLException {
		return serviceDao.find(id);
	}
	
	/**
	 * Gets all the services contained in the database
	 * @return an ArrayList of services
	 * @throws SQLException if the query fails
	 */
	public ArrayList<Service> getAllServices() throws SQLException{
		return serviceDao.getAll();
	}

	public ArrayList<Service> getUnbookedServices() throws  SQLException{
		return serviceDao.getUnbookedServices();
	}

	/**
	 * Gets all the services created by a given user
	 * @param user the user concerned
	 * @return an ArrayList of services
	 * @throws SQLException if the query fails
	 */
	public ArrayList<Service> getAllServicesByUser(User user) throws SQLException{
		return serviceDao.getAllByUser(user);
	}

	/**
	 * Deletes a service in the database
	 * @param service the service to be deleted
	 * @return 1 if the query succeeded, 0 otherwise
	 * @throws SQLException if the query fails
	 */
	public int deleteService(Service service) throws SQLException{
		return serviceDao.delete(service);
	}

	public int bookService(Service service) throws SQLException{

		return serviceDao.bookService(service);
	}

}
