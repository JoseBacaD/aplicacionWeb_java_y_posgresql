/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.extended;

import com.mycompany.entity.Item;
import com.mycompany.entity.SaleHeader;
import com.mycompany.enums.SaleHeaderEnum;
import java.io.Serializable;
import java.util.LinkedHashMap;
import org.hibernate.criterion.Example;

/**
 *
 * @author bacajos
 */
public class SaleHeaderExt extends EntityExt implements Serializable {

    private SaleHeader saleHeadEntity;
    
    
    public SaleHeaderExt(){
        saleHeadEntity= new SaleHeader();
        
        countColumnName = SaleHeaderEnum.CONSECUTIVE.getColumnName();
        orderColumnName = SaleHeaderEnum.CONSECUTIVE.getColumnName();
        mapExample = new LinkedHashMap<String, Example>();
       
    }
    
    @Override
    public void entity2Example() {
        example = Example.create(saleHeadEntity);
     }

    
    @Override
    public int getObjId(Object obj) {
        return ((SaleHeader)obj).getIdSaleHeader();

    }

    @Override
    public String getExtClassName() {
        return saleHeadEntity.getClass().getName();
    }

    public SaleHeader getSaleHeadEntity() {
        return saleHeadEntity;
    }

    public void setSaleHeadEntity(SaleHeader saleHeadEntity) {
        this.saleHeadEntity = saleHeadEntity;
    }

  
    
    
    
}
