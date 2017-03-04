package factories;

import DAO.AbstractDAO;
import DAO.UserDAO;

public class DAOFactory extends AbstractDAOFactory{
	
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
