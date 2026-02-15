/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.LabelItemPrintBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Item;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.extended.ItemExt;
import com.mycompany.interfaces.CatalogInterface;
import com.mycompany.lazy.ItemLazyDataModel;
import com.mycompany.lazy.ItemLazySearch;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.util.Date;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author yo
 */
public class LabelItemPrintController implements Serializable, CatalogInterface {
    static final Long serialVersionUID = 1L;
    private LabelItemPrintBean viewBean;
    
    public LabelItemPrintController(LabelItemPrintBean viewBean){
        this.viewBean = viewBean;
    }
    
    @Override
    public void runMerge() {
      }

    @Override
    public void runDelete() {
    }

    @Override
    public void translations() {
         if(viewBean.getSearchItem().getDescItem() == null){
            viewBean.getSearchItem().setDescItem("nulll");
        }
        if(viewBean.getSearchItem().getStandardCodeItem() == null){
            viewBean.getSearchItem().setStandardCodeItem("0");
        }
        if(viewBean.getSearchItem().getItemLocation() == null){
            viewBean.getSearchItem().setItemLocation("nulll");
        }
        if(viewBean.getSearchItem().getIdDepartment().getIdDepartment() == null){
            viewBean.getSearchItem().getIdDepartment().setIdDepartment(0);
        }
        
        if(viewBean.getSearchItem().getIdCurrency().getIdCurrency() == null){
            viewBean.getSearchItem().getIdCurrency().setIdCurrency(0);
        }
         if(viewBean.getSearchItem().getIdProvider().getIdProvider()== null){
            viewBean.getSearchItem().getIdProvider().setIdProvider(0);
        }
         
          if(viewBean.getSearchItem().getIdBrand().getIdBrand() == null){
            viewBean.getSearchItem().getIdBrand().setIdBrand(0);
        }
          
          if(viewBean.getSearchItem().getIdLocation().getIdLocation()== null){
             viewBean.getSearchItem().getIdLocation().setIdLocation(0);
        }  
        
         if ( viewBean.getFilterValue().equals(0)){
             viewBean.setInitCode(0L);
             viewBean.setEndCode(999999999999999L);

        }else{
            viewBean.setInitCode(0L);
            viewBean.setEndCode(0L);
        }
    }

    @Override
    public void businessRules() {
    }

    @Override
    public void mergeRules() {
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
    
    public LazyDataModel<Item> callLazyList(){
        
          return new ItemLazyDataModel("Item.findItem",
                                    viewBean.getSearchItem().getDescItem(),
                                    viewBean.getSearchItem().getStandardCodeItem(),
                                    viewBean.getSearchItem().getIdDepartment().getIdDepartment(),
                                    viewBean.getSearchItem().getIdCurrency().getIdCurrency(),
                                    viewBean.getSearchItem().getIdLocation().getIdLocation(),
                                    viewBean.getInitCode(),
                                    viewBean.getEndCode(),
                                    viewBean.getSearchItem().getIdProvider().getIdProvider(),
                                    viewBean.getSearchItem().getIdBrand().getIdBrand()
         );
    }
    
     public Item searchItem(){
      
        
        return (Item) new BasicDAO().readSPReturnObject("Item.finditemByCode"
                                              
                                                 ,viewBean.getAppConfig().getIdDivition()
                                                 ,viewBean.getAppConfig().getIdSubdivition()
                                                 ,viewBean.getSearchStdCode());
        

       
    }
     
     public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.LABEL_ITEM_PRINT_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
       
    
     }
    
}
