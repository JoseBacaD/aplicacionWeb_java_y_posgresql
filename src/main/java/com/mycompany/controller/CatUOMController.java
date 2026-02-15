/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;


import com.mycompany.bean.CatUOMBean;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.extended.UOMExt;
import com.mycompany.interfaces.CatalogInterface;
import com.mycompany.lazy.UomLazySearch;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author bacajos
 */
public class CatUOMController implements Serializable, CatalogInterface {
    static final long serialVersionUID = 1L;
    private CatUOMBean viewBean;
    private Date today;

    public CatUOMController() {
    }
    
    
    
    public CatUOMController(CatUOMBean viewBean){
        this.viewBean = viewBean;
          today = Calendar.getInstance().getTime();
    }
    
    
    

    @Override
    public void runDelete() {
        try{
           if(deletePermission()){
            BasicController.preSetDelete(viewBean);
           }else{
               viewBean.setAnswerMessage("Su usuario no tiene permisos para eliminar el registro");

           }
        }catch(Exception ex){
               
                ex.printStackTrace();
                }
                   
                
    }

    @Override
    public void translations() {
         if (viewBean.getUomExt().getUomEntity().getDescUom()== null){
            viewBean.getUomExt().getUomEntity().setDescUom("nulll");
        }
        
         if ( viewBean.getFilterValue().equals(0)){
             viewBean.getUomExt().getAbstractField1().setLongIniValue(0L);
             viewBean.getUomExt().getAbstractField1().setLongEndValue(999999999999999L);

        }else{
            viewBean.getUomExt().getAbstractField1().setLongIniValue(0L);
            viewBean.getUomExt().getAbstractField1().setLongEndValue(0L);
        }
    }

    @Override
    public void businessRules(){
        
    }

    @Override
    public void mergeRules() {
        
        viewBean.getUomExt().getSelected().setModifiedBy(viewBean.getUserApp().getUserAlias());
        viewBean.getUomExt().getSelected().setCreationDate(today);
        if (viewBean.getUomExt().getSelected().getLastModDate() == null){
            viewBean.getUomExt().getSelected().setLastModDate(today);
        }

    }

    @Override
    public void deleteRules() {
 
    }

    @Override
    public void runMerge(){
        try{
           mergeRules();
        // sí se va a utilizar el basicController hay que asignar
      // la entidad extendida especifica a  la entidad ext. genérica.
            if(mergePermission()){
            viewBean.getEntityExt().setObjSelected(viewBean.getUomExt().getSelected());
             BasicController.preSetMerge(viewBean);
            }else{
                 viewBean.setAnswerMessage("Su usuario no tiene permisos para modificar el registro");

            }
        }catch( Exception ex){
            
            ex.printStackTrace();
            
            
        }
 }

    @Override
    public Date getToday() {
        return null;
    }

    @Override
    public LazyDataModel<Object> callReadLazyList(EntityExt entity) {
        return new UomLazySearch(entity,
                                     "UnitOfMeasureUom.findUOM",
                                     "UnitOfMeasureUom.findCountUOM",
                                     ((UOMExt)entity).getUomEntity().getDescUom(),
                                     entity.getAbstractField1().getLongIniValue(),
                                     entity.getAbstractField1().getLongEndValue()
         );
    }
  

   private boolean mergePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_UOM_OPTION.getCodeMenuOption())){
                return permission.getCanEditData();
            }
            
        }
        return false;
        
    }
    
    
    private boolean deletePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_UOM_OPTION.getCodeMenuOption())){
                return permission.getCanDeleteData();
            }
            
        }
        return false;
        
    }
    
     public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_UOM_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
    
     }
    
    
}
