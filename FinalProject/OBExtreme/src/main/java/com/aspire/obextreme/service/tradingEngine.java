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
import com.aspire.obextreme.repositories.BuyRepo;
import com.aspire.obextreme.repositories.SellRepo;
import com.aspire.obextreme.repositories.StockRepo;
import com.aspire.obextreme.repositories.TradeRepo;
import com.aspire.obextreme.repositories.TypeRepo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean5
 */
@Service
public class tradingEngine {
    
    @Autowired
    SellRepo sells;
    
    @Autowired
    BuyRepo buys;
    
    @Autowired
    TypeRepo types;
    
    @Autowired
    TradeRepo trades;
   
    
    @Autowired
    StockServiceLayer stocks;

    public BuyOrder shopBuy(BuyOrder order) {
        
        // a buy order has been either created or edited...
        
        List<SellOrder> buyPhase1;
        List<SellOrder> buyPhase2;
        List<SellOrder> buyPhase3;
            
        switch(order.getType().getType()){
            // new trade will affect the market differently depending on its type so we switch based on trade type
            case "Market":
                
                /*
                market order trade is compatible with any and all market sell orders of the same stock. we will match them at the current
                stock price until there are either no more sell market orders, or no more outstanding from this buy order. The market buys are also compatible with 
                sell limit orders so if it at any point encounters no market sell orders, it will then snap up the most competitive ask limits
                */
                
                // buy phase 1 is this market buy matching with the first market sells it sees so our list is all open sell orders 
                // of this stock 
                buyPhase1 = sells.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(order.getStock(), types.getOne(1), 0);
                
                // while the buy order has positive outstanding shares AND there are sell market orders to match we simply match them up
                while(order.getOutstanding() > 0 && !buyPhase1.isEmpty()){
                    SellOrder sellOrder = buyPhase1.get(0);
                    Trade newTrade = new Trade();
                    newTrade.setBuyOrder(order);
                    newTrade.setSellOrder(sellOrder);
                    
                    // the number of shares in a trade is the max of the outstanding shares from the buy or sell order
                    if(order.getOutstanding() >= sellOrder.getOutstanding()){
                        newTrade.setNumber(sellOrder.getOutstanding());
                        // here the buy had more than the sell so we update the buy and set the sell to 0
                        order.setOutstanding(order.getOutstanding() - sellOrder.getOutstanding());
                        sellOrder.setOutstanding(0);
                        
                        // now our sell order is no longer active since it has 0 outstanding orders so we save it and remove it from the 
                        // sell order list
                        sells.save(sellOrder);
                        buyPhase1.remove(sellOrder);
                    }
                    else{
                        newTrade.setNumber(order.getOutstanding());
                        // since the sell had more outstanding shares than the buy it gets updated and the buy is set to zero
                        sellOrder.setOutstanding(sellOrder.getOutstanding() - order.getOutstanding());
                        order.setOutstanding(0);
                        // the sell order is saved here since the buy is no longer active this trade making session will end and 
                        // we need to save our changes
                        sells.save(sellOrder);
                    }
                    
                    // since this is a market-market trade the exection price will be at market price
                    newTrade.setTime(LocalDateTime.now());
                    newTrade.setPrice(order.getStock().getPrice());
                    
                    // save our buy order and then save our new trade
                    buys.save(order);
                    
                    trades.save(newTrade);
                }
                
                // if our buy order still has outstanding and there are no more sell market orders we will now snap up limit orders
                if(order.getOutstanding() > 0){
                    // a list of all of the active sell limit orders of this stock, with the lowest(most competitive) sell pirce as the first 
                    buyPhase2 = sells.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(order.getStock(), types.getOne(2), 0);;
                    buyPhase3 = sells.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(order.getStock(), types.getOne(2), 0);
                    
                    while(!buyPhase3.isEmpty() && buyPhase3.get(0).getPrice().compareTo(order.getStock().getPrice()) < 1){
                        buyPhase3.remove(0);
                    }
                    
                    if(!buyPhase3.isEmpty()){
                        buyPhase3.addAll(buyPhase2);
                        buyPhase2 = buyPhase3;
                    }
                    
                    // the market buy order doesnt care what the sell limit order price is so we will match it to the most competitive sell 
                    while(order.getOutstanding() > 0 && !buyPhase2.isEmpty()){
                        SellOrder sellOrder = buyPhase2.get(0);
                        Trade newTrade = new Trade();
                        newTrade.setBuyOrder(order);
                        newTrade.setSellOrder(sellOrder);
                    
                        if(order.getOutstanding() >= sellOrder.getOutstanding()){
                            newTrade.setNumber(sellOrder.getOutstanding());
                            order.setOutstanding(order.getOutstanding() - sellOrder.getOutstanding());
                            sellOrder.setOutstanding(0);
                            sells.save(sellOrder);
                            buyPhase2.remove(sellOrder);
                            
                        }
                        else{
                            newTrade.setNumber(order.getOutstanding());
                            sellOrder.setOutstanding(sellOrder.getOutstanding() - order.getOutstanding());
                            order.setOutstanding(0);
                        }

                        // the price of our stock updates to the price of our most recent trade
                        
                        newTrade.setTime(LocalDateTime.now());
                        newTrade.setPrice(sellOrder.getPrice());
                        
                        Stock changedStock = order.getStock();
                        changedStock.setPrice(newTrade.getPrice());
                        stocks.editStock(changedStock);
                        
                        // the buy order and trade are saved to the DB
                        buys.save(order);
                        trades.save(newTrade);
                    }
                }
                break;
                
            case "Limit": 
                /*
                A buy limit order should look for market sell orders market sell orders will fulfill at the limit price, to be thorough,
                although this shoudl never happen we will also check for sell limit orders that match with the price
                */
                buyPhase1 = sells.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(order.getStock(), types.getOne(1), 0);
                
                // first check if it is above the market price, if it is it will have a chance at matching up with 
                // sell limit orders
                if(order.getPrice().compareTo(order.getStock().getPrice()) < 1){
                    // we want to check for limit orders after market orders
                    while(order.getOutstanding() > 0 && !buyPhase1.isEmpty()){
                        SellOrder sellOrder = buyPhase1.get(0);
                        Trade newTrade = new Trade();
                        newTrade.setBuyOrder(order);
                        newTrade.setSellOrder(sellOrder);

                        if(order.getOutstanding() >= sellOrder.getOutstanding()){
                            newTrade.setNumber(sellOrder.getOutstanding());
                            order.setOutstanding(order.getOutstanding() - sellOrder.getOutstanding());
                            sellOrder.setOutstanding(0);
                            sells.save(sellOrder);
                            buyPhase1.remove(sellOrder);

                        }
                        else{
                            newTrade.setNumber(order.getOutstanding());
                            sellOrder.setOutstanding(sellOrder.getOutstanding() - order.getOutstanding());
                            order.setOutstanding(0);
                        }

                        newTrade.setTime(LocalDateTime.now());
                        newTrade.setPrice(order.getPrice());
                        

                        Stock changedStock = order.getStock();
                        changedStock.setPrice(newTrade.getPrice());
                        stocks.editStock(changedStock);
                        
                        buys.save(order);
                        trades.save(newTrade);
                    }
                    
                    if(order.getOutstanding() > 0){
                        // a list of all of the active sell limit orders of this stock, with the lowest(most competitive) sell pirce as the first 
                        buyPhase2 = sells.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(order.getStock(), types.getOne(2), 0);;
                        buyPhase3 = sells.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(order.getStock(), types.getOne(2), 0);

                        while(!buyPhase3.isEmpty() && buyPhase3.get(0).getPrice().compareTo(order.getStock().getPrice()) < 1){
                            buyPhase3.remove(0);
                        }

                        if(!buyPhase3.isEmpty()){
                            buyPhase3.addAll(buyPhase2);
                            buyPhase2 = buyPhase3;
                        }

                        // the market buy order doesnt care what the sell limit order price is so we will match it to the most competitive sell 
                        while(order.getOutstanding() > 0 && !buyPhase2.isEmpty()){
                            SellOrder sellOrder = buyPhase2.get(0);
                            Trade newTrade = new Trade();
                            newTrade.setBuyOrder(order);
                            newTrade.setSellOrder(sellOrder);

                            if(order.getOutstanding() >= sellOrder.getOutstanding()){
                                newTrade.setNumber(sellOrder.getOutstanding());
                                order.setOutstanding(order.getOutstanding() - sellOrder.getOutstanding());
                                sellOrder.setOutstanding(0);
                                sells.save(sellOrder);
                                buyPhase2.remove(sellOrder);

                            }
                            else{
                                newTrade.setNumber(order.getOutstanding());
                                sellOrder.setOutstanding(sellOrder.getOutstanding() - order.getOutstanding());
                                order.setOutstanding(0);
                            }

                            // the price of our stock updates to the price of our most recent trade

                            newTrade.setTime(LocalDateTime.now());
                            newTrade.setPrice(sellOrder.getPrice());
                            
                            Stock changedStock = order.getStock();
                            changedStock.setPrice(newTrade.getPrice());
                            stocks.editStock(changedStock);

                            // the buy order and trade are saved to the DB
                            buys.save(order);
                            trades.save(newTrade);
                        }
                    }
                }
                else{
                    while(order.getOutstanding() > 0 && !buyPhase1.isEmpty()){
                        SellOrder sellOrder = buyPhase1.get(0);
                        Trade newTrade = new Trade();
                        newTrade.setBuyOrder(order);
                        newTrade.setSellOrder(sellOrder);

                        if(order.getOutstanding() >= sellOrder.getOutstanding()){
                            newTrade.setNumber(sellOrder.getOutstanding());
                            order.setOutstanding(order.getOutstanding() - sellOrder.getOutstanding());
                            sellOrder.setOutstanding(0);
                            sells.save(sellOrder);
                            buyPhase1.remove(sellOrder);

                        }
                        else{
                            newTrade.setNumber(order.getOutstanding());
                            sellOrder.setOutstanding(sellOrder.getOutstanding() - order.getOutstanding());
                            order.setOutstanding(0);
                        }

                        
                        newTrade.setTime(LocalDateTime.now());
                        newTrade.setPrice(order.getPrice());

                        Stock changedStock = order.getStock();
                        changedStock.setPrice(newTrade.getPrice());
                        stocks.editStock(changedStock);
                        
                        buys.save(order);
                        trades.save(newTrade);

                    }
                }
                
                break;
             
            default:
                /*
                     A stop buy is only active when the price of the stock drops below the stop price, the following function 
                    only entertains the fringe case that somebody entered a buy stop manually with the price below the market price
                    since our system should update the stops to market once the indicated price is reached
                    */
                
                if(order.getStock().getPrice().compareTo(order.getPrice()) < 0){
                    // inactive
                    
                    break;
                }
                else{
                    // make them marketOrders
                    order.setType(types.getOne(1));
                   
                    buyPhase1 = sells.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(order.getStock(), types.getOne(1), 0);

                    while(order.getOutstanding() > 0 && !buyPhase1.isEmpty()){
                        SellOrder sellOrder = buyPhase1.get(0);
                        Trade newTrade = new Trade();
                        newTrade.setBuyOrder(order);
                        newTrade.setSellOrder(sellOrder);

                        if(order.getOutstanding() >= sellOrder.getOutstanding()){
                            newTrade.setNumber(sellOrder.getOutstanding());
                            order.setOutstanding(order.getOutstanding() - sellOrder.getOutstanding());
                            sellOrder.setOutstanding(0);
                            sells.save(sellOrder);
                            buyPhase1.remove(sellOrder);
                        }
                        else{
                            newTrade.setNumber(order.getOutstanding());
                            sellOrder.setOutstanding(sellOrder.getOutstanding() - order.getOutstanding());
                            order.setOutstanding(0);
                            sells.save(sellOrder);
                        }

                        newTrade.setTime(LocalDateTime.now());
                        newTrade.setPrice(order.getStock().getPrice());
                        
                        buys.save(order);
                        trades.save(newTrade);
                    }

                    if(order.getOutstanding() > 0){
                        buyPhase2 = sells.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(order.getStock(), types.getOne(2), 0);

                        while(order.getOutstanding() > 0 && !buyPhase2.isEmpty()){
                            SellOrder sellOrder = buyPhase2.get(0);
                            Trade newTrade = new Trade();
                            newTrade.setBuyOrder(order);
                            newTrade.setSellOrder(sellOrder);

                            if(order.getOutstanding() >= sellOrder.getOutstanding()){
                                newTrade.setNumber(sellOrder.getOutstanding());
                                order.setOutstanding(order.getOutstanding() - sellOrder.getOutstanding());
                                sellOrder.setOutstanding(0);
                                sells.save(sellOrder);
                                buyPhase2.remove(sellOrder);

                            }
                            else{
                                newTrade.setNumber(order.getOutstanding());
                                sellOrder.setOutstanding(sellOrder.getOutstanding() - order.getOutstanding());
                                order.setOutstanding(0);
                            }

                            newTrade.setTime(LocalDateTime.now());
                            newTrade.setPrice(sellOrder.getPrice());

                            Stock changedStock = order.getStock();
                            changedStock.setPrice(newTrade.getPrice());
                            stocks.editStock(changedStock);
                        
                            buys.save(order);
                            trades.save(newTrade);
                        }
                    }
                    break;
                }
        }
        return order;
    }

