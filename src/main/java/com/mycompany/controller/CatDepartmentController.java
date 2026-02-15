/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;



import com.mycompany.bean.CatDepartmentBean;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.DepartmentExt;
import com.mycompany.extended.EntityExt;
import com.mycompany.interfaces.CatalogInterface;
import com.mycompany.lazy.DepartmentLazySearch;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author bacajos
 */
public class CatDepartmentController implements Serializable, CatalogInterface {
    
    static final long serialVersionUID = 1L;
    private CatDepartmentBean viewBean;
    private Date today;
   
    
    public CatDepartmentController() {
    }
    
    
    
    public CatDepartmentController(CatDepartmentBean viewBean){
        this.viewBean = viewBean;
          today = Calendar.getInstance().getTime();
    }

    @Override
    public void runMerge() {
       try{
           mergeRules();
//           SÍ SE VA A UTILIZAR EL BasicController HAY QUE ASIGNAR
//           LA ENTIDAD EXTENDIDA EL OBJETO SELECTED DE LA ENTIDAD
//           ESPECIFICA A LA ENTIDAD OBJSELECTED DE LA ENTIDAD PADRE
//           PARA QUE FUNCIONE.
            if(mergePermission()){
             viewBean.getEntityExt().
                     setObjSelected(viewBean.getDepartmentExt().getSelected());
             BasicController.preSetMerge(viewBean);
             
            }else{
            viewBean.setAnswerMessage("Su usuario no tiene permisos para modificar el registro");
        }
        }catch( Exception ex){
            
            ex.printStackTrace();
            
            
        }
        
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
        
        if (viewBean.getDepartmentExt().getDepEntity().getDescDepartment() == null){
            viewBean.getDepartmentExt().getDepEntity().setDescDepartment("nulll");
        }
        
         if ( viewBean.getFilterValue().equals(0)){
             viewBean.getDepartmentExt().getAbstractField1().setLongIniValue(0L);
             viewBean.getDepartmentExt().getAbstractField1().setLongEndValue(999999999999999L);

        }else{
            viewBean.getDepartmentExt().getAbstractField1().setLongIniValue(0L);
            viewBean.getDepartmentExt().getAbstractField1().setLongEndValue(0L);
        }
    }

    @Override
    public void businessRules() {
 
    }

    @Override
    public void mergeRules() {
 
        viewBean.getDepartmentExt().getSelected().setModifiedBy(viewBean.getUserApp().getUserAlias());
        viewBean.getDepartmentExt().getSelected().setCreationDate(today);
        if (viewBean.getDepartmentExt().getSelected().getLastModDate() == null){
            viewBean.getDepartmentExt().getSelected().setLastModDate(today);
        }
    }


    @Override
    public LazyDataModel<Object> callReadLazyList(EntityExt entity) {
        return new DepartmentLazySearch(entity,
                                     "Department.findDepartment",
                                     "Department.findCountDepartment",
                                     ((DepartmentExt)entity).getDepEntity().getDescDepartment(),
                                     entity.getAbstractField1().getLongIniValue(),
                                     entity.getAbstractField1().getLongEndValue()
         );
                
    }
   
     private boolean mergePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_DEPARTMENT_OPTION.getCodeMenuOption())){
                return permission.getCanEditData();
            }
            
        }
        return false;
        
    }
    
    
    private boolean deletePermission(){
        
        for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_DEPARTMENT_OPTION.getCodeMenuOption())){
                return permission.getCanDeleteData();
            }
            
        }
        return false;
        
    }
    
    public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.CAT_DEPARTMENT_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
        
    
     }
    
    
    @Override
    public void deleteRules() {
 }

    @Override
    public Date getToday() {
      return Utility.getDate();
    }
    
}
