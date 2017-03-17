package tests;

import DAO.PostgresUserDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class UserTest {

    //Creates DAOfactory
    AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
    //Creates userDAO object with factory;
    PostgresUserDAO userDaoTest = (PostgresUserDAO) daoFactory.getDao(PostgresDAOFactory.USER_DAO);
    User userTest = null;

    @Test
    public void findbyNameTest(){
        try {
            userTest = userDaoTest.find("userTest");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals("firstnameTest",userTest.getFirstname());
    }

    @Test
    public void findbyNameAndPasswordTest(){
        try {
            userTest = userDaoTest.find("userTest", "test");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals("firstnameTest",userTest.getFirstname());
    }

}
