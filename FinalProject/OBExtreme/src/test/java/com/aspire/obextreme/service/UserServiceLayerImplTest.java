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
import com.aspire.obextreme.entities.User;
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
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author sean5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceLayerImplTest {
    
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
   
    
    private User user;
    
    public UserServiceLayerImplTest() {
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
        user = new User();
        
        user.setActive(true);
        user.setAdmin(false);
        user.setMarketParticipant("ggttpp");
        user.setName("Gordan Gecko");
        user.setPassword("password");
        user.setUsername("g_gecks");
        
        user = users.addUser(user);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getById method, of class UserServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testGetById() {
        User testUser = users.getById(user.getUserId());
        
        assertEquals(user, testUser);
    }

    /**
     * Test of getAllUsers method, of class UserServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testGetAllUsers() {
        List<User> allUsers = users.getAllUsers();
        
        assertNotNull(allUsers);
        assertFalse(allUsers.isEmpty());
        assertTrue(allUsers.contains(user));
    }

    /**
     * Test of addUser method, of class UserServiceLayerImpl.
     */
    @Test
    @Transactional
    public void testAddEditDeleteUser() {
        User newUser = new User();
        
        newUser.setActive(false);
        newUser.setAdmin(false);
        newUser.setMarketParticipant("QQQQQQ");
        newUser.setName("Bernie Madoff");
        newUser.setPassword("abcdefg");
        newUser.setUsername("pyramidschememan");
        
        User user1 = users.addUser(newUser);
        newUser.setUserId(user1.getUserId());
        
        assertEquals(user1, newUser);
        
        user1.setPassword("jjerlsnfjdsb");
        User user2 = users.addUser(user1);
        
        assertEquals(user1, user2);
        
        users.deleteUser(user2);
        
        boolean deleted = false;
        try{
            users.getById(user2.getUserId());
        } catch(JpaObjectRetrievalFailureException e) {
            deleted=true;
        }
        assertTrue(deleted);
    }
}
