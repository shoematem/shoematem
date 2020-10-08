package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.SellOrder;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellServiceLayerImplTest {

    @Autowired
    private SellServiceLayer sells;
    
    @Autowired
    private StockServiceLayer stocks;
    
    private SellOrder mySell;
    private Stock stock;

    @Before
    public void setUp() {
        stock = new Stock();
        stock.setPrice(new BigDecimal("20.50"));
        stock.setTickSize(new BigDecimal("0.01"));
        stock.setTicker("MSFT");
        
        stock = stocks.addStock(stock);

        mySell = new SellOrder();
        mySell.setSeller("the Seller");
        mySell.setFulfilled(false);
        mySell.setNumber(100);
        mySell.setPrice(new BigDecimal("52.11"));
        mySell.setType("Limit");
        mySell.setStock(stock);
        
        mySell = sells.addOrder(mySell);
    }

    /**
     * Test of getById method, of class SellServiceLayerImpl.
     */
    @Test
    public void testGetById() {
        SellOrder testSell = sells.getById(mySell.getOrderId());
        assertEquals(testSell, mySell);
    }

    /**
     * Test of getAll method, of class SellServiceLayerImpl.
     */
    @Test
    public void testGetAll() {
        List<SellOrder> testAll = sells.getAll();

        assertNotNull(testAll);
        assertFalse(testAll.isEmpty());
        assertTrue(testAll.contains(mySell));
    }

    /**
     * Test of getByStock method, of class SellServiceLayerImpl.
     */
    @Test
    public void testGetByStock() {
        List<SellOrder> testStock = sells.getByStock(stock);

        assertNotNull(testStock);
        assertFalse(testStock.isEmpty());
        assertTrue(testStock.contains(mySell));

        Stock newStock = new Stock();
        newStock.setPrice(BigDecimal.ONE);
        newStock.setTickSize(new BigDecimal("0.01"));
        newStock.setTicker("SQ");
        
        stocks.addStock(newStock);
        
        List<SellOrder> empty = sells.getByStock(newStock);
        
        assertNotNull(empty);
        assertTrue(empty.isEmpty());
        assertFalse(empty.contains(mySell));
    }

    /**
     * Test of addOrder, editOrder, deleteOrder methods, of class SellServiceLayerImpl.
     */
    @Test
    public void testAddEditDeleteOrder() {
        SellOrder anotherSell = new SellOrder();
        anotherSell.setSeller("another Seller");
        anotherSell.setFulfilled(false);
        anotherSell.setNumber(150);
        anotherSell.setPrice(new BigDecimal("44.44"));
        anotherSell.setType("Limit");
        anotherSell.setStock(stock);

        SellOrder order = sells.addOrder(anotherSell);
        anotherSell.setOrderId(order.getOrderId());
        SellOrder order2 = sells.getById(anotherSell.getOrderId());

        assertEquals(order, anotherSell);
        assertEquals(anotherSell, order2);

        order2.setNumber(22);
        SellOrder order3 = sells.editOrder(order2);

        assertEquals(order2, order3);
        assertNotEquals(order3, order);
        assertTrue(order3.getOrderId() == order.getOrderId());

        sells.deleteOrder(order3);
        
        boolean deleted = false;
        try{
            sells.getById(order3.getOrderId());
        } catch(NoResultException e) {
            deleted=true;
        }

        assertTrue(deleted);
    }
}
