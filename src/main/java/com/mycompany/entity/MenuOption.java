

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jerry
 */
@Entity
@Table(name = "menu_option", indexes = {@Index(name = "IDX__idMenuOption_menuOptionCode",
                                       unique = true,
                                       columnList = "id_menu_option,menu_option_code" )})



public class MenuOption implements Serializable {
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_menu_option")
    private Integer idMenuOption;
    @Basic(optional = false)
    @Column(name = "menu_option_code")
    private Integer menuOptionCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "menu_option_name")
    private String menuOptionName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "menu_option_url")
    private String menuOptionURL;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "menu_option_icon")
    private String menuOptionIcon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Basic(optional = true)
    @Column(name = "id_parent_menu")
    private Integer idParentMenu;

    public MenuOption() {
    }

    public MenuOption(Integer idMenuOption, Integer menuOptionCode, String menuOptionName, String menuOptionURL, String menuOptionIcon, String modifiedBy, Integer idParentMenu) {
        this.idMenuOption = idMenuOption;
        this.menuOptionCode = menuOptionCode;
        this.menuOptionName = menuOptionName;
        this.menuOptionURL = menuOptionURL;
        this.menuOptionIcon = menuOptionIcon;
        this.modifiedBy = modifiedBy;
        this.idParentMenu = idParentMenu;
    }

    public MenuOption(Integer menuOptionCode, String menuOptionName, String menuOptionURL, String menuOptionIcon, String modifiedBy, Integer idParentMenu) {
        this.menuOptionCode = menuOptionCode;
        this.menuOptionName = menuOptionName;
        this.menuOptionURL = menuOptionURL;
        this.menuOptionIcon = menuOptionIcon;
        this.modifiedBy = modifiedBy;
        this.idParentMenu = idParentMenu;
    }

   
    public Integer getIdMenuOption() {
        return idMenuOption;
    }

    public void setIdMenuOption(Integer idMenuOption) {
        this.idMenuOption = idMenuOption;
    }

    public Integer getMenuOptionCode() {
        return menuOptionCode;
    }

    public void setMenuOptionCode(Integer menuOptionCode) {
        this.menuOptionCode = menuOptionCode;
    }

    public String getMenuOptionName() {
        return menuOptionName;
    }

    public void setMenuOptionName(String menuOptionName) {
        this.menuOptionName = menuOptionName;
    }

    public String getMenuOptionURL() {
        return menuOptionURL;
    }

    public void setMenuOptionURL(String menuOptionURL) {
        this.menuOptionURL = menuOptionURL;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getMenuOptionIcon() {
        return menuOptionIcon;
    }

    public void setMenuOptionIcon(String menuOptionIcon) {
        this.menuOptionIcon = menuOptionIcon;
    }

    public Integer getIdParentMenu() {
        return idParentMenu;
    }

    public void setIdParentMenu(Integer idParentMenu) {
        this.idParentMenu = idParentMenu;
    }

   
    
}
