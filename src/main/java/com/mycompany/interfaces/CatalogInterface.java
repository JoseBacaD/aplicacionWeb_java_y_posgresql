/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

import com.mycompany.extended.EntityExt;
import com.mycompany.extended.SaleDetailExt;
import java.util.Calendar;
import java.util.Date;
import org.primefaces.model.LazyDataModel;



/**
 *
 * @author bacajos
 */
public interface CatalogInterface {
    public void runMerge();

    /**
     *método para  eliminar el registro seleccionado en la vista,
     * no recibe parametro pero hay que enviar la vista al controlador
     * a través del objeto controlador perteneciente a esa vista.
     */
    public void runDelete();
    
    public void translations();
    
    public void businessRules();
    
    public void mergeRules();
    
    public void deleteRules();
    
    public Date getToday();
    
    public LazyDataModel<Object> callReadLazyList(EntityExt entity);
}
