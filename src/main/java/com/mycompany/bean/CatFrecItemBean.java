/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.CatFrecItemController;
import com.mycompany.controller.CatItemController;
import com.mycompany.controller.ProcSaleController;
import com.mycompany.dropdown.CurrencyDropdown;
import com.mycompany.dropdown.DepartmentDropdown;
import com.mycompany.dropdown.UomDropdown;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.FrecuentSaleItem;
import com.mycompany.entity.Item;
import com.mycompany.entity.UserApp;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author jerry
 */
@ManagedBean
@SessionScoped
public class CatFrecItemBean extends AbstractCatalogBean implements Serializable {
    static final long serialVersionUID = 1L;
    
    String strDateToday;
    Item itemSearch;
    private LoginBean loggedUsr;
    private AppConfiguration appConfig;
    private UserApp userApp;
    private FrecuentSaleItem selected;
    
    
    @PostConstruct
    public void init(){
        itemSearch = new Item();
        if (selected == null){
            selected = new FrecuentSaleItem();
        }
       
           Date today = Calendar.getInstance().getTime();
        strDateToday = new SimpleDateFormat("yyyy-MM-dd").format(today);
        
         FacesContext fc = FacesContext.getCurrentInstance();
        loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
        try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    userApp = loggedUsr.getUsr();
                appConfig = loggedUsr.getAppConfig();
                if(!new CatFrecItemController(this).accessPermission()){
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
    public void populateTable() { DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
                               getViewRoot().
                               findComponent("tableForm:resultTable");
        dataTable.reset(); 
        
        objLazyList = new CatFrecItemController(this).callReadLazyList();
        itemSearch = new Item();
       }

    @Override
    public void callMerge() {
        new CatFrecItemController(this).runMerge();
       
        PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
        PrimeFaces.current().executeScript("$('#CRUDModal').modal('close');");
       
      }

    @Override
    public void callDelete() {
        answerMessage = "";
     new CatFrecItemController(this).runDelete();
    
     PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
        PrimeFaces.current().executeScript("$('#waringModal').modal('close');");
    }

    @Override
    public void addNew() {
      selected = new FrecuentSaleItem();
    }

    @Override
    public void resetVariables() {
      }

    public Item getItemSearch() {
        return itemSearch;
    }

    public void setItemSearch(Item itemSearch) {
        this.itemSearch = itemSearch;
    }

    public LoginBean getLoggedUsr() {
        return loggedUsr;
    }

    public void setLoggedUsr(LoginBean loggedUsr) {
        this.loggedUsr = loggedUsr;
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

    public LazyDataModel<Object> getObjLazyList() {
        return objLazyList;
    }

    public void setObjLazyList(LazyDataModel<Object> objLazyList) {
        this.objLazyList = objLazyList;
    }

    public FrecuentSaleItem getSelected() {
        return selected;
    }

    public void setSelected(FrecuentSaleItem selected) {
        this.selected = selected;
    }
    
    
}
