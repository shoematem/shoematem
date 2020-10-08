/**
 * SightingsDao - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.util.List;
import superherosightings.dto.Sightings;

public interface SightingsDao
{
    Sightings getSightingByID(int sightingID);
    List<Sightings> getAllSightings();
    Sightings addSighting(Sightings sighting);
    void updateSighting(Sightings sighting);
    void deleteSightingByID(int sightingID);
    
    List<Sightings> getSightingsByLocation(int locationID);
    List<Sightings> getSightingsBySuperPerson(int superPersonID);
    List<Sightings> getRecentSightings();
}