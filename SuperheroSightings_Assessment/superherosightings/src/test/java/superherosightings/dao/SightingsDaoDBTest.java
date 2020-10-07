/**
 * SightingsDaoDBTest - 
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
import superherosightings.dto.Sightings;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SightingsDaoDBTest
{
    @Autowired
    SightingsDao sightingsDao;
    
    @Autowired
    LocationDao locationDao;
    
    public SightingsDaoDBTest() {}

    @Test
    public void testGetAllSightings()
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
        
        Sightings sighting1 = new Sightings();
        Sightings sighting2 = new Sightings();
        
        sighting1.setSightingDate("01/01/2020 12:00 AM");
        sighting1.setLocation(fromLocationDao1);
        
        Sightings fromSightingsDao1 = sightingsDao.addSighting(sighting1);
        
        sighting2.setSightingDate("02/02/2020 01:00 AM");
        sighting2.setLocation(fromLocationDao2);
        
        Sightings fromSightingsDao2 = sightingsDao.addSighting(sighting2);
        
        List<Sightings> sightingList = sightingsDao.getAllSightings();
        
        assertNotNull(sightingList);
        assertEquals(2, sightingList.size());
        assertTrue(sightingList.contains(sighting1));
        assertTrue(sightingList.contains(sighting2));
        
        sightingsDao.deleteSightingByID(fromSightingsDao1.getSightingID());
        sightingsDao.deleteSightingByID(fromSightingsDao2.getSightingID());
        locationDao.deleteLocationByID(fromLocationDao1.getLocationID());
        locationDao.deleteLocationByID(fromLocationDao2.getLocationID());
    }
    
    @Test
    public void testGetSightingByID()
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
        
        Sightings sighting1 = new Sightings();
        Sightings sighting2 = new Sightings();
        
        sighting1.setSightingDate("01/01/2020 12:00 AM");
        sighting1.setLocation(fromLocationDao1);
        
        Sightings fromSightingsDao1 = sightingsDao.addSighting(sighting1);
        
        sighting2.setSightingDate("02/02/2020 01:00 AM");
        sighting2.setLocation(fromLocationDao2);
        
        Sightings fromSightingsDao2 = sightingsDao.addSighting(sighting2);
        
        assertEquals(fromSightingsDao1, sightingsDao.getSightingByID(fromSightingsDao1.getSightingID()));
        assertEquals(fromSightingsDao2, sightingsDao.getSightingByID(fromSightingsDao2.getSightingID()));
        
        assertNotEquals(fromSightingsDao1, sightingsDao.getSightingByID(fromSightingsDao2.getSightingID()));
        assertNotEquals(fromSightingsDao2, sightingsDao.getSightingByID(fromSightingsDao1.getSightingID()));
        
        sightingsDao.deleteSightingByID(fromSightingsDao1.getSightingID());
        sightingsDao.deleteSightingByID(fromSightingsDao2.getSightingID());
        locationDao.deleteLocationByID(fromLocationDao1.getLocationID());
        locationDao.deleteLocationByID(fromLocationDao2.getLocationID());
    }
    
    @Test
    public void testAddSighting()
    {
        Location location = new Location();
        
        location.setLocationName("test one");
        location.setLocationDescription("one location description");
        location.setAddress("Test Road");
        location.setLatitude(123.456789);
        location.setLongitude(321.987654);
        
        Location fromLocationDao = locationDao.addLocation(location);
        
        Sightings sighting = new Sightings();
        
        sighting.setSightingDate("01/01/2020 12:00 AM");
        sighting.setLocation(fromLocationDao);
        
        Sightings fromSightingsDao = sightingsDao.addSighting(sighting);
        
        assertEquals(sighting, fromSightingsDao);
        
        sightingsDao.deleteSightingByID(fromSightingsDao.getSightingID());
        locationDao.deleteLocationByID(fromLocationDao.getLocationID());
    }
    
    @Test
    public void testDeleteSighting()
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
        
        Sightings sighting1 = new Sightings();
        Sightings sighting2 = new Sightings();
        
        sighting1.setSightingDate("01/01/2020 12:00 AM");
        sighting1.setLocation(fromLocationDao1);
        
        Sightings fromSightingsDao1 = sightingsDao.addSighting(sighting1);
        
        sighting2.setSightingDate("02/02/2020 01:00 AM");
        sighting2.setLocation(fromLocationDao2);
        
        Sightings fromSightingsDao2 = sightingsDao.addSighting(sighting2);
        
        List<Sightings> sightingList = sightingsDao.getAllSightings();
        
        assertEquals(2, sightingList.size());
        
        sightingsDao.deleteSightingByID(fromSightingsDao1.getSightingID());
        locationDao.deleteLocationByID(fromLocationDao1.getLocationID());
        
        List<Sightings> newSightingList = sightingsDao.getAllSightings();
        
        assertNotEquals(sightingList.size(), newSightingList.size());
        assertFalse(newSightingList.contains(fromSightingsDao1));
        
        sightingsDao.deleteSightingByID(fromSightingsDao2.getSightingID());
        locationDao.deleteLocationByID(fromLocationDao2.getLocationID());
        
        List<Sightings> zeroSightingList = sightingsDao.getAllSightings();
        
        assertEquals(0, zeroSightingList.size());
        assertFalse(zeroSightingList.contains(fromSightingsDao2));
    }
    
    @Test
    public void testUpdateSighting()
    {
        Location location = new Location();
        
        location.setLocationName("test one");
        location.setLocationDescription("one location description");
        location.setAddress("Test Road");
        location.setLatitude(123.456789);
        location.setLongitude(321.987654);
        
        Location fromLocationDao = locationDao.addLocation(location);
        
        Sightings sighting = new Sightings();
        
        sighting.setSightingDate("01/01/2020 12:00 AM");
        sighting.setLocation(fromLocationDao);
        
        Sightings fromSightingsDao = sightingsDao.addSighting(sighting);
        
        assertEquals(sighting, fromSightingsDao);
        
        sighting.setSightingDate("12/12/2012 11:20 PM");
        assertNotEquals(sighting, fromSightingsDao);
        
        sightingsDao.updateSighting(sighting);
        fromSightingsDao = sightingsDao.getSightingByID(fromSightingsDao.getSightingID());
        
        assertEquals(sighting, fromSightingsDao);
        
        sightingsDao.deleteSightingByID(fromSightingsDao.getSightingID());
        locationDao.deleteLocationByID(fromLocationDao.getLocationID());
    }
}