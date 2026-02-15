/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.CatBrandController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.Brand;
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
public class CatBrandBean extends AbstractCatalogBean implements Serializable{
    static final long serialVersionUID = 1L;
     
     private Brand searchBrand;
     private Brand selectedBrand;
     private Integer filterValue;
     private String hideDesc = "";
     private LoginBean loggedUsr;
     private AppConfiguration appConfig;
     private UserApp userApp;
     private int initId;
     private int endId;
     protected LazyDataModel<Brand> brandLazyList = null;
     
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
                    
                    if(searchBrand == null){
                        searchBrand = new Brand();
                    }
                 
               if(!new CatBrandController(this).accessPermission()){
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
        
  
        new CatBrandController(this).translations();
        brandLazyList = new CatBrandController(this).callLazyList();
        
        searchBrand = new Brand();
     }
    
     public void setLabels(){
        
            hideDesc = "hide-label";
  
        if (filterValue.equals(1)){
            hideDesc = "";
        }
         
    
    }

    @Override
    public void callMerge() {
         new CatBrandController(this).runMerge();
          PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
        PrimeFaces.current().executeScript("$('#CRUDModal').modal('close');");

    }

    @Override
    public void callDelete() {
   }

    @Override
    public void addNew() {
        setSelectedBrand(new Brand());
    }

    @Override
    public void resetVariables() {
    }

    public Brand getSearchBrand() {
        return searchBrand;
    }

    public void setSearchBrand(Brand searchBrand) {
        this.searchBrand = searchBrand;
    }

    public Brand getSelectedBrand() {
        return selectedBrand;
    }

    public void setSelectedBrand(Brand selectedBrand) {
        this.selectedBrand = selectedBrand;
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

    public LazyDataModel<Brand> getBrandLazyList() {
        return brandLazyList;
    }

    public void setBrandLazyList(LazyDataModel<Brand> brandLazyList) {
        this.brandLazyList = brandLazyList;
    }

}
