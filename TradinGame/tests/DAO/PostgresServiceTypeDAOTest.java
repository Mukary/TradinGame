package DAO;

import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.ServiceType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mukary on 24/03/2017.
 */
public class PostgresServiceTypeDAOTest {

    //Creates DAOfactory
    AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
    //Creates userDAO object with factory;
    PostgresServiceTypeDAO serviceTypeDaoTest = (PostgresServiceTypeDAO) daoFactory.getDao(PostgresDAOFactory.SERVICE_TYPE_DAO);

    ServiceType newServiceTypeTest = new ServiceType("serviceTypeTest", "descriptionTest");

    @Test
    public void delete() throws Exception {
        assertEquals(1, serviceTypeDaoTest.delete(newServiceTypeTest));
    }

    @Test
    public void create() throws Exception {
        assertEquals(1, serviceTypeDaoTest.create(newServiceTypeTest));
    }

    @Test
    public void find() throws Exception {
        ServiceType serviceTypeTest = serviceTypeDaoTest.find("serviceTypeTest");
        assertEquals(newServiceTypeTest.getLabel(), serviceTypeTest.getLabel());
    }

}