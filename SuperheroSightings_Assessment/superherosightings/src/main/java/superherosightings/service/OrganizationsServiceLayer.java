/**
 * OrganizationsServiceLayer - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.service;

import java.util.List;
import superherosightings.dao.OrganizationsDao;
import superherosightings.dto.Organizations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationsServiceLayer
{
    @Autowired
    OrganizationsDao orgDao;

    public OrganizationsServiceLayer(OrganizationsDao orgDao)
    {
        this.orgDao = orgDao;
    }
    
    public Organizations getOrganizationByID(int organizationID)
    {
        return orgDao.getOrganizationByID(organizationID);
    }
    
    public List<Organizations> getAllOrganizations()
    {
        return orgDao.getAllOrganizations();
    }
    
    public Organizations addOrganization(Organizations organizations)
    {
        return orgDao.addOrganization(organizations);
    }
    
    public void updateOrganization(Organizations organizations)
    {
        orgDao.updateOrganization(organizations);
    }
    
    public void deleteOrganizationByID(int organizationID)
    {
        orgDao.deleteOrganizationByID(organizationID);
    }
    
    public List<Organizations> getOrganizationsBySuperPerson(int superPersonID)
    {
        return orgDao.getOrganizationsBySuperPerson(superPersonID);
    }
}