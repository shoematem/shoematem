/**
 * OrganizationsDao - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.util.List;
import superherosightings.dto.Organizations;

public interface OrganizationsDao
{
    Organizations getOrganizationByID(int organizationID);
    List<Organizations> getAllOrganizations();
    Organizations addOrganization(Organizations organization);
    void updateOrganization(Organizations organization);
    void deleteOrganizationByID(int organizationID);
    
    List<Organizations> getOrganizationsBySuperPerson(int superPersonID);
}