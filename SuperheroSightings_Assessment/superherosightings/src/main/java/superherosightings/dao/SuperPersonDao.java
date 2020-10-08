/**
 * SuperPersonDao - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 * @date 08/28/2020
 */

package superherosightings.dao;

import java.util.List;
import superherosightings.dto.SuperPerson;

public interface SuperPersonDao
{
    SuperPerson getSuperPersonByID(int superPersonID);
    List<SuperPerson> getAllSuperPersons();
    SuperPerson addSuperPerson(SuperPerson superPerson);
    void updateSuperPerson(SuperPerson superPerson);
    void deleteSuperPersonByID(int locationID);
    
    List<SuperPerson> getSuperPersonBySighting(int sightingID);
    List<SuperPerson> getAllSuperPersonsBySuperPower(int superPowerID);
    List<SuperPerson> getAllSuperPersonsByOrganization(int organizationID);
}