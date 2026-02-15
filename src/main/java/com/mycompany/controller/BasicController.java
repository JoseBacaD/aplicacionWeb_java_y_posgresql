/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;


import com.mycompany.bean.AbstractCatalogBean;
import com.mycompany.dao.BasicDAO;
import javax.faces.context.FacesContext;

/**
 *
 * @author bacajos
 */
public class BasicController {
   
    
    public static void preSetMerge(AbstractCatalogBean viewBean) throws Exception{
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            BasicDAO.basicMerge(viewBean.getEntityExt().getObjSelected()); 
            
        viewBean.setAnswerMessage(context.getApplication().evaluateExpressionGet(
                    context,
                    "#{bundle['modal.updtcreate.succeed']}", String.class));
        }catch(Exception ex){
            ex.printStackTrace();
            viewBean.setAnswerMessage(context.getApplication().evaluateExpressionGet(
                    context,
                    "#{bundle['modal.updtcreate.fail']}", String.class));
        }
    }
    
    public static void preSetDelete(AbstractCatalogBean viewBean) throws Exception{
        FacesContext context = FacesContext.getCurrentInstance();
        try{
        BasicDAO.basicDelete(viewBean.getEntityExt().getObjSelected());
        
        viewBean.setAnswerMessage(context.getApplication().evaluateExpressionGet(
                    context,
                    "#{bundle['modal.delete.succeed']}", String.class));
        } catch (Exception ex){
            ex.printStackTrace();
            viewBean.setAnswerMessage(context.getApplication().evaluateExpressionGet(
                    context,
                    "#{bundle['modal.delete.fail']}", String.class));
        }
    }
        
    
    
}
