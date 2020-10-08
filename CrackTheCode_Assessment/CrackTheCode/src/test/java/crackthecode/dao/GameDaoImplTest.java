/**
 * GameDaoImplTest
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/15/2020
 */

package crackthecode.dao;

import crackthecode.TestApplicationConfiguration;
import crackthecode.dto.Game;
import crackthecode.service.CrackTheCodeServiceLayer;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDaoImplTest
{
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    CrackTheCodeServiceLayer myService;
    
    public GameDaoImplTest() {}
    
    @Test
    public void testGetAllGames()
    {
        Game game = new Game();
        Game fromDao = gameDao.addGame(game, myService.generateAnswer());
        
        Game game2 = new Game();
        Game fromDao2 = gameDao.addGame(game2, myService.generateAnswer());
        
        List<Game> gameList = gameDao.getAllGames();
        
        assertNotNull(gameList);
        assertEquals(2, gameList.size());
        assertTrue(gameList.contains(fromDao));
        assertTrue(gameList.contains(fromDao2));
        
        gameDao.deleteGames();
    }

    @Test
    public void testGetGameByID()
    {
        Game game = new Game();
        Game fromDao = gameDao.addGame(game, myService.generateAnswer());
        
        Game game2 = new Game();
        Game fromDao2 = gameDao.addGame(game2, myService.generateAnswer());
        
        assertEquals(fromDao, gameDao.getGameByID(fromDao.getGameID(), false));
        assertEquals(fromDao2, gameDao.getGameByID(fromDao2.getGameID(), false));
        
        assertNotEquals(fromDao, gameDao.getGameByID(fromDao2.getGameID(), false));
        assertNotEquals(fromDao2, gameDao.getGameByID(fromDao.getGameID(), false));
        
        gameDao.deleteGames();
    }

    @Test
    public void testAddGame()
    {
        Game game = new Game();
        game.setGameID(1);
        game.setGameStatus("In Progress");
        game.setAnswer("1234");
        
        Game fromDao = gameDao.addGame(game, game.getAnswer());
        
        assertEquals(game, fromDao);
        
        gameDao.deleteGames();
    }

    @Test
    public void testUpdateGame()
    {
        Game game = new Game();
        game.setGameID(1);
        game.setGameStatus("In Progress");
        game.setAnswer("1234");
        
        Game fromDao = gameDao.addGame(game, game.getAnswer());
        assertEquals(game, fromDao);
        
        game.setGameStatus("finished");
        fromDao = gameDao.getGameByID(game.getGameID(), false);
        assertNotEquals(game, fromDao);

        gameDao.deleteGames();
    }   
}