/**
 * SuperPersonDaoDBTest - 
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
import superherosightings.dto.SuperPerson;
import superherosightings.dto.SuperPower;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SuperPersonDaoDBTest
{
    @Autowired
    SuperPersonDao superPersonDao;
    
    @Autowired
    SuperPowerDao superPowerDao;
    
    public SuperPersonDaoDBTest() {}
    
    @Test
    public void testGetAllSuperPersons()
    {
        SuperPower superPower1 = new SuperPower();
        SuperPower superPower2 = new SuperPower();
        
        superPower1.setSuperPowerName("strength");
        superPower2.setSuperPowerName("immortality");
        
        SuperPower fromSuperPowerDao1 = superPowerDao.addSuperPower(superPower1);
        SuperPower fromSuperPowerDao2 = superPowerDao.addSuperPower(superPower2);
        
        SuperPerson superPerson1 = new SuperPerson();
        SuperPerson superPerson2 = new SuperPerson();
        
        superPerson1.setSuperPersonName("The Hulk");
        superPerson1.setSuperPersonDescription("Big Giant Green Guy");
        superPerson1.setIsSuperHero(true);
        superPerson1.setSuperPower(fromSuperPowerDao1);
        
        superPerson1.setSuperPersonName("Thanos");
        superPerson1.setSuperPersonDescription("A Titan");
        superPerson1.setIsSuperHero(false);
        superPerson1.setSuperPower(fromSuperPowerDao2);
        
        SuperPerson fromSuperPersonDao1 = superPersonDao.addSuperPerson(superPerson1);
        SuperPerson fromSuperPersonDao2 = superPersonDao.addSuperPerson(superPerson2);
        
        List<SuperPerson> superPersonList = superPersonDao.getAllSuperPersons();
        
        assertNotNull(superPersonList);
        assertEquals(2, superPersonList.size());
        assertTrue(superPersonList.contains(superPerson1));
        assertTrue(superPersonList.contains(superPerson2));
        
        superPersonDao.deleteSuperPersonByID(fromSuperPersonDao1.getSuperPersonID());
        superPersonDao.deleteSuperPersonByID(fromSuperPersonDao2.getSuperPersonID());
        superPowerDao.deleteSuperPowerByID(fromSuperPowerDao1.getSuperPowerID());
        superPowerDao.deleteSuperPowerByID(fromSuperPowerDao2.getSuperPowerID());
    }
    
    @Test
    public void testGetSuperPersonByID()
    {
        SuperPower superPower1 = new SuperPower();
        SuperPower superPower2 = new SuperPower();
        
        superPower1.setSuperPowerName("strength");
        superPower2.setSuperPowerName("immortality");
        
        SuperPower fromSuperPowerDao1 = superPowerDao.addSuperPower(superPower1);
        SuperPower fromSuperPowerDao2 = superPowerDao.addSuperPower(superPower2);
        
        SuperPerson superPerson1 = new SuperPerson();
        SuperPerson superPerson2 = new SuperPerson();
        
        superPerson1.setSuperPersonName("The Hulk");
        superPerson1.setSuperPersonDescription("Big Giant Green Guy");
        superPerson1.setIsSuperHero(true);
        superPerson1.setSuperPower(fromSuperPowerDao1);
        
        superPerson1.setSuperPersonName("Thanos");
        superPerson1.setSuperPersonDescription("A Titan");
        superPerson1.setIsSuperHero(false);
        superPerson1.setSuperPower(fromSuperPowerDao2);
        
        SuperPerson fromSuperPersonDao1 = superPersonDao.addSuperPerson(superPerson1);
        SuperPerson fromSuperPersonDao2 = superPersonDao.addSuperPerson(superPerson2);
        
        assertEquals(fromSuperPersonDao1, superPersonDao.getSuperPersonByID(fromSuperPersonDao1.getSuperPersonID()));
        assertEquals(fromSuperPersonDao2, superPersonDao.getSuperPersonByID(fromSuperPersonDao2.getSuperPersonID()));
        
        assertNotEquals(fromSuperPersonDao1, superPersonDao.getSuperPersonByID(fromSuperPersonDao2.getSuperPersonID()));
        assertNotEquals(fromSuperPersonDao2, superPersonDao.getSuperPersonByID(fromSuperPersonDao1.getSuperPersonID()));
        
        superPersonDao.deleteSuperPersonByID(fromSuperPersonDao1.getSuperPersonID());
        superPersonDao.deleteSuperPersonByID(fromSuperPersonDao2.getSuperPersonID());
        superPowerDao.deleteSuperPowerByID(fromSuperPowerDao1.getSuperPowerID());
        superPowerDao.deleteSuperPowerByID(fromSuperPowerDao2.getSuperPowerID());
    }
    
    @Test
    public void testAddSuperPerson()
    {
        SuperPower superPower = new SuperPower();
        
        superPower.setSuperPowerName("strength");
        
        SuperPower fromSuperPowerDao = superPowerDao.addSuperPower(superPower);
        
        SuperPerson superPerson = new SuperPerson();
        
        superPerson.setSuperPersonName("The Hulk");
        superPerson.setSuperPersonDescription("Big Giant Green Guy");
        superPerson.setIsSuperHero(true);
        superPerson.setSuperPower(fromSuperPowerDao);
        
        SuperPerson fromSuperPersonDao = superPersonDao.addSuperPerson(superPerson);
        
        assertEquals(superPerson, fromSuperPersonDao);
        
        superPersonDao.deleteSuperPersonByID(fromSuperPersonDao.getSuperPersonID());
        superPowerDao.deleteSuperPowerByID(fromSuperPowerDao.getSuperPowerID());
    }
    
    @Test
    public void testDeleteSuperPerson()
    {
        SuperPower superPower1 = new SuperPower();
        SuperPower superPower2 = new SuperPower();
        
        superPower1.setSuperPowerName("strength");
        superPower2.setSuperPowerName("immortality");
        
        SuperPower fromSuperPowerDao1 = superPowerDao.addSuperPower(superPower1);
        SuperPower fromSuperPowerDao2 = superPowerDao.addSuperPower(superPower2);
        
        SuperPerson superPerson1 = new SuperPerson();
        SuperPerson superPerson2 = new SuperPerson();
        
        superPerson1.setSuperPersonName("The Hulk");
        superPerson1.setSuperPersonDescription("Big Giant Green Guy");
        superPerson1.setIsSuperHero(true);
        superPerson1.setSuperPower(fromSuperPowerDao1);
        
        superPerson1.setSuperPersonName("Thanos");
        superPerson1.setSuperPersonDescription("A Titan");
        superPerson1.setIsSuperHero(false);
        superPerson1.setSuperPower(fromSuperPowerDao2);
        
        SuperPerson fromSuperPersonDao1 = superPersonDao.addSuperPerson(superPerson1);
        SuperPerson fromSuperPersonDao2 = superPersonDao.addSuperPerson(superPerson2);
        
        List<SuperPerson> superPersonList = superPersonDao.getAllSuperPersons();
        
        assertEquals(2, superPersonList.size());
        
        superPersonDao.deleteSuperPersonByID(fromSuperPersonDao1.getSuperPersonID());
        superPowerDao.deleteSuperPowerByID(fromSuperPowerDao1.getSuperPowerID());
        
        List<SuperPerson> newSuperPersonList = superPersonDao.getAllSuperPersons();
        
        assertNotEquals(superPersonList.size(), newSuperPersonList.size());
        assertFalse(newSuperPersonList.contains(fromSuperPersonDao1));
        
        superPersonDao.deleteSuperPersonByID(fromSuperPersonDao2.getSuperPersonID());
        superPowerDao.deleteSuperPowerByID(fromSuperPowerDao2.getSuperPowerID());
        
        List<SuperPerson> zeroSuperPersonList = superPersonDao.getAllSuperPersons();
        
        assertEquals(0, zeroSuperPersonList.size());
        assertFalse(zeroSuperPersonList.contains(fromSuperPersonDao2));
    }
    
    @Test
    public void testUpdateSuperPerson()
    {
        SuperPower superPower = new SuperPower();
        
        superPower.setSuperPowerName("strength");
        
        SuperPower fromSuperPowerDao = superPowerDao.addSuperPower(superPower);
        
        SuperPerson superPerson = new SuperPerson();
        
        superPerson.setSuperPersonName("The Hulk");
        superPerson.setSuperPersonDescription("Big Giant Green Guy");
        superPerson.setIsSuperHero(true);
        superPerson.setSuperPower(fromSuperPowerDao);
        
        SuperPerson fromSuperPersonDao = superPersonDao.addSuperPerson(superPerson);
        
        assertEquals(superPerson, fromSuperPersonDao);
        
        superPerson.setSuperPersonName("Thanos");
        assertNotEquals(superPerson, fromSuperPersonDao);
        
        superPersonDao.updateSuperPerson(superPerson);
        fromSuperPersonDao = superPersonDao.getSuperPersonByID(fromSuperPersonDao.getSuperPersonID());
        
        assertEquals(superPerson, fromSuperPersonDao);
        
        superPersonDao.deleteSuperPersonByID(fromSuperPersonDao.getSuperPersonID());
        superPowerDao.deleteSuperPowerByID(fromSuperPowerDao.getSuperPowerID());
    }
}