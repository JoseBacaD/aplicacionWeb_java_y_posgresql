/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.converter;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("defaultDateConverter")
public class DefaultDateConverter extends DateTimeConverter {

    public DefaultDateConverter() {
        setPattern("yyyy/MM/dd");
    }
    
    
    
}
