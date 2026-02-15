/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author yo
 */

@Entity
@Table(name = "Provider", indexes = {@Index(name = "IDX_idProvider",
                                       unique = true,
                                       columnList = "id_provider" )})
@NamedNativeQueries({
                    @NamedNativeQuery(name = "Provider.findProvider", query = "select * from findProvider(?,?,?)", resultClass = Provider.class)
                   

})
@SqlResultSetMapping(name = "scalarProvider", columns = @ColumnResult(name = "id_provider"))

public class Provider implements Serializable {
    private static final long serialVersionUID = 1L;
    
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_provider")
    private Integer idProvider;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "name_provider")
    private String nameProvider;
    @Basic(optional = true)
    @Size(min = 1, max = 20)
    @Column(name = "provider_phone_number")
    private String providerPhoneNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "contact_info_provider")
    private String contactInfoProvider;
    @Basic(optional = false)
    @Size(max = 45)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_mod_date")
    @Temporal(TemporalType.DATE)
    private Date lastModDate;
    @OneToMany(mappedBy = "idProvider")
    private Set<Item> itemCollection;
    @Basic(optional = true)
    @Column(name = "count_rows")
    private Long countRows;

    public Provider() {
    }

    public Provider(Integer idProvider, String nameProvider, String providerPhoneNumber, String contactInfoProvider, String modifiedBy, Date lastModDate, Set<Item> itemCollection, Long countRows) {
        this.idProvider = idProvider;
        this.nameProvider = nameProvider;
        this.providerPhoneNumber = providerPhoneNumber;
        this.contactInfoProvider = contactInfoProvider;
        this.modifiedBy = modifiedBy;
        this.lastModDate = lastModDate;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
    }

    public Provider(String nameProvider, String providerPhoneNumber, String contactInfoProvider, String modifiedBy, Date lastModDate, Set<Item> itemCollection, Long countRows) {
        this.nameProvider = nameProvider;
        this.providerPhoneNumber = providerPhoneNumber;
        this.contactInfoProvider = contactInfoProvider;
        this.modifiedBy = modifiedBy;
        this.lastModDate = lastModDate;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
    }
   
    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Integer idProvider) {
        this.idProvider = idProvider;
    }

    public String getNameProvider() {
        return nameProvider;
    }

    public void setNameProvider(String nameProvider) {
        this.nameProvider = nameProvider;
    }

    public String getContactInfoProvider() {
        return contactInfoProvider;
    }

    public void setContactInfoProvider(String contactInfoProvider) {
        this.contactInfoProvider = contactInfoProvider;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    public Set<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Set<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public String getProviderPhoneNumber() {
        return providerPhoneNumber;
    }

    public void setProviderPhoneNumber(String providerPhoneNumber) {
        this.providerPhoneNumber = providerPhoneNumber;
    }
    
     @Override
    public String toString() {
        return "" + nameProvider ;
    }

    public Long getCountRows() {
        return countRows;
    }

    public void setCountRows(Long countRows) {
        this.countRows = countRows;
    }

   
}
