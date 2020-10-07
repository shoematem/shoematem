/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.Stock;
import com.aspire.orderbook.entities.Trade;
import com.aspire.orderbook.repositories.BuyOrderRepo;
import com.aspire.orderbook.repositories.SellOrderRepo;
import com.aspire.orderbook.repositories.StockRepo;
import com.aspire.orderbook.repositories.TradeRepo;

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
    BuyOrderRepo buys;
    
    @Autowired
    SellOrderRepo sells;
    
    @Autowired 
    StockRepo stocks;
    
    @Autowired
    TradeRepo trades;

    @Override
    public Trade getById(int id) {
        return trades.findById(id).orElse(null);
    }

    @Override
    public List<Trade> getAll() {
        return trades.findAll();
    }

    @Override
    public List<Trade> getByStock(Stock stock) {
        List<Trade> result = new ArrayList<>();
        for(Trade trade : trades.findAll())
            if(trade.getBuyOrder().getStock().equals(stock) && trade.getSellOrder().getStock().equals(stock))
                result.add(trade);
        
        return result;
    }

    @Override
    public void deleteTrade(Trade trade) {
        trades.delete(trade);
    }

    @Override
    public Trade addTrade(Trade trade) {
        return trades.save(trade);
    }

    @Override
    public Trade editTrade(Trade trade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
