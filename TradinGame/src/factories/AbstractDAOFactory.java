package factories;

import DAO.AbstractDAO;

public abstract class AbstractDAOFactory {
	
	public final static int DAO_FACTORY = 0;
	
	
	
	public abstract AbstractDAO<?> getDao(int type);
	
	/**
	 * Produces concrete factory given the type in parameter
	 * @param type the type of factory we want : 0 for DAO factory
	 * @return a concrete factory given the type
	 */
	public static AbstractDAOFactory getFactory(int type){
		switch(type){
		case DAO_FACTORY:
			return new DAOFactory();
		default:
			return null;
		}
	}

}
