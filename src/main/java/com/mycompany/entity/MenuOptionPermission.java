/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jerry
 */
@Entity
@Table(name = "menu_option_permission")

public class MenuOptionPermission implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_menu_option_permission")
    private Integer idMenuOptionPermission;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_mod_date")
    @Temporal(TemporalType.DATE)
    private Date lastModDate;
    @Basic(optional = false)
    @Column(name = "can_delete_data")
    private Boolean canDeleteData;
    @Basic(optional = false)
    @Column(name = "can_edit_data")
    private Boolean canEditData;
    @Basic(optional = true)
    @Column(name = "can_read_data")
    private Boolean canReadData;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Basic(optional = false)
    @JoinColumn(name = "id_usr_app", referencedColumnName = "id_usr_app", foreignKey = @ForeignKey(name = "fk_MenuOptionPerm_UserApp"))
    @ManyToOne
    @NotNull
    private UserApp idUserApp;
    @Basic(optional = false)
    @JoinColumn(name = "id_menu_option", referencedColumnName = "id_menu_option", foreignKey = @ForeignKey(name = "fk_MenuOptionPerm_MenuOption"))
    @ManyToOne
    @NotNull
    private MenuOption idMenuOption;

    public MenuOptionPermission() {
    }

    public MenuOptionPermission(Integer idMenuOptionPermission, Date lastModDate, Boolean canDeleteData, Boolean canEditData, Boolean canReadData, String modifiedBy, UserApp idUserApp, MenuOption idMenuOption) {
        this.idMenuOptionPermission = idMenuOptionPermission;
        this.lastModDate = lastModDate;
        this.canDeleteData = canDeleteData;
        this.canEditData = canEditData;
        this.canReadData = canReadData;
        this.modifiedBy = modifiedBy;
        this.idUserApp = idUserApp;
        this.idMenuOption = idMenuOption;
    }

    public MenuOptionPermission(Date lastModDate, Boolean canDeleteData, Boolean canEditData, Boolean canReadData, String modifiedBy, UserApp idUserApp, MenuOption idMenuOption) {
        this.lastModDate = lastModDate;
        this.canDeleteData = canDeleteData;
        this.canEditData = canEditData;
        this.canReadData = canReadData;
        this.modifiedBy = modifiedBy;
        this.idUserApp = idUserApp;
        this.idMenuOption = idMenuOption;
    }

    public Integer getIdMenuOptionPermission() {
        return idMenuOptionPermission;
    }

    public void setIdMenuOptionPermission(Integer idMenuOptionPermission) {
        this.idMenuOptionPermission = idMenuOptionPermission;
    }

    public Date getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    public Boolean getCanDeleteData() {
        return canDeleteData;
    }

    public void setCanDeleteData(Boolean canDeleteData) {
        this.canDeleteData = canDeleteData;
    }

    public Boolean getCanEditData() {
        return canEditData;
    }

    public void setCanEditData(Boolean canEditData) {
        this.canEditData = canEditData;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public UserApp getIdUserApp() {
        return idUserApp;
    }

    public void setIdUserApp(UserApp idUserApp) {
        this.idUserApp = idUserApp;
    }

    public MenuOption getIdMenuOption() {
        return idMenuOption;
    }

    public void setIdMenuOption(MenuOption idMenuOption) {
        this.idMenuOption = idMenuOption;
    }

    public Boolean getCanReadData() {
        return canReadData;
    }

    public void setCanReadData(Boolean canReadData) {
        this.canReadData = canReadData;
    }
    
}
