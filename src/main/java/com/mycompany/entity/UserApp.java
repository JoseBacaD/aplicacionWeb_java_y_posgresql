/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author jerry
 */
@Entity
@Table(name = "user_app",indexes = {@Index(name = "IDX_idUserApp_alias",
                                       unique = true,
                                       columnList = "user_alias" )})

        
        
@NamedNativeQueries({
                    @NamedNativeQuery(name = "UserApp.findUsrByAlias", query = "select * from findUsrByAlias(?)", resultClass = UserApp.class)
                   
})

public class UserApp implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usr_app")
    private Integer idusrApp;
    @Basic(optional = false)
    @Column(name = "user_alias")
    private String userAlias;
    @Basic(optional = false)
    @Column(name = "user_password")
    private String user_password;
     @Basic(optional = false)
    @Column(name = "is_active_bit")
    private boolean isActiveBit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_mod_date")
    @Temporal(TemporalType.DATE)
    private Date lastModDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "modified_by")
    private String modifiedBy;
    @OneToMany(mappedBy = "idUserApp", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<MenuOptionPermission> ListMenuOptionPermission;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_divition")
    private Integer idDivition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_subdivition")
    private Integer idSubdivition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "user_name")
    private String userName;

    public UserApp() {
    }

    public UserApp(Integer idusrApp, String userAlias, String user_password, boolean isActiveBit, Date lastModDate, String modifiedBy, List<MenuOptionPermission> ListMenuOptionPermission, Integer idDivition, Integer idSubdivition, String userName) {
        this.idusrApp = idusrApp;
        this.userAlias = userAlias;
        this.user_password = user_password;
        this.isActiveBit = isActiveBit;
        this.lastModDate = lastModDate;
        this.modifiedBy = modifiedBy;
        this.ListMenuOptionPermission = ListMenuOptionPermission;
        this.idDivition = idDivition;
        this.idSubdivition = idSubdivition;
        this.userName = userName;
    }

    public UserApp(String userAlias, String user_password, boolean isActiveBit, Date lastModDate, String modifiedBy, List<MenuOptionPermission> ListMenuOptionPermission, Integer idDivition, Integer idSubdivition, String userName) {
        this.userAlias = userAlias;
        this.user_password = user_password;
        this.isActiveBit = isActiveBit;
        this.lastModDate = lastModDate;
        this.modifiedBy = modifiedBy;
        this.ListMenuOptionPermission = ListMenuOptionPermission;
        this.idDivition = idDivition;
        this.idSubdivition = idSubdivition;
        this.userName = userName;
    }

    
    public Integer getIdusrApp() {
        return idusrApp;
    }

    public void setIdusrApp(Integer idusrApp) {
        this.idusrApp = idusrApp;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public boolean isIsActiveBit() {
        return isActiveBit;
    }

    public void setIsActiveBit(boolean isActiveBit) {
        this.isActiveBit = isActiveBit;
    }

    public List<MenuOptionPermission> getListMenuOptionPermission() {
        return ListMenuOptionPermission;
    }

    public void setListMenuOptionPermission(List<MenuOptionPermission> ListMenuOptionPermission) {
        this.ListMenuOptionPermission = ListMenuOptionPermission;
    }

    public Date getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getIdDivition() {
        return idDivition;
    }

    public void setIdDivition(Integer idDivition) {
        this.idDivition = idDivition;
    }

    public Integer getIdSubdivition() {
        return idSubdivition;
    }

    public void setIdSubdivition(Integer idSubdivition) {
        this.idSubdivition = idSubdivition;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}

