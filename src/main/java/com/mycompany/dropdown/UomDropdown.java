/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dropdown;


import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.UnitOfMeasureUom;
import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.criterion.Example;

/**
 *
 * @author bacajos
 */
public class UomDropdown extends AbstractDropdown implements Serializable{
private static final long serialVersionUID = 1L;
    private UnitOfMeasureUom uomEntity;
    
    public UomDropdown(){
        uomEntity = new UnitOfMeasureUom();
        example = Example.create(uomEntity);
        liObject = new ArrayList<>();
        loadDropdown();
    }
    
    
    
    @Override
    public String getEntityClassName() {
        return uomEntity.getClass().getName();
    }

    @Override
    public void entityToExample() {
        example = Example.create(uomEntity);
    }

    @Override
    public void loadDropdown() {
        BasicDAO.readDropDownList(this);
    }

    public UnitOfMeasureUom getUomEntity() {
        return uomEntity;
    }

    public void setUomEntity(UnitOfMeasureUom uomEntity) {
        this.uomEntity = uomEntity;
    }
    
    
}
