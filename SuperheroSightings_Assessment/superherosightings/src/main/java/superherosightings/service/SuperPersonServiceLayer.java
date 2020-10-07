/**
 * SuperPersonServiceLayer - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.service;

import java.util.List;
import superherosightings.dao.SuperPersonDao;
import superherosightings.dto.Organizations;
import superherosightings.dto.Sightings;
import superherosightings.dto.SuperPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperPersonServiceLayer
{
    @Autowired
    SuperPersonDao superPersonDao;

    public SuperPersonServiceLayer(SuperPersonDao superPersonDao)
    {
        this.superPersonDao = superPersonDao;
    }
    
    public SuperPerson getSuperPersonByID(int superPersonID)
    {
        return superPersonDao.getSuperPersonByID(superPersonID);
    }
    
    public List<SuperPerson> getAllSuperPersons()
    {
        return superPersonDao.getAllSuperPersons();
    }
    
    public SuperPerson addSuperPerson(SuperPerson superPerson)
    {
        return superPersonDao.addSuperPerson(superPerson);
    }
    
    public void updateSuperPerson(SuperPerson superPerson)
    {
        superPersonDao.updateSuperPerson(superPerson);
    }
    
    public void deleteSuperPersonByID(int superPersonID)
    {
        superPersonDao.deleteSuperPersonByID(superPersonID);
    }
    
    public List<SuperPerson> getSuperPersonBySighting(int sightingID)
    {
        return superPersonDao.getSuperPersonBySighting(sightingID);
    }
    
    public List<SuperPerson> getAllSuperPersonsBySuperPower(int superPowerID)
    {
        return superPersonDao.getAllSuperPersonsBySuperPower(superPowerID);
    }
    
    public List<SuperPerson> getAllSuperPersonsByOrganization(int organizationID)
    {
        return superPersonDao.getAllSuperPersonsByOrganization(organizationID);
    }
}