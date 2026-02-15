/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enums;

import java.math.BigDecimal;

/**
 *
 * @author bacajos
 */
public enum ProcSaleEnum {
    MIN_QUANTITY(1),
    ZERO_PAYMENT(new BigDecimal("0.00"));
    
    
    private ProcSaleEnum( int minQuantity){
        this.minQuantity = minQuantity;
    }
    
    private ProcSaleEnum(BigDecimal payment){
        this.paymet = payment;
    }
    
    private BigDecimal paymet;
    private int minQuantity;

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public BigDecimal getPaymet() {
        return paymet;
    }

    public void setPaymet(BigDecimal paymet) {
        this.paymet = paymet;
    }
    
    
    
}
