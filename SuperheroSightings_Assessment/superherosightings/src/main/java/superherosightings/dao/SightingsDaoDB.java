/**
 * SightingsDaoDB - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import superherosightings.dao.LocationDaoDB.LocationMapper;
import superherosightings.dao.SuperPersonDaoDB.SuperPersonMapper;
import superherosightings.dto.Location;
import superherosightings.dto.Sightings;
import superherosightings.dto.SuperPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SightingsDaoDB implements SightingsDao
{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sightings getSightingByID(int sightingID)
    {
        try
        {
            final String SELECT_SIGHTING_BY_ID = "SELECT loc.*, sight.* FROM sightings AS sight "
                    +"JOIN location AS loc ON sight.locationID = loc.locationID WHERE sight.sightingID = ?";
            
            Sightings sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingsMapper(), sightingID);
            sighting.setSuperPerson(getSuperPersonsForSighting(sighting.getSightingID()));
            
            return sighting;
        } catch (DataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Sightings> getAllSightings()
    {
        final String SELECT_ALL_SIGHTINGS = "SELECT loc.*, sight.* FROM sightings AS sight JOIN location AS loc ON sight.locationID = loc.locationID";
        
        List<Sightings> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingsMapper());
        sightings = associateSuperPerson(sightings);
        
        return sightings;
    }

    @Override
    @Transactional
    public Sightings addSighting(Sightings sighting)
    {
        final String INSERT_SIGHTING = "INSERT INTO sightings(sightingDate, locationID) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getSightingDate(),
                sighting.getLocation().getLocationID());
        
        int newSightingID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(newSightingID);
        insertSuperPersonSighting(sighting);
        
        return sighting;
    }

    @Override
    public void updateSighting(Sightings sighting)
    {
        final String UPDATE_SIGHTING = "UPDATE sightings SET sightingDate = ?, locationID = ? "
                + "WHERE sightingID = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getSightingDate(),
                sighting.getLocation().getLocationID(),
                sighting.getSightingID());
        
        final String DELETE_SUPERPERSON_SIGHTING = "DELETE FROM superPerson_sightings WHERE sightingID = ?";
        jdbc.update(DELETE_SUPERPERSON_SIGHTING, sighting.getSightingID());
            
        insertSuperPersonSighting(sighting);
    }

    @Override
    @Transactional
    public void deleteSightingByID(int sightingID)
    {
        final String DELETE_SUPERPERSON_SIGHTINGS = "DELETE FROM superPerson_sightings WHERE sightingID = ?";
        jdbc.update(DELETE_SUPERPERSON_SIGHTINGS, sightingID);
        
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE sightingID = ?";
        jdbc.update(DELETE_SIGHTING, sightingID);
    }
    
    @Override
    public List<Sightings> getSightingsByLocation(int locationID)
    {
        final String SIGHTINGS_BY_LOCATION = "SELECT loc.*, sight.* FROM location AS loc "
                + "JOIN sightings AS sight ON loc.locationID = sight.locationID WHERE loc.locationID = ?";
        
        return jdbc.query(SIGHTINGS_BY_LOCATION, new SightingsMapper(), locationID);
    }
    
    @Override
    public List<Sightings> getSightingsBySuperPerson(int superPersonID)
    {
        final String SIGHTINGS_BY_LOCATION = "SELECT loc.*, sight.* FROM location AS loc "
                + "JOIN sightings AS sight ON loc.locationID = sight.locationID JOIN superPerson_sightings AS sps ON sight.sightingID = sps.sightingID "
                + "WHERE sps.superPersonID = ?";
        
        return jdbc.query(SIGHTINGS_BY_LOCATION, new SightingsMapper(), superPersonID);
    }
    
    @Override
    public List<Sightings> getRecentSightings()
    {
        final String SELECT_RECENT_SIGHTINGS = "SELECT sight.*, loc.* FROM sightings AS sight "
                + "JOIN location AS loc ON sight.locationID = loc.locationID ORDER BY sight.sightingDate DESC";
        
        List<Sightings> sightings = jdbc.query(SELECT_RECENT_SIGHTINGS, new SightingsMapper());
        List<Sightings> newSightings = new ArrayList<Sightings>();
        
        sightings = associateSuperPerson(sightings);
        
        for(int i = 0; i < sightings.size(); i++)
        {
            Sightings sighting = sightings.get(i);
            //System.out.println(sighting.getSuperPerson().isEmpty());
            if((!sighting.getSuperPerson().isEmpty()) && (i < 10))
            {
                newSightings.add(sighting);
                //System.out.println(newSightings.toString());
            }
        }
        
        return newSightings;
    }
    
    public static final class SightingsMapper implements RowMapper<Sightings>
    {
        @Override
        public Sightings mapRow(ResultSet rs, int index) throws SQLException
        {
            Location location = new Location();
            location.setLocationID(rs.getInt("locationID"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescription(rs.getString("locationDescription"));
            location.setAddress(rs.getString("address"));
            location.setLongitude(rs.getDouble("longitude"));
            location.setLatitude(rs.getDouble("latitude"));
            
            Sightings sighting = new Sightings();
            sighting.setSightingID(rs.getInt("sightingID"));
            sighting.setSightingDate(rs.getString("sightingDate"));
            sighting.setLocation(location);

            return sighting;
        }
    }
    
    private List<Sightings> associateSuperPerson(List<Sightings> sightings)
    {
        List<Sightings> newSightingsList = new ArrayList<Sightings>();
        
        for(Sightings sighting : sightings)
        {
            sighting.setSuperPerson(getSuperPersonsForSighting(sighting.getSightingID()));
            
            newSightingsList.add(sighting);
        }
        
        return newSightingsList;
    }
    
    private List<SuperPerson> getSuperPersonsForSighting(int sightingID)
    {
        final String SELECT_ALL_SUPERPERSONS_FOR_SIGHTING = "SELECT sp.* FROM superPerson AS sp "
                + "JOIN superPerson_sightings AS sps ON sp.superPersonID = sps.superPersonID "
                + "JOIN sightings AS sight ON sps.sightingID = sight.sightingID WHERE sight.sightingID = ?";
        
        return jdbc.query(SELECT_ALL_SUPERPERSONS_FOR_SIGHTING, new SuperPersonMapper(), sightingID);
    }
    
    private void insertSuperPersonSighting(Sightings sighting)
    {
        final String INSERT_SUPERPERSONS_SIGHTING = "INSERT INTO superPerson_sightings(superPersonID, sightingID) VALUES(?,?)";
        
        for(SuperPerson superPerson : sighting.getSuperPerson())
        {
            jdbc.update(INSERT_SUPERPERSONS_SIGHTING,
                    superPerson.getSuperPersonID(),
                    sighting.getSightingID());
        }
    }
}