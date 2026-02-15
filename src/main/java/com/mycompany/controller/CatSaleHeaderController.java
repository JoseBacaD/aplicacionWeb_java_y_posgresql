/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.CatSaleHeaderBean;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.extended.ItemExt;
import com.mycompany.extended.SaleHeaderExt;
import com.mycompany.interfaces.CatalogInterface;
import com.mycompany.lazy.ItemLazySearch;
import com.mycompany.lazy.SaleHeaderLazySearch;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.util.Date;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author jerry
 */
public class CatSaleHeaderController  implements Serializable, CatalogInterface{
    static final long serialVersionUID = 1L;
    private CatSaleHeaderBean viewBean;
    
    public CatSaleHeaderController(){
        
    }
    
    public CatSaleHeaderController(CatSaleHeaderBean viewBean){
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

   
    public LazyDataModel<Object> callReadLazyList(SaleHeaderExt entity) {
      return new SaleHeaderLazySearch(entity,
                                     "SaleHeader.findSaleHeader",
                                      viewBean.getAppConfig().getIdDivition(),
                                      viewBean.getAppConfig().getIdSubdivition(),
                                      entity.getAbstractField1().getDateIniValue(),
                                      entity.getAbstractField1().getDateEndValue(),
                                      entity.getSaleHeadEntity().getIsWithdraw()
         );
           
    }

    @Override
    public LazyDataModel<Object> callReadLazyList(EntityExt entity) {
     return null;
    }
    
     public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_SALE_HEADER_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
    
     }
    
    
}
