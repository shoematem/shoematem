/**
 * SuperPersonDaoDB - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import superherosightings.dao.LocationDaoDB.LocationMapper;
import superherosightings.dao.OrganizationsDaoDB.OrganizationsMapper;
import superherosightings.dao.SightingsDaoDB.SightingsMapper;
import superherosightings.dao.SuperPowerDaoDB.SuperPowerMapper;
import superherosightings.dto.Location;
import superherosightings.dto.Organizations;
import superherosightings.dto.Sightings;
import superherosightings.dto.SuperPerson;
import superherosightings.dto.SuperPower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SuperPersonDaoDB implements SuperPersonDao
{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public SuperPerson getSuperPersonByID(int superPersonID)
    {
        try
        {
            final String SELECT_SUPERPERSON_BY_ID = "SELECT * FROM superPerson WHERE superPersonID = ?";
            
            SuperPerson superPerson = jdbc.queryForObject(SELECT_SUPERPERSON_BY_ID, new SuperPersonMapper(), superPersonID);
            superPerson.setSuperPower(getSuperPowerForSuperPerson(superPerson.getSuperPersonID()));
            superPerson.setOrganizations(getOrganizationsForSuperPerson(superPerson.getSuperPersonID()));
            superPerson.setSightings(getSightingsForSuperPerson(superPerson.getSuperPersonID()));
            
            return superPerson;
        } catch (DataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<SuperPerson> getAllSuperPersons()
    {
        final String SELECT_ALL_SUPERPERSONS = "SELECT * FROM superPerson";
        
        List<SuperPerson> superPerson = jdbc.query(SELECT_ALL_SUPERPERSONS, new SuperPersonMapper());
        superPerson = associateSuperPowerOrganizationsSightings(superPerson);
        
        return superPerson;
    }

    @Override
    @Transactional
    public SuperPerson addSuperPerson(SuperPerson superPerson)
    {
        final String INSERT_SUPERPERSON = "INSERT INTO superPerson(superPersonName, superPersonDescription, isSuperHero, superPowerID) " 
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_SUPERPERSON,
                superPerson.getSuperPersonName(),
                superPerson.getSuperPersonDescription(),
                superPerson.getIsSuperHero(),
                superPerson.getSuperPower().getSuperPowerID());
        
        int newSuperPersonID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superPerson.setSuperPersonID(newSuperPersonID);
        insertOrganizationSuperPerson(superPerson);
        
        return superPerson;
    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson)
    {
        final String UPDATE_SUPERPERSON = "UPDATE superPerson SET "
                + "superPersonName = ?, superPersonDescription = ?, isSuperHero = ?, superPowerID = ? "
                + "WHERE superPersonID = ?";
        jdbc.update(UPDATE_SUPERPERSON,
                superPerson.getSuperPersonName(),
                superPerson.getSuperPersonDescription(),
                superPerson.getIsSuperHero(),
                superPerson.getSuperPower().getSuperPowerID(),
                superPerson.getSuperPersonID());
    }

    @Override
    @Transactional
    public void deleteSuperPersonByID(int superPersonID)
    {
        final String DELETE_ORGANIZATIONS_SUPERPERSON = "DELETE FROM organizations_superPerson WHERE superPersonID = ?";
        jdbc.update(DELETE_ORGANIZATIONS_SUPERPERSON, superPersonID);
        
        final String DELETE_SUPERPERSON_SIGHTINGS = "DELETE FROM superPerson_sightings WHERE superPersonID = ?";
        jdbc.update(DELETE_SUPERPERSON_SIGHTINGS, superPersonID);
        
        final String DELETE_SUPERPERSON = "DELETE FROM superPerson WHERE superPersonID = ?";
        jdbc.update(DELETE_SUPERPERSON, superPersonID);
    }

    @Override
    public List<SuperPerson> getSuperPersonBySighting(int sightingID)
    {
        final String SUPERPERSON_BY_SIGHTING = "SELECT sp.superPersonID, sp.superPersonName, sp.superPersonDescription, sp.isSuperHero "
                + "FROM superPerson AS sp JOIN superPerson_sightings AS sps ON sp.superPersonID = sps.superPersonID "
                + "WHERE sps.sightingID = ?";
        
        return jdbc.query(SUPERPERSON_BY_SIGHTING, new SuperPersonMapper(), sightingID);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsBySuperPower(int superPowerID)
    {
        final String ALL_SUPERPERSON_BY_SUPERPOWER = "SELECT sp.superPersonID, sp.superPersonName, sp.superPersonDescription, sp.isSuperHero, "
                + "power.superPowerID, power.superPowerName FROM superPerson AS sp "
                + "JOIN superPower AS power ON sp.superPowerID = power.superPowerID WHERE power.superPowerID = ?";
        
        return jdbc.query(ALL_SUPERPERSON_BY_SUPERPOWER, new SuperPersonMapper(), superPowerID);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsByOrganization(int organizationID)
    {
        final String ALL_SUPERPERSONS_BY_ORGANIZATION = "SELECT sp.superPersonID, sp.superPersonName, sp.superPersonDescription, sp.isSuperHero "
               + "FROM superPerson AS sp JOIN organizations_superPerson AS spo ON sp.superPersonID = spo.superPersonID "
               + "WHERE spo.organizationID = ?";
        
        return jdbc.query(ALL_SUPERPERSONS_BY_ORGANIZATION, new SuperPersonMapper(), organizationID);
    }

    public static final class SuperPersonMapper implements RowMapper<SuperPerson>
    {
        @Override
        public SuperPerson mapRow(ResultSet rs, int index) throws SQLException
        {
            SuperPower superPower = new SuperPower();
            superPower.setSuperPowerID(rs.getInt("superPowerID"));
            //superPower.setSuperPowerName("superPowerName");
            
            SuperPerson superPerson = new SuperPerson();
            superPerson.setSuperPersonID(rs.getInt("superPersonID"));
            superPerson.setSuperPersonName(rs.getString("superPersonName"));
            superPerson.setSuperPersonDescription(rs.getString("superPersonDescription"));
            superPerson.setIsSuperHero(rs.getBoolean("isSuperHero"));
            superPerson.setSuperPower(superPower);

            return superPerson;
        }
    }
    
    private List<SuperPerson> associateSuperPowerOrganizationsSightings(List<SuperPerson> superPersons)
    {
        List<SuperPerson> newSuperPersonList = new ArrayList<SuperPerson>();
        
        for(SuperPerson superPerson : superPersons)
        {
            superPerson.setSuperPower(getSuperPowerForSuperPerson(superPerson.getSuperPersonID()));
            superPerson.setOrganizations(getOrganizationsForSuperPerson(superPerson.getSuperPersonID()));
            superPerson.setSightings(getSightingsForSuperPerson(superPerson.getSuperPersonID()));
            
            newSuperPersonList.add(superPerson);
        }
        
        return newSuperPersonList;
    }
    
    private SuperPower getSuperPowerForSuperPerson(int superPersonID)
    {
        final String SELECT_SUPERPOWER_FOR_SUPERPERSON = "SELECT power.* FROM superPower AS power "
                + "JOIN superPerson AS sp ON power.superPowerID = sp.superPowerID WHERE sp.superPersonID = ?";
        
        return jdbc.queryForObject(SELECT_SUPERPOWER_FOR_SUPERPERSON, new SuperPowerMapper(), superPersonID);
    }
    
    private List<Organizations> getOrganizationsForSuperPerson(int superPersonID)
    {
        final String SELECT_ALL_ORGANIZATIONS_FOR_SUPERPERSON = "SELECT org.* FROM organizations AS org "
                + "JOIN organizations_superPerson AS osp ON org.organizationID = osp.organizationID "
                + "JOIN superPerson AS sp ON osp.superPersonID = sp.superPersonID WHERE sp.superPersonID = ?";
        
        return jdbc.query(SELECT_ALL_ORGANIZATIONS_FOR_SUPERPERSON, new OrganizationsMapper(), superPersonID);
    }
    
    private List<Sightings> getSightingsForSuperPerson(int superPersonID)
    {
        final String SELECT_ALL_SIGHTINGS_FOR_SUPERPERSON = "SELECT loc.*, sight.* FROM sightings AS sight "
                + "JOIN location AS loc ON sight.locationID = loc.locationID "
                + "JOIN superPerson_sightings AS sps ON sight.sightingID = sps.sightingID "
                + "JOIN superPerson AS sp ON sps.superPersonID = sp.superPersonID WHERE sp.superPersonID = ?";
        
        return jdbc.query(SELECT_ALL_SIGHTINGS_FOR_SUPERPERSON, new SightingsMapper(), superPersonID);
    }
    
    private List<Location> getLocationForSightingsSuperPerson(int sightingID)
    {
        final String SELECT_ALL_LOCATION_FOR_SIGHTINGSSUPERPERSON = "SELECT loc.* FROM location AS loc "
                + "JOIN sightings AS sight ON loc.locationID = sight.locationID WHERE sight.sightingID = ?";
        
        return jdbc.query(SELECT_ALL_LOCATION_FOR_SIGHTINGSSUPERPERSON, new LocationMapper(), sightingID);
    }
    
    private void insertOrganizationSuperPerson(SuperPerson superPerson)
    {
        final String INSERT_ORGANIZATION_SUPERPERSON = "INSERT INTO organizations_superPerson(superPersonID, organizationID) VALUES(?,?)";
        
        for(Organizations organization : superPerson.getOrganizations())
        {
            jdbc.update(INSERT_ORGANIZATION_SUPERPERSON,
                    superPerson.getSuperPersonID(),
                    organization.getOrganizationID());
        }
    }
}