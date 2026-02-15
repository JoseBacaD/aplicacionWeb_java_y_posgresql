/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.CatFrecItemBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.FrecuentSaleItem;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.interfaces.CatalogInterface;
import com.mycompany.lazy.QuickItemLazySearch;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author jerry
 */
public class CatFrecItemController implements Serializable, CatalogInterface{
    static final long serialVersionUID = 1L;
    
    CatFrecItemBean viewBean;
    
    public CatFrecItemController(CatFrecItemBean viewBean){
        this.viewBean = viewBean;
    }
    
        public LazyDataModel<Object> callReadLazyList() {
           
        
         return new QuickItemLazySearch("Item.findItemByDesc",
                                     viewBean.getAppConfig().getIdDivition(),
                                     viewBean.getAppConfig().getIdSubdivition(),
                                     viewBean.getItemSearch().getDescItem()
         );
                 
                 
        }
        
         private Object callReadFrecItem(String standardCodeItem){
   
        return new BasicDAO().readSPReturnObject("FrecuentSaleItem.findFrecItemByCode", standardCodeItem );
        
   }
        

    @Override
    public void runMerge() {
        FrecuentSaleItem foundFrecItem;
     try{
         foundFrecItem = (FrecuentSaleItem) callReadFrecItem(viewBean.getSelected().getStandardCodeItem().getStandardCodeItem());
          if (foundFrecItem != null){
              foundFrecItem.setCreationDate(getToday());
              foundFrecItem.setIsFrecuentExtra(viewBean.getSelected().getIsFrecuentExtra());
              foundFrecItem.setIsFrecuentOther(viewBean.getSelected().getIsFrecuentOther());
              foundFrecItem.setIsFrecuentPromo(viewBean.getSelected().getIsFrecuentPromo());
              foundFrecItem.setStandardCodeItem(viewBean.getSelected().getStandardCodeItem());
          }else{
              viewBean.getSelected().setCreationDate(getToday());
              foundFrecItem = viewBean.getSelected();
          }
    
       
        if(mergePermission()){
       
             BasicDAO.basicMerge(foundFrecItem);
              viewBean.setAnswerMessage("Registro creado | actualizado");
        }else{
            viewBean.setAnswerMessage("Su usuario no tiene permisos para modificar el registro");
        }
        }catch( Exception ex){
            
            ex.printStackTrace();
            
            
        }
    }
    
    private boolean mergePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_FRECUENT.getCodeMenuOption())){
                return permission.getCanEditData();
            }
            
        }
        return false;
        
    }
    
    private boolean deletePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_FRECUENT.getCodeMenuOption())){
                return permission.getCanDeleteData();
            }
            
        }
        return false;
        
    }
    
     public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_FRECUENT.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
    
     }
    

    @Override
    public void runDelete() {
        if (deletePermission()){
       FrecuentSaleItem foundFrecItem;
     try{
         foundFrecItem = (FrecuentSaleItem) callReadFrecItem(viewBean.getSelected().getStandardCodeItem().getStandardCodeItem());
          if (foundFrecItem != null){
              BasicDAO.basicDelete(foundFrecItem);
              viewBean.setAnswerMessage("Registro eliminiado");
             }else{
              viewBean.setAnswerMessage("El artículo no esta en el menú de ventas");
          }
     
           
          
              
        }catch( Exception ex){
            
            ex.printStackTrace();
            
            
        }
        }else{
           viewBean.setAnswerMessage("Su usuario no tiene permisos para eliminar el registro");
        
        }
    }

    @Override
    public void translations() {
  
    }

    @Override
    public void businessRules() {

    }

    @Override
    public void mergeRules() {
    viewBean.getSelected().setCreationDate(getToday());
    }

    @Override
    public void deleteRules() {
      
    }

    @Override
    public Date getToday() {
     return Utility.getDate();
    }

    @Override
    public LazyDataModel<Object> callReadLazyList(EntityExt entity) {
        return null;
    }
    
}
