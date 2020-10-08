/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.controller;

import java.util.List;

import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Trade;
import com.aspire.obextreme.service.StockServiceLayer;
import com.aspire.obextreme.service.TradeServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author sean5
 */
@Controller
public class MainController
{    
    @Autowired
    TradeServiceLayer trades;
    
    @Autowired
    StockServiceLayer stocks;
    
    @RequestMapping(value="/")
    public String getIndexPage(Model model)
    {
        List<Stock> stocksList = stocks.getAll();
        List<Trade> tradesList = trades.getAll();
        List<Trade> recentTrades = trades.mostRecent();
        
        model.addAttribute("stocksList", stocksList);
        model.addAttribute("tradesList", tradesList);
        model.addAttribute("recentTrades", recentTrades);
        
        return "index";
    }
}