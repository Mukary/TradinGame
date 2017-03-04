package facades;

import factories.AbstractDAOFactory;
import factories.DAOFactory;
import models.User;

import java.sql.SQLException;

import DAO.AbstractDAO;
import DAO.UserDAO;

public class UserFacade {
	
	private User user;
	private UserDAO userDAO;
	
	public UserFacade(User user){
		this();
		this.user = user;
	}
	public UserFacade(){
		//Creates DAOfactory
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		//Creates userDAO object with factory;
		userDAO = (UserDAO) daoFactory.getDao(DAOFactory.USER_DAO);
	}
	
	public User login(String nickname, String password) throws SQLException{
		return userDAO.find(nickname, password);
	}

}
