/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author jerry
 */
public class RecentItem implements Serializable{
     static final long serialVersionUID = 1L;
     
     private BigDecimal quantity;
     private String description;

    public RecentItem(BigDecimal quantity, String description) {
        this.quantity = quantity;
        this.description = description;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

     
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     
     
}
