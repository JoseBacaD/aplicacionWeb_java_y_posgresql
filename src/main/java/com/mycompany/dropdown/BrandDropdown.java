/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dropdown;

import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Brand;
import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.criterion.Example;

/**
 *
 * @author yo
 */
public class BrandDropdown extends AbstractDropdown implements Serializable {
     private static final long serialVersionUID = 1L;
     private Brand brandEntity;
     
     public BrandDropdown(){
        brandEntity = new Brand();
        example = Example.create(brandEntity);
        liObject = new ArrayList<>();
        loadDropdown();
     }

    @Override
    public String getEntityClassName() {
         return brandEntity.getClass().getName();
    }

    @Override
    public void entityToExample() {
        example = Example.create(brandEntity);
    }

    @Override
    public void loadDropdown() {
          BasicDAO.readDropDownList(this);
    }

    public Brand getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(Brand brandEntity) {
        this.brandEntity = brandEntity;
    }
     
     
}
