/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;


import com.mycompany.controller.CatUOMController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.UnitOfMeasureUom;
import com.mycompany.entity.UserApp;
import com.mycompany.enums.UOMEnum;
import com.mycompany.extended.UOMExt;
import com.mycompany.lazy.BasicLazySearch;
import com.mycompany.pojo.AbstractField;
import java.io.Serializable;
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
public class CatUOMBean extends AbstractCatalogBean implements Serializable {
    static final long serialVerisionUID = 1L;
    
   private UOMExt uomExt;
   private Integer filterValue;
   private String hideDesc = "";
   private LoginBean loggedUsr;
    private AppConfiguration appConfig;
    private UserApp userApp;
   
  @PostConstruct
   public void init(){
       if (uomExt == null){
            uomExt = new UOMExt();
            uomExt.setAbstractField1(new AbstractField());
            uomExt.setSelected(new UnitOfMeasureUom());
            entityExt = uomExt;
//       para poder utilizar el basicDAO y basicController
       
       }
      
       
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
                if(!new CatUOMController(this).accessPermission()){
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
        
        
        uomExt.entity2Example();  
        
        new CatUOMController(this).translations();
        objLazyList = new CatUOMController().callReadLazyList(uomExt);
        
        uomExt.getUomEntity().setDescUom("");
        uomExt = new UOMExt();
    }

    @Override
    public void callMerge() {
      
       new CatUOMController(this).runMerge();
       
       PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
       PrimeFaces.current().executeScript("$('#CRUDModal').modal('close');");
    }

    @Override
    public void callDelete() {
//        resetVariables();
//        
//        
//       entityExt.setObjSelected(uomExt.getSelected());
//        new CatUOMController(this).runDelete();
     }

    @Override
    public void addNew() {
        uomExt.setSelected(new UnitOfMeasureUom());
     }

    @Override
    public void resetVariables() {
       answerMessage = "";
     }
    
      public void setLabels(){
        
            hideDesc = "hide-label";
  
        if (filterValue.equals(1)){
            hideDesc = "";
        }
    }

    public UOMExt getUomExt() {
        return uomExt;
    }

    public void setUomExt(UOMExt uomExt) {
        this.uomExt = uomExt;
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
    
    
}
