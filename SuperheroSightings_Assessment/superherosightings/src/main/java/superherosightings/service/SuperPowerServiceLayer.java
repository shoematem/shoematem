/**
 * SuperPowerServiceLayer - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.service;

import java.util.List;
import superherosightings.dao.SuperPowerDao;
import superherosightings.dto.SuperPower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperPowerServiceLayer
{
    @Autowired
    SuperPowerDao superPowerDao;

    public SuperPowerServiceLayer(SuperPowerDao superPowerDao)
    {
        this.superPowerDao = superPowerDao;
    }
    
    public SuperPower getSuperPowerByID(int superPowerID)
    {
        return superPowerDao.getSuperPowerByID(superPowerID);
    }
    
    public List<SuperPower> getAllSuperPowers()
    {
        return superPowerDao.getAllSuperPowers();
    }
    
    public SuperPower addSuperPower(SuperPower superPower)
    {
        return superPowerDao.addSuperPower(superPower);
    }
    
    public void updateSuperPower(SuperPower superPower)
    {
        superPowerDao.updateSuperPower(superPower);
    }
    
    public void deleteSuperPowerByID(int superPowerID)
    {
        superPowerDao.deleteSuperPowerByID(superPowerID);
    }
    
    public List<SuperPower> getAllSuperPowersBySuperPerson(int superPersonID)
    {
        return superPowerDao.getAllSuperPowersBySuperPerson(superPersonID);
    }
}