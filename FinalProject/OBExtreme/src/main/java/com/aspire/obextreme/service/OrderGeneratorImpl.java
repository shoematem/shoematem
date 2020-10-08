package com.aspire.obextreme.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import com.aspire.obextreme.entities.BuyOrder;
import com.aspire.obextreme.entities.SellOrder;
import com.aspire.obextreme.entities.Stock;
import com.aspire.obextreme.entities.Type;
import com.aspire.obextreme.entities.User;
import com.aspire.obextreme.repositories.StockRepo;
import com.aspire.obextreme.repositories.TypeRepo;
import com.aspire.obextreme.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class OrderGeneratorImpl implements OrderGenerator {

    static final Random random = new Random();

    @Autowired
    BuyServiceLayer buys;

    @Autowired
    SellServiceLayer sells;

    @Autowired
    StockRepo stocks;

    @Autowired
    UserRepo users;

    @Autowired
    TypeRepo types;

    // Generates a BuyOrder when given a Stock
    // Number of stocks set randomly between 1-1000
    // Stock type generated via private method
    // BuyOrder price randomly set based on stock type and stock price
    public BuyOrder generateBuy(Stock stock) {
        BuyOrder result = new BuyOrder();
        result.setUser(generateUser());
        result.setNumber(random.nextInt(1000)+1);
        result.setOutstanding(result.getNumber());
        result.setType(generateType());
        result.setPrice(generateBuyPrice(stock, result.getType()).setScale(2, RoundingMode.CEILING));
        result.setStock(stock);

        return result;
    }

    // Generates a SellOrder when given a Stock
    // Number of stocks set randomly between 1-1000
    // Stock type generated via private method
    // BuyOrder price randomly set based on stock type and stock price
    public SellOrder generateSell(Stock stock) {
        SellOrder result = new SellOrder();
        result.setUser(generateUser());
        result.setNumber(random.nextInt(1000)+1);
        result.setOutstanding(result.getNumber());
        result.setType(generateType());
        result.setPrice(generateSellPrice(stock, result.getType()));
        result.setStock(stock);

        return result;
    }

    // Randomly generates a type
    // 10% Stop
    // 20% Market
    // 70% Limit
    private Type generateType() {
        int r = random.nextInt(100)+1;

        if(r <= 10)
            return types.findById(3).orElse(null);
        else if(r > 10 && r <= 30)
            return types.findById(1).orElse(null);
        else
            return types.findById(2).orElse(null);
    }

    // Randomly generates a user based on User database
    private User generateUser() {
        return users.findAll().get(random.nextInt(users.findAll().size()));
    }

    // Randomly generates BuyOrder price based on stock type 
    // If stock type is limit, price is set between 75%-99% of stock price
    // If stock type is stop, price is set between 100%-125% of stock price
    // If stock type is market, price is set at market
    private BigDecimal generateBuyPrice(Stock stock, Type type) {
        if(type.getType().equalsIgnoreCase("limit"))
            return stock.getPrice().multiply(new BigDecimal(random.nextInt(25)+75)).divide(new BigDecimal("100.00")).setScale(2, RoundingMode.HALF_UP);
        else if(type.getType().equalsIgnoreCase("stop"))
            return stock.getPrice().multiply(new BigDecimal(random.nextInt(25)+100)).divide(new BigDecimal("100.00")).setScale(2, RoundingMode.HALF_UP);
        else
            return stock.getPrice().setScale(2, RoundingMode.HALF_UP);
    }

    // Randomly generates SellOrder price based on stock type 
    // If stock type is stop, price is set between 75%-99% of stock price
    // If stock type is limit, price is set between 100%-125% of stock price
    // If stock type is market, price is set at market
    private BigDecimal generateSellPrice(Stock stock, Type type) {
        if(type.getType().equalsIgnoreCase("stop"))
            return stock.getPrice().multiply(new BigDecimal(random.nextInt(25)+75)).divide(new BigDecimal("100.00")).setScale(2, RoundingMode.HALF_UP);
        else if(type.getType().equalsIgnoreCase("limit"))
            return stock.getPrice().multiply(new BigDecimal(random.nextInt(50)+100)).divide(new BigDecimal("100.00")).setScale(2, RoundingMode.HALF_UP);
        else
            return stock.getPrice().setScale(2, RoundingMode.HALF_UP);
    }

    // Random order generator that generates either a BuyOrder/SellOrder every 10 seconds
    // 50% chance of generating a BuyOrder
    // 50% chance of generating a SellOrder
    @Scheduled(fixedDelay = 10000)
    public void run() {
        int r = random.nextInt(100) + 1;

        if (r < 50)
            buys.addOrder(generateBuy(stocks.findAll().get(random.nextInt(stocks.findAll().size()))));
        else
            sells.addOrder(generateSell(stocks.findAll().get(random.nextInt(stocks.findAll().size()))));
    }
}
