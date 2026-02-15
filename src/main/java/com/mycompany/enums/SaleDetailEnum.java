/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enums;

/**
 *
 * @author jerry
 */
public enum SaleDetailEnum {
    
    ID_DETAIL("idDetail"),
    FOLIO("folio"),
    STANDARD_CODE_ITEM("standardCodeItem"),
    QUANTITY("quantity"),
    PRICE_DETAIL("priceDetail"),
    CREATION_DATE("creationDate");

    private SaleDetailEnum(String columnName){
        this.columnName = columnName;
    }
    
    private String columnName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    
}
