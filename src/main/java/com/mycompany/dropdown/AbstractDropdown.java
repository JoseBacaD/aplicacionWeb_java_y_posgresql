/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dropdown;

import java.util.List;
import org.hibernate.criterion.Example;

/**
 *
 * @author bacajos
 */
public abstract class AbstractDropdown {
    protected List<Object> liObject;
    protected Example example;

    public List<Object> getLiObject() {
        return liObject;
    }

    public void setLiObject(List<Object> liObject) {
        this.liObject = liObject;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }
    
    
    
    
    
    public abstract String getEntityClassName();
    public abstract void entityToExample();
    public abstract void loadDropdown();
    
}
