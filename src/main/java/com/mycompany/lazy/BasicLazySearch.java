/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lazy;


import com.mycompany.dao.BasicDAO;
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
 * @author bacajos
 */
public class BasicLazySearch extends LazyDataModel<Object> implements SelectableDataModel<Object> {
    static final long serialVersionUID = 1L;
    private List<Object> liObject;
    private EntityExt entity;
    
    public BasicLazySearch(EntityExt entity) {
        this.entity = entity;
    }
    
//    @Override
//    public List<Object> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
//        BasicDAO basicDAO = new BasicDAO(entity);
//        liObject = basicDAO.readLazyList(first, pageSize);
//        
//        if (getRowCount() <= 0){
//            setRowCount(basicDAO.resultSize(first,pageSize));
//        }
//        
//        setPageSize(pageSize);
//        return liObject;
//    }

//    @Override
//    public Object getRowKey(Object object) {
//     return entity.getObjId(object);
//     
//    }

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

    @Override
    public List<Object> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        return null;
    }
    
    
    
}
