/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lazy;

import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Item;
import com.mycompany.extended.EntityExt;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author jerry
 */
public class QuickItemLazySearch extends LazyDataModel<Object> {
    private final Object [] params;
    private List<Object> liObject;
    private final String nameSP;
   
    
    public QuickItemLazySearch(String nameSP, Object... params) {
     this.params = params;
     this.nameSP = nameSP;
     
    }
    
    @Override
    public List<Object> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
        BasicDAO basicDao = new BasicDAO();
        liObject = basicDao.readSPLazyList(nameSP, first, pageSize, params);
        
        
        
        if (getRowCount() <= 0){
            if(liObject.size() > 0){
                Item item = (Item)liObject.get(0); 
                setRowCount(item.getCountRows().intValue());
        }
        }
        
        setPageSize(pageSize);
        return liObject;
    }

    @Override
    public String getRowKey(Object item) {
          return String.valueOf(((Item)item).getIdItem());
    }

   
    
    @Override
    public Object getRowData(String rowKey) {
       
        for (Object obj : liObject) {
            if (((Item)obj).getIdItem() == Integer.parseInt(rowKey)) {
                return obj;
        }
        
    }
        return null;
    }
    
}