package DAO;

import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.Service;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Created by bouygueq on 29/03/2017.
 */
public class PostgresServiceDAOTest {

    //Creates DAOfactory
    AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
    //Creates userDAO object with factory;
    PostgresServiceDAO serviceDaoTest = (PostgresServiceDAO) daoFactory.getDao(PostgresDAOFactory.SERVICE_DAO);
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    Service newServiceTest = new Service(1, "descriptionTest", sqlDate, "userTest", "serviceTypeTest", "gameTest", "userTest");

    @Test
    public void create() throws Exception {
        assertEquals(1, serviceDaoTest.create(newServiceTest));
    }

}