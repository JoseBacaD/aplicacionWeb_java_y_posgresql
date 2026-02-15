/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dropdown;


import com.mycompany.dao.BasicDAO;
import java.util.List;
import org.hibernate.criterion.Example;
import java.util.ArrayList;
import com.mycompany.entity.Department;
import java.io.Serializable;
/**
 *
 * @author bacajos
 */
public class DepartmentDropdown  extends AbstractDropdown implements Serializable{
    private static final long serialVersionUID = 1L;
    private Department depEntity;
    
    
    
    
    public DepartmentDropdown(){
        depEntity = new Department();
        example = Example.create(depEntity);
        liObject = new ArrayList<>();
        loadDropdown();
        
    }

    @Override
    public String getEntityClassName() {
        return depEntity.getClass().getName();
    }

    @Override
    public void entityToExample() {
        example = Example.create(depEntity);
    }

    @Override
    public void loadDropdown() {
        BasicDAO.readDropDownList(this);
    }

    public Department getDepEntity() {
        return depEntity;
    }

    public void setDepEntity(Department depEntity) {
        this.depEntity = depEntity;
    }
    
    
    
}
