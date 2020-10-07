package com.aspire.orderbook.sevicelayer;

import com.aspire.orderbook.entities.BuyOrder;
import com.aspire.orderbook.entities.Stock;
import com.aspire.orderbook.repositories.BuyOrderRepo;
import com.aspire.orderbook.repositories.SellOrderRepo;
import com.aspire.orderbook.repositories.StockRepo;
import com.aspire.orderbook.repositories.TradeRepo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean5
 */
@Service
public class BuyServiceLayerImpl implements BuyServiceLayer {

    @Autowired
    BuyOrderRepo buys;

    @Autowired
    SellOrderRepo sells;

    @Autowired
    StockRepo stocks;

    @Autowired
    TradeRepo trades;

    @Override
    public BuyOrder getById(int id) {
        return buys.findById(id).orElse(null);
    }

    @Override
    public List<BuyOrder> getAll() {
        return buys.findAll();
    }

    @Override
    public List<BuyOrder> getByStock(Stock stock) {
        return buys.findByStock(stock);
    }

    @Override
    public void deleteOrder(BuyOrder buyOrder) {
        buys.delete(buyOrder);
    }

    @Override
    public BuyOrder addOrder(BuyOrder buyOrder) {
        return buys.save(buyOrder);
    }

    @Override
    public BuyOrder editOrder(BuyOrder buyOrder) {
        return buys.save(buyOrder);
    }

    @Override
    public BuyOrder generateRandom() {
        Stock stock = new Stock();
        stock.setPrice(new BigDecimal("44.44"));
        stock.setTickSize(new BigDecimal("0.04"));
        stock.setTicker("TEST");

        stocks.save(stock);

        Random random = new Random();
        String[] temp = {"Limit", "Stop", "Market"};

        BuyOrder result = new BuyOrder();
        result.setBuyer(generateBuyer());
        result.setFulfilled(false);
        result.setNumber(random.nextInt(1000)+1);
        result.setPrice(new BigDecimal(BigInteger.valueOf(random.nextInt(100001)), 2));
        result.setType(temp[random.nextInt(3)]);
        result.setStock(stock);

        return result;
    }

    private String generateBuyer() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
                                    + "0123456789" 
                                    + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(4);

        for (int i = 0; i < 4; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
