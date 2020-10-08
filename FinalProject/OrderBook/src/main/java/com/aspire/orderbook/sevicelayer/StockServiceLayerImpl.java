package com.aspire.orderbook.sevicelayer;

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
public class StockServiceLayerImpl implements StockServiceLayer{
    
    @Autowired
    BuyOrderRepo buys;
    
    @Autowired
    SellOrderRepo sells;
    
    @Autowired 
    StockRepo stocks;
    
    @Autowired
    TradeRepo trades;

    @Override
    public Stock getById(int id) {
        return stocks.findById(id).orElse(null);
    }

    @Override
    public List<Stock> getAll() {
        return stocks.findAll();
    }


    @Override
    public void deleteStock(Stock stock) {
        stocks.delete(stock);
    }

    @Override
    public Stock addStock(Stock stock) {
        return stocks.save(stock);
    }

    @Override
    public Stock editStock(Stock stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
