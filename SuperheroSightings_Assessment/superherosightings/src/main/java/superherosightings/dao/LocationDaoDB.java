/**
 * LocationDaoDB - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import superherosightings.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LocationDaoDB implements LocationDao
{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationByID(int locationID)
    {
        try
        {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM location WHERE locationID = ?";
            
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), locationID);
        } catch (DataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations()
    {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM location WHERE locationID <> 1";
        
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location)
    {
        final String INSERT_LOCATION = "INSERT INTO location(locationName, locationDescription, address, latitude, longitude) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());
        
        int newLocationID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationID(newLocationID);
        
        return location;
    }

    @Override
    public void updateLocation(Location location)
    {
        //if locationID = 1... relay an error to the user
        final String UPDATE_LOCATION = "UPDATE location SET locationName = ?, locationDescription = ?, "
                + "address = ?, latitude = ?, longitude = ? "
                + "WHERE locationID = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationID());
    }

    @Override
    @Transactional
    public void deleteLocationByID(int locationID)
    {
        //if locationID = 1... relay an error to the user
        //locationID = 1 may be impossible for the user to click on, so ID would need to be calculated when clicked as ID - 1
        final String UPDATE_ORGANIZATIONS = "UPDATE organizations SET locationID = ? WHERE locationID = ?";
        jdbc.update(UPDATE_ORGANIZATIONS, 1, locationID);
        
        final String UPDATE_SIGHTINGS = "UPDATE sightings SET locationID = ? WHERE locationID = ?";
        jdbc.update(UPDATE_SIGHTINGS, 1, locationID);
        
        final String DELETE_LOCATION = "DELETE FROM location WHERE locationID = ?";
        jdbc.update(DELETE_LOCATION, locationID);
    }
    
    public static final class LocationMapper implements RowMapper<Location>
    {
        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException
        {
            Location location = new Location();
            location.setLocationID(rs.getInt("locationID"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescription(rs.getString("locationDescription"));
            location.setAddress(rs.getString("address"));
            location.setLatitude(rs.getDouble("latitude"));
            location.setLongitude(rs.getDouble("longitude"));

            return location;
        }
    }
}