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
public enum CurrencyEnum {
    ID_CURRENCY("idCurrency"),
    CURRENCY_CODE("currencyCode"),
    DESC_CURRENCY("descCurrency"),
    ISO_CODE_CURRENCY("isoCodeCurrency"),
    CREATION_DATE("creationDate"),
    LAST_MOD_DATE("lastModDate"),
    MODIFIED_BY("modifiedBy");
    
    private CurrencyEnum(String columnName){
        this.columnName = columnName;
    }
    String columnName;
    
    public String getColumnName() {
        return columnName;
    }
}
