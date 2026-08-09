/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.LoginController;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.entity.UserApp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;



/**
 *
 * @author jerry
 */
@ManagedBean(name = "loginSession")
@SessionScoped
public class LoginBean extends AbstractProcessBean implements Serializable {
    static final long serialVersionUID = 1L;

    private UserApp usr;
    private AppConfiguration appConfig;
    private String answerMessage;
    
            List<MenuOptionPermission>liTransactions; 
            List<MenuOptionPermission>liReports; 
            List<MenuOptionPermission>liCatalog; 
            List<MenuOptionPermission>liLabels; 
            List<MenuOptionPermission>liConfiguration; 
            List<MenuOptionPermission>liOther; 
    
    @PostConstruct
    public void init(){
        usr = new UserApp();
        
    }
    
    @Override
    public void callMainProcess() {
        
            liTransactions = new ArrayList<>();
            liReports = new ArrayList<>();
            liCatalog = new ArrayList<>();
            liLabels = new ArrayList<>();
            liConfiguration = new ArrayList<>();
            liOther = new ArrayList<>();
        new LoginController(this).mainProcess();
        new LoginController(this).groupOptionMenu();
          PrimeFaces.current().executeScript("$('#modal1').modal('open');");
         
    }
    

    public UserApp getUsr() {
        return usr;
    }

    public void setUsr(UserApp usr) {
        this.usr = usr;
    }

    @Override
    public String getAnswerMessage() {
        return answerMessage;
    }

    @Override
    public void setAnswerMessage(String answerMessage) {
        this.answerMessage = answerMessage;
    }

    public AppConfiguration getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

  public void logof() {
    try {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        // 1. Invalidar la sesión actual en Tomcat / Servidor
        ec.invalidateSession();

        // 2. Redirigir al login usando la ruta absoluta de la app
        ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public List<MenuOptionPermission> getLiTransactions() {
        return liTransactions;
    }

    public void setLiTransactions(List<MenuOptionPermission> liTransactions) {
        this.liTransactions = liTransactions;
    }

    public List<MenuOptionPermission> getLiReports() {
        return liReports;
    }

    public void setLiReports(List<MenuOptionPermission> liReports) {
        this.liReports = liReports;
    }

    public List<MenuOptionPermission> getLiCatalog() {
        return liCatalog;
    }

    public void setLiCatalog(List<MenuOptionPermission> liCatalog) {
        this.liCatalog = liCatalog;
    }

    public List<MenuOptionPermission> getLiLabels() {
        return liLabels;
    }

    public void setLiLabels(List<MenuOptionPermission> liLabels) {
        this.liLabels = liLabels;
    }

    public List<MenuOptionPermission> getLiConfiguration() {
        return liConfiguration;
    }

    public void setLiConfiguration(List<MenuOptionPermission> liConfiguration) {
        this.liConfiguration = liConfiguration;
    }

    public List<MenuOptionPermission> getLiOther() {
        return liOther;
    }

    public void setLiOther(List<MenuOptionPermission> liOther) {
        this.liOther = liOther;
    }
    
    
    
}