    public SellOrder shopSell(SellOrder order) {
        List<BuyOrder> sellPhase1;
        List<BuyOrder> sellPhase2;
        List<BuyOrder> sellPhase3;
            
        switch(order.getType().getType()){
            // new trade will affect the market differently depending on its type
            case "Market":
                /*
                market order trade is compatible with any and all market sell orders of the same stock. we will match them at the current
                stock price until there are either no more sell market orders, or no more outstanding buy orders. The market buys are also compatible with 
                sell limit orders so if it at any point encounters no market sell orders, it will then snap up the most competitive ask limits
                */
                sellPhase1 = buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(order.getStock(), types.getOne(1), 0);
                
                while(order.getOutstanding() > 0 && !sellPhase1.isEmpty()){
                    BuyOrder buyOrder = sellPhase1.get(0);
                    Trade newTrade = new Trade();
                    newTrade.setSellOrder(order);
                    newTrade.setBuyOrder(buyOrder);
                    
                    if(order.getOutstanding() >= buyOrder.getOutstanding()){
                        newTrade.setNumber(buyOrder.getOutstanding());
                        order.setOutstanding(order.getOutstanding() - buyOrder.getOutstanding());
                        buyOrder.setOutstanding(0);
                        buys.save(buyOrder);
                        sellPhase1.remove(buyOrder);
                    }
                    else{
                        newTrade.setNumber(order.getOutstanding());
                        buyOrder.setOutstanding(buyOrder.getOutstanding() - order.getOutstanding());
                        order.setOutstanding(0);
                        buys.save(buyOrder);
                    }
                    
                    newTrade.setTime(LocalDateTime.now());
                    newTrade.setPrice(order.getStock().getPrice());
                    
                    // save our new trade
                    sells.save(order);
                    trades.save(newTrade);
                }
                
                if(order.getOutstanding() > 0){
                    sellPhase2 = buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(order.getStock(), types.getOne(2), 0);
                    sellPhase3 = buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(order.getStock(), types.getOne(3), 0);
                    
                    while(!sellPhase3.isEmpty() && sellPhase3.get(0).getPrice().compareTo(order.getPrice()) > 0){
                        sellPhase3.remove(0);
                    }
                    
                    if(!sellPhase3.isEmpty()){
                        sellPhase3.addAll(sellPhase2);
                        sellPhase2 = sellPhase3;
                    }
                    
                    while(order.getOutstanding() > 0 && !sellPhase2.isEmpty()){
                        BuyOrder buyOrder = sellPhase2.get(0);
                        Trade newTrade = new Trade();
                        newTrade.setSellOrder(order);
                        newTrade.setBuyOrder(buyOrder);
                    
                        if(order.getOutstanding() >= buyOrder.getOutstanding()){
                            newTrade.setNumber(buyOrder.getOutstanding());
                            order.setOutstanding(order.getOutstanding() - buyOrder.getOutstanding());
                            buyOrder.setOutstanding(0);
                            buys.save(buyOrder);
                            sellPhase2.remove(buyOrder);
                            
                        }
                        else{
                            newTrade.setNumber(order.getOutstanding());
                            buyOrder.setOutstanding(buyOrder.getOutstanding() - order.getOutstanding());
                            order.setOutstanding(0);
                        }

                        newTrade.setTime(LocalDateTime.now());
                        BigDecimal price = buyOrder.getPrice();
                        newTrade.setPrice(price);
                        
                        Stock changedStock = order.getStock();
                        changedStock.setPrice(newTrade.getPrice());
                        stocks.editStock(changedStock);
                        
                        sells.save(order);
                        trades.save(newTrade);
                    }
                }
            
                break;
                
            case "Limit":
                
                /*
                A buy limit order should look for market sell orders market sell orders will fulfill at the limit price, to be thorough,
                although this shoudl never happen we will also check for sell limit orders that match with the price
                */
                
                sellPhase1 = buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(order.getStock(), types.getOne(1), 0);
                
                // first check if it is below the markey price
                if(order.getPrice().compareTo(order.getStock().getPrice()) < 1){
                    // we want to check for limit orders after market orders
                    while(order.getOutstanding() > 0 && !sellPhase1.isEmpty()){
                        BuyOrder buyOrder = sellPhase1.get(0);
                        Trade newTrade = new Trade();
                        newTrade.setSellOrder(order);
                        newTrade.setBuyOrder(buyOrder);

                        if(order.getOutstanding() >= buyOrder.getOutstanding()){
                            newTrade.setNumber(buyOrder.getOutstanding());
                            order.setOutstanding(order.getOutstanding() - buyOrder.getOutstanding());
                            buyOrder.setOutstanding(0);
                            buys.save(buyOrder);
                            sellPhase1.remove(buyOrder);

                        }
                        else{
                            newTrade.setNumber(order.getOutstanding());
                            buyOrder.setOutstanding(buyOrder.getOutstanding() - order.getOutstanding());
                            order.setOutstanding(0);
                        }

                        newTrade.setTime(LocalDateTime.now());
                        newTrade.setPrice(order.getPrice());

                        Stock changedStock = order.getStock();
                        changedStock.setPrice(newTrade.getPrice());
                        stocks.editStock(changedStock);
                        
                        sells.save(order);
                        trades.save(newTrade);

                    }
                    
                    
                    if(order.getOutstanding() > 0 ){
                        sellPhase2 = buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(order.getStock(), types.getOne(2), 0);
                        sellPhase3 = buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(order.getStock(), types.getOne(3), 0);
                    
                        while(!sellPhase3.isEmpty() && sellPhase3.get(0).getPrice().compareTo(order.getPrice()) > 0){
                            sellPhase3.remove(0);
                        }

                        if(!sellPhase3.isEmpty()){
                            sellPhase3.addAll(sellPhase2);
                            sellPhase2 = sellPhase3;
                        }
                        
                        while(order.getOutstanding() > 0 && !sellPhase2.isEmpty() && sellPhase2.get(0).getPrice().compareTo(order.getPrice()) >= 0){
                            BuyOrder buyOrder = sellPhase2.get(0);
                            Trade newTrade = new Trade();
                            newTrade.setSellOrder(order);
                            newTrade.setBuyOrder(buyOrder);

                            if(order.getOutstanding() >= buyOrder.getOutstanding()){
                                newTrade.setNumber(buyOrder.getOutstanding());
                                order.setOutstanding(order.getOutstanding() - buyOrder.getOutstanding());
                                buyOrder.setOutstanding(0);
                                buys.save(buyOrder);
                                sellPhase2.remove(buyOrder);

                            }
                            else{
                                newTrade.setNumber(order.getOutstanding());
                                buyOrder.setOutstanding(buyOrder.getOutstanding() - order.getOutstanding());
                                order.setOutstanding(0);
                            }

                            newTrade.setTime(LocalDateTime.now());
                            newTrade.setPrice(buyOrder.getPrice());

                            Stock changedStock = order.getStock();
                            changedStock.setPrice(newTrade.getPrice());
                            stocks.editStock(changedStock);
                        
                            sells.save(order);
                            trades.save(newTrade);
                        }
                    }
                }
                else{
                    while(order.getOutstanding() > 0 && !sellPhase1.isEmpty()){
                        BuyOrder buyOrder = sellPhase1.get(0);
                        Trade newTrade = new Trade();
                        newTrade.setSellOrder(order);
                        newTrade.setBuyOrder(buyOrder);

                        if(order.getOutstanding() >= buyOrder.getOutstanding()){
                            newTrade.setNumber(buyOrder.getOutstanding());
                            order.setOutstanding(order.getOutstanding() - buyOrder.getOutstanding());
                            buyOrder.setOutstanding(0);
                            buys.save(buyOrder);
                            sellPhase1.remove(buyOrder);

                        }
                        else{
                            newTrade.setNumber(order.getOutstanding());
                            buyOrder.setOutstanding(buyOrder.getOutstanding() - order.getOutstanding());
                            order.setOutstanding(0);
                        }

                        newTrade.setTime(LocalDateTime.now());
                        newTrade.setPrice(order.getPrice());

                        Stock changedStock = order.getStock();
                        changedStock.setPrice(newTrade.getPrice());
                        stocks.editStock(changedStock);
                        
                        sells.save(order);
                        trades.save(newTrade);

                    }
                }
                
                break;
             
            default:
                if(order.getStock().getPrice().compareTo(order.getPrice()) < 0){
                    // inactive
                    break;
                }
                else{
                    // make them marketOrders
                    order.setType(types.getOne(1));
                    
                    /*
                    market order trade is compatible with any and all market sell orders of the same stock. we will match them at the current
                    stock price until there are either no more sell market orders, or no more outstanding buy orders. The market buys are also compatible with 
                    sell limit orders so if it at any point encounters no market sell orders, it will then snap up the most competitive ask limits
                    */
                    sellPhase1 = buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(order.getStock(), types.getOne(1), 0);

                    while(order.getOutstanding() > 0 && !sellPhase1.isEmpty()){
                        BuyOrder buyOrder = sellPhase1.get(0);
                        Trade newTrade = new Trade();
                        newTrade.setSellOrder(order);
                        newTrade.setBuyOrder(buyOrder);

                        if(order.getOutstanding() >= buyOrder.getOutstanding()){
                            newTrade.setNumber(buyOrder.getOutstanding());
                            order.setOutstanding(order.getOutstanding() - buyOrder.getOutstanding());
                            buyOrder.setOutstanding(0);
                            buys.save(buyOrder);
                            sellPhase1.remove(buyOrder);
                        }
                        else{
                            newTrade.setNumber(order.getOutstanding());
                            buyOrder.setOutstanding(buyOrder.getOutstanding() - order.getOutstanding());
                            order.setOutstanding(0);
                            buys.save(buyOrder);
                        }

                        newTrade.setTime(LocalDateTime.now());
                        newTrade.setPrice(order.getStock().getPrice());
                        
                        Stock changedStock = order.getStock();
                        changedStock.setPrice(newTrade.getPrice());
                        stocks.editStock(changedStock);

                        // save our new trade
                        sells.save(order);
                        trades.save(newTrade);
                    }

                    if(order.getOutstanding() > 0){
                        sellPhase2 = buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(order.getStock(), types.getOne(2), 0);
                        sellPhase3 = buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(order.getStock(), types.getOne(3), 0);
                    
                        while(!sellPhase3.isEmpty() && sellPhase3.get(0).getPrice().compareTo(order.getPrice()) > 0){
                            sellPhase3.remove(0);
                        }

                        if(!sellPhase3.isEmpty()){
                            sellPhase3.addAll(sellPhase2);
                            sellPhase2 = sellPhase3;
                        }

                        while(order.getOutstanding() > 0 && !sellPhase2.isEmpty()){
                            BuyOrder buyOrder = sellPhase2.get(0);
                            Trade newTrade = new Trade();
                            newTrade.setSellOrder(order);
                            newTrade.setBuyOrder(buyOrder);

                            if(order.getOutstanding() >= buyOrder.getOutstanding()){
                                newTrade.setNumber(buyOrder.getOutstanding());
                                order.setOutstanding(order.getOutstanding() - buyOrder.getOutstanding());
                                buyOrder.setOutstanding(0);
                                buys.save(buyOrder);
                                sellPhase2.remove(buyOrder);

                            }
                            else{
                                newTrade.setNumber(order.getOutstanding());
                                buyOrder.setOutstanding(buyOrder.getOutstanding() - order.getOutstanding());
                                order.setOutstanding(0);
                            }

                            newTrade.setTime(LocalDateTime.now());
                            newTrade.setPrice(buyOrder.getPrice());

                            Stock changedStock = order.getStock();
                            changedStock.setPrice(newTrade.getPrice());
                            stocks.editStock(changedStock);
                        
                            sells.save(order);
                            trades.save(newTrade);
                        }
                    }
                    break;
                }
        }
        return order;
    }
}
