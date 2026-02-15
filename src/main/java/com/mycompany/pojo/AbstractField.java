/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author bacajos
 * 
 *    * MÉTODOS GET Y SET QUE RECIBEN OBJECT SON PARA LAS CONSULTAS EN EL BasicDAO
 *    * MÉODOS GET Y SET QUE DEVUELVEN Y RECIBEN UN TIPO DE DATO ESPECIFICO ES UTILIZADO
 *      EN EL MANAGED BEAN PARA QUE REALICE LAS VALIDACIONES EN LA PANTALLA DEPENDIENDO DEL TIPO DE DATO
 *      PARA LUEGO SER ASIGNADO A UN OBJECT.
 * 
 *      AL MOMENTO SOLO HAY FILTROS PARES PARA INT Y DATE, PERO HABRIA QUE GREGAR
 *      PARA LOS DEMÁS TIPOS DE DATO SEGÚN SEA NECESARIO.
 */
public class AbstractField implements Serializable {
    static final long serialVersionUID = 1L;
    private String id;
//    ESTOS TIPOS Object SON PARA HACER LA BÚSQUEDA SON UNA COPIA DE LOS
//    TIPOS ESPECIFICOS QUE SON LLENADOS POR EL USUARIO EN LA VISTA
    private Object objIniValue;
    private Object objEndValue;
//    CADA TIPO DE DATO ESPECIFICO ES UTILIZADO PARA SER MOSTRADO EN LA VISTA
//    SE UTILIZAN TIPOS DE DATO ESPECIFICOS PARA QUE FUNCIONEN LAS VALIDACIONES
//    NATIVAS DE JSF
    private Integer intIniValue;
    private Integer intEndIntValue;
    private Date    dateIniValue;
    private Date    dateEndValue;
    private Long    longIniValue;
    private Long    longEndValue;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Object getObjIniValue() {
        return objIniValue;
    }

    public void setObjIniValue(Object objIniValue) {
        this.objIniValue = objIniValue;
    }

    public Object getObjEndValue() {
        return objEndValue;
    }

    public void setObjEndValue(Object objEndValue) {
        this.objEndValue = objEndValue;
    }

    public Integer getIntIniValue() {
        return intIniValue;
    }

    public void setIntIniValue(Integer intIniValue) {
        this.intIniValue = intIniValue;
        objIniValue = intIniValue;
    }

    public Integer getIntEndIntValue() {
        return intEndIntValue;
    }

    public void setIntEndIntValue(Integer intEndIntValue) {
        this.intEndIntValue = intEndIntValue;
        objEndValue = intEndIntValue;
    }

    public Date getDateIniValue() {
        return dateIniValue;
    }

    public void setDateIniValue(Date dateIniValue) {
        this.dateIniValue = dateIniValue;
        objIniValue = dateIniValue;
    }

    public Date getDateEndValue() {
        return dateEndValue;
    }

    public void setDateEndValue(Date dateEndValue) {
        this.dateEndValue = dateEndValue;
        objEndValue = dateEndValue;
    }

    public Long getLongIniValue() {
        return longIniValue;
    }

    public void setLongIniValue(Long longIniValue) {
        this.longIniValue = longIniValue;
        objIniValue = longIniValue;
    }

    public Long getLongEndValue() {
        return longEndValue;
    }

    public void setLongEndValue(Long longEndValue) {
        this.longEndValue = longEndValue;
        objEndValue = longEndValue;
    }

   
            
          
    
    
   
    
}
