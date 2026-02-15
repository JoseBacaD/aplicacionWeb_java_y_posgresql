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
public enum MenuOptionEnum {
//    estas constantes deben coincidir con lo que
//    hay en BD para que se puedan validar permisos
    PROC_SALE_OPTION(1),
    CAT_SALE_DETAIL_OPTION(2),
    CAT_SALE_HEADER_OPTION(3),
    CAT_DEPARTMENT_OPTION(4),
    CAT_ITEM_OPTION(5),
    CAT_UOM_OPTION(6),
    APP_CONFIG_OPTION(7),
    HOME_OPTION(8),
    CAT_FRECUENT(9),
    PROC_REFUND(10),
    LABEL_ITEM_PRINT_OPTION(11),
    CAT_PROVIDER_OPTION(12),
    CAT_BRAND_OPTION(13),
    CAT_LOCATION_OPTION(14),
    FRECUENT_REPORT_OPTION(15),
    CASH_CLOSING_OPTION(16),
    REPORT_CASH_CLOSING_OPTION(17);
    
    private MenuOptionEnum(Integer codeMenuOption){
        this.codeMenuOption = codeMenuOption;
    }
    
    private Integer codeMenuOption;

    public Integer getCodeMenuOption() {
        return codeMenuOption;
    }


}
