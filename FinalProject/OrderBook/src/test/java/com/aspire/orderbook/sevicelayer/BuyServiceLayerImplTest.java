package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.BuyOrder;
import com.aspire.orderbook.entities.Stock;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.NoResultException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author sean5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyServiceLayerImplTest {
    
    @Autowired
    private BuyServiceLayer buys;
    
    @Autowired
    private StockServiceLayer stocks;
    
    private BuyOrder myBuy;
    private Stock stock;
    
    @Before
    public void setUp() {
        stock = new Stock();
        stock.setPrice(new BigDecimal("20.50"));
        stock.setTickSize(new BigDecimal("0.01"));
        stock.setTicker("MSFT");
        
        stock = stocks.addStock(stock);
         
        myBuy = new BuyOrder();
        myBuy.setBuyer("the Buyer");
        myBuy.setFulfilled(false);
        myBuy.setNumber(100);
        myBuy.setPrice(new BigDecimal("52.11"));
        myBuy.setType("Limit");
        myBuy.setStock(stock);
        
        myBuy = buys.addOrder(myBuy);
    }

    /**
     * Test of getById method, of class BuyServiceLayerImpl.
     */
    @Test
    public void testGetById() {
        BuyOrder testBuy = buys.getById(myBuy.getOrderId());
        assertEquals(testBuy, myBuy);
    }

    /**
     * Test of getAll method, of class BuyServiceLayerImpl.
     */
    @Test
    public void testGetAll() {
        List<BuyOrder> allBuys = buys.getAll();
        
        assertNotNull(allBuys);
        assertFalse(allBuys.isEmpty());
        assertTrue(allBuys.contains(myBuy));
    }

    /**
     * Test of getByStock method, of class BuyServiceLayerImpl.
     */
    @Test
    public void testGetByStock() {
        List<BuyOrder> stockBuys = buys.getByStock(stock);
        
        assertNotNull(stockBuys);
        assertFalse(stockBuys.isEmpty());
        assertTrue(stockBuys.contains(myBuy));
        
        Stock newStock = new Stock();
        newStock.setPrice(BigDecimal.ONE);
        newStock.setTickSize(new BigDecimal("0.01"));
        newStock.setTicker("SQ");
        
        stocks.addStock(newStock);
        
        List<BuyOrder> empty = buys.getByStock(newStock);
        
        assertNotNull(empty);
        assertTrue(empty.isEmpty());
        assertFalse(empty.contains(myBuy));
    }

    /**
     * Test of addOrder, editOrder, deleteOrder methods, of class BuyServiceLayerImpl.
     */
    @Test
    public void testAddEditDeleteOrder() {
        // test adding
        
        BuyOrder newOrder = new BuyOrder();
        newOrder.setBuyer("Mr. Buyer Guy");
        newOrder.setFulfilled(false);
        newOrder.setNumber(13);
        newOrder.setPrice(new BigDecimal("5500.11"));
        newOrder.setStock(stock);
        newOrder.setType("limit");
        
        BuyOrder order = buys.addOrder(newOrder);
        newOrder.setOrderId(order.getOrderId());
        BuyOrder order2 = buys.getById(newOrder.getOrderId());
        
        assertEquals(order, newOrder);
        assertEquals(newOrder, order2);
        
        // test editing
        
        order2.setNumber(55);
        BuyOrder order3 = buys.editOrder(order2);
        
        assertEquals(order2, order3);
        //assertNotEquals(order3, order);
        assertTrue(order3.getOrderId() == order.getOrderId());
        
        buys.deleteOrder(order3);

        assertFalse(buys.getAll().contains(order3));
    }

    @Test
    public void testGenerateRandom() {

        for(int i=0; i<10; i++) 
            buys.addOrder(buys.generateRandom());
        
        assertTrue(true);
    }
}
