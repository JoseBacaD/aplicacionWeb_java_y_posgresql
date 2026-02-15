/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wrapper;

import com.mycompany.enums.ProcSaleEnum;
import com.mycompany.extended.ItemExt;
import com.mycompany.extended.SaleHeaderExt;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author bacajos
 */
public class ProcSaleWrapper implements Serializable {
    private ItemExt itemSearch;
    private SaleHeaderExt saleHeader;
    private BigDecimal quantity;
    BigDecimal subtotal;
    BigDecimal total;
    BigDecimal tax;
    BigDecimal change;
    BigDecimal cashPayment;
    BigDecimal debitPayment;
    BigDecimal cardPayment;
    BigDecimal otherPayment;  
    BigDecimal totPaymet;
    
    public ProcSaleWrapper(){
        itemSearch = new ItemExt();
        quantity = new BigDecimal("1");
        cashPayment = ProcSaleEnum.ZERO_PAYMENT.getPaymet();
        cardPayment = ProcSaleEnum.ZERO_PAYMENT.getPaymet();
        debitPayment = ProcSaleEnum.ZERO_PAYMENT.getPaymet();
        otherPayment = ProcSaleEnum.ZERO_PAYMENT.getPaymet();
        totPaymet = ProcSaleEnum.ZERO_PAYMENT.getPaymet();
        
         saleHeader = new SaleHeaderExt();
         saleHeader.getSaleHeadEntity().setFolioCollection(new ArrayList<>());
         subtotal = new BigDecimal(0.00).setScale(2,RoundingMode.HALF_UP);
        total = new BigDecimal(0.00).setScale(2,RoundingMode.HALF_UP);
        tax = new BigDecimal(0.00).setScale(2,RoundingMode.HALF_UP);
        change = ProcSaleEnum.ZERO_PAYMENT.getPaymet();
        
    }

    public ItemExt getItemSearch() {
        return itemSearch;
    }

    public void setItemSearch(ItemExt itemSearch) {
        this.itemSearch = itemSearch;
    }

    public SaleHeaderExt getSaleHeader() {
        return saleHeader;
    }

    public void setSaleHeader(SaleHeaderExt saleHeader) {
        this.saleHeader = saleHeader;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

  

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(BigDecimal cashPayment) {
        this.cashPayment = cashPayment;
    }

    public BigDecimal getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(BigDecimal cardPayment) {
        this.cardPayment = cardPayment;
    }

    public BigDecimal getOtherPayment() {
        return otherPayment;
    }

    public void setOtherPayment(BigDecimal otherPayment) {
        this.otherPayment = otherPayment;
    }

    public BigDecimal getTotPaymet() {
        return totPaymet;
    }

    public void setTotPaymet(BigDecimal totPaymet) {
        this.totPaymet = totPaymet;
    }

    public BigDecimal getDebitPayment() {
        return debitPayment;
    }

    public void setDebitPayment(BigDecimal debitPayment) {
        this.debitPayment = debitPayment;
    }
    
    

}
