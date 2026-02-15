/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.controller.FirstConnectionController;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author jerry
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class FirstConectionBean implements Serializable{
    static final long serialVersionUID = 1L;
    
    
    @PostConstruct
    public void init(){
        System.out.println("!!!!INICIANDO TOMCAT!!!!!!!!!");
        FirstConnectionController fsc = new FirstConnectionController();
        fsc.callRead();
    }
    
}
