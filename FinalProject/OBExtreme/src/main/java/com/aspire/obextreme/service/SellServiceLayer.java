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
import java.util.List;

/**
 *
 * @author sean5
 */
public interface SellServiceLayer {
    SellOrder getById(int id);
    List<SellOrder> getAll();
    List<SellOrder> getOpenOrders(Stock stock);
    List<SellOrder> getByStock(Stock stock);
    List<SellOrder> getByUser(User user);
    List<SellOrder> getByTradeType(Type type);
    SellOrder addOrder(SellOrder order);
    SellOrder editOrder(SellOrder order);
    void deleteOrder(SellOrder order);
    List<SellOrder> findByStockAndType(Stock stock, Type type);
}
