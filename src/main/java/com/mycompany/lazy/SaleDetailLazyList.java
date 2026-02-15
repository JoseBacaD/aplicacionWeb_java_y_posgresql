/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lazy;

import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.SaleDetail;
import com.mycompany.extended.EntityExt;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author jerry
 */
public class SaleDetailLazyList extends LazyDataModel<Object> implements SelectableDataModel<Object> {
    private Object [] params;
    private List<Object> liObject;
    private String nameSP;
    private EntityExt entity;
    
    public SaleDetailLazyList(EntityExt entity, String nameSP, Object... params) {
     this.params = params;
     this.nameSP = nameSP;
     this.entity = entity;
    }
    
      @Override
    public List<Object> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
        BasicDAO basicDao = new BasicDAO();
        liObject = basicDao.readSPLazyList(nameSP, first, pageSize, params);
        
       if (getRowCount() <= 0){
            if(liObject.size() > 0){
                SaleDetail saleDetail = (SaleDetail)liObject.get(0); 
                setRowCount(saleDetail.getCountRows().intValue());
        }
        }
        
        setPageSize(pageSize);
        return liObject;
    }

    @Override
    public String getRowKey(Object object) {
     return String.valueOf(entity.getObjId(object));
   }

    
    
    @Override
    public Object getRowData(String userId) {
        Integer id = Integer.valueOf(userId);
        
        for (Object obj : liObject) {
            if (id.equals(entity.getObjId(obj))){
                return obj;
            }
        }
        return null;
    }
    
}
