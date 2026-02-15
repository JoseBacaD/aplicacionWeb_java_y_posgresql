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
public enum DepartmentEnum {
    
    ID_DEPARTMENT("idDepartment"),
    CODE_DEPARTMENT("codeDepartment"),
    DESC_DEPARTMENT("descDepartment"),
    MODIFY_BY("modifiedBy"),
    CREATION_DATE("creationDate"),
    LAST_MOD_DATE("lastModDate"),
    MIN_CODE_DEPARTMENT(0),
    MAX_CODE_DEPARTMENT(9999);
    
    private DepartmentEnum(String columnName){
        this.columnName = columnName;
    }
    
    private DepartmentEnum(Integer rangeValue){
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

    public void setRangeValue(Integer rangeValue) {
        this.rangeValue = rangeValue;
    }
    
    
}
