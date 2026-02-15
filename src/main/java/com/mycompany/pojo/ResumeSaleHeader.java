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
public class ResumeSaleHeader implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private BigDecimal totCash;
    private BigDecimal totCard;
    private BigDecimal totOther;

    public BigDecimal getTotCash() {
        return totCash;
    }

    public void setTotCash(BigDecimal totCash) {
        this.totCash = totCash;
    }

    public BigDecimal getTotCard() {
        return totCard;
    }

    public void setTotCard(BigDecimal totCard) {
        this.totCard = totCard;
    }

    public BigDecimal getTotOther() {
        return totOther;
    }

    public void setTotOther(BigDecimal totOther) {
        this.totOther = totOther;
    }
    
    
    
}
