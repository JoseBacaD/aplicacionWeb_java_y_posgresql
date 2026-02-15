/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.extended;

import com.mycompany.entity.FrecuentSaleItem;
import com.mycompany.entity.Item;
import com.mycompany.enums.FrecSaleItemEnum;
import com.mycompany.enums.ItemEnum;
import java.io.Serializable;
import java.util.LinkedHashMap;
import org.hibernate.criterion.Example;

/**
 *
 * @author jerry
 */
public class FrecSaleItemExt extends EntityExt implements Serializable {
    
    private FrecuentSaleItem frecSaleItemEntity;
    
    public FrecSaleItemExt(){
        frecSaleItemEntity = new FrecuentSaleItem();
        frecSaleItemEntity.setStandardCodeItem(new Item());
        orderColumnName = FrecSaleItemEnum.ID_FREC_SALE_ITEM.getColumnName();
        countColumnName = FrecSaleItemEnum.ID_FREC_SALE_ITEM.getColumnName();
        mapExample = new LinkedHashMap<String, Example>();
    }

    @Override
    public void entity2Example() {
    example = Example.create(frecSaleItemEntity);
    mapExample.put(
                   FrecSaleItemEnum.STANDARD_CODE_ITEM.getColumnName(),
                   Example.create(frecSaleItemEntity.getStandardCodeItem()));
    }

    @Override
    public int getObjId(Object obj) {
        return ((FrecuentSaleItem)obj).getIdFrecuentSaleItem();
     }

    @Override
    public String getExtClassName() {
      return frecSaleItemEntity.getClass().getName();
    }
    
    
}
