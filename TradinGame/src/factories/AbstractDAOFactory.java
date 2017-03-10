package factories;

import DAO.AbstractDAO;
import database.Database;

/**
 * Abstract factory creating different types of sub-factories such as XMLFactory for instance. The main goal here is to
 * provide an easy way to anticipate evolutivity of the program. Developers can easily implement other ways to manage persistent datas.
 * @author bouygueq
 *
 */
public abstract class AbstractDAOFactory {
	/**
	 * static constant reprensenting a DAO factory to be used in the getFactory method
	 */
	public final static int POSTGRES_DAO_FACTORY = 0;
	
	
	/**
	 * Abstract method used by the sub-class to create different DAO objects, generally one for each model.
	 * @param type: type of the DAO to create
	 * @return
	 */
	public abstract AbstractDAO<?> getDao(int type);
	
	/**
	 * Produces concrete factory given the type in parameter
	 * @param type the type of factory we want : 0 for DAO factory
	 * @return a concrete factory given the type
	 */
	public static AbstractDAOFactory getFactory(int type){
		switch(type){
		case POSTGRES_DAO_FACTORY:
			Database.setSgbd(Database.POSTGRESQL);
			return new PostgresDAOFactory();
		default:
			return null;
		}
	}

}
