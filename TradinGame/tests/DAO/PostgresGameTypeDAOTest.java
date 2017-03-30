package DAO;

import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.GameType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bouygueq on 29/03/2017.
 */
public class PostgresGameTypeDAOTest {

    //Creates DAOfactory
    AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
    //Creates userDAO object with factory;
    PostgresGameTypeDAO gameTypeDaoTest = (PostgresGameTypeDAO) daoFactory.getDao(PostgresDAOFactory.GAME_TYPE_DAO);

    GameType newGameTypeTest = new GameType("gameTypeTest");

    @Test
    public void create() throws Exception {
        assertEquals(1, gameTypeDaoTest.create(newGameTypeTest));
    }

    @Test
    public void find() throws Exception {
        GameType gameTypeTest = gameTypeDaoTest.find("gameTypeTest");
        assertEquals(newGameTypeTest.getLabel(), gameTypeTest.getLabel());
    }

}