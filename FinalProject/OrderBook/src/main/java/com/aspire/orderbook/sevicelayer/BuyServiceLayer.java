/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.BuyOrder;
import com.aspire.orderbook.entities.Stock;
import java.util.List;

/**
 *
 * @author sean5
 */
public interface BuyServiceLayer {
    BuyOrder getById(int id);
    List<BuyOrder> getAll();
    List<BuyOrder> getByStock(Stock stock);
    BuyOrder addOrder(BuyOrder buyOrder);
    BuyOrder editOrder(BuyOrder buyOrder);
    void deleteOrder(BuyOrder buyOrder);
    BuyOrder generateRandom();
}
