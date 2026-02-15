/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.CatSaleDetailBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.entity.SaleDetail;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.extended.SaleDetailExt;
import com.mycompany.interfaces.CatalogInterface;
import com.mycompany.lazy.BasicLazySearch;
import com.mycompany.lazy.SaleDetailLazyList;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author bacajos
 */ 
public class CatSaleDetailController implements Serializable, CatalogInterface {
    static final long serialVersionUID = 1L;
    private CatSaleDetailBean viewBean;

    public CatSaleDetailController() {
    }

    
    
    public CatSaleDetailController(CatSaleDetailBean viewBean) {
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
    
    @Override
    public LazyDataModel<Object> callReadLazyList(EntityExt saleDetExt){
        
       return new SaleDetailLazyList(saleDetExt,
                                     "SaleDetail.findSaleDetail",
                                     viewBean.getAppConfig().getIdDivition(),
                                     viewBean.getAppConfig().getIdSubdivition(),
                                     saleDetExt.getAbstractField1().getDateIniValue(),
                                     saleDetExt.getAbstractField1().getDateEndValue());
    }
    
     public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_SALE_DETAIL_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
       
    
     }
    
      public List<SaleDetail> readPrintList(EntityExt saleDetExt){
        List<SaleDetail> liSaleDetail = new ArrayList<>();
       
       liSaleDetail = (List<SaleDetail>)(Object)BasicDAO.readSPReturnList("SaleDetail.findSaleDetail",
                                     viewBean.getAppConfig().getIdDivition(),
                                     viewBean.getAppConfig().getIdSubdivition(),
                                     saleDetExt.getAbstractField1().getDateIniValue(),
                                     saleDetExt.getAbstractField1().getDateEndValue());
       
       return liSaleDetail;
       
    }

      public void calcTotals(){
         BigDecimal tempTotTax = new BigDecimal("0.00");
         BigDecimal tempTotAmount = new BigDecimal("0.00");
         viewBean.setTotTax(new BigDecimal("0.00"));
         viewBean.setTotAmount(new BigDecimal("0.00"));
         viewBean.setSubTotal(new BigDecimal("0.00"));
         
          if (viewBean.getPrintableList() != null){
              for (SaleDetail saleDetail : viewBean.getPrintableList()) {
                 tempTotTax = tempTotTax.add(saleDetail.getItemTax());
                  tempTotAmount = tempTotAmount.add(saleDetail.getPriceDetail());
              }
              viewBean.setTotTax(tempTotTax);
              viewBean.setTotAmount(tempTotAmount);
              viewBean.setSubTotal(tempTotAmount.subtract(tempTotTax));
          }
      }
      
   
     
}