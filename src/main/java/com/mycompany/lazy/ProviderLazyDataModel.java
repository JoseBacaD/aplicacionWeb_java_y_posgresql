/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lazy;

import com.mycompany.dao.BasicDAO;
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
public class ProviderLazyDataModel extends LazyDataModel<Provider>{

    private final Object [] params;
    private List<Object> liObject;
    private final String nameSP;

 public ProviderLazyDataModel(String nameSP, Object... params) {
     this.params = params;
     this.nameSP = nameSP;
    
    }
 
    
   @Override
    public List<Provider> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
         BasicDAO basicDao = new BasicDAO();
        liObject = basicDao.readSPLazyList(nameSP, first, pageSize, params);
        
        if (getRowCount() <= 0){
            if(liObject.size() > 0){
                Provider provider = (Provider)liObject.get(0); 
                setRowCount(provider.getCountRows().intValue());
        }
        }
        
        
        setPageSize(pageSize);
        return (List<Provider>)(Object)liObject;
    }

    @Override
    public String getRowKey(Provider provider) {
        return String.valueOf(provider.getIdProvider());
    }

   
   
   

    @Override
    public Provider getRowData(String rowKey) {
        for (Provider provider : (List<Provider>)(Object)liObject ) {
            if (provider.getIdProvider()== Integer.parseInt(rowKey)) {
                return provider;
            }
        }

        return null;
    }
    
    
    
}
