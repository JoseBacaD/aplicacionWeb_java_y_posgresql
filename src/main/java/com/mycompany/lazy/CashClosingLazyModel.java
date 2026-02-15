/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lazy;

import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.CashClosing;
import com.mycompany.entity.Item;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author yo
 */
public class CashClosingLazyModel  extends LazyDataModel<CashClosing>{
    
    private final Object [] params;
    private List<Object> liObject;
    private final String nameSP;
    
    public CashClosingLazyModel(String nameSP, Object... params) {
     this.params = params;
     this.nameSP = nameSP;
    
    }
    
    @Override
    public List<CashClosing> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
         BasicDAO basicDao = new BasicDAO();
        liObject = basicDao.readSPLazyList(nameSP, first, pageSize, params);
        
      if (getRowCount() <= 0){
            if(liObject.size() > 0){
                CashClosing cashClosing = (CashClosing)liObject.get(0); 
                setRowCount(cashClosing.getCountRows().intValue());
        }
        }
        
        setPageSize(pageSize);
        return (List<CashClosing>)(Object)liObject;
    }
    
   @Override
    public String getRowKey(CashClosing cashClosing) {
        return String.valueOf(cashClosing.getIdCashClosing());
    }

   
   
   

    @Override
    public CashClosing getRowData(String rowKey) {
        for (CashClosing cashClosing : (List<CashClosing>)(Object)liObject ) {
            if (cashClosing.getIdCashClosing() == Integer.parseInt(rowKey)) {
                return cashClosing;
            }
        }

        return null;
    }
    
    
    
    
}
