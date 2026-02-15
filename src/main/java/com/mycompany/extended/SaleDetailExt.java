/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.extended;

import com.mycompany.entity.Item;
import java.io.Serializable;
import com.mycompany.entity.SaleDetail;
import com.mycompany.entity.SaleHeader;
import com.mycompany.enums.ItemEnum;
import com.mycompany.enums.SaleDetailEnum;
import com.mycompany.enums.SaleHeaderEnum;
import com.mycompany.pojo.AbstractField;
import java.util.LinkedHashMap;
import org.hibernate.criterion.Example;
/**
 *
 * @author bacajos
 */
public class SaleDetailExt extends EntityExt implements Serializable {

    private SaleDetail saleDetsrch;
    public AbstractField dateField1;
//    private SaleDetail listed;
    
    public SaleDetailExt(){
        saleDetsrch = new SaleDetail();
        saleDetsrch.setFolio(new SaleHeader());
        saleDetsrch.setStandardCodeItem(new Item()); 
        
        dateField1= new AbstractField();
        dateField1.setId(SaleDetailEnum.CREATION_DATE.getColumnName());
//        listed = new SaleDetail();
//        listed.setFolio(new SaleHeader());
//        listed.setStandardCodeItem(new Item()); 
//        
        mapExample = new LinkedHashMap<String, Example>();
        
        countColumnName = SaleHeaderEnum.FOLIO.getColumnName();
        orderColumnName = SaleHeaderEnum.FOLIO.getColumnName();
    }
    
    
    
    @Override
    public void entity2Example() {
        
        example = Example.create(saleDetsrch);
        
    mapExample.put(
                   SaleHeaderEnum.FOLIO.getColumnName(),
                   Example.create(saleDetsrch.getFolio()));
    }
        
     

    @Override
    public int getObjId(Object obj) {
        return  ((SaleDetail)obj).getIdDetail();
    }

    @Override
    public String getExtClassName() {
        return saleDetsrch.getClass().getName();
    }

    public SaleDetail getSaleDetsrch() {
        return saleDetsrch;
    }

    public void setSaleDetsrch(SaleDetail saleDetsrch) {
        this.saleDetsrch = saleDetsrch;
    }
    
    
    

//    public SaleDetail getListed() {
//        return listed;
//    }
//
//    public void setListed(SaleDetail listed) {
//        this.listed = listed;
//    }

    public AbstractField getDateField1() {
        return dateField1;
    }

    public void setDateField1(AbstractField dateField1) {
        this.dateField1 = dateField1;
    }
    
    
    
}
