package factories;

public class AbstractDAOFactory {
	
	public final static int DAO_FACTORY = 0;
	
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
