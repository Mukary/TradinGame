package factories;

import DAO.AbstractDAO;
import DAO.PostgresGameDAO;
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
	public final static int GAME_DAO = 1;
	public final static int SERVICE_TYPE_DAO = 2;
	
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
		default:
			return null;
		}
	}

}
