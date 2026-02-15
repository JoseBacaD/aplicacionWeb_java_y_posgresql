/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;


import com.mycompany.extended.EntityExt;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author bacajos
 */
public abstract class AbstractCatalogBean {
    
    protected String answerMessage;
    protected LazyDataModel<Object> objLazyList = null;
    protected EntityExt entityExt; 
    

    public String getAnswerMessage() {
        return answerMessage;
    }

    public void setAnswerMessage(String answerMessage) {
        this.answerMessage = answerMessage;
    }

    public LazyDataModel<Object> getObjLazyList() {
        return objLazyList;
    }

    public void setObjLazyList(LazyDataModel<Object> objLazyList) {
        this.objLazyList = objLazyList;
    }

    public EntityExt getEntityExt() {
        return entityExt;
    }

    public void setEntityExt(EntityExt entityExt) {
        this.entityExt = entityExt;
    }
    
    

    public abstract void populateTable();
    public abstract void callMerge();
    public abstract void callDelete();
    public abstract void addNew();
    public abstract void resetVariables();
}
