/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lazy;

import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Brand;
import com.mycompany.entity.Provider;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author yo
 */
public class BrandLazyDataModel extends LazyDataModel<Brand> {
    private final Object [] params;
    private List<Object> liObject;
    private final String nameSP;
    private final String nameSPCount;

    public BrandLazyDataModel(String nameSP, String nameSPCount, Object... params) {
     this.params = params;
     this.nameSP = nameSP;
     this.nameSPCount = nameSPCount;
    
    }

    @Override
    public List<Brand> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
         BasicDAO basicDao = new BasicDAO();
        liObject = basicDao.readSPLazyList(nameSP, first, pageSize, params);
        
       if (getRowCount() <= 0){
            if(liObject.size() > 0){
                Brand brand = (Brand)liObject.get(0); 
                setRowCount(brand.getCountRows().intValue());
        }
        }
        
        
        setPageSize(pageSize);
        return (List<Brand>)(Object)liObject;
    }

    @Override
    public String getRowKey(Brand brand) {
        return String.valueOf(brand.getIdBrand());
    }

   
   
   

    @Override
    public Brand getRowData(String rowKey) {
        for (Brand brand : (List<Brand>)(Object)liObject ) {
            if (brand.getIdBrand()== Integer.parseInt(rowKey)) {
                return brand;
            }
        }

        return null;
    }
    
    
    
}
