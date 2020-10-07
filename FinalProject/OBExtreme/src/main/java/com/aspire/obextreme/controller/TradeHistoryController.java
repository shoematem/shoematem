/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.controller;

import com.aspire.obextreme.entities.Trade;
import com.aspire.obextreme.service.TradeServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author shoem
 */
@Controller
public class TradeHistoryController
{
    @Autowired
    TradeServiceLayer trades;
    
    @GetMapping("tradeHistory")
    public String getTradeHistory(Model model)
    {
        List<Trade> tradesList = trades.getAll();
        List<Trade> recentTrades = trades.mostRecent();
        
        model.addAttribute("tradesList", tradesList);
        model.addAttribute("recentTrades", recentTrades);
        
        return "tradeHistory";
    }
}