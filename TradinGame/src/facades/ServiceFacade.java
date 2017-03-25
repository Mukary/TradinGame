package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PostgresGameDAO;
import DAO.PostgresServiceDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.Service;

public class ServiceFacade {
	
	private static ServiceFacade serviceFacade;
	private PostgresServiceDAO serviceDao;
	
	private ServiceFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates userDAO object with factory;
		serviceDao = (PostgresServiceDAO) daoFactory.getDao(PostgresDAOFactory.SERVICE_DAO);

	}
	
	public static ServiceFacade getInstance(){
		if(serviceFacade == null)
			serviceFacade = new ServiceFacade();
		return serviceFacade;
	}
	
	public int insertService(Service service) throws SQLException{
		return serviceDao.create(service);
	}
	
	public ArrayList<Service> getAllServices() throws SQLException{
		return serviceDao.getAll();
	}

}
