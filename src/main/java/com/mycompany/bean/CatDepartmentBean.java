/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;


import com.mycompany.controller.CatDepartmentController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.Department;
import com.mycompany.entity.UserApp;
import com.mycompany.extended.DepartmentExt;
import com.mycompany.lazy.BasicLazySearch;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;


/**
 *
 * @author bacajos
 */

@ManagedBean
@ViewScoped
public class CatDepartmentBean extends  AbstractCatalogBean implements Serializable{
    static final long serialVersionUID = 1L;
    
    private DepartmentExt departmentExt;
    private Integer filterValue;
    private String hideDesc = "";
    private LoginBean loggedUsr;
    private AppConfiguration appConfig;
    private UserApp userApp;
    
    @PostConstruct 
    public void init(){
        if(departmentExt == null){
        departmentExt = new DepartmentExt();
        departmentExt.setSelected(new Department());
       //ASIGNACIÓN EN SIG. LOC ES PARA UTILIZAR EL BasicDAO y BasicController
       entityExt = departmentExt;
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
                if(!new CatDepartmentController(this).accessPermission()){
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
        
        
        departmentExt.entity2Example();  
        
        new CatDepartmentController(this).translations();
        objLazyList = new CatDepartmentController().callReadLazyList(departmentExt);
        
        departmentExt.getDepEntity().setDescDepartment("");
        departmentExt = new DepartmentExt();
    }

    @Override
    public void callMerge() {
        new CatDepartmentController(this).runMerge();
        PrimeFaces.current().executeScript("$('#answerModal').modal('open');");
        PrimeFaces.current().executeScript("$('#CRUDModal').modal('close');");
    }

    @Override
    public void callDelete() {
//        resetVariables();
//        
//         entityExt.setObjSelected(departmentExt.getSelected());
//        new CatDepartmentController(this).runDelete();
    }

    @Override
    public void addNew() {
        departmentExt.setSelected(new Department());
    }

    @Override
    public void resetVariables() {

    }
    
     public void setLabels(){
        
            hideDesc = "hide-label";
  
        if (filterValue.equals(1)){
            hideDesc = "";
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

    public DepartmentExt getDepartmentExt() {
        return departmentExt;
    }

    public void setDepartmentExt(DepartmentExt departmentExt) {
        this.departmentExt = departmentExt;
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
