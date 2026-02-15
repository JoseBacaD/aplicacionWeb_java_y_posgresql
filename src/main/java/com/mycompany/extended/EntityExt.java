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
//   ¿PRIMERA VEZ POR AQUÍ? LEEME!!  ----------------------------------------------------------------------------------------------------------------------------------
//       *COLUMNAS:
//        CREACIÓN DE VARIABLES ESTÁTICAS CON LOS NOMBRES DE CADA COLUMNA O FILTRO DE BÚSQUEDA
//        LA CANTIDAD DE COLUMNAS DEPENDERÁ DE LA CANTIDAD DE FILTROS QUE HABRAS DE PERMITIRLE AL USUARIO UTILIZAR PARA SU BÚSQUEDA.
//          EJEMPLOS:      
//              public static final String ID_STATE = "idstates"; LOS NOMBRES DE COLUMNAS DEBEN SER EXACTAMENTE IGUALES A LOS NOMBRES DE LAS ENTIDADES DE BD.
//              public static final String STATE_CODE_INT = "stateCodeInt";
//              public static final String STATE_NAME_VC = "stateNameVc";
//     *OBJETO ENTIDAD: 
//                ES UNA VARIABLE  QUE LLEVA LA ENTIDAD PERTENECIENTE A UNA TABLA DE BD.
//                ESTA VARIABLE DEBE SER INICIALIZADA CON EL TIPO DE OBJETO PERTENECIENTE A ESA ENTIDAD EN LAS CLASES QUE HEREDEN DE ESTA
//      *GETTES Y SETTER: AGREGAR GET Y SET DE TODAS ESTAS VARIABLES EXCEPTO LAS ESTATICAS.



import com.mycompany.pojo.AbstractField;
import org.hibernate.criterion.Example;
import java.util.Date;
import java.util.Map;
import org.primefaces.model.LazyDataModel;

public abstract class EntityExt {
    
//    VARIABLES PARA CONSTRUIR CRITERIO DE BÚSQUEDA PARA:
//    TIPO STRING, SI QUIERES QUE BUSQUE COINCIDENCIA AL PRINCIPIO,
//            AL FINAL O EN CUAQUIER PARTE DE LA CADENA.
//    TIPO ENTERO, SI QUIERES QUE TRAIGA DATOS DE LA TABLA ORDERNADOS
//            DE FORMA ASCENDENTE O DESCENDENTE
    
    public static final int ORDER_ASC = 1;
    public static final int ORDER_DESC = 2;
    public static final int MATCH_START = 1;
    public static final int MATCH_END = 2;
    public static final int MATCH_ANYWHERE = 3;
    public static final int MATCH_EXACT = 4;
    public static final int ROW_SIZE_UNLIMITED = 0;
    public AbstractField abstractField1;
    public AbstractField abstractField2;
    public AbstractField abstractField3;
    
//    *VARIABLES UTILIZADAS PRINCIPALMENTE PARA BÚSQUEDA EN UN CATALOGO
//       BÁSICO CRUD
    protected String countColumnName;
    protected String orderColumnName;
    
//    ESTE EXAMPLE ES PARA el objeto Criteria, ES EL EXAMPLE PADRE
    protected Example example;
    
//    LISTA QUE LLEVA LO NECESARIO PARA BUSCAR EN UNA TABLA CON N RELACIONES
//    O ASOCIACIONES, SE NECECITA UNA ID DE CAMPO JAVA Y EL OBJETO EXAMPLE  
    protected Map<String,Example> mapExample;
    
    
    // ESTE OBJETO ES PARA HACER MERGE Y DELETE CUANDO HAS SELECCIONADO
//    UN REGISTRO DE UNA TABLA;
    protected Object objSelected;
    
    
//    * 
    public EntityExt(){}

    public String getCountColumnName() {
        return countColumnName;
    }

    public String getOrderColumnName() {
        return orderColumnName;
    }

   
    
//     *
    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }

    public Object getObjSelected() {
        return objSelected;
    }

    public void setObjSelected(Object objSelected) {
        this.objSelected = objSelected;
    }

    public Map<String, Example> getMapExample() {
        return mapExample;
    }

    public AbstractField getAbstractField1() {
        return abstractField1;
    }

    public void setAbstractField1(AbstractField abstractField1) {
        this.abstractField1 = abstractField1;
    }

    public AbstractField getAbstractField2() {
        return abstractField2;
    }

    public void setAbstractField2(AbstractField abstractField2) {
        this.abstractField2 = abstractField2;
    }

    public AbstractField getAbstractField3() {
        return abstractField3;
    }

    public void setAbstractField3(AbstractField abstractField3) {
        this.abstractField3 = abstractField3;
    }

    

   

    

   
   
//    CREA LOS OBJETOS Example utilizados PARA BÚSQUEDA
   public abstract void entity2Example();
//   ES PARA QUE FUNCIONE LA CLASE GENÉRICA DE BÚSQUEDAS LAZY
   public abstract int getObjId(Object obj);
//   RETORNA EL NOMBRE DE LA CLASE O ENTIDAD DE BD QUE SERÁ AFECTADA
//   POR UN MERGE
   public abstract String getExtClassName();
   
}
