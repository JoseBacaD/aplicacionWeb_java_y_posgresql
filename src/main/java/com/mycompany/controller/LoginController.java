/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.LoginBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.AppConfiguration;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.entity.UserApp;
import com.mycompany.enums.ParentOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.lazy.ItemLazySearch;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author jerry
 */
public class LoginController implements Serializable {
        static final long serialVersionUID = 1L;
        private LoginBean viewBean;
        
        public LoginController(){
            
        }
        
        public LoginController(LoginBean viewBean){
            this.viewBean = viewBean;
        }
        
        public void groupOptionMenu(){
            List<MenuOptionPermission>liPerm = viewBean.getUsr().getListMenuOptionPermission();
            if(liPerm !=null){
            for (MenuOptionPermission mop : liPerm) {
                   Integer idParentMenu = mop.getIdMenuOption().getIdParentMenu();
                   if(idParentMenu != null){
                        if(idParentMenu.equals(ParentOptionEnum.TRANSACTION_PARENT_MENU.getCodeParentOption())){
                        viewBean.getLiTransactions().add(mop);
                        }else if(idParentMenu.equals(ParentOptionEnum.REPORT_PARENT_MENU.getCodeParentOption())){
                        viewBean.getLiReports().add(mop);
                        }else if(idParentMenu.equals(ParentOptionEnum.CATALOG_PARENT_MENU.getCodeParentOption())){
                        viewBean.getLiCatalog().add(mop);
                        }else if(idParentMenu.equals(ParentOptionEnum.LABEL_PARENT_MENU.getCodeParentOption())){
                        viewBean.getLiLabels().add(mop);
                        }else if(idParentMenu.equals(ParentOptionEnum.CONFIGURATION_PARENT_MENU.getCodeParentOption())){
                        viewBean.getLiConfiguration().add(mop);
                        }else if(idParentMenu.equals(ParentOptionEnum.OTHER_PARENT_MENU.getCodeParentOption())){
                        viewBean.getLiOther().add(mop);
                        }
                    } 
                }
            }    
        }
         public void mainProcess(){
             UserApp validUser;
             AppConfiguration appConfig;
             
             validUser = (UserApp)callReadUserApp(viewBean.getUsr().getUserAlias());
            
               try{
        if (validUser != null) {
            if (validUser.isIsActiveBit()){
                if (validUser.getUser_password().equals(viewBean.getUsr().getUser_password())) {
                    viewBean.setUsr(validUser);
                     appConfig = (AppConfiguration)callReadAppConfig(validUser.getIdDivition(),validUser.getIdSubdivition());
                    viewBean.setAppConfig(appConfig);
                      FacesContext.getCurrentInstance().getExternalContext().redirect("Home.xhtml");
                } else {
                    viewBean.setAnswerMessage("usuario o Contraseña son incorrectos");

                }
            }else{
                 viewBean.setAnswerMessage("Usuario inactivo");

            }
                
        } else {
            viewBean.setAnswerMessage("Usuario o contraseña son incorrectos");
            
        }
         
        
         
        }catch(Exception ex){ 
            ex.printStackTrace();
        }
             
        }
         
        private Object callReadUserApp(String userAlias){
   
        return new BasicDAO().readSPReturnObject("UserApp.findUsrByAlias", userAlias );
        
   }
        
        private Object callReadAppConfig(int idDivition, int idSuvDivition){
              return new BasicDAO().readSPReturnObject("appConfig.findAppConfig", idDivition, idSuvDivition);
      
        }
      
     
}


