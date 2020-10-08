/**
 * SuperPowerDaoDBTest - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.dao;

import java.util.List;
import superherosightings.dto.SuperPower;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import superherosightings.TestApplicationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SuperPowerDaoDBTest
{
    public SuperPowerDaoDBTest() {}
    
    @Autowired
    SuperPowerDao superPowerDao;

    @Test
    public void testGetAllSuperPowers()
    {
        SuperPower superPower1 = new SuperPower();
        SuperPower superPower2 = new SuperPower();
        
        superPower1.setSuperPowerName("fly");
        superPower2.setSuperPowerName("strength");
        
        SuperPower fromDao1 = superPowerDao.addSuperPower(superPower1);
        SuperPower fromDao2 = superPowerDao.addSuperPower(superPower2);
        
        List<SuperPower> superPowerList = superPowerDao.getAllSuperPowers();
        
        assertNotNull(superPowerList);
        assertEquals(2, superPowerList.size());
        assertTrue(superPowerList.contains(superPower1));
        assertTrue(superPowerList.contains(superPower2));
        
        superPowerDao.deleteSuperPowerByID(fromDao1.getSuperPowerID());
        superPowerDao.deleteSuperPowerByID(fromDao2.getSuperPowerID());
    }
    
    @Test
    public void testGetSuperPowerByID()
    {
        SuperPower superPower1 = new SuperPower();
        superPower1.setSuperPowerName("fly");
        
        SuperPower fromDao1 = superPowerDao.addSuperPower(superPower1);
        
        SuperPower superPower2 = new SuperPower();
        superPower2.setSuperPowerName("strength");
        
        SuperPower fromDao2 = superPowerDao.addSuperPower(superPower2);
        
        assertEquals(fromDao1, superPowerDao.getSuperPowerByID(fromDao1.getSuperPowerID()));
        assertEquals(fromDao2, superPowerDao.getSuperPowerByID(fromDao2.getSuperPowerID()));
        
        assertNotEquals(fromDao1, superPowerDao.getSuperPowerByID(fromDao2.getSuperPowerID()));
        assertNotEquals(fromDao2, superPowerDao.getSuperPowerByID(fromDao1.getSuperPowerID()));
        
        superPowerDao.deleteSuperPowerByID(fromDao1.getSuperPowerID());
        superPowerDao.deleteSuperPowerByID(fromDao2.getSuperPowerID());
    }
    
    @Test
    public void testAddSuperPower()
    {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPowerName("fly");
        
        SuperPower fromDao = superPowerDao.addSuperPower(superPower);
        
        assertEquals(superPower, fromDao);
        
        superPowerDao.deleteSuperPowerByID(fromDao.getSuperPowerID());
    }
    
    @Test
    public void testDeleteSuperPower()
    {
        SuperPower superPower1 = new SuperPower();
        superPower1.setSuperPowerName("fly");
        
        SuperPower fromDao1 = superPowerDao.addSuperPower(superPower1);
        
        SuperPower superPower2 = new SuperPower();
        superPower2.setSuperPowerName("strength");
        
        SuperPower fromDao2 = superPowerDao.addSuperPower(superPower2);
        
        List<SuperPower> superPowerList = superPowerDao.getAllSuperPowers();
        
        assertEquals(2, superPowerList.size());
        
        superPowerDao.deleteSuperPowerByID(fromDao1.getSuperPowerID());
        
        List<SuperPower> newSuperPowerList = superPowerDao.getAllSuperPowers();
        
        assertNotEquals(superPowerList.size(), newSuperPowerList.size());
        assertFalse(newSuperPowerList.contains(fromDao1));
        
        superPowerDao.deleteSuperPowerByID(fromDao2.getSuperPowerID());
        
        List<SuperPower> zeroSuperPowerList = superPowerDao.getAllSuperPowers();
        
        assertEquals(0, zeroSuperPowerList.size());
        assertFalse(zeroSuperPowerList.contains(fromDao2));
    }
    
    @Test
    public void testUpdateSuperPower()
    {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPowerName("Fly");
        
        SuperPower fromDao = superPowerDao.addSuperPower(superPower);
        
        assertEquals(superPower, fromDao);
        
        superPower.setSuperPowerName("Strength");
        assertNotEquals(superPower, fromDao);
        
        superPowerDao.updateSuperPower(superPower);
        fromDao = superPowerDao.getSuperPowerByID(fromDao.getSuperPowerID());
        
        assertEquals(superPower, fromDao);
        
        superPowerDao.deleteSuperPowerByID(fromDao.getSuperPowerID());
    }
}