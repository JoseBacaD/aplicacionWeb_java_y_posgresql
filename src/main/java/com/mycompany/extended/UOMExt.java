/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.extended;

import com.mycompany.entity.UnitOfMeasureUom;
import com.mycompany.enums.UOMEnum;
import com.mycompany.pojo.AbstractField;
import java.io.Serializable;
import org.hibernate.criterion.Example;

/**
 *
 * @author bacajos
 */



public class UOMExt extends EntityExt implements Serializable {

    //ESTE OBJETO ES PARA BÚSQUEDA----------------------------------------------
         private UnitOfMeasureUom uomEntity;
    //--------------------------------------------------------------------------
         
    // ESTE OBJETO ES PARA HACER MERGE Y DELETE---------------------------------
        private UnitOfMeasureUom selected;
    //--------------------------------------------------------------------------
        
    //abstractFields DEBEN SER PUBLICOS PARA QUE LOS RECONOZCAN EN
    // EN EL MÉTODO buildQuery DE LA CLASE BasicDAO AGREGAR AQUI TANTOS 
    // FILTROS PARESCOMO SEA NECESARIO
    // NOTA:NO OLVIDAR CREAR SU GET Y SU SET
    // PARA QUE APREZCA EN LA PANTALLA.-----------------------------------------
  
        public AbstractField intField;
    //--------------------------------------------------------------------------
    public UOMExt(){
        // INICIALIZAR OBJETO DE BÚSQUEDA---------------------------------------
        uomEntity = new UnitOfMeasureUom();
       //INICIALIZAR CAMPOS PARES----------------------------------------------- 
        intField = new AbstractField();
      //------------------------------------------------------------------------  
       
      //ASIGNAR EL NOMBRE DE LA COLUMNA POR LA CUAL SE HACE EL COUNT EN EL
      //BasicDAO PARA LAS lazylist----------------------------------------------
        countColumnName = UOMEnum.ID_UOM.getColumnName();
      //------------------------------------------------------------------------
      //ASIGNAR EL NOMBRE DE LA COLUMNA POR LA CUAL SE HACE EL COUNT EN EL
      //BasicDAO EN EL MÉTODO resultSize() PARA LAS lazylist--------------------
        orderColumnName = UOMEnum.ID_UOM.getColumnName();
      //------------------------------------------------------------------------
      // SE SETEAN LOS VALORES DE LOS FILTROS PARES CON LOS QUE VA A
      // INICIALIZAR LA PANTALLA------------------------------------------------
        intField.setId(UOMEnum.CODE_UOM.getColumnName());
        intField.setIntIniValue(UOMEnum.MIN_CODE_UOM.getRangeValue());
        intField.setIntEndIntValue(UOMEnum.MAX_CODE_UOM.getRangeValue());
     //-------------------------------------------------------------------------
     
     abstractField1 = new AbstractField();
    }
        
    @Override
    public void entity2Example() {
        example = Example.create(uomEntity);
    }

    @Override
    public int getObjId(Object obj) {
        return ((UnitOfMeasureUom)obj).getIdUom();
    }

    @Override
    public String getExtClassName() {
        return uomEntity.getClass().getName();
    }

    public UnitOfMeasureUom getUomEntity() {
        return uomEntity;
    }

    public void setUomEntity(UnitOfMeasureUom uomEntity) {
        this.uomEntity = uomEntity;
    }

    public UnitOfMeasureUom getSelected() {
        return selected;
    }

    public void setSelected(UnitOfMeasureUom selected) {
        this.selected = selected;
    }

    public AbstractField getIntField() {
        return intField;
    }

    public void setIntField(AbstractField intField) {
        this.intField = intField;
    }

    
  
    
}
