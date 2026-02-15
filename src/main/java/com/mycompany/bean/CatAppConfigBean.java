/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.AppConfigController;
import com.mycompany.controller.CatItemController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.UserApp;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jerry
 */
@ManagedBean
@SessionScoped
public class CatAppConfigBean extends AbstractCatalogBean implements Serializable{
    static final long serialVersionUID = 1L;
    
    AppConfiguration appConfig;
    LoginBean loggedUsr;
    UserApp user;
    
    @PostConstruct
    public void init(){
        
         FacesContext fc = FacesContext.getCurrentInstance();
        loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
        try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    user = loggedUsr.getUsr();
                appConfig = loggedUsr.getAppConfig();
                if(!new AppConfigController(this).accessPermission()){
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
        if(loggedUsr != null){
            if(loggedUsr.getUsr().isIsActiveBit()){
                AppConfigController appController = new  AppConfigController(this);
                   appConfig = (AppConfiguration)appController.callReadAppConfig(loggedUsr.getUsr().getIdDivition(),
                                          loggedUsr.getUsr().getIdSubdivition());
                   loggedUsr.setAppConfig(appConfig);
            }
        }
        
        
      }

    @Override
    public void callMerge() {
          new AppConfigController(this).runMerge();
          populateTable();
        PrimeFaces.current().executeScript("$('#answerModal').modal('open');");

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

    public AppConfiguration getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public LoginBean getLoggedUsr() {
        return loggedUsr;
    }

    public void setLoggedUsr(LoginBean loggedUsr) {
        this.loggedUsr = loggedUsr;
    }

    public UserApp getUser() {
        return user;
    }

    public void setUser(UserApp user) {
        this.user = user;
    }
    
    
}
