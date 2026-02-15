/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.CommonSaleReportController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.Item;
import com.mycompany.entity.SaleHeader;
import com.mycompany.entity.UserApp;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author yo
 */
@SessionScoped
@ManagedBean
public class CommonSaleReportBean extends AbstractProcessBean implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer idReport;
    private AppConfiguration appConfig;
    private String strSaleTotCash;
    private String strSaleTotCard;
    private String strSaleTotOther;
    private String strSaleGrandTotal;
    private String strCashwithdrawTotal;
    private String strTotRefund;
    private String strTotInvoiced;
    private String strTotNotInvoiced;
    private String strSaleTotDebit;
    private LoginBean loggedUsr;
    private UserApp userApp;
    private String strDateToday;
    private List<Item> liLowStock;
    private List<SaleHeader> liNonInvoicedHeader;
    private List<SaleHeader> liInvoicedHeader;
    
    @PostConstruct 
    public void init(){
        pageSetup();
    }
    
     public void onPageLoad(){
         pageSetup();
     }
    
    private void pageSetup(){
      
        strDateToday = new SimpleDateFormat("dd-MMMMM-yyyy").format(Utility.getDate());
        FacesContext fc = FacesContext.getCurrentInstance();
          loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
         try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    userApp = loggedUsr.getUsr();
                    appConfig = loggedUsr.getAppConfig();   
                    
                    if(liNonInvoicedHeader == null){
                        liNonInvoicedHeader = new ArrayList<>();
                    }
                    if(liInvoicedHeader == null){
                        liInvoicedHeader = new ArrayList<>();
                    }
                    
               if(!new CommonSaleReportController(this).accessPermission()){
                    fc.getExternalContext().redirect("errorPage.xhtml");
                }
                }else{
                    fc.getExternalContext().redirect("errorPage.xhtml");
                }
                
            }else{

                fc.getExternalContext().redirect("errorPage.xhtml");
            }
        }catch(Exception e){
            e.printStackTrace();
            
        }
   }

    

    @Override
    public void callMainProcess() {
       liNonInvoicedHeader = new ArrayList<>();
       liInvoicedHeader = new ArrayList<>();
       CommonSaleReportController controller =  new CommonSaleReportController(this);
        
        switch(idReport){
                case 1: controller.searchTodayTotals(); 
                        PrimeFaces.current().executeScript("popupWindow('print-sale-report.xhtml','Impresión');"); break;
                case 2: controller.searchLowStock();
                        PrimeFaces.current().executeScript("popupWindow('print-lowstock-report.xhtml','Impresión');"); break;
               
            }
       
        
    }
    
    public void updateReport(){
        
    }

    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public AppConfiguration getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public String getStrSaleTotCash() {
        return strSaleTotCash;
    }

    public void setStrSaleTotCash(String strSaleTotCash) {
        this.strSaleTotCash = strSaleTotCash;
    }

    public String getStrSaleTotCard() {
        return strSaleTotCard;
    }

    public void setStrSaleTotCard(String strSaleTotCard) {
        this.strSaleTotCard = strSaleTotCard;
    }

    public String getStrSaleTotOther() {
        return strSaleTotOther;
    }

    public void setStrSaleTotOther(String strSaleTotOther) {
        this.strSaleTotOther = strSaleTotOther;
    }

    public String getStrSaleGrandTotal() {
        return strSaleGrandTotal;
    }

    public void setStrSaleGrandTotal(String strSaleGrandTotal) {
        this.strSaleGrandTotal = strSaleGrandTotal;
    }

    public String getStrCashwithdrawTotal() {
        return strCashwithdrawTotal;
    }

    public void setStrCashwithdrawTotal(String strCashwithdrawTotal) {
        this.strCashwithdrawTotal = strCashwithdrawTotal;
    }

    public String getStrTotRefund() {
        return strTotRefund;
    }

    public void setStrTotRefund(String strTotRefund) {
        this.strTotRefund = strTotRefund;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public String getStrDateToday() {
        return strDateToday;
    }

    public void setStrDateToday(String strDateToday) {
        this.strDateToday = strDateToday;
    }

    public List<Item> getLiLowStock() {
        return liLowStock;
    }

    public void setLiLowStock(List<Item> liLowStock) {
        this.liLowStock = liLowStock;
    }

    public String getStrTotInvoiced() {
        return strTotInvoiced;
    }

    public void setStrTotInvoiced(String strTotInvoiced) {
        this.strTotInvoiced = strTotInvoiced;
    }

    public List<SaleHeader> getLiNonInvoicedHeader() {
        return liNonInvoicedHeader;
    }

    public void setLiNonInvoicedHeader(List<SaleHeader> liNonInvoicedHeader) {
        this.liNonInvoicedHeader = liNonInvoicedHeader;
    }

    public List<SaleHeader> getLiInvoicedHeader() {
        return liInvoicedHeader;
    }

    public void setLiInvoicedHeader(List<SaleHeader> liInvoicedHeader) {
        this.liInvoicedHeader = liInvoicedHeader;
    }

    public String getStrTotNotInvoiced() {
        return strTotNotInvoiced;
    }

    public void setStrTotNotInvoiced(String strTotNotInvoiced) {
        this.strTotNotInvoiced = strTotNotInvoiced;
    }

    public String getStrSaleTotDebit() {
        return strSaleTotDebit;
    }

    public void setStrSaleTotDebit(String strSaleTotDebit) {
        this.strSaleTotDebit = strSaleTotDebit;
    }
    
    
        
}

   
