/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.CatAppConfigBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.interfaces.CatalogInterface;
import java.io.Serializable;
import java.util.Date;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author jerry
 */
public class AppConfigController implements Serializable, CatalogInterface {
    static final long serialVersionUID = 1L;
    CatAppConfigBean viewBean;

    public AppConfigController() {
    }

    public AppConfigController(CatAppConfigBean viewBean) {
        this.viewBean = viewBean;
    }
    
    

    @Override
    public void runMerge() {
         FacesContext context = FacesContext.getCurrentInstance();
        try{
            if(mergePermission()){
            BasicDAO.basicMerge(viewBean.getAppConfig()); 
            viewBean.setAnswerMessage(context.getApplication().evaluateExpressionGet(
                    context,
                    "#{bundle['modal.updtcreate.succeed']}", String.class));
            }else{
                 viewBean.setAnswerMessage("Su usuario no tiene permisos para modificar el registro");

            }
        
        }catch(Exception ex){
            ex.printStackTrace();
            viewBean.setAnswerMessage(context.getApplication().evaluateExpressionGet(
                    context,
                    "#{bundle['modal.updtcreate.fail']}", String.class));
        }
      }

    
    public Object callReadAppConfig(int idDivition, int idSuvDivition){
     
        return new BasicDAO().readSPReturnObject("appConfig.findAppConfig", idDivition, idSuvDivition);
        
   }
    
     private boolean mergePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUser().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.APP_CONFIG_OPTION.getCodeMenuOption())){
                return permission.getCanEditData();
            }
            
        }
        return false;
        
    }
     
     public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUser().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.APP_CONFIG_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
    
     }
     
         @Override
    public void runDelete() {
    }

    @Override
    public void translations() {
    }

    @Override
    public void businessRules() {
    }

    @Override
    public void mergeRules() {
     }

    @Override
    public void deleteRules() {
    }

    @Override
    public Date getToday() {
        return null;
    }

    @Override
    public LazyDataModel<Object> callReadLazyList(EntityExt entity) {
        return null;

    }
    
}
