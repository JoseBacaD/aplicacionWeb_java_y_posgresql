/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;


import com.mycompany.bean.CatItemBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.extended.ItemExt;
import com.mycompany.interfaces.CatalogInterface;
import com.mycompany.lazy.ItemLazySearch;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author bacajos
 */
public class CatItemController implements Serializable, CatalogInterface {
    static final long serialVersionUID = 1L;
    private CatItemBean viewBean;
    private Date today;
    
    public CatItemController(){
        
    }

    public CatItemController(CatItemBean viewBean) {
        this.viewBean = viewBean;
        today = Calendar.getInstance().getTime();
        
    }
    
    @Override
    public Date getToday(){
       return Utility.getDate();
    }
    
    @Override
    public void runMerge() {
        try{
           mergeRules();
        // sí se va a utilizar el basicController hay que asignar
      // la entidad extendida especifica a  la entidad ext. genérica.
      
        if(mergePermission()){
            BasicDAO.basicMerge(viewBean.getSelectedItem());
             viewBean.setAnswerMessage("Registro creado | actualizado");
        }else{
            viewBean.setAnswerMessage("Su usuario no tiene permisos para modificar el registro");
        }
        }catch( Exception ex){
            viewBean.setAnswerMessage("Hubo un problema en el proceso, favor de validar la información :(.");
            
            ex.printStackTrace();
            
            
        }
    }

    @Override
    public void runDelete() {
        try{
            if(deletePermission()){
            BasicController.preSetDelete(viewBean);
            }else{
                viewBean.setAnswerMessage("Su usuario no tiene permisos para eliminar el registro");
            }
        }catch(Exception ex){
               
                ex.printStackTrace();
                }
    }

    @Override
    public void translations() {
        if(viewBean.getItemExt().getItemEntity().getDescItem() == null){
            viewBean.getItemExt().getItemEntity().setDescItem("nulll");
        }
        if(viewBean.getItemExt().getItemEntity().getStandardCodeItem() == null){
            viewBean.getItemExt().getItemEntity().setStandardCodeItem("0");
        }
        if(viewBean.getItemExt().getItemEntity().getItemLocation() == null){
            viewBean.getItemExt().getItemEntity().setItemLocation("nulll");
        }
        if(viewBean.getItemExt().getItemEntity().getIdDepartment().getIdDepartment() == null){
            viewBean.getItemExt().getItemEntity().getIdDepartment().setIdDepartment(0);
        }
        
        if(viewBean.getItemExt().getItemEntity().getIdCurrency().getIdCurrency() == null){
            viewBean.getItemExt().getItemEntity().getIdCurrency().setIdCurrency(0);
        }
        
         if(viewBean.getItemExt().getItemEntity().getIdProvider().getIdProvider()== null){
            viewBean.getItemExt().getItemEntity().getIdProvider().setIdProvider(0);
        }
         
          if(viewBean.getItemExt().getItemEntity().getIdBrand().getIdBrand() == null){
            viewBean.getItemExt().getItemEntity().getIdBrand().setIdBrand(0);
        }
          
           if(viewBean.getItemExt().getItemEntity().getIdLocation().getIdLocation()== null){
            viewBean.getItemExt().getItemEntity().getIdLocation().setIdLocation(0);
        }
        
         if ( viewBean.getFilterValue().equals(0)){
             viewBean.getItemExt().getAbstractField1().setLongIniValue(0L);
             viewBean.getItemExt().getAbstractField1().setLongEndValue(999999999999999L);

        }else{
            viewBean.getItemExt().getAbstractField1().setLongIniValue(0L);
            viewBean.getItemExt().getAbstractField1().setLongEndValue(0L);
        }
    }

    @Override
    public void businessRules() {

    }

    @Override
    public void mergeRules() {
      
        
           if( viewBean.getSelectedItem().getIdProvider().getIdProvider() == null){
               viewBean.getSelectedItem().setIdProvider(null);
           }
             if( viewBean.getSelectedItem().getIdProvider2().getIdProvider() == null){
               viewBean.getSelectedItem().setIdProvider2(null);
           }
               if( viewBean.getSelectedItem().getIdProvider3().getIdProvider() == null){
               viewBean.getSelectedItem().setIdProvider3(null);
           }
        if(viewBean.getSelectedItem().getIdBrand().getIdBrand() == null){
            viewBean.getSelectedItem().setIdBrand(null);
        }
       
        viewBean.getSelectedItem().setLastModDate(today);
        if (viewBean.getSelectedItem().getCreationDate() == null){
            viewBean.getSelectedItem().setCreationDate(today);
        }
     
        viewBean.getSelectedItem().setModifiedBy(viewBean.getUserApp().getUserAlias());
        viewBean.getSelectedItem().setIdDivition(viewBean.getAppConfig().getIdDivition());
        viewBean.getSelectedItem().setIdSubdivition(viewBean.getAppConfig().getIdSubdivition());
    }

    @Override
    public void deleteRules() {
 
    }

    @Override
    public LazyDataModel<Object> callReadLazyList(EntityExt entity) {
         return new ItemLazySearch(entity,
                                     "Item.findItem",
                                     ((ItemExt)entity).getItemEntity().getDescItem(),
                                     ((ItemExt)entity).getItemEntity().getStandardCodeItem(),
                                     ((ItemExt)entity).getItemEntity().getIdDepartment().getIdDepartment(),
                                     ((ItemExt)entity).getItemEntity().getIdCurrency().getIdCurrency(),
                                     ((ItemExt)entity).getItemEntity().getIdLocation().getIdLocation(),
                                      entity.getAbstractField1().getLongIniValue(),
                                      entity.getAbstractField1().getLongEndValue(),
                                      ((ItemExt)entity).getItemEntity().getIdProvider().getIdProvider(),
                                      ((ItemExt)entity).getItemEntity().getIdBrand().getIdBrand()
         );
                 
                 
        }
    
    private boolean mergePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_ITEM_OPTION.getCodeMenuOption())){
                return permission.getCanEditData();
            }
            
        }
        return false;
        
    }
    
    
    private boolean deletePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_ITEM_OPTION.getCodeMenuOption())){
                return permission.getCanDeleteData();
            }
            
        }
        return false;
        
    }
    
     public boolean accessPermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_ITEM_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
    }
}
