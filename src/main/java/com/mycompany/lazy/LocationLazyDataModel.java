/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lazy;

import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Location;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author yo
 */
public class LocationLazyDataModel extends LazyDataModel<Location>{
    private final Object [] params;
    private List<Object> liObject;
    private final String nameSP;
    
    public LocationLazyDataModel(String nameSP, Object... params) {
     this.params = params;
     this.nameSP = nameSP;
    
    }
    
    @Override
    public List<Location> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
         BasicDAO basicDao = new BasicDAO();
        liObject = basicDao.readSPLazyList(nameSP, first, pageSize, params);
        
       if (getRowCount() <= 0){
            if(liObject.size() > 0){
                Location location = (Location)liObject.get(0); 
                setRowCount(location.getCountRows().intValue());
        }
        }
        
        setPageSize(pageSize);
        return (List<Location>)(Object)liObject;
    }

    @Override
    public String getRowKey(Location brand) {
        return String.valueOf(brand.getIdLocation());
    }
    
     @Override
    public Location getRowData(String rowKey) {
        for (Location location : (List<Location>)(Object)liObject ) {
            if (location.getIdLocation()== Integer.parseInt(rowKey)) {
                return location;
            }
        }

        return null;
    }

}
