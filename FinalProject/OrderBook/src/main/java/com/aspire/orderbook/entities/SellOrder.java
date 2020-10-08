package com.aspire.orderbook.entities;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sean5
 */
@Entity(name = "sell_order")
public class SellOrder {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "orderid")
    private int orderId;
    
    @NotBlank(message = "sell order type must not be blank")
    @Size(max = 45, message = "sell order size must be less than 45 characters")
    @Column(nullable = false, name = "type")
    private String type;
    
    @NotNull(message = "sell order quantity must not be blank")
    @Column(nullable = false, name = "number")
    private int number;
    
    @Digits(integer = 4, fraction = 2, message = "sell order price must be between 0.00 and 9999.99")
    @Column
    private BigDecimal price;
    
    @NotBlank(message = "MPID must not be blank")
    @Size(max = 45, message = "MPID must be less than 45 characters")
    @Column(nullable = false, name = "mpid")
    private String seller;
    
    @NotNull(message = "sell order must have fulfillment value")
    @Column(nullable = false, name = "fulfulled")
    private boolean fulfilled;
    
    @NotNull(message = "there must be a stock for a sell order")
    @ManyToOne
    @JoinColumn(name = "stock_stockid", nullable = false)
    private Stock stock;

    // getters and setters

    public int getOrderId() {  return orderId;  }
    public String getType() {  return type;  }
    public int getNumber() {  return number;  }
    public BigDecimal getPrice() {  return price;  }
    public String getSeller() {  return seller;  }
    public boolean isFulfilled() {  return fulfilled;  }
    public Stock getStock() {  return stock;  }

    public void setOrderId(int orderId) {  this.orderId = orderId;  }
    public void setType(String type) {  this.type = type;  }
    public void setNumber(int number) {  this.number = number;  }
    public void setPrice(BigDecimal price) {  this.price = price;  }
    public void setSeller(String seller) {  this.seller = seller;  }
    public void setFulfilled(boolean fulfilled) {  this.fulfilled = fulfilled;  }
    public void setStock(Stock stock) {  this.stock = stock;  }

    // equals and hashcode

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.orderId;
        hash = 43 * hash + Objects.hashCode(this.type);
        hash = 43 * hash + this.number;
        hash = 43 * hash + Objects.hashCode(this.price);
        hash = 43 * hash + Objects.hashCode(this.seller);
        hash = 43 * hash + (this.fulfilled ? 1 : 0);
        hash = 43 * hash + Objects.hashCode(this.stock);
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
        final SellOrder other = (SellOrder) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (this.fulfilled != other.fulfilled) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.seller, other.seller)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        return true;
    }
}
