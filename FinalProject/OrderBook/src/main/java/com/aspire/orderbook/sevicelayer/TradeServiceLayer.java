/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.Stock;
import com.aspire.orderbook.entities.Trade;
import java.util.List;

/**
 *
 * @author sean5
 */
public interface TradeServiceLayer {
    Trade getById(int id);
    List<Trade> getAll();
    List<Trade> getByStock(Stock stock);
    Trade addTrade(Trade trade);
    Trade editTrade(Trade trade);
    void deleteTrade(Trade trade);
}
