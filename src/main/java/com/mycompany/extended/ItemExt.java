/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.extended;



/**
 *
 * @author bacajos
 */

import com.mycompany.dropdown.CurrencyDropdown;
import com.mycompany.dropdown.DepartmentDropdown;
import com.mycompany.dropdown.UomDropdown;
import com.mycompany.entity.Brand;
import com.mycompany.entity.Currency;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.hibernate.criterion.Example;
import com.mycompany.entity.Department;
import com.mycompany.entity.Item;
import com.mycompany.entity.Location;
import com.mycompany.entity.Provider;
import com.mycompany.entity.UnitOfMeasureUom;
import com.mycompany.enums.ItemEnum;
import com.mycompany.pojo.AbstractField;
public class ItemExt extends EntityExt implements Serializable {

//    IMPORTANTE!!!!
//   CUANDO SE CREA UN AbstractFiel HAY QUE CREAR UN POJO QUE HEREDE DE 
//   AbstractField DE ESE TIPO QUE SE VAYA A UTILIZAR Y ASIGNAR EN LOS SETTERS
//   DEL NUEVO POJO EL VALOR AL setObjeIniValue y setObjEndValue PARA QUE
//   LA BÚSQUEDA FUNCIONE.
    //ESTE OBJETO ES PARA BÚSQUEDA----------------------------------------------
         private Item itemEntity;
    //--------------------------------------------------------------------------
         
    // ESTE OBJETO ES PARA HACER MERGE Y DELETE---------------------------------
        private Item selected;
    //--------------------------------------------------------------------------
        
    //abstractFields DEBEN SER PUBLICOS PARA QUE LOS RECONOZCAN EN
    // EN EL MÉTODO buildQuery DE LA CLASE BasicDAO AGREGAR AQUI TANTOS 
    // FILTROS PARES COMO SEA NECESARIO
    // NOTA:NO OLVIDAR CREAR SU GET Y SU SET
    // PARA QUE APREZCA EN LA PANTALLA.-----------------------------------------
          public AbstractField longField1;
          
//        DROPDOWNMENU O LOS ONESELECT
//          DepartmentDropdown depDropdown;
//          UomDropdown uomDropdown; 
//          CurrencyDropdown currencyDropdown;

    public ItemExt() {
         // INICIALIZAR OBJETO DE BÚSQUEDA---------------------------------------
         //INICIALIZAR SUBOBJETOS SI ES QUE LA ENTIDAD TIENE RELACIONES
         //POR EJEMPLO ESTA TABLA TIENE 3 RELACIONES HAY QUE INICIALIZAR 3
         //  SUBOBJETOS

        itemEntity = new Item();
        itemEntity.setIdDepartment(new Department());
        itemEntity.setIdUom(new UnitOfMeasureUom());
        itemEntity.setIdCurrency(new Currency());
        itemEntity.setIdProvider(new Provider());
        itemEntity.setIdBrand(new Brand());
        itemEntity.setIdLocation(new Location());
        
         // INICIALIZAR OBJETO  PARA MERGE|DELETE-------------------------------
         //INICIALIZAR SUBOBJETOS SI ES QUE LA ENTIDAD TIENE RELACIONES
         //POR EJEMPLO ESTA TABLA TIENE DOS RELACIONES HAY QUE INICIALIZAR DOS
         //  SUBOBJETOS
        selected = new Item();
        selected.setIdDepartment(new Department());
        selected.setIdUom(new UnitOfMeasureUom());
        selected.setIdCurrency(new Currency());
        selected.setIdProvider(new Provider());
        selected.setIdBrand(new Brand());
        //----------------------------------------------------------------------     

       //INICIALIZAR CAMPOS PARES----------------------------------------------- 
        longField1= new AbstractField();
        abstractField1 = new AbstractField();
       
      //------------------------------------------------------------------------  
       
      //INICIALIZAR LLENAR LA LISTA EXAMPLES SOLO PARA TABLAS QUE TENGAN
      //RELACIONES Y HAY QUE AGREGAR TANTOS ELEMENTOS A LA LISTA COMO RELACIONES
      //O SUBOBJETOS TENGA------------------------------------------------------
      mapExample = new LinkedHashMap<String, Example>();
      



//------------------------------------------------------------------------------

      //ASIGNAR EL NOMBRE DE LA COLUMNA POR LA CUAL SE HACE EL COUNT EN EL
      //BasicDAO PARA LAS lazylist----------------------------------------------
        countColumnName = ItemEnum.ID_ITEM.getColumnName();
      //------------------------------------------------------------------------
      //ASIGNAR EL NOMBRE DE LA COLUMNA POR LA CUAL SE HACE EL COUNT EN EL
      //BasicDAO EN EL MÉTODO resultSize() PARA LAS lazylist--------------------
        orderColumnName = ItemEnum.ID_ITEM.getColumnName();
      //------------------------------------------------------------------------
      // SE SETEAN LOS VALORES DE LOS FILTROS PARES CON LOS QUE VA A
      //IMPORTANTE!!, HAY QUE INICIALIZAR EL ID DEL CAMPO PAR CON EL NOMBRE DE
      //LA COLUMNA
      //POR LA CUAL SE VA A HACER EL BETWEEN, DE LO CONTRARIO MARCA NPE SIN MAS
      //DETALLE DEL PORQUE NO EJECUTA EL QUERY
      // LA INICIALIACIÓN DEL INIVALUE Y ENDVALUE ES PARA LO QUE SE VA 
      // VER EN PANTALLA EN LA CARGA INICIAL DE LAPANTALLA------------------------------------------------
        longField1.setId(ItemEnum.STANDARD_CODE_ITEM.getColumnName());
        longField1.setLongIniValue(ItemEnum.MIN_STANDARD_CODE.getRangeValue());
        longField1.setLongEndValue(ItemEnum.MAX_STANDARD_CODE.getRangeValue());
     //-------------------------------------------------------------------------
 
     //INICIALIZAR DROPDOWN O SELECTMENU-----------------------------------------
     // SI QUIERES QUE SE LLENE EN CUANTO CARGA LA PANTALLA SINO HACER UN MÉTODO
     // PARA LLAMARLO CUANDO SE REQUIERA
     
//        depDropdown = new DepartmentDropdown();
//        uomDropdown = new UomDropdown();
//        currencyDropdown = new CurrencyDropdown();
     //-------------------------------------------------------------------------
    }
          
          
  
