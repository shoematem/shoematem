/**
 * RoundDaoImpl - this implements the RoundDao
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/14/2020
 */

package crackthecode.dao;

import crackthecode.dto.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RoundDaoImpl implements RoundDao
{
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class RoundMapper implements RowMapper<Round>
    {
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException
        {
            Round round = new Round();
            round.setRoundID(rs.getInt("roundID"));
            round.setGuess(rs.getString("guess"));
            round.setResult(rs.getString("result"));
            round.setTimeOfGuess(rs.getTimestamp("timeOfGuess").toLocalDateTime().toString());
            
            return round;
        }
    }
    
    /**
     * getRoundByID - this gets a Round object by the round ID
     * -------------------------------------------------------------------------
     * @param roundID - the round ID the user wants
     * @return - the Round object if the round ID is found -> if not found then return null
     */
    @Override
    public Round getRoundByID(int roundID)
    {
        try
        {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE roundID = ? ORDER BY timeOfGuess";
            
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundDaoImpl.RoundMapper(), roundID);
        } catch(DataAccessException e)
        {
            return null;
        }
    }
    
    /**
     * addRound - this adds a round if the game ID is not finished
     * -------------------------------------------------------------------------
     * @param round
     * @return - the created Round object
     */
    @Override
    public Round addRound(Round round)
    {
        String currentDay = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        round.setTimeOfGuess(currentDay);
        final String ADD_ROUND = "INSERT INTO round(guess, timeOfGuess, result, gameID) VALUES(?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
            ADD_ROUND, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, round.getGuess());
            statement.setString(2, round.getTimeOfGuess());
            statement.setString(3, round.getResult());
            statement.setInt(4, round.getGameID());
            
            return statement;
        }, keyHolder);
       
        round.setRoundID(keyHolder.getKey().intValue());
        
        return round;
    }
    
    @Override
    public void deleteRounds()
    {
        final String DELETE_ROUNDS = "DELETE FROM round";
        jdbc.update(DELETE_ROUNDS);
    }
}