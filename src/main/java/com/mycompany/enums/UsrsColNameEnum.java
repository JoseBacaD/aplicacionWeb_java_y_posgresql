package com.mycompany.enums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bacajos
 */
public enum UsrsColNameEnum {
    ID_USER("idusrintAI"),
    COD_EMPLOYEE("codEmployeeInt"),
    LAST_NAME("lastNameVarc"),
    MO_LAST_NAME("moLastNameVarc"),
    NAME("nameVarc"),
    ALIAS("aliasVarc"),
    PWD("pwdVarc"),
    DATE_CREATION("dateCreationDate"),
    DATE_LAST_MOD("dateLastModDate"),
    IS_ACTIVE("isActiveBit");
    
    private UsrsColNameEnum(String columnName){
        this.columnName = columnName;
    }
    
    private String columnName;

    public String getColumnName() {
        return columnName;
    }
    
    
    
}
