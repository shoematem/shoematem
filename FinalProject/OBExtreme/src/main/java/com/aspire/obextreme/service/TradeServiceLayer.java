/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Trade;
import com.aspire.obextreme.entities.User;
import java.util.List;

/**
 *
 * @author sean5
 */
public interface TradeServiceLayer {
    Trade getById(int id);
    List<Trade> getAll();
    List<Trade> getByStock(Stock stock);
    List<Trade> getByUser(User user);
    Trade addTrade(Trade trade);
    Trade editTrade(Trade trade);
    void deleteTrade(Trade trade);
    List<Trade> mostRecent();
}