    @Override
    public void entity2Example() {
//        ESTE ES PARA  EL EXAMPLE DE LA TABLA ITEM
        example = Example.create(itemEntity);
        
//        Y LOS QUE VAN EN EL Map SON PARA LOS OBJETOS HIJOS DE ITEM
//        O PARA LAS TABLAS RELACIONADAS
//        HAY QUE AGREGAR TANTOS ELEMENTOS COMO CRITERIOS DE BÚSQUEDA
//        HAYA EN LA PANTALLA QUE SEAN LLAVES FORANEAS.
        mapExample.put(
                   ItemEnum.ID_CURRENCY.getColumnName(),
                   Example.create(itemEntity.getIdCurrency()));

        mapExample.put(
                   ItemEnum.ID_DEPARTMENT.getColumnName(),
                   Example.create(itemEntity.getIdDepartment()));
        mapExample.put(ItemEnum.ID_UOM.getColumnName(),
                   Example.create(itemEntity.getIdUom()));
    }

    @Override
    public int getObjId(Object obj) {
        return ((Item)obj).getIdItem();
    }
    
    public void loadDropDowns(){
//        depDropdown = new DepartmentDropdown();
//        uomDropdown = new UomDropdown();
//        currencyDropdown = new CurrencyDropdown();
    }

    @Override
    public String getExtClassName() {
        return itemEntity.getClass().getName();
    }

    public Item getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(Item itemEntity) {
        this.itemEntity = itemEntity;
    }

    public Item getSelected() {
        return selected;
    }

    public void setSelected(Item selected) {
        this.selected = selected;
    }

    public AbstractField getLongField1() {
        return longField1;
    }

    public void setLongField1(AbstractField longField1) {
        this.longField1 = longField1;
    }

//    public DepartmentDropdown getDepDropdown() {
//        return depDropdown;
//    }
//
//    public void setDepDropdown(DepartmentDropdown depDropdown) {
//        this.depDropdown = depDropdown;
//    }
//
//    public UomDropdown getUomDropdown() {
//        return uomDropdown;
//    }
//
//    public void setUomDropdown(UomDropdown uomDropdown) {
//        this.uomDropdown = uomDropdown;
//    }
//
//    public CurrencyDropdown getCurrencyDropdown() {
//        return currencyDropdown;
//    }
//
//    public void setCurrencyDropdown(CurrencyDropdown currencyDropdown) {
//        this.currencyDropdown = currencyDropdown;
//    }

}
