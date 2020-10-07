/**
 * GameDaoImpl - this implements the GameDao's methods
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/14/2020
 */

package crackthecode.dao;

import crackthecode.dto.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GameDaoImpl implements GameDao
{
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class GameMapper implements RowMapper<Game>
    {
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException
        {
            Game game = new Game();
            game.setGameID(rs.getInt("gameID"));
            game.setGameStatus(rs.getString("gameStatus"));
            game.setAnswer(rs.getString("answer"));
            
            return game;
        }
    }
    
    /**
     * getAllGames - gets a query of all games - do not display the answer if the game is not finished
     * -------------------------------------------------------------------------
     * @return - the list of all games
     */
    @Override
    public List<Game> getAllGames()
    {
        final String SELECT_ALL_GAMES = "SELECT gameID, gameStatus, IF(gameStatus = 'In Progress', '', answer) AS 'answer' FROM game";
        
        return jdbc.query(SELECT_ALL_GAMES, new GameDaoImpl.GameMapper());
    }
    
    /**
     * getGameByID - gets a query of the game ID from the game ID passed from the user
     * -------------------------------------------------------------------------
     * @param gameID - the game ID the user wants to view
     * @param isCreateRound
     * @return - the game from the game ID or null if the game ID is not found
     */
    @Override
    public Game getGameByID(int gameID, boolean isCreateRound)
    {
        String SELECT_GAME_BY_ID;
        
        try
        {
            if(!isCreateRound)
            {
                SELECT_GAME_BY_ID = "SELECT gameID, gameStatus, IF(gameStatus = 'In Progress', '', answer) AS 'answer' FROM game WHERE gameID = ?";
            } else
            {
                SELECT_GAME_BY_ID = "SELECT gameID, gameStatus, answer FROM game WHERE gameID = ?";
            }
            
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameDaoImpl.GameMapper(), gameID);
        } catch(DataAccessException e)
        {
            return null;
        }
    }
    
    /**
     * addGame - this creates a game with a new game ID and the answer
     * -------------------------------------------------------------------------
     * @param game - the Game object
     * @param gameAnswer - the 4 digit number
     * @return 
     */
    @Override
    public Game addGame(Game game, String gameAnswer)
    {
        final String ADD_GAME = "INSERT INTO game(gameStatus, answer) VALUES(?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
            ADD_GAME, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, "In Progress");
            statement.setString(2, gameAnswer);
            
            return statement;
        }, keyHolder);
       
        int newGameID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        game.setGameID(newGameID);
        game.setGameStatus("In Progress");
        game.setAnswer("");
        
        return game;
    }
    
    /**
     * updateGame - this updates the game status to finished by the game ID
     * -------------------------------------------------------------------------
     * @param gameID
     */
    @Override
    public void updateGame(int gameID)
    {
        final String UPDATE_GAME = "UPDATE game SET gameStatus = ? WHERE gameID = ?";
        
        jdbc.update(UPDATE_GAME, "finished", gameID);
    }
    
    /**
     * deleteGames - this is used for the tests
     */
    @Override
    public void deleteGames()
    {
        final String DELETE_GAMES = "DELETE FROM game";
        jdbc.update(DELETE_GAMES);
    }
}