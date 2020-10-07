/**
 * SightingsServiceLayer - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.service;

import java.util.List;
import superherosightings.dao.SightingsDao;
import superherosightings.dto.Sightings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SightingsServiceLayer
{
    @Autowired
    SightingsDao sightingsDao;

    public SightingsServiceLayer(SightingsDao sightingsDao)
    {
        this.sightingsDao = sightingsDao;
    }
    
    public Sightings getSightingByID(int sightingID)
    {
        return sightingsDao.getSightingByID(sightingID);
    }
    
    public List<Sightings> getAllSightings()
    {
        return sightingsDao.getAllSightings();
    }
    
    public Sightings addSighting(Sightings sightings)
    {
        return sightingsDao.addSighting(sightings);
    }
    
    public void updateSighting(Sightings sightings)
    {
        sightingsDao.updateSighting(sightings);
    }
    
    public void deleteSightingByID(int sightingID)
    {
        sightingsDao.deleteSightingByID(sightingID);
    }
    
    public List<Sightings> getSightingsByLocation(int locationID)
    {
        return sightingsDao.getSightingsByLocation(locationID);
    }
    
    public List<Sightings> getSightingsBySuperPerson(int superPersonID)
    {
        return sightingsDao.getSightingsBySuperPerson(superPersonID);
    }
    
    public List<Sightings> getRecentSightings()
    {
        return sightingsDao.getRecentSightings();
    }
}