/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.CatProviderController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.Provider;
import com.mycompany.entity.UserApp;
import java.io.Serializable;
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
 * @author yo
 */
@ManagedBean
@SessionScoped
public class CatProviderBean extends AbstractCatalogBean implements Serializable{
     static final long serialVersionUID = 1L;
     private Provider searchProvider;
     private Provider selectedProvider;
     private Integer filterValue;
     private String hideDesc = "";
     private LoginBean loggedUsr;
     private AppConfiguration appConfig;
     private UserApp userApp;
     private int initId;
     private int endId;
     protected LazyDataModel<Provider> providerLazyList = null;
     
      @PostConstruct 
    public void init(){
       if (filterValue == null){
           filterValue = 1;
       }
    
          FacesContext fc = FacesContext.getCurrentInstance();
          loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
         try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    userApp = loggedUsr.getUsr();
                    appConfig = loggedUsr.getAppConfig();
                    
                    if(searchProvider == null){
                        searchProvider = new Provider();
                    }
                 
               if(!new CatProviderController(this).accessPermission()){
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
    public void populateTable(){
         DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
                               getViewRoot().
                               findComponent("tableForm:resultTable");
        dataTable.reset(); 
        
  
        new CatProviderController(this).translations();
        providerLazyList = new CatProviderController(this).callLazyList();
        
        searchProvider = new Provider();

    }
    
     public void setLabels(){
        
            hideDesc = "hide-label";
  
        if (filterValue.equals(1)){
            hideDesc = "";
        }
         
    
    }
     
      @Override
     public void addNew() {
        setSelectedProvider(new Provider());
    }
     
      @Override
     public void callMerge() {
         new CatProviderController(this).runMerge();
          PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
        PrimeFaces.current().executeScript("$('#CRUDModal').modal('close');");
     }

    @Override
    public void callDelete() {
   
    }

    @Override
    public void resetVariables() {
  
    }
    
    
    
    public Integer getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(Integer filterValue) {
        this.filterValue = filterValue;
    }

    public String getHideDesc() {
        return hideDesc;
    }

    public void setHideDesc(String hideDesc) {
        this.hideDesc = hideDesc;
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

    public Provider getSearchProvider() {
        return searchProvider;
    }

    public void setSearchProvider(Provider searchProvider) {
        this.searchProvider = searchProvider;
    }

    public Provider getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(Provider selectedProvider) {
        this.selectedProvider = selectedProvider;
    }

    public int getInitId() {
        return initId;
    }

    public void setInitId(int initId) {
        this.initId = initId;
    }

    public int getEndId() {
        return endId;
    }

    public void setEndId(int endId) {
        this.endId = endId;
    }

    public LazyDataModel<Provider> getProviderLazyList() {
        return providerLazyList;
    }

    public void setProviderLazyList(LazyDataModel<Provider> providerLazyList) {
        this.providerLazyList = providerLazyList;
    }

}
