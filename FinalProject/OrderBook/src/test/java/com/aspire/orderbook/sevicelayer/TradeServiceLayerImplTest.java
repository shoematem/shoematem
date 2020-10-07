package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.BuyOrder;
import com.aspire.orderbook.entities.SellOrder;
import com.aspire.orderbook.entities.Stock;
import com.aspire.orderbook.entities.Trade;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class TradeServiceLayerImplTest {
    
    @Autowired
    private TradeServiceLayer trades;
    
    @Autowired
    private BuyServiceLayer buys;
    
    @Autowired
    private SellServiceLayer sells;
    
    @Autowired
    private StockServiceLayer stocks;
    
    private BuyOrder myBuy;
    private SellOrder mySell;
    private Trade myTrade;
    private Stock myStock;
    
    @Before
    public void setUp() {
        myStock = new Stock();
        myStock.setPrice(new BigDecimal("3500.00"));
        myStock.setTickSize(new BigDecimal("0.01"));
        myStock.setTicker("MSFT");
        
        myStock = stocks.addStock(myStock);
        
        myBuy = new BuyOrder();
        myBuy.setBuyer("Jimmy G");
        myBuy.setFulfilled(false);
        myBuy.setNumber(100);
        myBuy.setStock(myStock);
        myBuy.setPrice(new BigDecimal("4000.00"));
        myBuy.setType("limit");
        
        myBuy = buys.addOrder(myBuy);
        
        mySell = new SellOrder();

        mySell.setSeller("Kyler Murray");
        mySell.setFulfilled(false);
        mySell.setNumber(100);
        mySell.setStock(myStock);
        mySell.setType("market");
        
        mySell = sells.addOrder(mySell);
        
        myTrade = new Trade();
        
        myTrade.setBuyOrder(myBuy);
        myTrade.setTime(LocalDateTime.now().withNano(0));
        myTrade.setSellOrder(mySell);
        myTrade.setNumber(100);
        myTrade.setPrice(myBuy.getPrice());
        
        myTrade = trades.addTrade(myTrade);
    }

    /**
     * Test of getById method, of class TradeServiceLayerImpl.
     */
    @Test
    public void testGetById() {
        Trade testTrade = trades.getById(myTrade.getTradeId());

        assertEquals(myTrade, testTrade);
    }

    /**
     * Test of getAll method, of class TradeServiceLayerImpl.
     */
    @Test
    public void testGetAll() {
        List<Trade> allTrades = trades.getAll();
        
        assertTrue(allTrades.contains(myTrade));
        assertFalse(allTrades.isEmpty());
        assertNotNull(allTrades);
    }

    /**
     * Test of getByStock method, of class TradeServiceLayerImpl.
     */
    @Test
    public void testGetByStock() {
        List<Trade> notEmpty = trades.getByStock(myStock);
        
        assertNotNull(notEmpty);
        assertFalse(notEmpty.isEmpty());
        assertTrue(notEmpty.contains(myTrade));
        
        Stock newStock = new Stock();
        newStock.setPrice(BigDecimal.ONE);
        newStock.setTickSize(new BigDecimal("0.01"));
        newStock.setTicker("SQ");
        
        stocks.addStock(newStock);
        
        List<Trade> isEmpty = trades.getByStock(newStock);
        
        assertNotNull(isEmpty);
        assertTrue(isEmpty.isEmpty());
        assertFalse(isEmpty.contains(myTrade));
    }

    /**
     * Test of deleteTrade method, of class TradeServiceLayerImpl.
     */
    @Test
    public void testAddEditDeleteTrade() {
        Trade newTrade = new Trade();
        newTrade.setBuyOrder(myBuy);
        newTrade.setSellOrder(mySell);
        newTrade.setNumber(30);
        newTrade.setPrice(myBuy.getPrice());
        newTrade.setTime(LocalDateTime.now());
        
        Trade trade1 = trades.addTrade(newTrade);
        newTrade.setTradeId(trade1.getTradeId());
        
        assertEquals(newTrade, trade1);
        
        trade1.setNumber(40);
        Trade trade2 = trades.editTrade(trade1);
        
        assertEquals(trade1, trade2);
        assertNotEquals(trade2, newTrade);
        
        trades.deleteTrade(trade2);
        
        boolean deleted = false;
        try{
            trades.getById(trade2.getTradeId());
        } catch(NoResultException e){
            deleted = true;
        }
        
        assertTrue(deleted);
    }

    
}
