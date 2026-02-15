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
public enum ItemEnum {
    ID_DIVITION("idDivition"),
    ID_SUBDIVITION("idSubdivition"),
    ID_ITEM("idItem"),
    STANDARD_CODE_ITEM("standardCodeItem"),
    DESC_ITEM("descItem"),
    SHORT_DESC_ITEM("short_desc_item"),
    ID_UOM("idUom"),
    PRICE_SALE("priceSale"),
    ID_DEPARTMENT("idDepartment"),
    MIN_QUANTITY("minQuantity"),
    WHOLESALE_PRICE("wholesalePrice"),
    MODIFIED_BY("modifiedBY"),
    CREATION_DATE("creationDate"),
    LAST_MOD_DATE("lastModDate"),
    ID_CURRENCY("idCurrency"),
    MIN_STANDARD_CODE(0L),
    MAX_STANDARD_CODE(999999999999999L);
    
    private ItemEnum(String columnName){
        this.columnName = columnName;
    }
    private ItemEnum(Long rangeValue) {
        this.rangeValue = rangeValue;
    }
    
    private String columnName;
    private Long rangeValue;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Long getRangeValue() {
        return rangeValue;
    }

    public void setRangeValue(Long rangeValue) {
        this.rangeValue = rangeValue;
    }
    
    

    
    
    
    
    
}
