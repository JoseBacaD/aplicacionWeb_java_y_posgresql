/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;


import com.mycompany.extended.EntityExt;
import java.util.List;

/**
 *
 * @author bacajos
 */
public interface DAOInterface {
    
    public Object readReturnUnique(EntityExt entExt );
    public List<Object> readReturnMany(EntityExt entExt);
}
