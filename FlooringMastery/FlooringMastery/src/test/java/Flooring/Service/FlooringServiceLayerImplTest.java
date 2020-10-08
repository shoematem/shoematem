/**
 * FlooringServiceLayerImplTest
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         Created: 08/01/2020
 */

package Flooring.Service;

import Flooring.DAO.FlooringPersistenceException;
import Flooring.DTO.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FlooringServiceLayerImplTest
{
    private FlooringServiceLayer service;
    
    public FlooringServiceLayerImplTest()
    {
        //wire the Service Layer with stub implementations of the Dao and Audit Dao
        //ClassRosterDao dao = new ClassRosterDaoStubImpl();
        //ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
        
        //service = new ClassRosterServiceLayerImpl(dao, auditDao);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringServiceLayer.class);
    }
    
    @BeforeAll
    public static void setUpClass(){}
    
    @AfterAll
    public static void tearDownClass(){}
    
    @BeforeEach
    public void setUp(){}
    
    @AfterEach
    public void tearDown(){}

    @Test
    public void testCreateValidStudent()
    {
        //ARRANGE
        Orders order = new Orders(1);
        order.setCustomerName("Matthew Shoemate");
        order.setState("WA");
        order.setProductType("Tile");
        order.setArea("150");
        
        //ACT
        try
        {
            service.CreateOrder(order, "");
        } catch(FlooringPersistenceException e)
        {
            //ASSERT
            fail("Order was valid. No exception should have been thrown.");
        }
    }   
    
    @Test
    public void testCreateOrderInvalidData() throws Exception
    {
        //ARRANGE
        Orders order = new Orders(1);
        order.setCustomerName("Matthew Shoemate");
        order.setState("WA");
        order.setProductType("Tile");
        order.setArea("150");
        
        //ACT
        try
        {
            service.CreateOrder(order, "");
            fail("Expected ValidationException was not thrown.");
        } catch(FlooringPersistenceException e)
        {
            //fail("Incorrect exception was thrown.");
            //assert
        }
    }
    
    @Test
    public void testGetAllStudents() throws Exception
    {
        //ARRANGE
        Orders order = new Orders(1);
        order.setCustomerName("Matthew Shoemate");
        order.setState("WA");
        order.setProductType("Tile");
        order.setArea("150");
        
        //ACT & ASSERT
        assertEquals(1, service.GetOrdersByDate("").size(), "Should only have one order.");
        assertTrue(service.GetOrdersByDate("").containsKey(order.getOrderID()), "The one object should be Matthew Shoemate.");
    }
    
    @Test
    public void testRemoveStudent() throws Exception
    {
        //ARRANGE
        Orders order = new Orders(1);
        order.setCustomerName("Matthew Shoemate");
        order.setState("WA");
        order.setProductType("Tile");
        order.setArea("150");
        
        //ACT & ASSERT
        Orders shouldBeMatthewShoemate = service.RemoveOrder(1, "");
        assertNotNull(shouldBeMatthewShoemate, "Removing 1 should be not null");
        assertEquals(order, shouldBeMatthewShoemate, "Order removed from 1 should be Matthew Shoemate.");
        
        Orders shouldBeNull = service.RemoveOrder(100, "");
        assertNull(shouldBeNull, "Removing 100 should be null.");
    }
}
