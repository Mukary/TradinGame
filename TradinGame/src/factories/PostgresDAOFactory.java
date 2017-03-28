package factories;

import DAO.AbstractDAO;
import DAO.PostgresGameDAO;
import DAO.PostgresGameTypeDAO;
import DAO.PostgresServiceDAO;
import DAO.PostgresServiceTypeDAO;
import DAO.PostgresUserDAO;
import database.Database;

/**
 * Concrete factory creating different DAO objects that will manage persistent datas on a PostgreSQL database
 * @author bouygueq
 *
 */
public class PostgresDAOFactory extends AbstractDAOFactory{
	
	/**
	 * Static constant representing an user dao. To be used in the getDao method
	 */
	public final static int USER_DAO = 0;
	/**
	 * Static constant representing a game dao. To be used in the getDao method
	 */
	public final static int GAME_DAO = 1;
	/**
	 * Static constant representing a service type dao. To be used in the getDao method
	 */
	public final static int SERVICE_TYPE_DAO = 2;
	/**
	 * Static constant representing a service dao. To be used in the getDao method
	 */
	public final static int SERVICE_DAO = 3;
	/**
	 * Static constant representing a game type dao. To be used in the getDao method
	 */
	public final static int GAME_TYPE_DAO = 4;
	
	/**
	 * Produces a concrete DAO object given the type
	 * @param type the type of DAO we want to create : AbstractFactory.USER_DAO for userDAO
	 * @return a concrete DAO object given the type in argument
	 */
	public AbstractDAO<?> getDao(int type){
		switch(type){
		case USER_DAO:
			return new PostgresUserDAO();
		case GAME_DAO:
			return new PostgresGameDAO();
		case SERVICE_TYPE_DAO:
			return new PostgresServiceTypeDAO();
		case SERVICE_DAO:
			return new PostgresServiceDAO();
		case GAME_TYPE_DAO:
			return new PostgresGameTypeDAO();
		default:
			return null;
		}
	}

}
