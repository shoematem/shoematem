package com.aspire.orderbook.repositories;

import java.util.List;

import com.aspire.orderbook.entities.BuyOrder;
import com.aspire.orderbook.entities.SellOrder;
import com.aspire.orderbook.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sean5
 */
@Repository
public interface TradeRepo extends JpaRepository<Trade, Integer>{  
    List findByBuyOrder(BuyOrder buy);
    List findBySellOrder(SellOrder sell);
}