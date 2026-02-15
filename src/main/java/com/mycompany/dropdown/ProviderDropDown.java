/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dropdown;

import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Provider;
import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.criterion.Example;
/**
 *
 * @author yo
 */
public class ProviderDropDown extends AbstractDropdown implements Serializable {
    private static final long serialVersionUID = 1L;
    private Provider providerEntity;

    public ProviderDropDown() {
        providerEntity = new Provider();
        example = Example.create(providerEntity);
        liObject = new ArrayList<>();
        loadDropdown();
    }

    @Override
    public String getEntityClassName() {
        return providerEntity.getClass().getName();
    }

    @Override
    public void entityToExample() {
        example = Example.create(providerEntity);
    }

    @Override
    public void loadDropdown() {
        BasicDAO.readDropDownList(this);
    }

    public Provider getProviderEntity() {
        return providerEntity;
    }

    public void setProviderEntity(Provider providerEntity) {
        this.providerEntity = providerEntity;
    }

   
    
    
}
