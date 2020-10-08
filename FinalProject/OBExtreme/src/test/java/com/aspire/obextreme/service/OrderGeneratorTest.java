package com.aspire.obextreme.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import com.aspire.obextreme.entities.BuyOrder;
import com.aspire.obextreme.entities.SellOrder;
import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Trade;
import com.aspire.obextreme.entities.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderGeneratorTest {
    
    @Autowired
    BuyServiceLayer buys;
    
    @Autowired
    SellServiceLayer sells;
    
    @Autowired
    StockServiceLayer stocks;

    @Autowired
    TradeServiceLayer trades;
    
    @Autowired
    TypeServiceLayer types;
    
    @Autowired 
    UserServiceLayer users;

    @Autowired
    OrderGenerator og;

    private Stock myStock;
    private User user;
    
    @Before
    @Transactional
    public void setUp() {
        List<Trade> allTrades = trades.getAll();
        for(int i = 0; i< allTrades.size(); i++)
            trades.deleteTrade(allTrades.get(i));
        
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
    }

    @Test
    public void testGenerateBuyOrder() {
        user = new User();
        user.setActive(true);
        user.setAdmin(false);
        user.setMarketParticipant("ggttpp");
        user.setName("Gordan Gecko");
        user.setPassword("password");
        user.setUsername("g_gecks");
        
        user = users.addUser(user);

        assertTrue(users.getAllUsers().size() > 0);

        myStock = new Stock();
        myStock.setPrice(new BigDecimal("3500"));
        myStock.setTickSize(new BigDecimal("0.01"));
        myStock.setTicker("MSFT");
        
        myStock = stocks.addStock(myStock);
        
        BuyOrder generate = og.generateBuy(myStock);
        BuyOrder test = buys.addOrder(generate);

        assertTrue(buys.getAll().size() > 0);
    }

    @Test
    public void testGenerateSellOrder() {
        user = new User();
        user.setActive(true);
        user.setAdmin(false);
        user.setMarketParticipant("ggttpp");
        user.setName("Gordan Gecko");
        user.setPassword("password");
        user.setUsername("g_gecks");
        
        user = users.addUser(user);

        assertTrue(users.getAllUsers().size() > 0);

        myStock = new Stock();
        myStock.setPrice(new BigDecimal("3500"));
        myStock.setTickSize(new BigDecimal("0.01"));
        myStock.setTicker("MSFT");
        
        myStock = stocks.addStock(myStock);
        
        SellOrder generate = og.generateSell(myStock);
        SellOrder test = sells.addOrder(generate);

        assertTrue(sells.getAll().size() > 0);
    }

    @Test
    @Transactional
    public void testGenerateOrders() {
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

        for(int i=0; i<5; i++) {
            BuyOrder b = og.generateBuy(myStock);
            buys.addOrder(b);

            SellOrder s = og.generateSell(myStock);
            sells.addOrder(s);
        }

        assertTrue(true);
    }
}
