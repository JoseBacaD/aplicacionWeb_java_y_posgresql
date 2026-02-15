/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enums;

/**
 *
 * @author bacajos
 */
public enum SaleHeaderEnum {
    ID_SALE_HEADER("idSaleHeader"),
    FOLIO("folio"),
    SALE_DATE("saleDate"),
    SALE_RATE("rateSale"),
    TOTAL_SALE("totalSale"),
    TAX_TOTAL("taxTotal"),
    CONSECUTIVE("consecutive"),
    CASH_PAYMENT_TYPE(1),
    DEBIT_PAYMENT_TYPE(2),
    CREDIT_PAYMENT_TYPE(3);
    
    private SaleHeaderEnum(String columnName){
        this.columnName = columnName;
    }
    
     private SaleHeaderEnum(Integer paymentType){
        this.paymentType = paymentType;
    }
     
    private String columnName;
    private Integer paymentType;
    
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getPaymentType() {
        return paymentType;
    }
    
    
}
