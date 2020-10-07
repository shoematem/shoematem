/**
 * LocationServiceLayer - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.service;

import java.util.List;
import superherosightings.dao.LocationDao;
import superherosightings.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceLayer
{
    @Autowired
    LocationDao locationDao;

    public LocationServiceLayer(LocationDao locationDao)
    {
        this.locationDao = locationDao;
    }
    
    public Location getLocationByID(int locationID)
    {
        return locationDao.getLocationByID(locationID);
    }
    
    public List<Location> getAllLocations()
    {
        return locationDao.getAllLocations();
    }
    
    public Location addLocation(Location location)
    {
        return locationDao.addLocation(location);
    }
    
    public void updateLocation(Location location)
    {
        locationDao.updateLocation(location);
    }
    
    public void deleteLocationByID(int locationID)
    {
        locationDao.deleteLocationByID(locationID);
    }
}