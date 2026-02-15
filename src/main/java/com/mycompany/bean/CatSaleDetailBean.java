/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.AppConfigController;
import com.mycompany.controller.CatSaleDetailController;
import com.mycompany.controller.ProcSaleController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.SaleDetail;
import com.mycompany.entity.UserApp;
import com.mycompany.extended.SaleDetailExt;
import com.mycompany.lazy.BasicLazySearch;
import com.mycompany.pojo.AbstractField;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author bacajos
 */
@ManagedBean
@SessionScoped
public class CatSaleDetailBean extends AbstractCatalogBean implements Serializable {
    static final long serialVersioUID = 1L;
    private List<SaleDetail> liSaleDetail;
    private SaleDetailExt saleDetExt;
    private String strDateToday;
    private LoginBean loggedUsr;
    private AppConfiguration appConfig;
    private UserApp userApp;
    private List<SaleDetail> printableList;
    private BigDecimal totTax;
    private BigDecimal totAmount;
    private BigDecimal subTotal;
    
    @PostConstruct
    public void init(){
        Date today = Calendar.getInstance().getTime();
        strDateToday = new SimpleDateFormat("yyyy-MM-dd").format(today);
       
        if(saleDetExt == null){
            saleDetExt = new SaleDetailExt();
            entityExt = saleDetExt;
            saleDetExt.setAbstractField1(new AbstractField());
            saleDetExt.getAbstractField1().setDateIniValue(Utility.getDate());
            saleDetExt.getAbstractField1().setDateEndValue(Utility.getDate());
        }
         FacesContext fc = FacesContext.getCurrentInstance();
        loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
         try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    userApp = loggedUsr.getUsr();
                appConfig = loggedUsr.getAppConfig();
                
               if(!new CatSaleDetailController(this).accessPermission()){
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
        
        if(saleDetExt == null){
            saleDetExt = new SaleDetailExt();
            entityExt = saleDetExt;
            saleDetExt.setAbstractField1(new AbstractField());
            saleDetExt.getAbstractField1().setDateIniValue(Utility.getDate());
            saleDetExt.getAbstractField1().setDateEndValue(Utility.getDate());
        }
        try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    userApp = loggedUsr.getUsr();
                    appConfig = loggedUsr.getAppConfig();
                   
                  if(!new CatSaleDetailController(this).accessPermission()){
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
    public void populateTable() {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
                               getViewRoot().
                               findComponent("tableForm:resultTable");
        dataTable.reset(); 
        
//CREAR EXAMPLE PARA LOS CAMPOS DE BÚSQUEDA QUE SERÁ UTILIZADOS EN EL DAO.
        saleDetExt.entity2Example();  
//        carga lenta o por partes
          objLazyList = new CatSaleDetailController(this).callReadLazyList(saleDetExt);
//        carga completa
//        liSaleDetail = new CatSaleDetailController().callReadList(saleDetExt);
      }
    
    public void printSaleDetail(){
        CatSaleDetailController controller = new CatSaleDetailController(this);
        printableList = controller.readPrintList(saleDetExt);
        controller.calcTotals();
        PrimeFaces.current().executeScript("popupWindow('print-sale-detail.xhtml','Impresión');");
        PrimeFaces.current().executeScript("downloadTicket('print-sale-detail.xhtml','"+"reporte-ventas-detalle"+strDateToday+".html');");

    }

    @Override
    public void callMerge() {
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
    
    
        public SaleDetailExt getSaleDetExt() {
        return saleDetExt;
    }

    public void setSaleDetExt(SaleDetailExt saleDetExt) {
        this.saleDetExt = saleDetExt;
    }

    public List<SaleDetail> getLiSaleDetail() {
        return liSaleDetail;
    }

    public void setLiSaleDetail(List<SaleDetail> liSaleDetail) {
        this.liSaleDetail = liSaleDetail;
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

    public List<SaleDetail> getPrintableList() {
        return printableList;
    }

    public void setPrintableList(List<SaleDetail> printableList) {
        this.printableList = printableList;
    }

    
    public BigDecimal getTotTax() {
        return totTax;
    }

    public void setTotTax(BigDecimal totTax) {
        this.totTax = totTax;
    }

    public BigDecimal getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(BigDecimal totAmount) {
        this.totAmount = totAmount;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
    
    

}
