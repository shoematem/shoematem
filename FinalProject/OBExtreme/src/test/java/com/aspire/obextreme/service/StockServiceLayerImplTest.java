package com.aspire.obextreme.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import com.aspire.obextreme.entities.BuyOrder;
import com.aspire.obextreme.entities.SellOrder;
import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Trade;
import com.aspire.obextreme.entities.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author sean5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceLayerImplTest {
    
    @Autowired
    private TradeServiceLayer trades;
    
    @Autowired
    private BuyServiceLayer buys;
    
    @Autowired
    private SellServiceLayer sells;
    
    @Autowired
    private StockServiceLayer stocks;
    
    @Autowired
    private TypeServiceLayer types;
    
    @Autowired 
    private UserServiceLayer users;
    
    private Stock stock;
    
    public StockServiceLayerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Trade> allTrades = trades.getAll();
        for(int i = 0; i < allTrades.size(); i++){
            trades.deleteTrade(allTrades.get(i));
        }

        List<BuyOrder> allBuys = buys.getAll();
        for(int i = 0; i < allBuys.size(); i++){
            buys.deleteOrder(allBuys.get(i));
        }
        
        List<SellOrder> allSells = sells.getAll();
        for(int i = 0; i < allSells.size(); i++){
            sells.deleteOrder(allSells.get(i));
        }  
        
        List<Stock> allStocks = stocks.getAll();
        for(int i = 0; i < allStocks.size(); i ++){
            stocks.deleteStock(allStocks.get(i));
        }
        
        List<User> allUsers = users.getAllUsers();
        for(int i = 0; i < allUsers.size(); i++){
            users.deleteUser(allUsers.get(i));
        }
        
        stock = new Stock();
        stock.setPrice(BigDecimal.ONE);
        stock.setTickSize(new BigDecimal("0.01"));
        stock.setTicker("AAPL");
        
        stock = stocks.addStock(stock);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getById method, of class StockServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testGetById() {
        Stock testStock = stocks.getById(stock.getStockId());
        
        assertEquals(testStock, stock);
    }

    /**
     * Test of getAll method, of class StockServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testGetAll() {
        List<Stock> allStocks = stocks.getAll();
        
        assertNotNull(allStocks);
        assertFalse(allStocks.isEmpty());
        assertTrue(allStocks.contains(stock));
    }

    /**
     * Test of deleteStock method, of class StockServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testAddEditDeleteStock() {
        Stock newStock = new Stock();
        newStock.setPrice(new BigDecimal("110.5"));
        newStock.setTickSize(new BigDecimal("0.01"));
        newStock.setTicker("NUGT");
        
        Stock stock1 = stocks.addStock(newStock);
        newStock.setStockId(stock1.getStockId());
        assertEquals(stock1, newStock);
        
        stock1.setTicker("KODK");
        Stock stock2 = stocks.editStock(stock1);
        
        assertEquals(stock1, stock2);
        
        stocks.deleteStock(stock2);
        
        boolean deleted = false;
        try{
            stocks.getById(stock2.getStockId());
        } catch(JpaObjectRetrievalFailureException e) {
            deleted=true;
        }

        assertTrue(deleted);
        
    }

    
}
