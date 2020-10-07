/**
 * ServiceLayer - this class generates a random answer for the user to guess and
 * generates the user's guess so see how many exact or partial matches they have;
 * it also confirms if the game ID is finished or not
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/14/2020
 */

package crackthecode.service;

import crackthecode.dao.GameDao;
import crackthecode.dao.RoundDao;
import crackthecode.dto.Game;
import crackthecode.dto.Round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrackTheCodeServiceLayer
{
    @Autowired
    private final GameDao gameDao;
    
    @Autowired
    private final RoundDao roundDao;
    
    /**
     * CrackTheCodeServiceLayer - a constructor that passes in the GameDao and
     * RoundDao class object
     * -------------------------------------------------------------------------
     * @param gameDao 
     * @param roundDao
     */
    public CrackTheCodeServiceLayer(GameDao gameDao, RoundDao roundDao)
    {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    
    public List<Game> getAllGames()
    {
        return gameDao.getAllGames();
    }
    
    public Game addGame(Game game)
    {
        String gameAnswer = generateAnswer(); //generate a 4 digit answer for the new game (can be 0000)
        
        return gameDao.addGame(game, gameAnswer);
    }
    
    public Round getRoundByID(int roundID)
    {
        return roundDao.getRoundByID(roundID);
    }
    
    public Game getGameByID(int gameID)
    {
        return gameDao.getGameByID(gameID, false);
    }
    
    public Round addRound(Round round)
    {
        Round newRound = generateGuess(round); //this generates the results of the guess (ex: e:0:p:2)
        confirmPendingGame(round.getGameID()); //if the game is finished, then update the the game to be finished
        
        return roundDao.addRound(newRound);
    }
    
    /**
     * generateAnswer - this generates a 4 digit number (converted to a string)
     * with the random class; it is generated to a String as an int will not
     * display a number such as 0001 (this way a String will)
     * -------------------------------------------------------------------------
     * @return - the 4 digit number
     */
    public String generateAnswer()
    {
        Random answer = new Random();
        
        //randomize an int between 0000 - 9999 and display it as a 4 digit number every time
        String gameAnswer = String.format("%04d", answer.nextInt(10000));
        
        return gameAnswer;
    }
    
    /**
     * generateGuess - this gets the guess from the user and generates the results;
     * it generates an exact match to the position of the number and a partial
     * match if the number exists in the round answer but not at that specific
     * position
     * -------------------------------------------------------------------------
     * @param round - the round object passed from what the user created
     * @return - the result of the guess
     */
    public Round generateGuess(Round round)
    {
        int exactAnswer = 0, partialAnswer = 0;
        String[] guessArr, gameAnswerArr;

        Game game = gameDao.getGameByID(round.getGameID(), true); //get the Game object from the game ID
        String gameAnswer = game.getAnswer(); //get the Game answer from the Game object
        String guess = round.getGuess(); //get the Round guess from the Round object
                
        if(guess.equals(gameAnswer))
        {
            gameDao.updateGame(round.getGameID()); //update the game so that specific game ID is marked finished
            exactAnswer = 4;
        } else
        {
            guessArr = guess.split(""); //split the guess by each character
            gameAnswerArr = gameAnswer.split(""); //split the answer by each character
            
            if(gameAnswerArr[0].equals(guessArr[0]))
            {
                exactAnswer++;
            } else if(gameAnswerArr[0].equals(guessArr[1]) || gameAnswerArr[0].equals(guessArr[2]) || gameAnswerArr[0].equals(guessArr[3]))
            {
                partialAnswer++;
            }
            
            if(gameAnswerArr[1].equals(guessArr[1]))
            {
                exactAnswer++;
            } else if(gameAnswerArr[1].equals(guessArr[0]) || gameAnswerArr[1].equals(guessArr[2]) || gameAnswerArr[1].equals(guessArr[3]))
            {
                partialAnswer++;
            }
            
            if(gameAnswerArr[2].equals(guessArr[2]))
            {
                exactAnswer++;
            } else if(gameAnswerArr[2].equals(guessArr[0]) || gameAnswerArr[2].equals(guessArr[1]) || gameAnswerArr[2].equals(guessArr[3]))
            {
                partialAnswer++;
            }
            
            if(gameAnswerArr[3].equals(guessArr[3]))
            {
                exactAnswer++;
            } else if(gameAnswerArr[3].equals(guessArr[0]) || gameAnswerArr[3].equals(guessArr[1]) || gameAnswerArr[3].equals(guessArr[2]))
            {
                partialAnswer++;
            }
        }
        
        String gameResult = "e:" + exactAnswer + ":p:" + partialAnswer;
        round.setResult(gameResult);
        
        return round;
    }
    
    /**
     * confirmPendingGame - this confirms if the game ID, chosen to add a new round,
     * is finished or not
     * -------------------------------------------------------------------------
     * @param gameID - game ID the user wants to add a round to
     */
    public void confirmPendingGame(int gameID)
    {
        Game game = gameDao.getGameByID(gameID, false); //get the game object by game ID
    }
}