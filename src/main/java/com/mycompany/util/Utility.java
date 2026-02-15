/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jerry
 */
public class Utility {
      
    
    
    public static Date getDate(){
//        DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
         return Calendar.getInstance().getTime();
         
    }
}
