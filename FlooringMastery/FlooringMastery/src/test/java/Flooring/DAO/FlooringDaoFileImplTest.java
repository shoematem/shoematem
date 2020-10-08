/**
 * FlooringDaoFileImplTest
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 08/01/2020
 */
package Flooring.DAO;

import Flooring.DTO.Orders;
import java.io.FileWriter;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlooringDaoFileImplTest
{
    FlooringDao testDao;
    
    public FlooringDaoFileImplTest(){}
    
    @BeforeAll
    public static void setUpClass() {}
    
    @AfterAll
    public static void tearDownClass() {}
    
    @BeforeEach
    public void setUp() throws Exception
    {
        String testFile = "files/Test/Orders.txt";
        
        //Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new FlooringDaoFileImpl(testFile);
    }
    
    @AfterEach
    public void tearDown() {}

    @Test
    public void testAddOrder() throws Exception
    {
        //Create our method test inputs
        int orderID = 1;
        Orders order = new Orders(orderID);
        order.setCustomerName("Matthew Shoemate");
        order.setState("WA");
        order.setProductType("Tile");
        order.setArea("150");
        
        //Add the order to the DAO
        testDao.CreateOrder(order, "");
        
        //Get the order from the DAO
        Map<Integer, Orders> retrievedOrder = testDao.GetOrdersByDate("");
        
        int getOrderID = 0;
        String getCustomerName = "", getState = "", getProductType = "", getArea = "";
        String[] orderArr;
        
        for(Integer orderKey : retrievedOrder.keySet())
        {
            getOrderID = orderKey;
            
            orderArr = retrievedOrder.get(orderKey).toString().split("/");
            getCustomerName = orderArr[0];
            getState = orderArr[1];
            getProductType = orderArr[3];
            getArea = orderArr[4];
        }
        
        //Check the data is equal
        assertEquals(order.getOrderID(), getOrderID, "Checking order ID.");
        assertEquals(order.getCustomerName(), getCustomerName, "Checking order customer name.");
        assertEquals(order.getState(), getState, "Checking order state.");
        assertEquals(order.getProductType(), getProductType, "Checking order product type.");
        assertEquals(order.getArea(), getArea, "Checking order area.");
    }
    
    @Test
    public void testRemoveOrder() throws Exception
    {
        //Create two new orders
        Orders firstOrder = new Orders(1);
        firstOrder.setCustomerName("Matthew Shoemate");
        firstOrder.setState("WA");
        firstOrder.setProductType("Tile");
        firstOrder.setArea("150");
        
        Orders secondOrder = new Orders(2);
        secondOrder.setCustomerName("Matthew R Shoemate");
        secondOrder.setState("CA");
        secondOrder.setProductType("Wood");
        secondOrder.setArea("200");
        
        //Add both to the DAO
        testDao.CreateOrder(firstOrder, "");
        testDao.CreateOrder(secondOrder, "");
        
        //Remove the first order - Matthew Shoemate
        Orders removedOrder = testDao.RemoveOrder(firstOrder.getOrderID(), "");
        
        //Check that the correct object was removed.
        assertEquals(removedOrder, firstOrder, "The removed order should be Matthew Shoemate.");
        
        //Get all the orders
        Map<Integer, Orders> allOrders = testDao.GetOrdersByDate("");
        
        //First check the general contents of the list
        assertNotNull(allOrders, "All orders list should be not null.");
        assertEquals(1, allOrders.size(), "All orders should have only have 1 order.");
        
        //Then the specifics
        assertFalse(allOrders.containsKey(firstOrder.getOrderID()), "All orders should NOT include Matthew Shoemate.");
        assertTrue(allOrders.containsKey(secondOrder.getOrderID()), "All orders should NOT include Matthew R Shoemate.");
        
        //Remove the second order
        removedOrder = testDao.RemoveOrder(secondOrder.getOrderID(), "");
        
        //Check that the correct object was removed.
        assertEquals(removedOrder, secondOrder, "The removed order should be Matthew R Shoemate.");
        
        //retrieve all of the orders again, and check the list.
        allOrders = testDao.GetOrdersByDate("");
        
        //Check the contents of the list - it should be empty
        assertTrue(allOrders.isEmpty(), "The retrieved list of orders should be empty.");
    }
}