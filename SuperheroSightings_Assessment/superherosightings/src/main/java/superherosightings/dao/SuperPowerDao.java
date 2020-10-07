/**
 * SuperPowerDao - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.util.List;
import superherosightings.dto.SuperPower;

public interface SuperPowerDao
{
    SuperPower getSuperPowerByID(int superPowerID);
    List<SuperPower> getAllSuperPowers();
    SuperPower addSuperPower(SuperPower superPower);
    void updateSuperPower(SuperPower superPower);
    void deleteSuperPowerByID(int superPowerID);
    
    List<SuperPower> getAllSuperPowersBySuperPerson(int superPersonID);
}