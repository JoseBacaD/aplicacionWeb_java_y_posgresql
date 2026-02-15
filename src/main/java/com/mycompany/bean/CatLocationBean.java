/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.CatLocationController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.Location;
import com.mycompany.entity.UserApp;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author yo
 */
@ManagedBean
@SessionScoped
public class CatLocationBean extends AbstractCatalogBean implements Serializable {
    static final long serialVersionUID = 1L;
    
    private Location searchLocation;
     private Location selectedLocation;
     private Integer filterValue;
     private String hideDesc = "";
     private LoginBean loggedUsr;
     private AppConfiguration appConfig;
     private UserApp userApp;
     private int initId;
     private int endId;
     protected LazyDataModel<Location> brandLazyList = null;
     
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
                    
                    if(searchLocation == null){
                        searchLocation = new Location();
                    }
                 
               if(!new CatLocationController(this).accessPermission()){
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
     public void setLabels(){
        
            hideDesc = "hide-label";
  
        if (filterValue.equals(1)){
            hideDesc = "";
        }
         
    
    }

    @Override
    public void populateTable() {
          DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
                               getViewRoot().
                               findComponent("tableForm:resultTable");
        dataTable.reset(); 
        
  
        new CatLocationController(this).translations();
        brandLazyList = new CatLocationController(this).callLazyList();
        
        searchLocation = new Location();
    }

    @Override
    public void callMerge() {
           new CatLocationController(this).runMerge();
          PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
        PrimeFaces.current().executeScript("$('#CRUDModal').modal('close');");
    }

    @Override
    public void callDelete() {
    }

    @Override
    public void addNew() {
         setSelectedLocation(new Location());
    }

    @Override
    public void resetVariables() {
    }

    public Location getSearchLocation() {
        return searchLocation;
    }

    public void setSearchLocation(Location searchLocation) {
        this.searchLocation = searchLocation;
    }

    public Location getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(Location selectedLocation) {
        this.selectedLocation = selectedLocation;
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

    public LazyDataModel<Location> getBrandLazyList() {
        return brandLazyList;
    }

    public void setBrandLazyList(LazyDataModel<Location> brandLazyList) {
        this.brandLazyList = brandLazyList;
    }
    
    
     
}
