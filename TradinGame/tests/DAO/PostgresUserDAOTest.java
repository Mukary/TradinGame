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
    User userTest = null;

    @Test
    public void create() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void find() throws Exception {
        try {
            userTest = userDaoTest.find("userTest");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals("firstnameTest",userTest.getFirstname());
    }

    @Test
    public void find1() throws Exception {
    }

    @Test
    public void find2() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

}