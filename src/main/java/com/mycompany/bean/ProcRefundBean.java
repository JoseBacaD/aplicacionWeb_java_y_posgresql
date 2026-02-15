/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.CatSaleDetailController;
import com.mycompany.controller.ProcRefundController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.Item;
import com.mycompany.entity.SaleDetail;
import com.mycompany.entity.SaleHeader;
import com.mycompany.entity.UserApp;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author jerry
 */
@ManagedBean
@SessionScoped
public class ProcRefundBean implements Serializable {
    static final long serialVersionUID = 1L;
    private LoginBean loggedUsr;
    private AppConfiguration appConfig;
    private UserApp userApp;
    private List<SaleDetail> saleList;
    private Long searchFolio;
    private SaleDetail rowSelected;
    private String strFolio;
    private SaleHeader refundSaleHeader;
    private SaleDetail saleDetToSave;
    String strAnsMessIcon;
    String strIconColor;
    String answerMessage;
    BigDecimal maxQuantity;
    BigDecimal calculatedPriceDetail;
    BigDecimal calculatedTax;
    BigDecimal onScreenRefundPercentage;
    String strCalcPriceDetail;
    String strSaleNumber;
    String strSaleNumberBarCode;
    String strMercOrCash;
    String strDateToday;
    Long saleNumber;
    BigDecimal quantity;
    boolean isWithDiscountDisabled;
    boolean isMerchandiseChecked;
    boolean isWithDiscountChecked;
    @PostConstruct
    public void init(){
       
        FacesContext fc = FacesContext.getCurrentInstance();
        loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
        strDateToday = new SimpleDateFormat("dd-MMMMM-yyyy").format(Utility.getDate());
         isWithDiscountDisabled = true;
         isMerchandiseChecked = true;
         isWithDiscountChecked = false;
         onScreenRefundPercentage = new BigDecimal("0");
        ;
         try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    userApp = loggedUsr.getUsr();
                appConfig = loggedUsr.getAppConfig();
                onScreenRefundPercentage = appConfig.getRefundPercentage();
                onScreenRefundPercentage = onScreenRefundPercentage.multiply(new BigDecimal("100"));
                onScreenRefundPercentage = onScreenRefundPercentage.setScale(0, RoundingMode.HALF_UP);
               if(!new ProcRefundController(this).accessPermission()){
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
        FacesContext fc = FacesContext.getCurrentInstance();
        loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
        
         
         onScreenRefundPercentage = new BigDecimal("0");
        try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    userApp = loggedUsr.getUsr();
                    appConfig = loggedUsr.getAppConfig();
                    
                    onScreenRefundPercentage = appConfig.getRefundPercentage();
                    onScreenRefundPercentage = onScreenRefundPercentage.multiply(new BigDecimal("100"));
                    onScreenRefundPercentage = onScreenRefundPercentage.setScale(0, RoundingMode.HALF_UP);
                     if(!new ProcRefundController(this).accessPermission()){
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
     public void callCalcRefund(){
          ProcRefundController controller = new ProcRefundController(this);
          calculatedPriceDetail = controller.CalcRefund(quantity, rowSelected.getStandardCodeItem().getPriceSale());
          strCalcPriceDetail = calculatedPriceDetail.toString();
        
        calculatedTax = controller.calcItemTax(calculatedPriceDetail);
        
    }
    
     public void updateCheckBoxState(){
         if (isMerchandiseChecked){
             isWithDiscountDisabled = true;
             isWithDiscountChecked = false;
         }else{
         
         isWithDiscountDisabled = false;
         isWithDiscountChecked = true;
         }
         
         callCalcRefund();
         
     }
    
    public void callMainProcess(){
        refundSaleHeader = new SaleHeader();
        refundSaleHeader.setFolioCollection(new ArrayList<>());
        saleDetToSave = new SaleDetail();
        callCalcRefund();
        
        if(rowSelected.getStandardCodeItem().getSaleByFraction()){
                callProcessRefund();
               
            }else if(isInteger(quantity)){
                callProcessRefund();
        }else{
                answerMessage = "No se permiten fracciones para este artículo";
                strAnsMessIcon = "icofont-close-circled";
                strIconColor = "red-text";
                strSaleNumber = "";
            }
        
        
        PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
        PrimeFaces.current().executeScript("$('#refundModal').modal('close');");
    }
    
    public void onRowSelect(SelectEvent<SaleDetail> event) {
          rowSelected = event.getObject();   
          maxQuantity = rowSelected.getQuantity();
          quantity = rowSelected.getQuantity();
          isWithDiscountDisabled = true;
          isMerchandiseChecked = true;
          strCalcPriceDetail = rowSelected.getPriceDetail().toString();
           DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
                               getViewRoot().
                               findComponent("tableForm:resultTable");
        dataTable.reset();
          }
          
    public void callReadSaleList()
    {
        try{
        searchFolio = new Long(strFolio);
        saleList = new ProcRefundController(this).readSaleList();
        strFolio = "";
        }catch(Exception ex){
            saleList = new ArrayList<>();
                    
            ex.printStackTrace();
        }
    }
    
    private void callProcessRefund(){
         try{
              saleNumber = new ProcRefundController(this).processRefund();
                     answerMessage = "¡Devolución procesada con éxito!";
                     strAnsMessIcon = "icofont-check-circled";
                     strIconColor = "green-text";
                     strSaleNumber = "Devolución No. "+saleNumber.toString();
                     strSaleNumberBarCode = saleNumber.toString();
                     if(isMerchandiseChecked){
                         strMercOrCash = "Mercancía";
                     }else{
                         strMercOrCash = "Efectivo";
                     }
                     isWithDiscountDisabled = true;
                     isMerchandiseChecked = true;
                     isWithDiscountChecked = false;
                     saleList.clear();
                     if(appConfig.getPrintTicket()){
                        if (null != saleNumber){
                            genTicket();
                        }
                    }
                }catch(Exception ex){
                     ex.printStackTrace();
                     strAnsMessIcon = "icofont-close-circled";
                     strIconColor = "red-text";
                     answerMessage = "No se pudo procesar la devolución, favor de intertar más tarde";
                     strSaleNumber = "";
                 }
    }
    
    public void genTicket(){
     
      PrimeFaces.current().executeScript("popupWindow('print-refund-ticket.xhtml','Impresión');");
      PrimeFaces.current().executeScript("downloadTicket('print-refund-ticket.xhtml','"+strSaleNumber+".html');");
   }
    
    private boolean isInteger(BigDecimal number) {
        BigDecimal one = new BigDecimal("1");
        BigDecimal remainder = number.remainder(one);
        
        return BigDecimal.ZERO.compareTo(remainder) == 0;

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

    public List<SaleDetail> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<SaleDetail> saleList) {
        this.saleList = saleList;
    }

    public Long getSearchFolio() {
        return searchFolio;
    }

    public void setSearchFolio(Long searchFolio) {
        this.searchFolio = searchFolio;
    }

    public SaleDetail getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(SaleDetail rowSelected) {
        this.rowSelected = rowSelected;
    }

    public String getStrFolio() {
        return strFolio;
    }

    public void setStrFolio(String strFolio) {
        this.strFolio = strFolio;
    }

    public SaleHeader getRefundSaleHeader() {
        return refundSaleHeader;
    }

    public void setRefundSaleHeader(SaleHeader refundSaleHeader) {
        this.refundSaleHeader = refundSaleHeader;
    }

    public SaleDetail getSaleDetToSave() {
        return saleDetToSave;
    }

    public void setSaleDetToSave(SaleDetail saleDetToSave) {
        this.saleDetToSave = saleDetToSave;
    }

    public BigDecimal getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getStrAnsMessIcon() {
        return strAnsMessIcon;
    }

    public void setStrAnsMessIcon(String strAnsMessIcon) {
        this.strAnsMessIcon = strAnsMessIcon;
    }

    public String getStrIconColor() {
        return strIconColor;
    }

    public void setStrIconColor(String strIconColor) {
        this.strIconColor = strIconColor;
    }

    public String getAnswerMessage() {
        return answerMessage;
    }

    public void setAnswerMessage(String answerMessage) {
        this.answerMessage = answerMessage;
    }

    public Long getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Long saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getStrSaleNumber() {
        return strSaleNumber;
    }

    public void setStrSaleNumber(String strSaleNumber) {
        this.strSaleNumber = strSaleNumber;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getStrCalcPriceDetail() {
        return strCalcPriceDetail;
    }

    public void setStrCalcPriceDetail(String strCalcPriceDetail) {
        this.strCalcPriceDetail = strCalcPriceDetail;
    }

    public BigDecimal getCalculatedTax() {
        return calculatedTax;
    }

    public void setCalculatedTax(BigDecimal calculatedTax) {
        this.calculatedTax = calculatedTax;
    }

    public BigDecimal getCalculatedPriceDetail() {
        return calculatedPriceDetail;
    }

    public void setCalculatedPriceDetail(BigDecimal calculatedPriceDetail) {
        this.calculatedPriceDetail = calculatedPriceDetail;
    }

    public String getStrDateToday() {
        return strDateToday;
    }

    public void setStrDateToday(String strDateToday) {
        this.strDateToday = strDateToday;
    }

    public String getStrSaleNumberBarCode() {
        return strSaleNumberBarCode;
    }

    public void setStrSaleNumberBarCode(String strSaleNumberBarCode) {
        this.strSaleNumberBarCode = strSaleNumberBarCode;
    }

    public boolean isIsWithDiscountDisabled() {
        return isWithDiscountDisabled;
    }

    public void setIsWithDiscountDisabled(boolean isWithDiscountDisabled) {
        this.isWithDiscountDisabled = isWithDiscountDisabled;
    }

  

    public boolean isIsMerchandiseChecked() {
        return isMerchandiseChecked;
    }

    public void setIsMerchandiseChecked(boolean isMerchandiseChecked) {
        this.isMerchandiseChecked = isMerchandiseChecked;
    }

    public boolean isIsWithDiscountChecked() {
        return isWithDiscountChecked;
    }

    public void setIsWithDiscountChecked(boolean isWithDiscountChecked) {
        this.isWithDiscountChecked = isWithDiscountChecked;
    }

    public BigDecimal getOnScreenRefundPercentage() {
        return onScreenRefundPercentage;
    }

    public void setOnScreenRefundPercentage(BigDecimal onScreenRefundPercentage) {
        this.onScreenRefundPercentage = onScreenRefundPercentage;
    }

    public String getStrMercOrCash() {
        return strMercOrCash;
    }

    public void setStrMercOrCash(String strMercOrCash) {
        this.strMercOrCash = strMercOrCash;
    }

    
   
   
}
