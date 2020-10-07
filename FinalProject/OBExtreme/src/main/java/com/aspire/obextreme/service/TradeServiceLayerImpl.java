/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Trade;
import com.aspire.obextreme.entities.User;
import com.aspire.obextreme.repositories.TradeRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean5
 */
@Service
public class TradeServiceLayerImpl implements TradeServiceLayer{
    
    @Autowired
    TradeRepo trades;

    @Override
    public Trade getById(int id) {
        return trades.getOne(id);
    }

    @Override
    public List<Trade> getAll() {
        return trades.findAll();
    }

    @Override
    public List<Trade> getByStock(Stock stock) {
        List<Trade> allTrades = this.getAll();
        List<Trade> stockTrades = new ArrayList();
        
        for(Trade trade : allTrades){
            if(trade.getBuyOrder().getStock().equals(stock) || trade.getSellOrder().getStock().equals(stock)){
                stockTrades.add(trade);
            }
        }
        
        return stockTrades;
    }

    @Override
    public List<Trade> getByUser(User user) {
        List<Trade> allTrades = this.getAll();
        List<Trade> userTrades = new ArrayList();
        
        for(Trade trade : allTrades){
            if(trade.getBuyOrder().getUser().equals(user) || trade.getSellOrder().getUser().equals(user)){
                userTrades.add(trade);
            }
        }
        
        return userTrades;
    }

    @Override
    public Trade addTrade(Trade trade) {
        return trades.save(trade);
    }

    @Override
    public Trade editTrade(Trade trade) {
        return trades.save(trade);
    }

    @Override
    public void deleteTrade(Trade trade) {
        trades.delete(trade);
    }
    
    @Override
    public List<Trade> mostRecent() {
        return trades.findTop15ByOrderByTimeDesc();
    }
}