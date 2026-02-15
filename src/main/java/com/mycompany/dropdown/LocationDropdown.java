/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dropdown;

import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Location;
import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.criterion.Example;

/**
 *
 * @author yo
 */
public class LocationDropdown extends AbstractDropdown implements Serializable {
    private static final long serialVersionUID = 1L;
    private Location locationEntity;

    public LocationDropdown() {
         locationEntity = new Location();
        example = example.create(locationEntity);
        liObject = new ArrayList<>();
        loadDropdown();
        
    }

    @Override
    public String getEntityClassName() {
         return locationEntity.getClass().getName();
    }

    @Override
    public void entityToExample() {
          example = Example.create(locationEntity);
    }

    @Override
    public void loadDropdown() {
          BasicDAO.readDropDownList(this);
    }

    public Location getLocationEntity() {
        return locationEntity;
    }

    public void setLocationEntity(Location locationEntity) {
        this.locationEntity = locationEntity;
    }
    
    
}
