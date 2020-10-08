/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.repositories.BuyOrderRepo;
import com.aspire.orderbook.repositories.SellOrderRepo;
import com.aspire.orderbook.repositories.StockRepo;
import com.aspire.orderbook.repositories.TradeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean5
 */
@Service
public class NewOrderGenerator {
    
    @Autowired
    BuyOrderRepo buys;
    
    @Autowired
    SellOrderRepo sells;
    
    @Autowired 
    StockRepo stocks;
    
    @Autowired
    TradeRepo trades;
}
