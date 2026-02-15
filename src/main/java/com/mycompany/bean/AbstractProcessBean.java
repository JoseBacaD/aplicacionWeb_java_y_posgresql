/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.extended.EntityExt;



/**
 *
 * @author bacajos
 */
public abstract class AbstractProcessBean {
    
    protected EntityExt entityToProcess;
    protected String answerMessage;
    protected String strRedirect;
    protected boolean succeded;

    public EntityExt getEntityToProcess() {
        return entityToProcess;
    }

    public void setEntityToProcess(EntityExt entityToProcess) {
        this.entityToProcess = entityToProcess;
    }

   

    public String getAnswerMessage() {
        return answerMessage;
    }

    public void setAnswerMessage(String answerMessage) {
        this.answerMessage = answerMessage;
    }

    public String getStrRedirect() {
        return strRedirect;
    }

    public void setStrRedirect(String strRedirect) {
        this.strRedirect = strRedirect;
    }

    public boolean isSucceded() {
        return succeded;
    }

    public void setSucceded(boolean succeded) {
        this.succeded = succeded;
    }

   
    
    /**
     * PONER AQUÍ EL CÓDIGO DEL PROCESO PRINCIPAL QUE SE VA A
     * REALIZAR EN LA VENTANA CONTROLADA POR ESTE BEAN. 
     */
    public abstract void callMainProcess();
    
}
