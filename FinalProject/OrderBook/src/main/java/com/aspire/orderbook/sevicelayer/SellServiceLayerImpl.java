/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.SellOrder;
import com.aspire.orderbook.entities.Stock;
import com.aspire.orderbook.repositories.BuyOrderRepo;
import com.aspire.orderbook.repositories.SellOrderRepo;
import com.aspire.orderbook.repositories.StockRepo;
import com.aspire.orderbook.repositories.TradeRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean5
 */
@Service
public class SellServiceLayerImpl implements SellServiceLayer{
    
    @Autowired
    BuyOrderRepo buys;
    
    @Autowired
    SellOrderRepo sells;
    
    @Autowired 
    StockRepo stocks;
    
    @Autowired
    TradeRepo trades;

    @Override
    public SellOrder getById(int id) {
        return sells.findById(id).orElse(null);
    }

    @Override
    public List<SellOrder> getAll() {
        return sells.findAll();
    }

    @Override
    public List<SellOrder> getByStock(Stock stock) {
        return sells.findByStock(stock);
    }


    @Override
    public void deleteOrder(SellOrder sellOrder) {
        sells.delete(sellOrder);
    }

    @Override
    public SellOrder addOrder(SellOrder sellOrder) {
        return sells.save(sellOrder);
    }

    @Override
    public SellOrder editOrder(SellOrder sellOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
