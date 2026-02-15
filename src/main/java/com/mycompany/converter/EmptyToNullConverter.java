/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.converter;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author bacajos
 */
@FacesConverter("EmptyToNullConverter")
public class EmptyToNullConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            if (component instanceof EditableValueHolder) {
                ((EditableValueHolder) component).setSubmittedValue(null);
            }

            return null;
        }

        return submittedValue;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
            return (o == null) ? "" : o.toString();   
           
    }
    
    
    
}
