/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.CashClosingController;
import com.mycompany.controller.CatBrandController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.CashClosing;
import com.mycompany.entity.SaleDetail;
import com.mycompany.entity.UserApp;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author yo
 */
@ManagedBean
@SessionScoped
public class CashClosingBean extends AbstractCatalogBean implements Serializable{
   private static final long serialVersionUID = 1L;
   
   private CashClosing selectedCashClosing;
   private String strDateToday;
   private AppConfiguration appConfig;
   private UserApp userApp;
   private LoginBean loggedUsr;
   private String strcountedCash;
   private String strcountedDollar;
   private String strSaleTotCash;
   private String strSaleTotCard;
   private String strSaleTotDebit;
   private String strSaleTotOther;
   private String strCashwithdrawTotal;
   private String strTotRefund;
   private String strSaleGrandTotal;
   private String strDifCash;
   private String strDifDollar;
   private BigDecimal difCash;
   private BigDecimal difDollar;
   private Date dateIniValue;
   private Date dateEndValue;
   protected LazyDataModel<CashClosing> cashClosingLazyList;
   
   @PostConstruct
   public void init(){
         strDateToday = new SimpleDateFormat("dd-MMMMM-yyyy").format(Utility.getDate());

       FacesContext fc = FacesContext.getCurrentInstance();
          loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
         try{
            if(loggedUsr != null){
                                    if(loggedUsr.getUsr().isIsActiveBit()){
                                     userApp = loggedUsr.getUsr();
                                         appConfig = loggedUsr.getAppConfig();
                                         
                                     if(selectedCashClosing == null){
                                      selectedCashClosing = new CashClosing();
                                         setDateIniValue(Utility.getDate());
                                         setDateEndValue(Utility.getDate());
                                      }

                                     if(!new CashClosingController(this).accessPermission()){
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
   
   public void onPageLoad(){
        init();
    }
   
    @Override
    public void populateTable() {
          DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
                               getViewRoot().
                               findComponent("tableForm:resultTable");
        dataTable.reset(); 
        
           cashClosingLazyList = new CashClosingController(this).callLazyList();

    }
   
   @Override
   public void callMerge(){
     
   }
   
   public void callMainProcess(){
       if(new CashClosingController(this).mainProcess()){ 
        PrimeFaces.current().executeScript("popupWindow('print-cash-closing.xhtml','Corte caja');");
        selectedCashClosing = new CashClosing();
       }
            PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
       
   }
   
   public void print(SelectEvent<CashClosing> event){
       selectedCashClosing = event.getObject();
       new CashClosingController(this).convertToString();
       PrimeFaces.current().executeScript("popupWindow('print-cash-closing.xhtml','Corte caja');");
       selectedCashClosing = new CashClosing();
   }

    @Override
    public void callDelete() {
   
    }

    @Override
    public void addNew() {
  
    }

    @Override
    public void resetVariables() {
  
    }

    public String getStrDateToday() {
        return strDateToday;
    }

    public void setStrDateToday(String strDateToday) {
        this.strDateToday = strDateToday;
    }

    public AppConfiguration getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public CashClosing getSelectedCashClosing() {
        return selectedCashClosing;
    }

    public void setSelectedCashClosing(CashClosing selectedCashClosing) {
        this.selectedCashClosing = selectedCashClosing;
    }

    public String getStrcountedCash() {
        return strcountedCash;
    }

    public void setStrcountedCash(String strcountedCash) {
        this.strcountedCash = strcountedCash;
    }

    public String getStrcountedDollar() {
        return strcountedDollar;
    }

    public void setStrcountedDollar(String strcountedDollar) {
        this.strcountedDollar = strcountedDollar;
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

    public String getStrSaleGrandTotal() {
        return strSaleGrandTotal;
    }

    public void setStrSaleGrandTotal(String strSaleGrandTotal) {
        this.strSaleGrandTotal = strSaleGrandTotal;
    }

    public String getStrSaleTotCash() {
        return strSaleTotCash;
    }

    public void setStrSaleTotCash(String strSaleTotCash) {
        this.strSaleTotCash = strSaleTotCash;
    }

    public String getStrDifCash() {
        return strDifCash;
    }

    public void setStrDifCash(String strDifCash) {
        this.strDifCash = strDifCash;
    }

    public String getStrDifDollar() {
        return strDifDollar;
    }

    public void setStrDifDollar(String strDifDollar) {
        this.strDifDollar = strDifDollar;
    }

    public BigDecimal getDifCash() {
        return difCash;
    }

    public void setDifCash(BigDecimal difCash) {
        this.difCash = difCash;
    }

    public BigDecimal getDifDollar() {
        return difDollar;
    }

    public void setDifDollar(BigDecimal difDollar) {
        this.difDollar = difDollar;
    }

    public Date getDateIniValue() {
        return dateIniValue;
    }

    public void setDateIniValue(Date dateIniValue) {
        this.dateIniValue = dateIniValue;
    }

    public Date getDateEndValue() {
        return dateEndValue;
    }

    public void setDateEndValue(Date dateEndValue) {
        this.dateEndValue = dateEndValue;
    }

    public LazyDataModel<CashClosing> getCashClosingLazyList() {
        return cashClosingLazyList;
    }

    public void setCashClosingLazyList(LazyDataModel<CashClosing> cashClosingLazyList) {
        this.cashClosingLazyList = cashClosingLazyList;
    }

    public String getStrSaleTotDebit() {
        return strSaleTotDebit;
    }

    public void setStrSaleTotDebit(String strSaleTotDebit) {
        this.strSaleTotDebit = strSaleTotDebit;
    }
   
}
