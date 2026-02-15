/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.CommonSaleReportBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Item;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.entity.SaleHeader;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.interfaces.ProcessInterface;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yo
 */
public class CommonSaleReportController implements Serializable, ProcessInterface {
    static final long serialVersionUID = 1L;
    
    private CommonSaleReportBean viewBean;

    public CommonSaleReportController(CommonSaleReportBean viewBean) {
        this.viewBean = viewBean;
    }
    
    

    @Override
    public void translation() {
        
    }

    @Override
    public boolean mainProcess() {
        switch(viewBean.getIdReport()){
            case 1: searchTodayTotals();
                    sortHeaders(); break;
            case 2: searchLowStock(); break;
            
            
        }
        return false;
        
     }
    
    public void sortHeaders(){
        List <SaleHeader> liAllHeaders;
        BigDecimal totNonInvoiced = new BigDecimal("0.00");
        liAllHeaders =(List<SaleHeader>)(Object)BasicDAO.readSPReturnList("SaleHeader.findSaleHeader"
                                                 ,viewBean.getAppConfig().getIdDivition()
                                                 ,viewBean.getAppConfig().getIdSubdivition()
                                                 ,Utility.getDate()
                                                 ,Utility.getDate()
                                                 ,false);
        
        for (SaleHeader header : liAllHeaders) {
            if(header.getHasISRInvoice()){
                viewBean.getLiInvoicedHeader().add(header);
                
            }else{
                viewBean.getLiNonInvoicedHeader().add(header);
                totNonInvoiced = totNonInvoiced.add(header.getTotalSale());
            }
        }
        
        viewBean.setStrTotNotInvoiced(totNonInvoiced.toString());
    
    }
    
    public boolean searchTodayTotals(){
        Object[] objTotales;
        BigDecimal totInvoiced;
        objTotales = (Object[]) new  BasicDAO().readSPReturnObject("SaleHeader.findTotalSale"
                                                 ,viewBean.getAppConfig().getIdDivition()
                                                 ,viewBean.getAppConfig().getIdSubdivition()
                                                 ,Utility.getDate()
                                                 ,Utility.getDate()
                                                    );
        
        totInvoiced = (BigDecimal)new  BasicDAO().readSPReturnObject("SaleHeader.findTotalInvoiced"
                                                 ,viewBean.getAppConfig().getIdDivition()
                                                 ,viewBean.getAppConfig().getIdSubdivition()
                                                 ,Utility.getDate()
                                                 ,Utility.getDate()
                                                    );
        
        
        
        try{
                   if (objTotales != null){
                       if(objTotales.length > 0 ){
                     viewBean.setStrSaleTotCash(((BigDecimal)objTotales[0]).toString());
                     viewBean.setStrSaleTotCard(((BigDecimal)objTotales[1]).toString());
                     viewBean.setStrSaleTotOther(((BigDecimal)objTotales[2]).toString());
                     viewBean.setStrSaleGrandTotal(((BigDecimal)objTotales[3]).toString());
                     viewBean.setStrCashwithdrawTotal(((BigDecimal)objTotales[4]).toString());
                     viewBean.setStrTotRefund(((BigDecimal)objTotales[5]).toString());
                     viewBean.setStrSaleTotDebit(((BigDecimal)objTotales[6]).toString());
                     if(totInvoiced != null){
                      viewBean.setStrTotInvoiced(totInvoiced.toString());
                     }else{
                      viewBean.setStrTotInvoiced("0");   
                     }
//                     en el metodo de abajo se setea lo no facturado
                     sortHeaders();
                     return true;
                       }
                   }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
                   return false;
  }
    public boolean searchLowStock() {
        List<Item> liLowStock = (List<Item>)(Object)BasicDAO.readSPReturnList("Item.findLowStock"
                                                 ,viewBean.getAppConfig().getIdDivition()
                                                 ,viewBean.getAppConfig().getIdSubdivition()
                                                 
                                                   );
                   if (liLowStock != null){
                       if(liLowStock.size() > 0 ){
                       viewBean.setLiLowStock(liLowStock);
                     return true;
                       }
                   }
                   return false;
    }

    

    @Override
    public boolean accessPermission() {
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.FRECUENT_REPORT_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
    }

    @Override
    public boolean editPermission() {
        return false;
    }
}
