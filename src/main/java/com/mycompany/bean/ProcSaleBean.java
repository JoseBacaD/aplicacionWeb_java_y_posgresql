/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.CatItemController;
import com.mycompany.controller.ProcSaleController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.FrecuentSaleItem;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import com.mycompany.entity.Item;
import com.mycompany.entity.SaleDetail;
import com.mycompany.entity.UserApp;
import com.mycompany.extended.SaleHeaderExt;
import com.mycompany.pojo.RecentItem;
import com.mycompany.pojo.RecentSale;
import com.mycompany.wrapper.ProcSaleWrapper;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;


/**
 *
 * @author bacajos
 */
@ManagedBean
@SessionScoped
public class ProcSaleBean extends AbstractProcessBean implements Serializable{
    static final long serialVersionUID = 1L;
    ProcSaleWrapper wrapper;
    BigDecimal newSubtotal;
    BigDecimal newTax;
    BigDecimal newTotal;
    BigDecimal payableAmount;
    BigDecimal detailPrice;
    BigDecimal detailTax;
    BigDecimal totalPayment;
    Long saleNumber;
    SaleDetail saleDetail;
    DateFormat dateFormat;
    Date today;
    DecimalFormat df = new DecimalFormat("#,###.##");
    String strSubtotal;
    String strTotal = "";
    String strTax;
    String strPayment;
    String strChange;
    String strCashPayment;
    String strCardPayment;
    String strDebitPayment = "";
    String strOtherPayment;
    String strAnsMessIcon;
    String strIconColor;
    String strSaleNumber;
    String strSaleNumberBarCode;
    String strSaleTotCash;
    String strSaleTotCard;
    String strSaleTotOther;
    String strSaleGrandTotal;
    String strCashwithdrawTotal;
    String strTotRefund;
    String strPayableAmount;
    String strCommentInTicket;
    private List <FrecuentSaleItem> liFrecItem;
    private List <FrecuentSaleItem> liPromoItem;
    private List <FrecuentSaleItem> liExtraItem;
    private List <FrecuentSaleItem> liOtherItem;
    private List <RecentSale> liOrders;
     LoginBean loggedUsr;
     UserApp user;
     AppConfiguration appConfig;
     String strDateToday;
     private List<SaleDetail> ticketDetail;
     private String ticketTotal;
     private String ticketCashPayment;
     private String ticketChange;
     private String ticketCardPayment;
     private String ticketDebiPayment;
     private String ticketOtherPayment;
     private Item quickItemSearch;
     private Item quickItemSelected;
     
     
     private LazyDataModel<Object> objLazyList;
    @PostConstruct
    public void init(){
        //LOS SUBOBJETOS SE INICIALIZAN EN EL CONTRUCTOR
        // PERO CON CADA INICIALIZACIÓN HAY QUE INICIALIAR SUBOJETOS
        quickItemSearch = new Item();
        wrapper = new ProcSaleWrapper();
        dateFormat = new SimpleDateFormat("yy/MM/dd");
        today  = Calendar.getInstance().getTime();
        strDateToday = new SimpleDateFormat("dd-MMMMM-yyyy").format(today);
        FacesContext fc = FacesContext.getCurrentInstance();
        loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
        liOrders = new ArrayList();
        
        try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    user = loggedUsr.getUsr();
                appConfig = loggedUsr.getAppConfig();
                if(!new ProcSaleController(this).accessPermission()){
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
     
        if (strTotal.equals("")){
            strTotal = "0.00";
        }
        
        try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    user = loggedUsr.getUsr();
                    appConfig = loggedUsr.getAppConfig();
                    ProcSaleController controller =  new ProcSaleController(this);
                    controller.prepFrecItemList();
                    liFrecItem = controller.getLiFrecItem();
                    liExtraItem = controller.getLiExtraItem();
                    liPromoItem = controller.getLiPromoItem();
                     if(!new ProcSaleController(this).accessPermission()){
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

      public void populateQuickSearchTable() {
         DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
                               getViewRoot().
                               findComponent("quickSearchForm:resultTable");
        dataTable.reset(); 
        

        objLazyList = new ProcSaleController(this).callReadLazyList();
        quickItemSearch = new Item();

    }

    @Override
    public void callMainProcess() {
        callCalcTotals();
        refreshTotals();
        int paymentIsBigger = -1;
        
        paymentIsBigger = wrapper.getTotPaymet().compareTo(newTotal);
        ProcSaleController ctrlr = new ProcSaleController(this);
       
//        paymentIsBigger = wrapper.getTotal().compareTo(wrapper.getTotPaymet());
        try{
            
            if (!wrapper.getSaleHeader().getSaleHeadEntity().getFolioCollection().isEmpty()){
                if(paymentIsBigger >= 0){
                    saleNumber = ctrlr.processSale(wrapper.getSaleHeader());
                    strAnsMessIcon = "icofont-check-circled";
                    answerMessage = "¡Venta procesada con éxito!";
                    strSaleNumber = "Venta No. " + saleNumber.toString();
                    strSaleNumberBarCode = saleNumber.toString();
                    strCommentInTicket = wrapper.getSaleHeader().getSaleHeadEntity().getComment();
                    strIconColor = "green-text";
                    addRecentOrders(wrapper.getSaleHeader());
                    cleanRecentList();
                    if(appConfig.getPrintTicket()){
                        if (null != saleNumber){
                            genTicket();
                        }
                    }
                    wrapper = new ProcSaleWrapper();
                    strCardPayment = "";
                    strDebitPayment = "";
                    strCashPayment = "";
                    strOtherPayment = "";
                    strChange = "";
                    strPayment = "";
                    strTotal = "";
                    strSubtotal = "";
                    strTax = "";
//                    strSaleNumber = saleNumber.toString();
                }else{
                    strAnsMessIcon = "icofont-exclamation-circle";
                    strIconColor = "yellow-text";
                    answerMessage = "El monto a pagar es menor que el monto total";
                    strSaleNumber = "";
                }
            }else{
                strAnsMessIcon = "icofont-exclamation-circle";
                strIconColor = "yellow-text";
                answerMessage = "No hay articulos en la lista";
                strSaleNumber = "";
            }
            
            
        }catch(Exception ex){
            ex.printStackTrace();
            strAnsMessIcon = "icofont-close-circled";
            strIconColor = "red-text";
            answerMessage = "No se pudo concretar venta, favor de intertar más tarde";
        }
        
        PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
        PrimeFaces.current().executeScript("$('#CRUDModal').modal('close');");
       
    }
    
    
    public void callProcessWithdraw(){
        ProcSaleController ctrlr = new ProcSaleController(this);
        try{
            ctrlr.processWithdraw(wrapper.getSaleHeader());
            strAnsMessIcon = "icofont-check-circled";
                    answerMessage = "¡Retiro procesado con éxito!";
                    strSaleNumber = "";
                    strIconColor = "green-text";
        }catch(Exception ex){
            ex.printStackTrace();
            
            strAnsMessIcon = "icofont-close-circled";
            strIconColor = "red-text";
            answerMessage = "No se pudo procesar retiro, favor de intertar más tarde";
      
        }
          PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
          PrimeFaces.current().executeScript("$('#withdrawModal').modal('close');");
         
          wrapper = new ProcSaleWrapper();
    }
    private void callAddItem(){
                
      wrapper.getSaleHeader().
              getSaleHeadEntity().
              getFolioCollection().
              add(saleDetail);
      
     
        
    }
    public void addFrecuentItem(FrecuentSaleItem frecItem){
        Item detail = frecItem.getStandardCodeItem();
        
        wrapper.setQuantity(new BigDecimal("1"));
        detailPrice = callCalcDetail(detail);
        detailTax = callCalcItemTax(detailPrice);
        prepareADetail(detail);
            callAddItem();
            callCalcTotals();
            refreshTotals();
            wrapper.getItemSearch().getItemEntity().setStandardCodeItem(null);
        wrapper.setQuantity(new BigDecimal("1"));
    }
    public void callSearchItem(){
        
        ProcSaleController controller = new ProcSaleController(this);
        Item detail =  controller.searchItem(wrapper.getItemSearch());
        
        if (detail != null){
            answerMessage = "";
            wrapper.setQuantity(controller.calcQuantity(wrapper.getQuantity(), detail));
            detailPrice = callCalcDetail(detail);
            detailTax = callCalcItemTax(detailPrice);
            prepareADetail(detail);
            callAddItem();
            callCalcTotals();
            refreshTotals();
             
        }else{
            answerMessage = "Item not found";
        }
        wrapper.getItemSearch().getItemEntity().setStandardCodeItem(null);
        wrapper.setQuantity(new BigDecimal("1"));
        
    }

   public void callDeleteItem(SaleDetail itemDetail){
       if( wrapper.getSaleHeader().getSaleHeadEntity().getFolioCollection()!= null ) {
			wrapper.getSaleHeader().
                                getSaleHeadEntity().
                                getFolioCollection().
                                remove(itemDetail);
		}

        callCalcTotals();
        refreshTotals();
      
   }

   private BigDecimal callCalcDetail(Item itemFound){
       ProcSaleController controller = new ProcSaleController(this);  
       BigDecimal price = controller.calcDetail(wrapper.getQuantity(),
                                                itemFound.getPriceSale());
       
//       detailTax = controller.calcItemTax(price);
       return price;
   }
   
   private BigDecimal callCalcItemTax(BigDecimal price){
       ProcSaleController controller = new ProcSaleController(this);  
       
       
      return controller.calcItemTax(price);
       
   }
    
    private void refreshTotals(){
//        SE ACTUALIZAN VALORES EN PANTALLA
//        Y EN VARIABLES  DE SaleHeader
        strSubtotal = df.format(newSubtotal);
        strTax = df.format(newTax);
        strTotal = newTotal.toString();
        strPayableAmount = payableAmount.toString();
        wrapper.getSaleHeader().getSaleHeadEntity().setTaxTotal(newTax);
        wrapper.getSaleHeader().getSaleHeadEntity().setTotalSale(newTotal);
        strPayment = wrapper.getTotPaymet().toString();
        
        if (wrapper.getChange().intValue()>= 0){
        strChange = wrapper.getChange().toString();
        }else{
            strChange = "0.00";
        }
    }
    
    private void callCalcTotals(){
         int paymentIsBigger = -1;
        
         ProcSaleController controller = new ProcSaleController(this); 
         List<SaleDetail> itemList = wrapper.getSaleHeader().
                                getSaleHeadEntity().
                                getFolioCollection();
         
         newSubtotal = controller.calcSubTotal(itemList
                        );
             newTax = controller.calcTax(itemList);
             newTotal = controller.calcTotal(newSubtotal,
                                                    newTax);
             
             totalPayment = controller.calcPayment(wrapper.getCashPayment(),
                                                wrapper.getCardPayment(),
                                                wrapper.getOtherPayment(),
                                                wrapper.getDebitPayment()); 
          wrapper.setTotPaymet(totalPayment);    
          if(newTotal.compareTo(totalPayment)> 0){
          payableAmount = newTotal.subtract(totalPayment);
          }else{
              payableAmount = new BigDecimal("0.00");
          }
          paymentIsBigger = wrapper.getTotPaymet().compareTo(newTotal);
          if (!wrapper.getSaleHeader().getSaleHeadEntity().getFolioCollection().isEmpty() &&
                  paymentIsBigger >= 0){
                wrapper.setChange(controller.calcChange(wrapper.getTotPaymet(),
                                                              newTotal));
          }  else{
              wrapper.setChange(new BigDecimal("0.00"));
          }
          
 
            
    }
    
    private void prepareADetail(Item item){
        detailPrice = detailPrice.setScale(2,RoundingMode.HALF_UP);
        saleDetail = new SaleDetail();
        saleDetail.setFolio(wrapper.getSaleHeader().getSaleHeadEntity());
        saleDetail.setStandardCodeItem(item);
        saleDetail.setCreationDate(today);
        saleDetail.setSaleDate(today);
        saleDetail.setPriceDetail(detailPrice);
        saleDetail.setQuantity(wrapper.getQuantity());
        saleDetail.setItemTax(detailTax);
        
    }
    
    public void calcPayment(){
        
        try{
            wrapper.setCashPayment(new BigDecimal(strCashPayment));
            wrapper.getSaleHeader().getSaleHeadEntity().setCashPayment(new BigDecimal(strCashPayment));
        }catch(Exception ex){
            wrapper.setCashPayment(new BigDecimal("0"));
            wrapper.getSaleHeader().getSaleHeadEntity().setCashPayment(new BigDecimal("0"));
        }
        try{
            wrapper.setCardPayment(new BigDecimal(strCardPayment));
            wrapper.getSaleHeader().getSaleHeadEntity().setCardPayment(new BigDecimal(strCardPayment));
        }catch(Exception ex){
            wrapper.setCardPayment(new BigDecimal("0"));
            wrapper.getSaleHeader().getSaleHeadEntity().setCardPayment(new BigDecimal("0"));
        }
        try{
            wrapper.setDebitPayment(new BigDecimal(strDebitPayment));
            wrapper.getSaleHeader().getSaleHeadEntity().setDebitPayment(new BigDecimal(strDebitPayment));
        }catch(Exception ex){
            wrapper.setDebitPayment(new BigDecimal("0"));
            wrapper.getSaleHeader().getSaleHeadEntity().setDebitPayment(new BigDecimal("0"));
        }
        try{
            BigDecimal tempUSD = new BigDecimal(strOtherPayment);
            
            wrapper.setOtherPayment(tempUSD.multiply(appConfig.getRateSale()));
            
            wrapper.getSaleHeader().getSaleHeadEntity().setOtherPayment(tempUSD.multiply(appConfig.getRateSale()));
        }catch(Exception ex){
            wrapper.setOtherPayment(new BigDecimal("0"));
            wrapper.getSaleHeader().getSaleHeadEntity().setOtherPayment(new BigDecimal("0"));
        }
        
        callCalcTotals();
        refreshTotals();
        
//        wrapper.setTotPaymet(new BigDecimal(0.00));
//        wrapper.setChange(new BigDecimal(0.00));
//        wrapper.setCashPayment(new BigDecimal(0.00));
//        wrapper.setCardPayment(new BigDecimal(0.00));
//        wrapper.setOtherPayment(new BigDecimal(0.00));
    }
    
          public ProcSaleWrapper getWrapper() {
        return wrapper;
    }
          
   public void genTicket(){
      ticketDetail = new ArrayList<>(wrapper.getSaleHeader().getSaleHeadEntity().getFolioCollection());
     
      ticketChange = strChange;
      ticketTotal = strTotal;
      
      if(!strCashPayment.equals("")){
          if(new BigDecimal(strCashPayment).compareTo(BigDecimal.ZERO)> 0){
              ticketCashPayment = "Efectivo: $" + strCashPayment;
          }
      }else{
             ticketCashPayment = "Efectivo: $0.00";
          }
      
      if(!strCardPayment.equals("")){
          if(new BigDecimal(strCardPayment).compareTo(BigDecimal.ZERO)> 0){
              ticketCardPayment = "Crédito: $" + strCardPayment;
          }
      }else{
              ticketCardPayment = "Crédito: $0.00";
          }
      if(!strDebitPayment.equals("")){
          if(new BigDecimal(strDebitPayment).compareTo(BigDecimal.ZERO)> 0){
              ticketDebiPayment = "Débito: $" + strDebitPayment;
          }
      }else{
              ticketDebiPayment = " Débito: $0.00";
          }
      
      if(!strOtherPayment.equals("")){
          if(new BigDecimal(strOtherPayment).compareTo(BigDecimal.ZERO)> 0){
              ticketOtherPayment = "Dolares: $" + strOtherPayment;
          }
      }else{
              ticketOtherPayment = "Dolares: $0.00";
          }
      
      PrimeFaces.current().executeScript("popupWindow('print-ticket.xhtml','Impresión');");
      PrimeFaces.current().executeScript("downloadTicket('print-ticket.xhtml','"+strSaleNumber+".html');");
   }
    
   public void onRowSelect(SelectEvent<Item> event) {
           wrapper.getItemSearch().
                   getItemEntity().
                   setStandardCodeItem(event.getObject().
                   getStandardCodeItem());
           callSearchItem();
           DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
                               getViewRoot().
                               findComponent("quickSearchForm:resultTable");
        dataTable.reset();
          }

   
   private void addRecentOrders(SaleHeaderExt aSale){
       
       List<SaleDetail> liItem = aSale.getSaleHeadEntity().getFolioCollection();
       RecentSale recent = new RecentSale();
       recent.setItemRecentList(new ArrayList<>());
      
       recent.setFolio(aSale.getSaleHeadEntity().getFolio());
       recent.setRecentSaleComment(aSale.getSaleHeadEntity().getComment());
       
       for (SaleDetail saleDetail : liItem) {
        
           recent.getItemRecentList().add(new RecentItem(saleDetail.getQuantity(),
                   saleDetail.getStandardCodeItem().getDescItem()));
           
       }
       
        liOrders.add(recent);
      
   }
   
   public void removeRecentOrder(RecentSale order){
       liOrders.remove(order);
   }
   
   private void cleanRecentList(){
       int maxListCount = liOrders.size();
       if(maxListCount > 10){
          liOrders.remove(liOrders.get(0));
       }
   }
   
//   public void printCurrentSaleReport(){
//     BigDecimal[] obj =  new ProcSaleController(this).searchTodaySaleTotal();
//     if (obj[0] != null){
//        
//     strSaleTotCash = obj[0].toString();
//     strSaleTotCard = obj[1].toString();
//     strSaleTotOther = obj[2].toString();
//     strSaleGrandTotal = obj[3].toString();
//     strCashwithdrawTotal = obj[4].toString();
//     strTotRefund = obj[5].toString();
//     PrimeFaces.current().executeScript("popupWindow('print-sale-report.xhtml','Impresión');");
//     PrimeFaces.current().executeScript("downloadTicket('print-sale-report.xhtml','"+"reporte-ventas"+strDateToday+".html');");
//
//     }else{
//         answerMessage = "No se ha realizado ninguna venta el día de hoy";
//         PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
//     }
//     
//    }
   
    public void setWrapper(ProcSaleWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public String getStrSubtotal() {
        return strSubtotal;
    }

    public void setStrSubtotal(String strSubtotal) {
        this.strSubtotal = strSubtotal;
    }

    public String getStrTax() {
        return strTax;
    }

    public void setStrTax(String strTax) {
        this.strTax = strTax;
    }
    
    public String getStrTotal() {
        return strTotal;
    }

    public void setStrTotal(String strTotal) {
        this.strTotal = strTotal;
    }

    public String getStrPayment() {
        return strPayment;
    }

    public void setStrPayment(String strPayment) {
        this.strPayment = strPayment;
    }

    public String getStrChange() {
        return strChange;
    }

    public void setStrChange(String strChange) {
        this.strChange = strChange;
    }

    public List<FrecuentSaleItem> getLiFrecItem() {
        return liFrecItem;
    }

    public void setLiFrecItem(List<FrecuentSaleItem> liFrecItem) {
        this.liFrecItem = liFrecItem;
    }

    public List<FrecuentSaleItem> getLiPromoItem() {
        return liPromoItem;
    }

    public void setLiPromoItem(List<FrecuentSaleItem> liPromoItem) {
        this.liPromoItem = liPromoItem;
    }

    public List<FrecuentSaleItem> getLiExtraItem() {
        return liExtraItem;
    }

    public void setLiExtraItem(List<FrecuentSaleItem> liExtraItem) {
        this.liExtraItem = liExtraItem;
    }

    public List<FrecuentSaleItem> getLiOtherItem() {
        return liOtherItem;
    }

    public void setLiOtherItem(List<FrecuentSaleItem> liOtherItem) {
        this.liOtherItem = liOtherItem;
    }

    public String getStrCashPayment() {
        return strCashPayment;
    }

    public void setStrCashPayment(String strCashPayment) {
        this.strCashPayment = strCashPayment;
    }

    public String getStrCardPayment() {
        return strCardPayment;
    }

    public void setStrCardPayment(String strCardPayment) {
        this.strCardPayment = strCardPayment;
    }

    public String getStrOtherPayment() {
        return strOtherPayment;
    }

    public void setStrOtherPayment(String strOtherPayment) {
        this.strOtherPayment = strOtherPayment;
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

    public String getStrSaleNumber() {
        return strSaleNumber;
    }

    public void setStrSaleNumber(String strSaleNumber) {
        this.strSaleNumber = strSaleNumber;
    }

    public UserApp getUser() {
        return user;
    }

    public void setUser(UserApp user) {
        this.user = user;
    }

    public AppConfiguration getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public String getStrDateToday() {
        return strDateToday;
    }

    public void setStrDateToday(String strDateToday) {
        this.strDateToday = strDateToday;
    }

    public List<SaleDetail> getTicketDetail() {
        return ticketDetail;
    }

    public void setTicketDetail(List<SaleDetail> ticketDetail) {
        this.ticketDetail = ticketDetail;
    }

    public String getTicketTotal() {
        return ticketTotal;
    }

    public void setTicketTotal(String ticketTotal) {
        this.ticketTotal = ticketTotal;
    }

    public String getTicketCashPayment() {
        return ticketCashPayment;
    }

    public void setTicketCashPayment(String ticketCashPayment) {
        this.ticketCashPayment = ticketCashPayment;
    }

    public String getTicketChange() {
        return ticketChange;
    }

    public void setTicketChange(String ticketChange) {
        this.ticketChange = ticketChange;
    }

    public String getTicketCardPayment() {
        return ticketCardPayment;
    }

    public void setTicketCardPayment(String ticketCardPayment) {
        this.ticketCardPayment = ticketCardPayment;
    }

    public String getTicketOtherPayment() {
        return ticketOtherPayment;
    }

    public void setTicketOtherPayment(String ticketOtherPayment) {
        this.ticketOtherPayment = ticketOtherPayment;
    }

    public LoginBean getLoggedUsr() {
        return loggedUsr;
    }

    public Item getQuickItemSearch() {
        return quickItemSearch;
    }

    public void setQuickItemSearch(Item quickItemSearch) {
        this.quickItemSearch = quickItemSearch;
    }

    public LazyDataModel<Object> getObjLazyList() {
        return objLazyList;
    }

    public void setObjLazyList(LazyDataModel<Object> objLazyList) {
        this.objLazyList = objLazyList;
    }

    public Item getQuickItemSelected() {
        return quickItemSelected;
    }

    public void setQuickItemSelected(Item quickItemSelected) {
        this.quickItemSelected = quickItemSelected;
    }

    public List<RecentSale> getLiOrders() {
        return liOrders;
    }

    public void setLiOrders(List<RecentSale> liOrders) {
        this.liOrders = liOrders;
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

    public String getStrPayableAmount() {
        return strPayableAmount;
    }

    public void setStrPayableAmount(String strPayableAmount) {
        this.strPayableAmount = strPayableAmount;
    }

    public String getStrSaleNumberBarCode() {
        return strSaleNumberBarCode;
    }

    public void setStrSaleNumberBarCode(String strSaleNumberBarCode) {
        this.strSaleNumberBarCode = strSaleNumberBarCode;
    }

    public String getStrCommentInTicket() {
        return strCommentInTicket;
    }

    public void setStrCommentInTicket(String strCommentInTicket) {
        this.strCommentInTicket = strCommentInTicket;
    }

    public String getStrTotRefund() {
        return strTotRefund;
    }

    public void setStrTotRefund(String strTotRefund) {
        this.strTotRefund = strTotRefund;
    }

    public String getStrDebitPayment() {
        return strDebitPayment;
    }

    public void setStrDebitPayment(String strDebitPayment) {
        this.strDebitPayment = strDebitPayment;
    }

    public String getTicketDebiPayment() {
        return ticketDebiPayment;
    }

    public void setTicketDebiPayment(String ticketDebiPayment) {
        this.ticketDebiPayment = ticketDebiPayment;
    }

   
   
}
