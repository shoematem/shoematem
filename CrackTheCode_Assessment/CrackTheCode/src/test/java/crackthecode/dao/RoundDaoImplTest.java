/**
 * RoundDaoImplTest
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/15/2020
 */

package crackthecode.dao;

import crackthecode.TestApplicationConfiguration;
import crackthecode.dto.Game;
import crackthecode.dto.Round;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoImplTest
{
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    public RoundDaoImplTest() {}

    @Test
    public void testGetRoundByID()
    {
        Game game = new Game();
        game.setGameID(1);
        game.setAnswer("1234");
        game.setGameStatus("In Progress");
        
        gameDao.addGame(game, game.getAnswer());
        
        Round round = new Round();
        round.setRoundID(1);
        round.setTimeOfGuess("2020-08-15");
        round.setGuess("0001");
        round.setResult("e:0:p:1");
        round.setGameID(game.getGameID());
        
        Round fromDao = roundDao.addRound(round);
        
        assertEquals(round, fromDao);
        
        roundDao.deleteRounds();
        gameDao.deleteGames();
    }

    @Test
    public void testAddRound()
    {
        Game game = new Game();
        game.setGameID(1);
        game.setAnswer("1234");
        game.setGameStatus("In Progress");
        
        gameDao.addGame(game, game.getAnswer());
        
        Round round = new Round();
        round.setRoundID(1);
        round.setTimeOfGuess("2020-08-15");
        round.setGuess("0001");
        round.setResult("e:0:p:1");
        round.setGameID(game.getGameID());
        
        Round fromDao = roundDao.addRound(round);
        
        assertEquals(round, fromDao);
        
        roundDao.deleteRounds();
        gameDao.deleteGames();
    }
}