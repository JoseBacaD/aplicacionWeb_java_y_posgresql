/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.CatLocationBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Location;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.interfaces.CatalogInterface;
import com.mycompany.lazy.LocationLazyDataModel;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.util.Date;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author yo
 */
public class CatLocationController implements Serializable, CatalogInterface {
    static final long serialVersionUID = 1L;
    private CatLocationBean viewBean;
    
    public CatLocationController() {
    }

    public CatLocationController(CatLocationBean viewBean) {
        this.viewBean = viewBean;
    }
    
    public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_BRAND_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
     }
    
     public LazyDataModel<Location> callLazyList(){
        
          return new LocationLazyDataModel("location.findLocation",
                                         viewBean.getSearchLocation().getNameLocation(),
                                         viewBean.getInitId(),
                                         viewBean.getEndId()
         );
    }    
     
        private boolean mergePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_BRAND_OPTION.getCodeMenuOption())){
                return permission.getCanEditData();
            }
            
        }
        return false;
        
    }
        
        

    @Override
    public void runMerge() {
        try{
           mergeRules();
            if(mergePermission()){
                BasicDAO.basicMerge(viewBean.getSelectedLocation());
                viewBean.setAnswerMessage("Registro creado | actualizado");
                
            }else{
            viewBean.setAnswerMessage("Su usuario no tiene permisos para modificar el registro");
        }
        }catch( Exception ex){
            
            ex.printStackTrace();
            
            
        }
    }

    @Override
    public void runDelete() {
    }

    @Override
    public void translations() {
        if (viewBean.getSearchLocation().getNameLocation()== null){
            viewBean.getSearchLocation().setNameLocation("nulll");
        }
        
         if ( viewBean.getFilterValue().equals(0)){
             viewBean.setInitId(0);
             viewBean.setEndId(99999999);

        }else{
            viewBean.setInitId(0);
            viewBean.setEndId(0);
        }
    }

    @Override
    public void businessRules() {
    }

    @Override
    public void mergeRules() {
          viewBean.getSelectedLocation().setModifiedBy(viewBean.getUserApp().getUserAlias()); 
         viewBean.getSelectedLocation().setLastModDate(Utility.getDate());
        
    }

    @Override
    public void deleteRules() {
    }

    @Override
    public Date getToday() {
        return null;
    }

    @Override
    public LazyDataModel<Object> callReadLazyList(EntityExt entity) {
        return null;
    }
    
    
}
