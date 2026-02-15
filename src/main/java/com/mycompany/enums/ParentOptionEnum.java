/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enums;

/**
 *
 * @author yo
 */
public enum ParentOptionEnum {
    CATALOG_PARENT_MENU(1),
    REPORT_PARENT_MENU(2),
    TRANSACTION_PARENT_MENU(3),
    CONFIGURATION_PARENT_MENU(4),
    LABEL_PARENT_MENU(5),
    OTHER_PARENT_MENU(6);

    private ParentOptionEnum(Integer codeParentOption) {
        this.codeParentOption = codeParentOption;
    }
    
    
    private Integer codeParentOption;

    public Integer getCodeParentOption() {
        return codeParentOption;
    }
    
    
}
