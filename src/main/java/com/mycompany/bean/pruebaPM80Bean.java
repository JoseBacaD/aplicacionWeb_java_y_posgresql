/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jerry
 */
@ManagedBean
@ViewScoped
public class pruebaPM80Bean implements Serializable{
    static final long serialVersionUID = 1L; 
    
    public void callPrintTicket(){
       try{
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("companyName", "negocio N");
           System.out.println("entre al bean");
        File jasperFile = new File(FacesContext.
                                   getCurrentInstance().
                                   getExternalContext().
                                   getRealPath("/ticket-prueba.jasper"));
        JasperPrint jasperPrint = JasperFillManager.
                                  fillReport(jasperFile.getPath(),
                                            param);
        
        byte[] bytes = JasperRunManager.runReportToPdf(jasperFile.getPath(), param);
            HttpServletResponse response = (HttpServletResponse)FacesContext.
                                            getCurrentInstance().
                                            getExternalContext().
                                            getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
            
            FacesContext.getCurrentInstance().responseComplete();
            PrimeFaces.current().executeScript("$('#CRUDModal').modal('close');");
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("companyName", "MitoCode");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report1.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath() ,null);
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		ServletOutputStream stream = response.getOutputStream();
		
		      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
    
}
