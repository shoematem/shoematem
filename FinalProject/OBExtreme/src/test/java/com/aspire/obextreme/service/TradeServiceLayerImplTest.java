/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.BuyOrder;
import com.aspire.obextreme.entities.SellOrder;
import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Trade;
import com.aspire.obextreme.entities.Type;
import com.aspire.obextreme.entities.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
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
public class TradeServiceLayerImplTest {
    
    @Autowired
    TradeServiceLayer trades;
    
    @Autowired
    BuyServiceLayer buys;
    
    @Autowired
    SellServiceLayer sells;
    
    @Autowired
    StockServiceLayer stocks;
    
    @Autowired
    TypeServiceLayer types;
    
    @Autowired 
    UserServiceLayer users;
    
    private BuyOrder myBuy;
    private SellOrder mySell;
    private Trade myTrade;
    private Stock myStock;
    private Type myType;
    private User user;
    
    public TradeServiceLayerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    @Transactional
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
        myType = new Type();
        myType.setType("Limit");
        myType.setTypeId(2);
        
        user = new User();
        user.setActive(true);
        user.setAdmin(false);
        user.setMarketParticipant("ggttpp");
        user.setName("Gordan Gecko");
        user.setPassword("password");
        user.setUsername("g_gecks");
        
        user = users.addUser(user);
        
        myStock = new Stock();
        myStock.setPrice(new BigDecimal("3500"));
        myStock.setTickSize(new BigDecimal("0.01"));
        myStock.setTicker("MSFT");
        
        myStock = stocks.addStock(myStock);
        
        myBuy = new BuyOrder();
        myBuy.setUser(user);
        myBuy.setNumber(100);
        myBuy.setStock(myStock);
        myBuy.setPrice(new BigDecimal("4"));
        myBuy.setType(myType);
        myBuy.setOutstanding(100);
        
        myBuy = buys.addOrder(myBuy);
        
        mySell = new SellOrder();
        mySell.setUser(user);
        mySell.setNumber(100);
        mySell.setStock(myStock);
        mySell.setType(myType);
        mySell.setPrice(new BigDecimal("4001"));
        mySell.setOutstanding(100);
        
        mySell = sells.addOrder(mySell);
        
        myTrade = new Trade();
        
        myTrade.setBuyOrder(myBuy);
        myTrade.setTime(LocalDateTime.now());
        myTrade.setSellOrder(mySell);
        myTrade.setNumber(50);
        myTrade.setPrice(myBuy.getPrice());
        
        myTrade = trades.addTrade(myTrade);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getById method, of class TradeServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testTradeGetById() {
        Trade testTrade = trades.getById(myTrade.getTradeId());
        
        assertEquals(myTrade, testTrade);
    }
    
    @Test
    @Transactional
    public void testBuyOrderGetById() {
        BuyOrder testBuyOrder = buys.getById(myBuy.getOrderId());
        
        assertEquals(myBuy, testBuyOrder);
    }
    
    @Test
    @Transactional
    public void testSellOrderGetById() {
        SellOrder testSellOrder = sells.getById(mySell.getOrderId());
        
        assertEquals(mySell, testSellOrder);
    }

    
    
