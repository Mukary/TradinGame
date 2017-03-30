package DAO;

import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.Game;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mukary on 24/03/2017.
 */
public class PostgresGameDAOTest {

    //Creates DAOfactory
    AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
    //Creates userDAO object with factory;
    PostgresGameDAO gameDaoTest = (PostgresGameDAO) daoFactory.getDao(PostgresDAOFactory.GAME_DAO);
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    Game newGameTest = new Game("gameTest", "editorTest", sqlDate, "gameTypeTest" );


    @Test
    public void delete() throws Exception {
        assertEquals(1, gameDaoTest.delete(newGameTest));
    }

    @Test
    public void create() throws Exception {
        assertEquals(1, gameDaoTest.create(newGameTest));
    }

    @Test
    public void find() throws Exception {
        Game gameTest = gameDaoTest.find("gameTest");
        assertEquals(newGameTest.getName(), gameTest.getName());
    }

}