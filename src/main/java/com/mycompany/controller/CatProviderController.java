/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.CatProviderBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.entity.Provider;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.interfaces.CatalogInterface;
import com.mycompany.lazy.ProviderLazyDataModel;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.util.Date;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author yo
 */
public class CatProviderController implements Serializable, CatalogInterface {
    static final long serialVersionUID = 1L;
    
    private CatProviderBean viewBean;

    public CatProviderController() {
    }
    
    public CatProviderController(CatProviderBean viewBean) {
        this.viewBean = viewBean;
    }
    
     
    @Override
    public void runMerge() {
        
        try{
           mergeRules();
            if(mergePermission()){
                BasicDAO.basicMerge(viewBean.getSelectedProvider());
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
        
         if (viewBean.getSearchProvider().getNameProvider() == null){
            viewBean.getSearchProvider().setNameProvider("nulll");
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
         viewBean.getSelectedProvider().setModifiedBy(viewBean.getUserApp().getUserAlias()); 
         viewBean.getSelectedProvider().setLastModDate(Utility.getDate());
            
        
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
    
    public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_PROVIDER_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
     }
    
     public LazyDataModel<Provider> callLazyList(){
        
          return new ProviderLazyDataModel("Provider.findProvider",
                                         viewBean.getSearchProvider().getNameProvider(),
                                         viewBean.getInitId(),
                                         viewBean.getEndId()
         );
    }    
     
     private boolean mergePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_PROVIDER_OPTION.getCodeMenuOption())){
                return permission.getCanEditData();
            }
            
        }
        return false;
        
    }
}
