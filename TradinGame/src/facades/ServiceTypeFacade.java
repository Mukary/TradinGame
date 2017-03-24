package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PostgresGameDAO;
import DAO.PostgresServiceTypeDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.ServiceType;

public class ServiceTypeFacade {

	private static ServiceTypeFacade serviceTypeFacade;
	private PostgresServiceTypeDAO postgresServiceTypeDao;
	
	private ServiceTypeFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates userDAO object with factory;
		postgresServiceTypeDao = (PostgresServiceTypeDAO) daoFactory.getDao(PostgresDAOFactory.SERVICE_TYPE_DAO);
		
	}
	
	public static ServiceTypeFacade getInstance(){
		if(serviceTypeFacade == null)
			serviceTypeFacade = new ServiceTypeFacade();
		return serviceTypeFacade;
	}
	
	public ArrayList<ServiceType> getAllServiceTypes() throws SQLException{
		return postgresServiceTypeDao.getAll();
	}
}
