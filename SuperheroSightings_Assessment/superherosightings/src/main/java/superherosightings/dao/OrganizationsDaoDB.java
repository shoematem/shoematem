/**
 * OrganizationsDaoDB - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 * @date 08/28/2020
 */

package superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import superherosightings.dao.LocationDaoDB.LocationMapper;
import superherosightings.dao.SuperPersonDaoDB.SuperPersonMapper;
import superherosightings.dto.Location;
import superherosightings.dto.Organizations;
import superherosightings.dto.SuperPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrganizationsDaoDB implements OrganizationsDao
{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organizations getOrganizationByID(int organizationID)
    {
        try
        {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM organizations WHERE organizationID = ?";
            
            Organizations organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationsMapper(), organizationID);
            organization.setLocation(getLocationsForOrganization(organization.getOrganizationID()));
            organization.setSuperPerson(getSuperPersonsForOrganization(organization.getOrganizationID()));
            
            return organization;
        } catch (DataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Organizations> getAllOrganizations()
    {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organizations WHERE organizationID <> 1";
        List<Organizations> organizations = jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationsMapper());
        associateLocationsSuperPerson(organizations);
        
        return organizations;
    }

    @Override
    @Transactional
    public Organizations addOrganization(Organizations organization)
    {
        final String INSERT_ORGANIZATION = "INSERT INTO organizations(organizationName, organizationDescription, contactInfo, locationID) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getContactInfo(),
                organization.getLocation().getLocationID());
        
        int newOrganizationID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationID(newOrganizationID);
        insertOrganizationSuperPerson(organization);
        
        return organization;
    }

    @Override
    public void updateOrganization(Organizations organization)
    {
        //same as SightingsDaoDB -> updateSighting
        final String UPDATE_ORGANIZATION = "UPDATE organizations "
                + "SET organizationName = ?, organizationDescription = ?, contactInfo = ?, locationID = ? "
                + "WHERE organizationID = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getContactInfo(),
                organization.getLocation().getLocationID(),
                organization.getOrganizationID()); //need to get a list/query to find if a location id exists...
    }

    @Override
    @Transactional
    public void deleteOrganizationByID(int organizationID)
    {
        try
        {
            final String UPDATE_ORGANIZATIONS_SUPERPERSON = "UPDATE organizations_superPerson SET organizationID = ? WHERE organizationID = ?";
            jdbc.update(UPDATE_ORGANIZATIONS_SUPERPERSON, 1, organizationID);
        } catch(DataAccessException e)
        {
            final String DELETE_ORGANIZATIONS_SUPERPERSON = "DELETE FROM organizations_superPerson WHERE organizationID = ?";
            jdbc.update(DELETE_ORGANIZATIONS_SUPERPERSON, organizationID);
        }
        
        final String DELETE_ORGANIZATION = "DELETE FROM organizations WHERE organizationID = ?";
        jdbc.update(DELETE_ORGANIZATION, organizationID);
    }
    
    @Override
    public List<Organizations> getOrganizationsBySuperPerson(int superPersonID)
    {
        final String ORGANIZATIONS_BY_SUPERPERSON = "SELECT org.* FROM organizations AS org " 
                + "JOIN organizations_superPerson AS spo ON org.organizationID = spo.organizationID WHERE spo.superPersonID = ?";
        
        return jdbc.query(ORGANIZATIONS_BY_SUPERPERSON, new OrganizationsMapper(), superPersonID);
    }
    
    public static final class OrganizationsMapper implements RowMapper<Organizations>
    {
        @Override
        public Organizations mapRow(ResultSet rs, int index) throws SQLException
        {
            Location location = new Location();
            location.setLocationID(rs.getInt("locationID"));
            //location.setLocationName(rs.getString("locationName"));
            //location.setLocationDescription(rs.getString("locationDescription"));
            //location.setAddress(rs.getString("address"));
            //location.setLatitude(rs.getDouble("latitude"));
            //location.setLongitude(rs.getDouble("longitude"));
            
            Organizations organization = new Organizations();
            organization.setOrganizationID(rs.getInt("organizationID"));
            organization.setOrganizationName(rs.getString("organizationName"));
            organization.setOrganizationDescription(rs.getString("organizationDescription"));
            organization.setContactInfo(rs.getString("contactInfo"));
            //organization.setLocation(location);

            return organization;
        }
    }
    
    private void associateLocationsSuperPerson(List<Organizations> organizations)
    {
        for(Organizations organization: organizations)
        {
            organization.setLocation(getLocationsForOrganization(organization.getOrganizationID()));
            organization.setSuperPerson(getSuperPersonsForOrganization(organization.getOrganizationID()));
        }
    }
    
    private Location getLocationsForOrganization(int organizationID)
    {
        final String SELECT_LOCATION_FOR_ORGANIZATION = "SELECT loc.* FROM location AS loc "
                + "JOIN organizations AS org ON loc.locationID = org.locationID WHERE org.organizationID = ?";
        
        return jdbc.queryForObject(SELECT_LOCATION_FOR_ORGANIZATION, new LocationMapper(), organizationID);
    }
    
    private List<SuperPerson> getSuperPersonsForOrganization(int organizationID)
    {
        final String SELECT_ALL_SUPERPERSONS_FOR_ORGANIZATION = "SELECT sp.* FROM superPerson AS sp "
                + "JOIN organizations_superPerson AS sps ON sp.superPersonID = sps.superPersonID "
                + "JOIN organizations AS org ON sps.organizationID = org.organizationID WHERE org.organizationID = ?";
        
        return jdbc.query(SELECT_ALL_SUPERPERSONS_FOR_ORGANIZATION, new SuperPersonMapper(), organizationID);
    }
    
    private void insertOrganizationSuperPerson(Organizations organization)
    {
        final String INSERT_ORGANIZATION_SUPERPERSON = "INSERT INTO organizations_superPerson(superPersonID, organizationID) VALUES(?,?)";
        
        for(SuperPerson superPerson : organization.getSuperPerson())
        {
            jdbc.update(INSERT_ORGANIZATION_SUPERPERSON,
                    superPerson.getSuperPersonID(),
                    organization.getOrganizationID());
        }
    }
}