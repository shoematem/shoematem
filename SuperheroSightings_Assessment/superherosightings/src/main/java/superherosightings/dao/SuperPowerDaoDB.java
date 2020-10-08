/**
 * SuperPowerDaoDB - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import superherosightings.dto.SuperPower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SuperPowerDaoDB implements SuperPowerDao
{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public SuperPower getSuperPowerByID(int superPowerID)
    {
        try
        {
            final String SELECT_SUPERPOWER_BY_ID = "SELECT * FROM superPower WHERE superPowerID = ?";
            
            return jdbc.queryForObject(SELECT_SUPERPOWER_BY_ID, new SuperPowerMapper(), superPowerID);
        } catch (DataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<SuperPower> getAllSuperPowers()
    {
        final String SELECT_ALL_SUPERPOWERS = "SELECT * FROM superPower WHERE superPowerID <> 1";
        
        return jdbc.query(SELECT_ALL_SUPERPOWERS, new SuperPowerMapper());
    }

    @Override
    @Transactional
    public SuperPower addSuperPower(SuperPower superPower)
    {
        final String INSERT_SUPERPOWER = "INSERT INTO superPower(superPowerName) VALUES(?)";
        jdbc.update(INSERT_SUPERPOWER, superPower.getSuperPowerName());
        
        int newSuperPowerID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superPower.setSuperPowerID(newSuperPowerID);
        
        return superPower;
    }

    @Override
    public void updateSuperPower(SuperPower superPower)
    {
        //if superPowerID = 1... relay an error to the user
        final String UPDATE_SUPERPOWER = "UPDATE superPower SET superPowerName = ? WHERE superPowerID = ?";
        jdbc.update(UPDATE_SUPERPOWER,
                superPower.getSuperPowerName(),
                superPower.getSuperPowerID());
    }

    @Override
    @Transactional
    public void deleteSuperPowerByID(int superPowerID)
    {
        //if superPowerID = 1... relay an error to the user
        final String UPDATE_SUPERPERSON = "UPDATE superPerson SET superPowerID = ? WHERE superPowerID = ?";
        jdbc.update(UPDATE_SUPERPERSON, 1, superPowerID);
        
        final String DELETE_SIGHTING = "DELETE FROM superPower WHERE superPowerID = ?";
        jdbc.update(DELETE_SIGHTING, superPowerID);
    }

    @Override
    public List<SuperPower> getAllSuperPowersBySuperPerson(int superPersonID)
    {
        final String ALL_SUPERPOWERS_BY_SUPERPERSON = "SELECT power.superPower, power.superPowerName FROM superPower AS power "
                + "JOIN superPerson AS sp ON power.superPowerID = sp.superPowerID WHERE sp.superPersonID = ?";
    
        return jdbc.query(ALL_SUPERPOWERS_BY_SUPERPERSON, new SuperPowerMapper(), superPersonID);
    }
    
    public static final class SuperPowerMapper implements RowMapper<SuperPower>
    {
        @Override
        public SuperPower mapRow(ResultSet rs, int index) throws SQLException
        {
            SuperPower superPower = new SuperPower();
            superPower.setSuperPowerID(rs.getInt("superPowerID"));
            superPower.setSuperPowerName(rs.getString("superPowerName"));

            return superPower;
        }
    }
}