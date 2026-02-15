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
public enum FrecSaleItemEnum {
    ID_FREC_SALE_ITEM ("idFrecuentSaleItem"),
    CREATION_DATE("creationDate"),
    STANDARD_CODE_ITEM("standardCodeItem"),
    IS_FRECUENT_PROMO("isFrecuentPromo"),
    IS_FRECUENT_EXTRA("isFrecuentExtra"),
    IS_FRECUENT_OTHER("isFrecuentOther");
    
    private FrecSaleItemEnum(String columnName){
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
