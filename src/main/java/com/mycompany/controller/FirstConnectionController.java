/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.FrecuentSaleItem;
import java.io.Serializable;

/**
 *
 * @author jerry
 */
public class FirstConnectionController implements Serializable {
    static final long serialVersionUID = 1L;
    
    
    public void callRead(){
        //llamada a BD para corregir error en instalaciones locales
        //ya que si no se hace una conexion en cuanto se inicia tomcat
        //la conexión falla hasta que se haga un reinicio manual de tomcat
//        FrecuentSaleItem fsi = new FrecuentSaleItem();
//        BasicDAO.basicRead(fsi, 0);
//        System.out.println("Primera llamada a Base de datos");
    }
}
