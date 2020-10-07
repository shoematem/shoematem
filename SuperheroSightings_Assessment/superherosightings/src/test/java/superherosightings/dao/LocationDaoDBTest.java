/**
 * LocationDaoDBTest - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.util.List;
import superherosightings.dto.Location;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import superherosightings.TestApplicationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class LocationDaoDBTest
{
    @Autowired
    LocationDao locationDao;
    
    public LocationDaoDBTest() {}
    
    @Test
    public void testGetAllLocations()
    {
        Location location1 = new Location();
        Location location2 = new Location();
        
        location1.setLocationName("test one");
        location1.setLocationDescription("one location description");
        location1.setAddress("Test Road");
        location1.setLatitude(123.456789);
        location1.setLongitude(321.987654);
        
        Location fromDao1 = locationDao.addLocation(location1);
        
        location2.setLocationName("test two");
        location2.setLocationDescription("two location description");
        location2.setAddress("Testing Street");
        location2.setLatitude(987.654321);
        location2.setLongitude(789.123456);
        
        Location fromDao2 = locationDao.addLocation(location2);
        
        List<Location> locationList = locationDao.getAllLocations();
        
        assertNotNull(locationList);
        assertEquals(2, locationList.size());
        assertTrue(locationList.contains(location1));
        assertTrue(locationList.contains(location2));
        
        locationDao.deleteLocationByID(fromDao1.getLocationID());
        locationDao.deleteLocationByID(fromDao2.getLocationID());
    }
    
    @Test
    public void testGetLocationByID()
    {
        Location location1 = new Location();
        Location location2 = new Location();
        
        location1.setLocationName("test one");
        location1.setLocationDescription("one location description");
        location1.setAddress("Test Road");
        location1.setLatitude(123.456789);
        location1.setLongitude(321.987654);
        
        Location fromDao1 = locationDao.addLocation(location1);
        
        location2.setLocationName("test two");
        location2.setLocationDescription("two location description");
        location2.setAddress("Testing Street");
        location2.setLatitude(987.654321);
        location2.setLongitude(789.123456);
        
        Location fromDao2 = locationDao.addLocation(location2);
        
        assertEquals(fromDao1, locationDao.getLocationByID(fromDao1.getLocationID()));
        assertEquals(fromDao2, locationDao.getLocationByID(fromDao2.getLocationID()));
        
        assertNotEquals(fromDao1, locationDao.getLocationByID(fromDao2.getLocationID()));
        assertNotEquals(fromDao2, locationDao.getLocationByID(fromDao1.getLocationID()));
        
        locationDao.deleteLocationByID(fromDao1.getLocationID());
        locationDao.deleteLocationByID(fromDao2.getLocationID());
    }
    
    @Test
    public void testAddLocation()
    {
        Location location = new Location();
        
        location.setLocationName("test one");
        location.setLocationDescription("one location description");
        location.setAddress("Test Road");
        location.setLatitude(123.456789);
        location.setLongitude(321.987654);
        
        Location fromDao = locationDao.addLocation(location);
        
        assertEquals(location, fromDao);
        
        locationDao.deleteLocationByID(fromDao.getLocationID());
    }
    
    @Test
    public void testDeleteLocation()
    {
        Location location1 = new Location();
        Location location2 = new Location();
        
        location1.setLocationName("test one");
        location1.setLocationDescription("one location description");
        location1.setAddress("Test Road");
        location1.setLatitude(123.456789);
        location1.setLongitude(321.987654);
        
        Location fromDao1 = locationDao.addLocation(location1);
        
        location2.setLocationName("test two");
        location2.setLocationDescription("two location description");
        location2.setAddress("Testing Street");
        location2.setLatitude(987.654321);
        location2.setLongitude(789.123456);
        
        Location fromDao2 = locationDao.addLocation(location2);
        
        List<Location> locationList = locationDao.getAllLocations();
        
        assertEquals(2, locationList.size());
        
        locationDao.deleteLocationByID(fromDao1.getLocationID());
        
        List<Location> newLocationList = locationDao.getAllLocations();
        
        assertNotEquals(locationList.size(), newLocationList.size());
        assertFalse(newLocationList.contains(fromDao1));
        
        locationDao.deleteLocationByID(fromDao2.getLocationID());
        
        List<Location> zeroLocationList = locationDao.getAllLocations();
        
        assertEquals(0, zeroLocationList.size());
        assertFalse(zeroLocationList.contains(fromDao2));
    }
    
    @Test
    public void testUpdateLocation()
    {
        Location location = new Location();
        
        location.setLocationName("test one");
        location.setLocationDescription("one location description");
        location.setAddress("Test Road");
        location.setLatitude(123.456789);
        location.setLongitude(321.987654);
        
        Location fromDao = locationDao.addLocation(location);
        
        assertEquals(location, fromDao);
        
        location.setLocationName("change");
        assertNotEquals(location, fromDao);
        
        locationDao.updateLocation(location);
        fromDao = locationDao.getLocationByID(fromDao.getLocationID());
        
        assertEquals(location, fromDao);
        
        locationDao.deleteLocationByID(fromDao.getLocationID());
    }
}