    /**
     * Test of getAll method, of class TradeServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testTradeGetAll() {
        List<Trade> allTrades = trades.getAll();
        
        assertTrue(allTrades.contains(myTrade));
        assertFalse(allTrades.isEmpty());
        assertNotNull(allTrades);
    }
    
    @Test
    @Transactional
    public void testBuyGetAll() {
        List<BuyOrder> allBuys = buys.getAll();
        
        assertTrue(allBuys.contains(myBuy));
        assertFalse(allBuys.isEmpty());
        assertNotNull(allBuys);
    }
    
    @Test
    @Transactional
    public void testSellGetAll() {
        List<SellOrder> allSells = sells.getAll();
        
        assertTrue(allSells.contains(mySell));
        assertFalse(allSells.isEmpty());
        assertNotNull(allSells);
    }
    
    
    

    /**
     * Test of getByStock method, of class TradeServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testTradeGetByStock() {
        List<Trade> notEmpty = trades.getByStock(myStock);
        
        assertNotNull(notEmpty);
        assertFalse(notEmpty.isEmpty());
        assertTrue(notEmpty.contains(myTrade));
        
        Stock newStock = new Stock();
        newStock.setPrice(BigDecimal.ONE);
        newStock.setTickSize(new BigDecimal("0.01"));
        newStock.setTicker("SQ");
        
        newStock = stocks.addStock(newStock);
        
        List<Trade> isEmpty = trades.getByStock(newStock);
        
        assertNotNull(isEmpty);
        assertTrue(isEmpty.isEmpty());
        assertFalse(isEmpty.contains(myTrade));
    }
    
    
    @Test
    @Transactional
    public void testBuyGetByStock() {
        List<BuyOrder> notEmpty = buys.getByStock(myStock);
        
        assertNotNull(notEmpty);
        assertFalse(notEmpty.isEmpty());
        assertTrue(notEmpty.contains(myBuy));
        
        Stock newStock = new Stock();
        newStock.setPrice(BigDecimal.ONE);
        newStock.setTickSize(new BigDecimal("0.01"));
        newStock.setTicker("SQ");
        
        newStock = stocks.addStock(newStock);
        
        List<BuyOrder> isEmpty = buys.getByStock(newStock);
        
        assertNotNull(isEmpty);
        assertTrue(isEmpty.isEmpty());
        assertFalse(isEmpty.contains(myBuy));
    }
    
    
    @Test
    @Transactional
    public void testSellGetByStock() {
        List<SellOrder> notEmpty = sells.getByStock(myStock);
        
        assertNotNull(notEmpty);
        assertFalse(notEmpty.isEmpty());
        assertTrue(notEmpty.contains(mySell));
        
        Stock newStock = new Stock();
        newStock.setPrice(BigDecimal.ONE);
        newStock.setTickSize(new BigDecimal("0.01"));
        newStock.setTicker("SQ");
        
        newStock = stocks.addStock(newStock);
        
        List<SellOrder> isEmpty = sells.getByStock(newStock);
        
        assertNotNull(isEmpty);
        assertTrue(isEmpty.isEmpty());
        assertFalse(isEmpty.contains(mySell));
    }
    
    
    /**
     * Test of getByUser method, of class TradeServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testTradeGetByUser() {
        List<Trade> notEmpty = trades.getByUser(user);
        
        assertNotNull(notEmpty);
        assertFalse(notEmpty.isEmpty());
        assertTrue(notEmpty.contains(myTrade));
        
        User newUser = new User();
        newUser.setActive(true);
        newUser.setAdmin(false);
        newUser.setMarketParticipant("jdbfiuaebfvj");
        newUser.setName("Michael Burry");
        newUser.setPassword("the_password");
        newUser.setUsername("Mr User");
        
        newUser = users.addUser(newUser);
        
        List<Trade> isEmpty = trades.getByUser(newUser);
        
        assertNotNull(isEmpty);
        assertTrue(isEmpty.isEmpty());
        assertFalse(isEmpty.contains(myTrade));
    }
    
    
    @Test
    @Transactional
    public void testBuyGetByUser() {
        List<BuyOrder> notEmpty = buys.getByUser(user);
        
        assertNotNull(notEmpty);
        assertFalse(notEmpty.isEmpty());
        assertTrue(notEmpty.contains(myBuy));
        
        User newUser = new User();
        newUser.setActive(true);
        newUser.setAdmin(false);
        newUser.setMarketParticipant("jdbfiuaebfvj");
        newUser.setName("Michael Burry");
        newUser.setPassword("the_password");
        newUser.setUsername("Mr User");
        
        newUser = users.addUser(newUser);
        
        List<BuyOrder> isEmpty = buys.getByUser(newUser);
        
        assertNotNull(isEmpty);
        assertTrue(isEmpty.isEmpty());
        assertFalse(isEmpty.contains(myBuy));
    }
    
    
    @Test
    @Transactional
    public void testSellGetByUser() {
        List<SellOrder> notEmpty = sells.getByUser(user);
        
        assertNotNull(notEmpty);
        assertFalse(notEmpty.isEmpty());
        assertTrue(notEmpty.contains(mySell));
        
        User newUser = new User();
        newUser.setActive(true);
        newUser.setAdmin(false);
        newUser.setMarketParticipant("jdbfiuaebfvj");
        newUser.setName("Michael Burry");
        newUser.setPassword("the_password");
        newUser.setUsername("Mr User");
        
        newUser = users.addUser(newUser);
        
        List<SellOrder> isEmpty = sells.getByUser(newUser);
        
        assertNotNull(isEmpty);
        assertTrue(isEmpty.isEmpty());
        assertFalse(isEmpty.contains(mySell));
    }

    /**
     * Test of deleteTrade method, of class TradeServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testDeleteTrade() {
        trades.deleteTrade(myTrade);
        buys.deleteOrder(myBuy);
        sells.deleteOrder(mySell);
        
        assertTrue(trades.getAll().isEmpty());
        assertTrue(buys.getAll().isEmpty());
        assertTrue(sells.getAll().isEmpty());
    }
    
    @Test
    @Transactional
    public void testAddEditTrade() {
        
        
        // add a limit buy order and a market sell and create a trade
        
        BuyOrder buy1 = new BuyOrder();
        Type typeBuy = types.getById(2);
        buy1.setNumber(100);
        buy1.setOutstanding(100);
        buy1.setPrice(new BigDecimal("50"));
        buy1.setStock(myStock);
        buy1.setType(typeBuy);
        buy1.setUser(user);
        
        BuyOrder buy2 =  buys.addOrder(buy1);
        buy1.setOrderId(buy2.getOrderId());
        
     
        
        assertEquals(buy1, buy2);
        
        SellOrder sell1 = new SellOrder();
        sell1.setNumber(50);
        sell1.setOutstanding(50);
        sell1.setStock(myStock);
        sell1.setType(types.getById(1));
        sell1.setUser(user);
        
        SellOrder sell2 = sells.addOrder(sell1);
        
        assertEquals(sell2.getOutstanding(), 0);
        List<Trade> trade = trades.getAll();
        assertEquals(trades.getAll().size(), 2);
        
        // add a limit sell and a market buy and create a trade
        
        SellOrder sell3 = new SellOrder();
        sell3.setNumber(100);
        sell3.setOutstanding(100);
        sell3.setPrice(new BigDecimal("50"));
        sell3.setStock(myStock);
        sell3.setType(types.getById(2));
        sell3.setUser(user);
        
        SellOrder sell4 = sells.addOrder(sell3);
        sell3.setOrderId(sell4.getOrderId());
        
        assertEquals(sell3, sell4);
        
        BuyOrder buy3 = new BuyOrder();
        buy3.setNumber(50);
        buy3.setOutstanding(50);
        buy3.setStock(myStock);
        buy3.setType(types.getById(1));
        buy3.setUser(user);
        
        BuyOrder buy4 = buys.addOrder(buy3);
        
        assertTrue(buy4.getOutstanding() == 0);
        List<Trade> all = trades.getAll();
        assertEquals(trades.getAll().size(), 4);
        
        // edit orders
        
        
        
    }


    
}
