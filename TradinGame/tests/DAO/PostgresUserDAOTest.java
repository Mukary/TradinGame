package DAO;

import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by Tsiky on 23/03/2017.
 */
public class PostgresUserDAOTest {

    //Creates DAOfactory
    AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
    //Creates userDAO object with factory;
    PostgresUserDAO userDaoTest = (PostgresUserDAO) daoFactory.getDao(PostgresDAOFactory.USER_DAO);
    User newUserTest = new User("userTest", "firstNameTest", "lastNameTest", "passwordTest", "countryTest", "cityTest", "addressTest", false, false);

    @Test
    public void delete(){
        int res = 0;
        try {
            res = userDaoTest.delete(newUserTest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1, res);
    }

    @Test
    public void create() throws Exception {
        assertEquals(1, userDaoTest.create(newUserTest));
    }


    @Test
    public void find() throws Exception {
        User userTest = null;
        try {
            userTest = userDaoTest.find("userTest");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals("firstNameTest",userTest.getFirstname());
    }

}