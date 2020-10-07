/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.repositories.StockRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean5
 */
@Service
public class StockServiceLayerImpl implements StockServiceLayer{
    
    @Autowired
    StockRepo stocks;
    
    @Autowired
    tradingEngine engine;

    @Override
    public Stock getById(int id) {
        return stocks.getOne(id);
    }

    @Override
    public List<Stock> getAll() {
        return stocks.findAll();
    }

    @Override
    public Stock addStock(Stock stock) {
        return stocks.save(stock);
    }

    @Override
    public Stock editStock(Stock stock) {
        
        return stocks.save(stock);
    }

    @Override
    public void deleteStock(Stock stock) {
        stocks.delete(stock);
    }
    
}
