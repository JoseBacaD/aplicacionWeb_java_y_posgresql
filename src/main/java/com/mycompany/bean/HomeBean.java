/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.UserApp;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jerry
 */
@ManagedBean
@ViewScoped
public class HomeBean implements Serializable  {
    static final long serialVerisionUID = 1L;
    
    UserApp user;
     AppConfiguration appConfig;
    LoginBean loggedUsr;
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        loggedUsr = (LoginBean) fc.getExternalContext().getSessionMap().get("loginSession");
        
         try{
            if(loggedUsr != null){
                if(loggedUsr.getUsr().isIsActiveBit()){
                    user = loggedUsr.getUsr();
                appConfig = loggedUsr.getAppConfig();
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

    public LoginBean getLoggedUsr() {
        return loggedUsr;
    }

    public void setLoggedUsr(LoginBean loggedUsr) {
        this.loggedUsr = loggedUsr;
    }
    
    public void logof(){
        loggedUsr.setUsr(new UserApp());
        FacesContext fc = FacesContext.getCurrentInstance();
        try{
         fc.getExternalContext().redirect("login.xhtml");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
