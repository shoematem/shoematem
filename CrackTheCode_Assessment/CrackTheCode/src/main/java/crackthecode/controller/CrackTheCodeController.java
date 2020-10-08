/**
 * CrackTheCodeController - this class is the backbone of the program and directs
 * the program where it needs to go. It uses RestController for contacting the
 * server
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/14/2020
 */

package crackthecode.controller;

import crackthecode.dao.GameDao;
import crackthecode.dao.RoundDao;
import crackthecode.dto.*;
import crackthecode.service.CrackTheCodeServiceLayer;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crackthecode")
public class CrackTheCodeController
{
    private final CrackTheCodeServiceLayer myService;
    
    /**
     * CrackTheCodeController - a constructor that passes through several class
     * objects
     * -------------------------------------------------------------------------
     * @param gameDao
     * @param roundDao
     * @param myService 
     */
    public CrackTheCodeController(CrackTheCodeServiceLayer myService)
    {
        this.myService = myService;
    }
    
    /**
     * getAllGames - this returns a list of all games to the user
     * -------------------------------------------------------------------------
     * @return - list of all games
     */
    @GetMapping("/game")
    public List<Game> getAllGames()
    {
        return myService.getAllGames();
    }
    
    /**
     * addGame - this adds a game to the database and it reveals to the user it
     * was created
     * -------------------------------------------------------------------------
     * @param game - the body of JSON object returned from postman
     * @return - the added game
     */
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game)
    {
        return myService.addGame(game);
    }
    
    /**
     * getRoundByID - this gets the round by round ID and displays it back to
     * the user; if there is no round ID found, it will display a NOT_FOUND message
     * -------------------------------------------------------------------------
     * @param roundID - the round ID the user wants to find
     * @return - null if no round ID or the round ID found
     */
    @GetMapping("/round/{roundID}")
    public ResponseEntity<Round> getRoundByID(@PathVariable int roundID)
    {
        Round result = myService.getRoundByID(roundID); //get the round by round ID
        
        if(result == null)
        {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND); //display a NOT_FOUND to the user in the browser
        }
        
        return ResponseEntity.ok(result); //display the result of the round to the user
    }
    
    /**
     * getGameByID - this gets the game by game ID and displays it back to the
     * user; if there is no game ID found, it will display a NOT_FOUND message
     * -------------------------------------------------------------------------
     * @param gameID - the game ID the user wants to find
     * @return - null if no game ID or the game ID found
     */
    @GetMapping("/game/{gameID}")
    public ResponseEntity<Game> getGameByID(@PathVariable int gameID)
    {
        Game result = myService.getGameByID(gameID); //get the game by game ID
        
        if(result == null)
        {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND); //display a NOT_FOUND to the user in the browser
        }
        
        return ResponseEntity.ok(result); //display the result of the game to the user
    }
    
    /**
     * addRound - this adds a round by the game ID (if the game is not finished)
     * that the user wants; it will go to the service layer to generate the guess
     * -------------------------------------------------------------------------
     * @param round
     * @return 
     */
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round addRound(@RequestBody Round round)
    {
        return myService.addRound(round);
    }   
}