package com.aspire.orderbook.entities;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sean5
 */
@Entity
public class Stock {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "stockid")
    private int stockId;
    
    @NotBlank(message = "ticker must not be blank")
    @Size(max = 5, message = "ticker length must be less than 5 characters")
    @Column(nullable = false, name = "ticker")
    private String ticker;
    
    @NotNull(message = "stock must have a price")
    @Digits(integer = 4, fraction = 2, message = "price must be between 0.00 and 9999.99")
    @Column(nullable = false, name = "price")
    private BigDecimal price;
    
    @NotNull(message = "stock must have a tick size")
    @Digits(integer = 0, fraction = 2, message = "tick size must be between .00 and .99")
    @Column(nullable = false, name = "ticksize")
    private BigDecimal tickSize;
    
    // getters and setters 

    public int getStockId() {  return stockId;  }
    public String getTicker() {  return ticker;  }
    public BigDecimal getPrice() {  return price;  }
    public BigDecimal getTickSize() {  return tickSize;  }
    
    public void setStockId(int stockId) {  this.stockId = stockId;  }
    public void setTicker(String ticker) {  this.ticker = ticker;  }
    public void setPrice(BigDecimal price) {  this.price = price;  }
    public void setTickSize(BigDecimal tickSize) {  this.tickSize = tickSize;  }

    // equals and hashcode 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.stockId;
        hash = 13 * hash + Objects.hashCode(this.ticker);
        hash = 13 * hash + Objects.hashCode(this.price);
        hash = 13 * hash + Objects.hashCode(this.tickSize);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stock other = (Stock) obj;
        if (this.stockId != other.stockId) {
            return false;
        }
        if (!Objects.equals(this.ticker, other.ticker)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.tickSize, other.tickSize)) {
            return false;
        }
        return true;
    }
}
