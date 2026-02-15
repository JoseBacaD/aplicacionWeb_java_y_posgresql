/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;


import com.mycompany.controller.CatItemController;
import com.mycompany.dropdown.BrandDropdown;
import com.mycompany.dropdown.CurrencyDropdown;
import com.mycompany.dropdown.DepartmentDropdown;
import com.mycompany.dropdown.LocationDropdown;
import com.mycompany.dropdown.ProviderDropDown;
import com.mycompany.dropdown.UomDropdown;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.Brand;
import com.mycompany.entity.Currency;
import com.mycompany.entity.Department;
import com.mycompany.entity.Item;
import com.mycompany.entity.Location;
import com.mycompany.entity.Provider;
import com.mycompany.entity.UnitOfMeasureUom;
import com.mycompany.entity.UserApp;
import com.mycompany.extended.ItemExt;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author bacajos
 */
@ManagedBean
@ViewScoped
public class CatItemBean extends AbstractCatalogBean implements Serializable {
    static final long serialVersionUID = 1L;
    private ItemExt itemExt;
    private Integer filterValue;
    private String hideDesc = "";
    private String hideLocation = "hide-label";
    private String hideDepartment = "hide-label";
    private String hideCurrency = "hide-label";
    private String hideCode = "hide-label";
    private boolean saleByFraction;
    private String strDateToday;
    private UomDropdown uomDropdown; 
    private CurrencyDropdown currencyDropdown;
    private ProviderDropDown provDropdown;
    private BrandDropdown brandDropdown;
    private LocationDropdown locationDropdown;  
    private LoginBean loggedUsr;
    private AppConfiguration appConfig;
    private UserApp userApp;
    private Provider selectedProvider;
    private Brand selectedBrand;
    private Item selectedItem;
    private DepartmentDropdown depDropdown;
    
    
    @PostConstruct
    public void init(){
        
        Date today = Calendar.getInstance().getTime();
        strDateToday = new SimpleDateFormat("yyyy-MM-dd").format(today);
       
        if(itemExt == null){
        itemExt = new ItemExt();       
        }
//        itemExt.loadDropDowns();
        //       para poder utilizar el basicDAO y basicController
       entityExt = itemExt;
       
       if (filterValue == null){
           filterValue = 1;
       }
       
       
          FacesContext fc = FacesContext.getCurrentInstance();
        loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
        try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    userApp = loggedUsr.getUsr();
                    depDropdown = new DepartmentDropdown();
                    uomDropdown = new UomDropdown();
                    currencyDropdown = new CurrencyDropdown();
                    provDropdown = new ProviderDropDown();
                    brandDropdown = new BrandDropdown();
                    locationDropdown = new LocationDropdown();
                    selectedProvider = new Provider();
                    selectedBrand = new Brand();
                 
                    
                appConfig = loggedUsr.getAppConfig();
                if(!new CatItemController(this).accessPermission()){
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
        
//CREAR EXAMPLE PARA LOS CAMPOS DE BÚSQUEDA QUE SERÁ UTILIZADOS EN EL DAO.
        
        
        itemExt.entity2Example();  
        
        new CatItemController(this).translations();
        objLazyList = new CatItemController(this).callReadLazyList(itemExt);
        
        itemExt.getItemEntity().setDescItem("");
        itemExt.getItemEntity().setItemLocation("");
        itemExt = new ItemExt();

    }
    
    public void onRowSelect(SelectEvent<Item> event){
           selectedItem = event.getObject();
    }

    @Override
    public void callMerge() {
        selectedItem.setItemLocation("NA");
        new CatItemController(this).runMerge();
       
        PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
        PrimeFaces.current().executeScript("$('#CRUDModal').modal('close');");
       
         itemExt.setSelected(new Item());
         itemExt.getSelected().setIdDepartment(new Department());
         itemExt.getSelected().setIdUom(new UnitOfMeasureUom());
         itemExt.getSelected().setIdCurrency(new Currency());
         itemExt.getSelected().setIdProvider(new Provider());
         itemExt.getSelected().setIdBrand(new Brand());
         selectedBrand = new Brand();
         selectedProvider = new Provider();
         selectedItem = new Item();
         
    }

    @Override
    public void callDelete() {
//        resetVariables();
//        
//        entityExt.setObjSelected(itemExt.getSelected());
//        new CatItemController(this).runDelete();
    }

    @Override
    public void addNew() {
        selectedItem = new Item();
        selectedItem.setIdDepartment(new Department());
        selectedItem.setIdUom(new UnitOfMeasureUom());
        selectedItem.setIdCurrency(new Currency());
        selectedItem.setIdProvider(new Provider());
        selectedItem.setIdProvider2(new Provider());
        selectedItem.setIdProvider3(new Provider());
        selectedItem.setIdBrand(new Brand());
        selectedItem.setIdLocation(new Location());
    }

    @Override
    public void resetVariables() {
        answerMessage = "";
        
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
    
    public ItemExt getItemExt() {
        return itemExt;
    }

    public void setItemExt(ItemExt itemExt) {
        this.itemExt = itemExt;
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

    public UomDropdown getUomDropdown() {
        return uomDropdown;
    }

    public void setUomDropdown(UomDropdown uomDropdown) {
        this.uomDropdown = uomDropdown;
    }

    public CurrencyDropdown getCurrencyDropdown() {
        return currencyDropdown;
    }

    public void setCurrencyDropdown(CurrencyDropdown currencyDropdown) {
        this.currencyDropdown = currencyDropdown;
    }

    public boolean isSaleByFraction() {
        return saleByFraction;
    }

    public void setSaleByFraction(boolean saleByFraction) {
        this.saleByFraction = saleByFraction;
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

    public BrandDropdown getBrandDropdown() {
        return brandDropdown;
    }

    public void setBrandDropdown(BrandDropdown brandDropdown) {
        this.brandDropdown = brandDropdown;
    }

    public ProviderDropDown getProvDropdown() {
        return provDropdown;
    }

    public void setProvDropdown(ProviderDropDown provDropdown) {
        this.provDropdown = provDropdown;
    }

    public Provider getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(Provider selectedProvider) {
        this.selectedProvider = selectedProvider;
    }

    public Brand getSelectedBrand() {
        return selectedBrand;
    }

    public void setSelectedBrand(Brand selectedBrand) {
        this.selectedBrand = selectedBrand;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
        if(selectedItem.getIdProvider() == null){
            selectedItem.setIdProvider(new Provider());
        }
        if(selectedItem.getIdBrand() == null){
            selectedItem.setIdBrand(new Brand());
        }
        
        if(selectedItem.getIdLocation() == null){
            selectedItem.setIdLocation(new Location());
        }
        if(selectedItem.getIdProvider2() == null){
            selectedItem.setIdProvider2(new Provider());
        }
        
        if(selectedItem.getIdProvider3() == null){
            selectedItem.setIdProvider3(new Provider());
        }
    }

    public LocationDropdown getLocationDropdown() {
        return locationDropdown;
    }

    public void setLocationDropdown(LocationDropdown locationDropdown) {
        this.locationDropdown = locationDropdown;
    }
    
}
