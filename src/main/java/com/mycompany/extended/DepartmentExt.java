/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.extended;


import java.io.Serializable;
import org.hibernate.criterion.Example;
import com.mycompany.entity.Department;
import com.mycompany.enums.DepartmentEnum;
import com.mycompany.pojo.AbstractField;
/**
 *
 * @author bacajos
 */
public class DepartmentExt extends EntityExt implements Serializable {
    static final long serialVersionUID = 1L;
    
    
    //ESTE OBJETO ES PARA BÚSQUEDA----------------------------------------------
         private Department depEntity;
    //--------------------------------------------------------------------------
         
    // ESTE OBJETO ES PARA HACER MERGE Y DELETE---------------------------------
        private Department selected;
    //--------------------------------------------------------------------------
        
    //abstractFields DEBEN SER PUBLICOS PARA QUE LOS RECONOZCAN EN
    // EN EL MÉTODO buildQuery DE LA CLASE BasicDAO AGREGAR AQUI TANTOS 
    // FILTROS PARESCOMO SEA NECESARIO
    // NOTA:NO OLVIDAR CREAR SU GET Y SU SET
    // PARA QUE APREZCA EN LA PANTALLA.-----------------------------------------
  
        public AbstractField intField;
    
    public DepartmentExt(){
         // INICIALIZAR OBJETO DE BÚSQUEDA---------------------------------------
        depEntity = new Department();
       //INICIALIZAR CAMPOS PARES----------------------------------------------- 
        intField = new AbstractField();
      //------------------------------------------------------------------------  
       
      //ASIGNAR EL NOMBRE DE LA COLUMNA POR LA CUAL SE HACE EL COUNT EN EL
      //BasicDAO PARA LAS lazylist SE RECOMIENDA TOMAR SIEMPRE LA PK------------
        countColumnName = DepartmentEnum.ID_DEPARTMENT.getColumnName();
      //------------------------------------------------------------------------
      //ASIGNAR EL NOMBRE DE LA COLUMNA POR LA CUAL SE HACE EL ORDER BY EN EL
      //BasicDAO EN EL MÉTODO resultSize() PARA LAS lazylist--------------------
        orderColumnName = DepartmentEnum.ID_DEPARTMENT.getColumnName();
      //------------------------------------------------------------------------
      // SE SETEAN LOS VALORES DE LOS FILTROS PARES CON LOS QUE VA A
      // INICIALIZAR LA PANTALLA------------------------------------------------
        intField.setId(DepartmentEnum.CODE_DEPARTMENT.getColumnName());
        intField.setIntIniValue(DepartmentEnum.MIN_CODE_DEPARTMENT.getRangeValue());
        intField.setIntEndIntValue(DepartmentEnum.MAX_CODE_DEPARTMENT.getRangeValue());
     //-------------------------------------------------------------------------
    abstractField1 = new AbstractField();
    }

    @Override
    public void entity2Example() {
        example = Example.create(depEntity);
    }

    @Override
    public int getObjId(Object obj) {
        return ((Department)obj).getIdDepartment();
    }

    @Override
    public String getExtClassName() {
        return depEntity.getClass().getName();
    }

    public Department getDepEntity() {
        return depEntity;
    }

    public void setDepEntity(Department depEntity) {
        this.depEntity = depEntity;
    }

    public Department getSelected() {
        return selected;
    }

    public void setSelected(Department selected) {
        this.selected = selected;
    }

    public AbstractField getIntField() {
        return intField;
    }

    public void setIntField(AbstractField intField) {
        this.intField = intField;
    }
    
    
    
    
}
