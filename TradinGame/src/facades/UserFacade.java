package facades;

import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.User;

import java.sql.SQLException;

import DAO.AbstractDAO;
import DAO.PostgresUserDAO;

/**
 * Class applying the Facade design patterns. It hides the complexity behind the DAO package and the business logic
 * @author bouygueq
 *
 */
public class UserFacade {
	
	private User user;
	private PostgresUserDAO userDAO;
	
	/**
	 * Constructor
	 * @param user: an User object
	 */
	public UserFacade(User user){
		this();
		this.user = user;
	}
	
	/**
	 * Default constructor. Creates the different DAO objects needed
	 */
	public UserFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		//Creates userDAO object with factory;
		userDAO = (PostgresUserDAO) daoFactory.getDao(PostgresDAOFactory.USER_DAO);
	}
	
	/**
	 * Finds an user in database given his nickname and his password
	 * @param nickname: The nickname of the user to find
	 * @param password: The password of the user to find : NOT HASHED YET
	 * @return the user object if the user was found in the database, null otherwise
	 * @throws SQLException if the SQL query fails
	 */
	public User login(String nickname, String password) throws SQLException{
		return userDAO.find(nickname, password);
	}

}
