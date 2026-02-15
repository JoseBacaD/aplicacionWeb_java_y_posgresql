/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.CatSaleDetailController;
import com.mycompany.controller.CatSaleHeaderController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.SaleHeader;
import com.mycompany.entity.UserApp;
import com.mycompany.extended.SaleHeaderExt;
import com.mycompany.pojo.AbstractField;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author jerry
 */
@ManagedBean
@SessionScoped
public class CatSaleHeaderBean extends AbstractCatalogBean implements Serializable {
    static final long serialVersionUID = 1L;
    private SaleHeaderExt saleHeadExt;
    private String strDateToday;
    private LoginBean loggedUsr;
    private AppConfiguration appConfig;
    private UserApp userApp;
    
    
    @PostConstruct
    public void init(){
        Date today = Calendar.getInstance().getTime();
        strDateToday = new SimpleDateFormat("yyyy-MM-dd").format(today);
        
        if (saleHeadExt == null){
            saleHeadExt = new SaleHeaderExt();
//        entityExt = saleDetExt;
        saleHeadExt.setAbstractField1(new AbstractField());
        saleHeadExt.getAbstractField1().setDateIniValue(new CatSaleHeaderController().getToday());
        saleHeadExt.getAbstractField1().setDateEndValue(new CatSaleHeaderController().getToday());
        }
        
        
          FacesContext fc = FacesContext.getCurrentInstance();
        loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
        try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    userApp = loggedUsr.getUsr();
                appConfig = loggedUsr.getAppConfig();
                if(!new CatSaleHeaderController(this).accessPermission()){
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
        
           objLazyList = new CatSaleHeaderController(this).callReadLazyList(saleHeadExt);

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

    public SaleHeaderExt getSaleHeadExt() {
        return saleHeadExt;
    }

    public void setSaleHeadExt(SaleHeaderExt saleHeadExt) {
        this.saleHeadExt = saleHeadExt;
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

  
    
    
}
