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
public enum UOMEnum {
    ID_UOM("idUom"),
    CODE_UOM("codeUom"),
    SHORT_NAME("shortName"),
    DESC_UOM("descUom"),
    MODIFIED_BY("modifiedBy"),
    CREATION_DATE("creationDate"),
    LAST_MOD_DATE("lastModDate"),
    ID_LANGUAGE("idLanguage"),
    MIN_CODE_UOM(0),
    MAX_CODE_UOM(999);
    
    private UOMEnum(String columnName){
        this.columnName = columnName;
    }
    
    private UOMEnum(Integer rangeValue){
        this.rangeValue = rangeValue;
    }
    
    private String columnName;
    private Integer rangeValue;

    public String getColumnName() {
        return columnName;
    }

    public Integer getRangeValue() {
        return rangeValue;
    }

   
    
    
    
  
}
