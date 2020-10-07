/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.BuyOrder;
import com.aspire.obextreme.entities.SellOrder;
import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Type;
import com.aspire.obextreme.entities.User;
import com.aspire.obextreme.repositories.BuyRepo;
import com.aspire.obextreme.repositories.SellRepo;
import java.util.ArrayList;
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
    BuyRepo buys;
    
    @Autowired
    SellRepo sells;
    
    @Autowired
    tradingEngine engine;

    @Override
    public SellOrder getById(int id) {
        return sells.getOne(id);
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
    public List<SellOrder> getByUser(User user) {
        return sells.findByUser(user);
    }

    @Override
    public List<SellOrder> getByTradeType(Type type) {
        return sells.findByType(type);
    }

    @Override
    public SellOrder addOrder(SellOrder order) {
        SellOrder postTrade = engine.shopSell(order);
        
        return sells.save(postTrade);
    }

    @Override
    public SellOrder editOrder(SellOrder order) {
        SellOrder postTrade = engine.shopSell(order);
        
        return sells.save(postTrade);
    }

    @Override
    public void deleteOrder(SellOrder order) {
        sells.delete(order);
    }

    @Override
    public List<SellOrder> getOpenOrders(Stock stock) {
        return sells.findByStockAndOutstandingGreaterThan(stock, 0);
    }

    @Override
    public List<SellOrder> findByStockAndType(Stock stock, Type type) {
        return sells.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceAsc(stock, type, 0);
    }
    
}
