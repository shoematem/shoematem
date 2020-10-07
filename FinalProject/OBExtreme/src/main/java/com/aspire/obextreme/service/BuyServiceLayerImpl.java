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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean5
 */
@Service
public class BuyServiceLayerImpl implements BuyServiceLayer{
    
    @Autowired
    BuyRepo buys;
    
    @Autowired
    SellRepo sells;
    
    @Autowired
    tradingEngine engine;

    @Override
    public BuyOrder getById(int id) {
        return buys.getOne(id);
    }

    @Override
    public List<BuyOrder> getAll() {
        return buys.findAll();
    }

    @Override
    public List<BuyOrder> getByStock(Stock stock) {
        return buys.findByStock(stock);
    }

    @Override
    public List<BuyOrder> getByUser(User user) {
        return buys.findByUser(user);
    }

    @Override
    public List<BuyOrder> getByTradeType(Type type) {
        return buys.findByType(type);
    }

    @Override
    public BuyOrder addOrder(BuyOrder order) {
        BuyOrder postTrade = engine.shopBuy(order);
        
        return buys.save(postTrade);
    }

    @Override
    public BuyOrder editOrder(BuyOrder order) {
        BuyOrder postTrade = engine.shopBuy(order);
        
        return buys.save(postTrade);
    }

    @Override
    public void deleteOrder(BuyOrder order) {
        buys.delete(order);
    }

    @Override
    public List<BuyOrder> getOpenOrders(Stock stock) {
        return buys.findByStockAndOutstandingGreaterThan(stock, 0);
    }

    @Override
    public List<BuyOrder> findByStockAndType(Stock stock, Type type) {
        return buys.findByStockAndTypeAndOutstandingGreaterThanOrderByPriceDesc(stock,type, 0);
    }
    
}
