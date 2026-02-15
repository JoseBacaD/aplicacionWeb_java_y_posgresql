/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;


import com.mycompany.controller.LabelItemPrintController;
import com.mycompany.controller.ProcSaleController;
import com.mycompany.dropdown.BrandDropdown;
import com.mycompany.dropdown.DepartmentDropdown;
import com.mycompany.dropdown.LocationDropdown;
import com.mycompany.dropdown.ProviderDropDown;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.Brand;
import com.mycompany.entity.Currency;
import com.mycompany.entity.Department;
import com.mycompany.entity.Item;
import com.mycompany.entity.Location;
import com.mycompany.entity.Provider;
import com.mycompany.entity.SaleDetail;
import com.mycompany.entity.UserApp;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class LabelItemPrintBean  implements Serializable{
       static final long serialVersionUID = 1L;
       
       private Integer filterValue;
    private String hideDesc = "";
    private String hideLocation = "hide-label";
    private String hideDepartment = "hide-label";
    private String hideCurrency = "hide-label";
    private String hideCode = "hide-label";
    private Item searchItem;
    DepartmentDropdown depDropdown;
    protected LazyDataModel<Item> objLazyList = null;
    private Long initCode;
    private Long EndCode;
    private List<Item> printList;
    private List<Item> splittedPrintList;
    private Item rowSelected;
    private String searchStdCode;
    private AppConfiguration appConfig; 
    private String answerMessage;
    private Item rowDelete;
    private Item lastPrintableItem;
    private String strCurrentCode;
    private int totalTags;
    private LoginBean loggedUsr;
    private UserApp userApp;
    private ProviderDropDown provDropdown;
    private BrandDropdown brandDropdown;
    private LocationDropdown locationDropdown;
    
    @PostConstruct
    public void init(){
        depDropdown = new DepartmentDropdown();
       
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
                    depDropdown = new DepartmentDropdown();
                    provDropdown = new ProviderDropDown();
                    brandDropdown = new BrandDropdown();
                    locationDropdown = new LocationDropdown();
                    searchItem = new Item();
                    searchItem.setIdDepartment(new Department());
                    searchItem.setIdCurrency(new Currency());
                    searchItem.setIdProvider(new Provider());
                    searchItem.setIdBrand(new Brand());
                    searchItem.setIdLocation(new Location());
                  if (printList == null){
                    printList = new ArrayList<>();
                  }
               if(!new LabelItemPrintController(this).accessPermission()){
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
                    depDropdown = new DepartmentDropdown();
                    provDropdown = new ProviderDropDown();
                    brandDropdown = new BrandDropdown();
                    locationDropdown = new LocationDropdown();
                    searchItem = new Item();
                    searchItem.setIdDepartment(new Department());
                    searchItem.setIdCurrency(new Currency());
                    searchItem.setIdProvider(new Provider());
                    searchItem.setIdBrand(new Brand());
                    searchItem.setIdLocation(new Location());
                    
                  if (printList == null){
                   printList = new ArrayList<>();
                  }
               if(!new LabelItemPrintController(this).accessPermission()){
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
    
    public void populateTable(){
         DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
                               getViewRoot().
                               findComponent("tableForm:resultTable");
        dataTable.reset(); 
        
  
        new LabelItemPrintController(this).translations();
        objLazyList = new LabelItemPrintController(this).callLazyList();
        
        searchItem = new Item();

    }
    
    public void emptyPrintList(){
         printList.clear();
         totalTags = printList.size();
         System.out.println("vaciar lista!!!!!!");
    }
    
    public void addCurrentPageToList(){
        for (Item item : objLazyList.getWrappedData()) {
            printList.add(item);
        }
        totalTags = printList.size();
    }
     public void setLabels(){
        
            hideDesc = "hide-label";
        hideCode = "hide-label";
        hideLocation = "hide-label";
        hideDepartment = "hide-label";
        hideCurrency = "hide-label";
       
        if (filterValue.equals(1)){
            hideDesc = "";
        }
         if (filterValue.equals(2)){
            hideCode = "";
        }
        if (filterValue.equals(3)){
            hideLocation = "";
        }
        if (filterValue.equals(4)){
              hideDepartment = "";
        }
        if (filterValue.equals(5)){
            hideCurrency = "";
        }
    
    }
     

     public void onRowSelect(SelectEvent<Item> event){
           rowDelete = event.getObject();
          printList.remove(rowDelete);
          totalTags = printList.size();
//          DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
//                               getViewRoot().
//                               findComponent("printForm:printTable");
//        dataTable.reset();
     }
     
     public void searchOnRowSelect(SelectEvent<Item> event){
          rowSelected = event.getObject();   
          printList.add(rowSelected);
          totalTags = printList.size();
          
//          DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().
//                               getViewRoot().
//                               findComponent("tableForm:resultTable");
//        dataTable.reset();
     }
     
     public void callSearchItem(){
         LabelItemPrintController controller = new LabelItemPrintController(this);
        Item detail =  controller.searchItem();
        
        if (detail != null){
            answerMessage = "";
          printList.add(detail);
          totalTags = printList.size();
             
        }else{
            answerMessage = "Item not found";
        }
         setSearchStdCode(null);
        
     }
     
     public void preparePrintList(){
          
         
         if (printList.size() > 0){
             lastPrintableItem = printList.get(printList.size()- 1);
             splittedPrintList = new ArrayList<>(printList);
             splittedPrintList.remove(splittedPrintList.size() - 1);
         }
         
         
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

    public String getHideLocation() {
        return hideLocation;
    }

    public void setHideLocation(String hideLocation) {
        this.hideLocation = hideLocation;
    }

    public String getHideDepartment() {
        return hideDepartment;
    }

    public void setHideDepartment(String hideDepartment) {
        this.hideDepartment = hideDepartment;
    }

    public String getHideCurrency() {
        return hideCurrency;
    }

    public void setHideCurrency(String hideCurrency) {
        this.hideCurrency = hideCurrency;
    }

    public String getHideCode() {
        return hideCode;
    }

    public void setHideCode(String hideCode) {
        this.hideCode = hideCode;
    }

    public DepartmentDropdown getDepDropdown() {
        return depDropdown;
    }

    public void setDepDropdown(DepartmentDropdown depDropdown) {
        this.depDropdown = depDropdown;
    }

    public LazyDataModel<Item> getObjLazyList() {
        return objLazyList;
    }

    public void setObjLazyList(LazyDataModel<Item> objLazyList) {
        this.objLazyList = objLazyList;
    }

    public Long getInitCode() {
        return initCode;
    }

    public void setInitCode(Long initCode) {
        this.initCode = initCode;
    }

    public Long getEndCode() {
        return EndCode;
    }

    public void setEndCode(Long EndCode) {
        this.EndCode = EndCode;
    }

    public Item getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(Item searchItem) {
        this.searchItem = searchItem;
     
    }

    public List<Item> getPrintList() {
        return printList;
    }

    public void setPrintList(List<Item> printList) {
        this.printList = printList;
    }

    public Item getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(Item rowSelected) {
        this.rowSelected = rowSelected;
    }

    public String getSearchStdCode() {
        return searchStdCode;
    }

    public void setSearchStdCode(String searchStdCode) {
        this.searchStdCode = searchStdCode;
    }

    public AppConfiguration getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public String getAnswerMessage() {
        return answerMessage;
    }

    public void setAnswerMessage(String answerMessage) {
        this.answerMessage = answerMessage;
    }

    public Item getRowDelete() {
        return rowDelete;
    }

    public void setRowDelete(Item rowDelete) {
        this.rowDelete = rowDelete;
    }

    public int getTotalTags() {
        return totalTags;
    }

    public void setTotalTags(int totalTags) {
        this.totalTags = totalTags;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public List<Item> getSplittedPrintList() {
        return splittedPrintList;
    }

    public void setSplittedPrintList(List<Item> splittedPrintList) {
        this.splittedPrintList = splittedPrintList;
    }

    public Item getLastPrintableItem() {
        return lastPrintableItem;
    }

    public void setLastPrintableItem(Item lastPrintableItem) {
        this.lastPrintableItem = lastPrintableItem;
    }

    public ProviderDropDown getProvDropdown() {
        return provDropdown;
    }

    public void setProvDropdown(ProviderDropDown provDropdown) {
        this.provDropdown = provDropdown;
    }

    public BrandDropdown getBrandDropdown() {
        return brandDropdown;
    }

    public void setBrandDropdown(BrandDropdown brandDropdown) {
        this.brandDropdown = brandDropdown;
    }

    public LocationDropdown getLocationDropdown() {
        return locationDropdown;
    }

    public void setLocationDropdown(LocationDropdown locationDropdown) {
        this.locationDropdown = locationDropdown;
    }

    
}
