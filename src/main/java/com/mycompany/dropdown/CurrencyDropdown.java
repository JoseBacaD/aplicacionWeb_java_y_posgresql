/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dropdown;


import com.mycompany.dao.BasicDAO;
import java.util.ArrayList;
import org.hibernate.criterion.Example;
import com.mycompany.entity.Currency;
import java.io.Serializable;
/**
 *
 * @author bacajos
 * 
 */
public class CurrencyDropdown extends AbstractDropdown implements Serializable {
private static final long serialVersionUID = 1L;
    private Currency currencyEntity;
    
    public CurrencyDropdown(){
        currencyEntity = new Currency();
        liObject = new ArrayList<>();
        example = Example.create(currencyEntity);
        loadDropdown();
    }
    
    @Override
    public String getEntityClassName() {
        return currencyEntity.getClass().getName();
    }

    @Override
    public void entityToExample() {
        example = Example.create(currencyEntity);
    }

    @Override
    public void loadDropdown() {
        BasicDAO.readDropDownList(this);
    }

    public Currency getCurrencyEntity() {
        return currencyEntity;
    }

    public void setCurrencyEntity(Currency currencyEntity) {
        this.currencyEntity = currencyEntity;
    }
    
    
    
}
