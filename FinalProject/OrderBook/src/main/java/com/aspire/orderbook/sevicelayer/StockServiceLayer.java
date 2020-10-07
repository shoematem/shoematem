/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.Stock;
import java.util.List;

/**
 *
 * @author sean5
 */
public interface StockServiceLayer {
    Stock getById(int id);
    List<Stock> getAll();
    Stock addStock(Stock stock);
    Stock editStock(Stock stock);
    void deleteStock(Stock stock);
}
