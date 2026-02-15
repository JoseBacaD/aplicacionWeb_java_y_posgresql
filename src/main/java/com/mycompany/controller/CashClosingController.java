/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.CashClosingBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.CashClosing;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.lazy.CashClosingLazyModel;
import com.mycompany.lazy.ItemLazySearch;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author yo
 */
public class CashClosingController implements Serializable {
   private static final long serialVersionUID = 1L;
   private CashClosingBean viewBean;
   Calendar today;
   private DateFormat creationDateFormat;
    public CashClosingController() {
    }

    public CashClosingController(CashClosingBean viewBean) {
        today  = Calendar.getInstance();   
        creationDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
        this.viewBean = viewBean;
    }
    
    public boolean accessPermission(){
         for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CASH_CLOSING_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
    }
    
    private boolean mergePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CASH_CLOSING_OPTION.getCodeMenuOption())){
                return permission.getCanEditData();
            }
            
        }
        return false;
        
    }
    
    public void mergeRules() {
             
    }
    
   
   public void runMerge(){
         try{
           mergeRules();
            if(mergePermission()){
                BasicDAO.basicMerge(viewBean.getSelectedCashClosing());
                viewBean.setAnswerMessage("Corte de caja guardado");
                
            }else{
            viewBean.setAnswerMessage("Su usuario no tiene permisos para modificar el registro");
        }
        }catch( Exception ex){
            
            ex.printStackTrace();
            
            
        }
   }
   
   public void translations(){
       
       
      viewBean.getSelectedCashClosing().setModifiedBy(viewBean.getUserApp().getUserAlias()); 
      viewBean.getSelectedCashClosing().setLastModDate(today.getTime());
      viewBean.getSelectedCashClosing().setCreationDate(today.getTime());
      
   
   }
   
   public boolean mainProcess(){
       if(searchTodaySaleTotal()){
             translations();
             calcCashClosing();
             convertToString();
             runMerge();
             
             return true;
       }else{
           viewBean.setAnswerMessage("No se  ha realizado ninguna venta hoy");
           return false;
       }
     
   }
   
   public void convertToString(){
       viewBean.setStrDifCash(viewBean.getSelectedCashClosing().getDifCash().toString());
       viewBean.setStrDifDollar(viewBean.getSelectedCashClosing().getDifDollar().toString());
       viewBean.setStrcountedCash(viewBean.getSelectedCashClosing().getCashCount().toString());
       viewBean.setStrcountedDollar(viewBean.getSelectedCashClosing().getDollarCount().toString());
       viewBean.setStrSaleTotCash(viewBean.getSelectedCashClosing().getCashTotal().toString());
       viewBean.setStrSaleTotCard(viewBean.getSelectedCashClosing().getCardTotal().toString());
       viewBean.setStrSaleTotOther(viewBean.getSelectedCashClosing().getDollarTotal().toString());
       viewBean.setStrSaleGrandTotal(viewBean.getSelectedCashClosing().getGrandTotal().toString());
       viewBean.setStrCashwithdrawTotal(viewBean.getSelectedCashClosing().getWithdrawTotal().toString());
       viewBean.setStrTotRefund(viewBean.getSelectedCashClosing().getRefundTotal().toString());
       viewBean.setStrSaleTotDebit(viewBean.getSelectedCashClosing().getDebitTotal().toString());
   }
   
   public void calcCashClosing(){
       
       viewBean.getSelectedCashClosing().setDifCash(viewBean.getSelectedCashClosing().getCashTotal().subtract(viewBean.getSelectedCashClosing().getCashCount()));
       viewBean.getSelectedCashClosing().setDifDollar(viewBean.getSelectedCashClosing().getDollarTotal().subtract(viewBean.getSelectedCashClosing().getDollarCount()));
       
       
   }
   
   public boolean searchTodaySaleTotal(){
        Object[] objTotales;

        objTotales = (Object[]) new  BasicDAO().readSPReturnObject("SaleHeader.findTotalSale"
                                                 ,viewBean.getAppConfig().getIdDivition()
                                                 ,viewBean.getAppConfig().getIdSubdivition()
                                                 ,Utility.getDate()
                                                 ,Utility.getDate()
                                                    );
        try{
                   if (objTotales != null){
                       if(objTotales.length > 0 ){
                        if(objTotales[0] != null || objTotales[1] != null ||
                           objTotales[2] != null || objTotales[3] != null ||
                           objTotales[4] != null || objTotales[5] != null ||
                           objTotales[6] != null){
                     viewBean.getSelectedCashClosing().setCashTotal(((BigDecimal)objTotales[0]));
                     viewBean.getSelectedCashClosing().setCardTotal((BigDecimal)objTotales[1]);
                     viewBean.getSelectedCashClosing().setDollarTotal((BigDecimal)objTotales[2]);
                     viewBean.getSelectedCashClosing().setGrandTotal((BigDecimal)objTotales[3]);
                     viewBean.getSelectedCashClosing().setWithdrawTotal((BigDecimal)objTotales[4]);
                     viewBean.getSelectedCashClosing().setRefundTotal((BigDecimal)objTotales[5]);
                     viewBean.getSelectedCashClosing().setDebitTotal((BigDecimal)objTotales[6]);                return true;
                        }
                      }
                   }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
                   return false;
  }
   
     public LazyDataModel<CashClosing> callLazyList(){
        
          return new CashClosingLazyModel("CashClosing.findCashClosing",
                                         viewBean.getDateIniValue(),
                                         viewBean.getDateEndValue()
                                         
         );
    }    
}
