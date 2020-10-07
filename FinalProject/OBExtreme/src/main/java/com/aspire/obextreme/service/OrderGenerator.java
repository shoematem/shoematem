package com.aspire.obextreme.service;

import com.aspire.obextreme.entities.BuyOrder;
import com.aspire.obextreme.entities.SellOrder;
import com.aspire.obextreme.entities.Stock;

public interface OrderGenerator {
    BuyOrder generateBuy(Stock stock);
    SellOrder generateSell(Stock stock);
    void run();
}
