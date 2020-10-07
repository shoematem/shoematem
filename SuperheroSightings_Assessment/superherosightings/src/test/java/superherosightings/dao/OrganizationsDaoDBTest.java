/**
 * OrganizationsDaoDBTest - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import superherosightings.TestApplicationConfiguration;
import superherosightings.dto.Location;
import superherosightings.dto.Organizations;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class OrganizationsDaoDBTest
{
    @Autowired
    OrganizationsDao organizationDao;
    
    @Autowired
    LocationDao locationDao;
    
    public OrganizationsDaoDBTest() {}

    @Test
    public void testGetAllOrganizations()
    {
        Location location1 = new Location();
        Location location2 = new Location();
        
        location1.setLocationName("test one");
        location1.setLocationDescription("one location description");
        location1.setAddress("Test Road");
        location1.setLatitude(123.456789);
        location1.setLongitude(321.987654);
        
        location2.setLocationName("test two");
        location2.setLocationDescription("two location description");
        location2.setAddress("Testing Street");
        location2.setLatitude(987.654321);
        location2.setLongitude(789.123456);
        
        Location fromLocationDao1 = locationDao.addLocation(location1);
        Location fromLocationDao2 = locationDao.addLocation(location2);
        
        Organizations org1 = new Organizations();
        Organizations org2 = new Organizations();
        
        org1.setOrganizationName("Hero Organization");
        org1.setOrganizationDescription("this is the hero organization");
        org1.setContactInfo("contact one");
        org1.setLocation(fromLocationDao1);
        
        Organizations fromOrgDao1 = organizationDao.addOrganization(org1);
        
        org2.setOrganizationName("Villain Organization");
        org2.setOrganizationDescription("this is the villain organization");
        org2.setContactInfo("contact two");
        org2.setLocation(fromLocationDao2);
        
        Organizations fromOrgDao2 = organizationDao.addOrganization(org2);
        
        List<Organizations> orgList = organizationDao.getAllOrganizations();
        
        assertNotNull(orgList);
        assertEquals(2, orgList.size());
        assertTrue(orgList.contains(org1));
        assertTrue(orgList.contains(org2));
        
        organizationDao.deleteOrganizationByID(fromOrgDao1.getOrganizationID());
        organizationDao.deleteOrganizationByID(fromOrgDao2.getOrganizationID());
        locationDao.deleteLocationByID(fromLocationDao1.getLocationID());
        locationDao.deleteLocationByID(fromLocationDao2.getLocationID());
    }
    
    @Test
    public void testGetOrganizationByID()
    {
        Location location1 = new Location();
        Location location2 = new Location();
        
        location1.setLocationName("test one");
        location1.setLocationDescription("one location description");
        location1.setAddress("Test Road");
        location1.setLatitude(123.456789);
        location1.setLongitude(321.987654);
        
        location2.setLocationName("test two");
        location2.setLocationDescription("two location description");
        location2.setAddress("Testing Street");
        location2.setLatitude(987.654321);
        location2.setLongitude(789.123456);
        
        Location fromLocationDao1 = locationDao.addLocation(location1);
        Location fromLocationDao2 = locationDao.addLocation(location2);
        
        Organizations org1 = new Organizations();
        Organizations org2 = new Organizations();
        
        org1.setOrganizationName("Hero Organization");
        org1.setOrganizationDescription("this is the hero organization");
        org1.setContactInfo("contact one");
        org1.setLocation(fromLocationDao1);
        
        Organizations fromOrgDao1 = organizationDao.addOrganization(org1);
        
        org2.setOrganizationName("Villain Organization");
        org2.setOrganizationDescription("this is the villain organization");
        org2.setContactInfo("contact two");
        org2.setLocation(fromLocationDao2);
        
        Organizations fromOrgDao2 = organizationDao.addOrganization(org2);
        
        assertEquals(fromOrgDao1, organizationDao.getOrganizationByID(fromOrgDao1.getOrganizationID()));
        assertEquals(fromOrgDao2, organizationDao.getOrganizationByID(fromOrgDao2.getOrganizationID()));
        
        assertNotEquals(fromOrgDao1, organizationDao.getOrganizationByID(fromOrgDao2.getOrganizationID()));
        assertNotEquals(fromOrgDao2, organizationDao.getOrganizationByID(fromOrgDao1.getOrganizationID()));
        
        organizationDao.deleteOrganizationByID(fromOrgDao1.getOrganizationID());
        organizationDao.deleteOrganizationByID(fromOrgDao2.getOrganizationID());
        locationDao.deleteLocationByID(fromLocationDao1.getLocationID());
        locationDao.deleteLocationByID(fromLocationDao2.getLocationID());
    }
    
    @Test
    public void testAddOrganization()
    {
        Location location = new Location();
        
        location.setLocationName("test one");
        location.setLocationDescription("one location description");
        location.setAddress("Test Road");
        location.setLatitude(123.456789);
        location.setLongitude(321.987654);
        
        Location fromLocationDao = locationDao.addLocation(location);
        
        Organizations org = new Organizations();
        
        org.setOrganizationName("Hero Organization");
        org.setOrganizationDescription("this is the hero organization");
        org.setContactInfo("contact one");
        org.setLocation(fromLocationDao);
        
        Organizations fromOrgDao = organizationDao.addOrganization(org);
        
        assertEquals(org, fromOrgDao);
        
        organizationDao.deleteOrganizationByID(fromOrgDao.getOrganizationID());
        locationDao.deleteLocationByID(fromLocationDao.getLocationID());
    }
    
    @Test
    public void testDeleteOrganization()
    {
        Location location1 = new Location();
        Location location2 = new Location();
        
        location1.setLocationName("test one");
        location1.setLocationDescription("one location description");
        location1.setAddress("Test Road");
        location1.setLatitude(123.456789);
        location1.setLongitude(321.987654);
        
        location2.setLocationName("test two");
        location2.setLocationDescription("two location description");
        location2.setAddress("Testing Street");
        location2.setLatitude(987.654321);
        location2.setLongitude(789.123456);
        
        Location fromLocationDao1 = locationDao.addLocation(location1);
        Location fromLocationDao2 = locationDao.addLocation(location2);
        
        Organizations org1 = new Organizations();
        Organizations org2 = new Organizations();
        
        org1.setOrganizationName("Hero Organization");
        org1.setOrganizationDescription("this is the hero organization");
        org1.setContactInfo("contact one");
        org1.setLocation(fromLocationDao1);
        
        Organizations fromOrgDao1 = organizationDao.addOrganization(org1);
        
        org2.setOrganizationName("Villain Organization");
        org2.setOrganizationDescription("this is the villain organization");
        org2.setContactInfo("contact two");
        org2.setLocation(fromLocationDao2);
        
        Organizations fromOrgDao2 = organizationDao.addOrganization(org2);
        
        List<Organizations> orgList = organizationDao.getAllOrganizations();
        
        assertEquals(2, orgList.size());
        
        organizationDao.deleteOrganizationByID(fromOrgDao1.getOrganizationID());
        locationDao.deleteLocationByID(fromLocationDao1.getLocationID());
        
        List<Organizations> newOrgList = organizationDao.getAllOrganizations();
        
        assertNotEquals(orgList.size(), newOrgList.size());
        assertFalse(newOrgList.contains(fromOrgDao1));
        
        organizationDao.deleteOrganizationByID(fromOrgDao2.getOrganizationID());
        locationDao.deleteLocationByID(fromLocationDao2.getLocationID());
        
        List<Organizations> zeroOrgList = organizationDao.getAllOrganizations();
        
        assertEquals(0, zeroOrgList.size());
        assertFalse(zeroOrgList.contains(fromOrgDao2));
    }
    
    @Test
    public void testUpdateOrganization()
    {
        Location location = new Location();
        
        location.setLocationName("test one");
        location.setLocationDescription("one location description");
        location.setAddress("Test Road");
        location.setLatitude(123.456789);
        location.setLongitude(321.987654);
        
        Location fromLocationDao = locationDao.addLocation(location);
        
        Organizations org = new Organizations();
        
        org.setOrganizationName("Hero Organization");
        org.setOrganizationDescription("this is the hero organization");
        org.setContactInfo("contact one");
        org.setLocation(fromLocationDao);
        
        Organizations fromOrgDao = organizationDao.addOrganization(org);
        
        assertEquals(org, fromOrgDao);
        
        org.setOrganizationName("Villain Organization");
        assertNotEquals(org, fromOrgDao);
        
        organizationDao.updateOrganization(org);
        fromOrgDao = organizationDao.getOrganizationByID(fromOrgDao.getOrganizationID());
        
        assertEquals(org, fromOrgDao);
        
        organizationDao.deleteOrganizationByID(fromOrgDao.getOrganizationID());
        locationDao.deleteLocationByID(fromLocationDao.getLocationID());
    }
}