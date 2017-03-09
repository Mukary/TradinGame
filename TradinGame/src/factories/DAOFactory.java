package factories;

import DAO.AbstractDAO;
import DAO.UserDAO;

/**
 * Concrete factory creating different DAO objects that will manage persistent datas on a PostgreSQL database
 * @author bouygueq
 *
 */
public class DAOFactory extends AbstractDAOFactory{
	
	/**
	 * Static constant representing an user dao. To be used in the getDao method
	 */
	public final static int USER_DAO = 0;
	
	/**
	 * Produces a concrete DAO object given the type
	 * @param type the type of DAO we want to create : AbstractFactory.USER_DAO for userDAO
	 * @return a concrete DAO object given the type in argument
	 */
	public AbstractDAO<?> getDao(int type){
		switch(type){
		case USER_DAO:
			return new UserDAO();
		default:
			return null;
		}
	}

}
