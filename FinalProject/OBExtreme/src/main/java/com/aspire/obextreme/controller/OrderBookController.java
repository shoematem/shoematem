/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.obextreme.controller;

import com.aspire.obextreme.entities.BuyOrder;
import com.aspire.obextreme.entities.SellOrder;
import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Trade;
import com.aspire.obextreme.service.BuyServiceLayer;
import com.aspire.obextreme.service.SellServiceLayer;
import com.aspire.obextreme.service.StockServiceLayer;
import com.aspire.obextreme.service.TradeServiceLayer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author shoem
 */
@Controller
public class OrderBookController
{
    Set<ConstraintViolation<Trade>> tradeViolations = new HashSet<>();
    
    @Autowired
    BuyServiceLayer buys;
    
    @Autowired
    SellServiceLayer sells;
    
    @Autowired
    StockServiceLayer stocks;
    
    @Autowired
    TradeServiceLayer trades;
   
    @GetMapping("orderBook")
    public String getOrderBook(Model model)
    {
        //create new lists to put the sorted lists
        List<BigDecimal> sortedBuys = new ArrayList<>();
        List<BigDecimal> sortedSells = new ArrayList<>();
        List<BuyOrder> finalBuy = new ArrayList<>();
        List<SellOrder> finalSell = new ArrayList<>();
        
        List<Stock> allStocks = stocks.getAll();
        List<Trade> allTrades = trades.getAll();
        List<Trade> recentTrades = trades.mostRecent();
        List<BuyOrder> allBuys = buys.getAll();
        List<SellOrder> allSells = sells.getAll();
        
        model.addAttribute("allStocks", allStocks);
        model.addAttribute("allTrades", allTrades);
        model.addAttribute("recentTrades", recentTrades);
        
        //create an arraylist composed of prices
        for(BuyOrder buyOrder : allBuys)
        {
            //check if it is active
            if(buyOrder.getOutstanding() > 0)
            {
                sortedBuys.add(sortedBuys.size(), buyOrder.getPrice());
            }
        }
        
        //create an arraylist composed of prices
        for(SellOrder sellOrder : allSells)
        {
            //check if it is active
            if(sellOrder.getOutstanding() > 0)
            {
                sortedSells.add(sortedSells.size(), sellOrder.getPrice());
            }
        }
        
        //sort lists by price
        Collections.sort(sortedBuys);
        Collections.reverse(sortedBuys);
        
        Collections.sort(sortedSells);
            
        //add the buys to the final list in order of their price
        //sorted buys is a list of the 
        for (BigDecimal sortedBuy : sortedBuys)
        {
            for (BuyOrder buyOrder : allBuys)
            {
                //if the price matches and the object is not already there, add it to the list
                if (buyOrder.getPrice() == sortedBuy && !finalBuy.contains(buyOrder))
                {
                    finalBuy.add(finalBuy.size(), buyOrder);
                } 
            }
        }
        
        for (BigDecimal sellSort : sortedSells)
        {
            for (SellOrder sellOrder : allSells)
            {
                if (sellOrder.getPrice() == sellSort && !finalSell.contains(sellOrder))
                {
                    finalSell.add(finalSell.size(), sellOrder);
                }
            }
        }
        
        model.addAttribute("finalBuy", finalBuy);
        model.addAttribute("finalSell", finalSell);      

        return "orderBook";
    }
    
    @PostMapping("createTrade")
    public String postManualTrade(HttpServletRequest request)
    {
        Trade newTrade = new Trade();
        
        int buyOrderID = Integer.parseInt(request.getParameter("buyOrderID"));
        int sellOrderID = Integer.parseInt(request.getParameter("sellOrderID"));
        
        BuyOrder buyOrder = buys.getById(buyOrderID);
        SellOrder sellOrder = sells.getById(sellOrderID);
        
        // make sure they have the same stock
        if(!buyOrder.getStock().equals(sellOrder.getStock()))
        {
            return "redirect:/orderBook";
        }

        newTrade.setBuyOrder(buyOrder);
        newTrade.setSellOrder(sellOrder);
        
        //decide how many 
        if(buyOrder.getOutstanding() > sellOrder.getOutstanding())
        {
            newTrade.setNumber(sellOrder.getOutstanding());
            buyOrder.setOutstanding(buyOrder.getOutstanding() - sellOrder.getOutstanding());
            sellOrder.setOutstanding(0);
        }
        else
        {
            newTrade.setNumber(buyOrder.getOutstanding());
            buyOrder.setOutstanding(0);
            sellOrder.setOutstanding(sellOrder.getOutstanding() - buyOrder.getOutstanding());
        }
        
        buys.editOrder(buyOrder);
        sells.editOrder(sellOrder);
        
        // decide on a price
        if(buyOrder.getType().equals(sellOrder.getType()))
        {
            // two limit orders, match at average
            newTrade.setPrice((buyOrder.getPrice().add(sellOrder.getPrice())).divide(new BigDecimal("2")));
        }
        else if(buyOrder.getType().getTypeId() > sellOrder.getType().getTypeId()){
            newTrade.setPrice(buyOrder.getPrice());
        }
        else{
            newTrade.setPrice(sellOrder.getPrice());
        }
        
        newTrade.setTime(LocalDateTime.now());

        //validate newTrade
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        tradeViolations = validate.validate(newTrade);
        
        if(tradeViolations.isEmpty()) {
            trades.addTrade(newTrade);
        }
        
        return "redirect:/orderBook";
    }
    
    @GetMapping("deleteOrder")
    //public String deleteOrder(Integer orderId, boolean buyOrder){
    public String deleteOrder(HttpServletRequest request) {
        boolean isBuy = Boolean.parseBoolean(request.getParameter("isBuy"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        
        BuyOrder buyOrder;
        SellOrder sellOrder;
        
        if(isBuy) {
            buyOrder = buys.getById(orderID);
            
            if(buyOrder.getOutstanding() < buyOrder.getNumber()){
                buyOrder.setOutstanding(0);
            }
            else{
                buys.deleteOrder(buyOrder);
            }
        }
        else{
            sellOrder = sells.getById(orderID);
            
            if(sellOrder.getOutstanding() < sellOrder.getNumber()){
                sellOrder.setOutstanding(0);
            }
            else{
                sells.deleteOrder(sellOrder);
            }
        }
        
        return "redirect:/orderBook";
    }
}