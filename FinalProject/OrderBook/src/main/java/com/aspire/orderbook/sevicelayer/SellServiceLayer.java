/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.SellOrder;
import com.aspire.orderbook.entities.Stock;
import java.util.List;

/**
 *
 * @author sean5
 */
public interface SellServiceLayer {
    SellOrder getById(int id);
    List<SellOrder> getAll();
    List<SellOrder> getByStock(Stock stock);
    SellOrder addOrder(SellOrder buyOrder);
    SellOrder editOrder(SellOrder buyOrder);
    void deleteOrder(SellOrder buyOrder);
}
