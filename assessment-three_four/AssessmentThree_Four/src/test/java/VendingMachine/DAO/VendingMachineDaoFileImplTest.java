/**
 * VendingMachineDaoFileImplTest - test class for the VendingMachineDaoFileImpl
 * class
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 07/27/2020
 */

package VendingMachine.DAO;

import VendingMachine.DTO.VendingMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineDaoFileImplTest
{    
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest(){}
    
    @BeforeAll
    public static void setUpClass(){}
    
    @AfterAll
    public static void tearDownClass(){}
    
    @BeforeEach
    public void setUp()
    {
        String testFile = "files/test/testvendingmachine.txt";
        testDao = new VendingMachineDaoFileImpl(testFile);
    }
    
    @AfterEach
    public void tearDown(){}

    @Test
    public void testRemoveItem() throws Exception
    {
        VendingMachine firstDrink = new VendingMachine("Dasani");
        firstDrink.setDrinkPrice("1.29");
        firstDrink.setDrinkType("Water");
        firstDrink.setNumInventory(5);
        
        VendingMachine secondDrink = new VendingMachine("Gatorade");
        secondDrink.setDrinkPrice("2.49");
        secondDrink.setDrinkType("Sports");
        secondDrink.setNumInventory(1);
        
        VendingMachine thirdDrink = new VendingMachine("Apple Juice");
        thirdDrink.setDrinkPrice("2.09");
        thirdDrink.setDrinkType("Juice");
        thirdDrink.setNumInventory(0);
    }   
}