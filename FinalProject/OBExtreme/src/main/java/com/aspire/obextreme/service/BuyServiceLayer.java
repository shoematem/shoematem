/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.BuyOrder;
import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Type;
import com.aspire.obextreme.entities.User;
import java.util.List;

/**
 *
 * @author sean5
 */
public interface BuyServiceLayer {
    BuyOrder getById(int id);
    List<BuyOrder> getAll();
    List<BuyOrder> getOpenOrders(Stock stock);
    List<BuyOrder> getByStock(Stock stock);
    List<BuyOrder> getByUser(User user);
    List<BuyOrder> getByTradeType(Type type);
    BuyOrder addOrder(BuyOrder order);
    BuyOrder editOrder(BuyOrder order);
    void deleteOrder(BuyOrder order);
    List<BuyOrder> findByStockAndType(Stock stock, Type type);
}
