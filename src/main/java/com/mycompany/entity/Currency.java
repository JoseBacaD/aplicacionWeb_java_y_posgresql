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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author bacajos
 */
@Entity
@Table(name = "currency", indexes = {@Index(name = "IDX_currencyCode",
                                       unique = true,
                                       columnList = "currency_code" )})
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_currency")
    private Integer idCurrency;
    @Column(name = "currency_code")
    @Basic(optional = false)
    @NotNull
    private Integer currencyCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "desc_currency")
    private String descCurrency;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "iso_code_currency")
    private String isoCodeCurrency;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
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
    @OneToMany(mappedBy = "idCurrency")
    private Set<Item> itemCollection;
    
    public Currency() {
    }

    public Currency(Integer idCurrency) {
        this.idCurrency = idCurrency;
    }

    public Currency(Integer idCurrency, Integer currencyCode, String descCurrency, String isoCodeCurrency, Date creationDate, Date lastModDate, String modifiedBy) {
        this.idCurrency = idCurrency;
        this.currencyCode = currencyCode;
        this.descCurrency = descCurrency;
        this.isoCodeCurrency = isoCodeCurrency;
        this.creationDate = creationDate;
        this.lastModDate = lastModDate;
        this.modifiedBy = modifiedBy;
    }

   

    public Integer getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(Integer idCurrency) {
        this.idCurrency = idCurrency;
    }

    public Integer getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescCurrency() {
        return descCurrency;
    }

    public void setDescCurrency(String descCurrency) {
        this.descCurrency = descCurrency;
    }

    public String getIsoCodeCurrency() {
        return isoCodeCurrency;
    }

    public void setIsoCodeCurrency(String isoCodeCurrency) {
        this.isoCodeCurrency = isoCodeCurrency;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Set<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Set<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }
    
    @Override
    public String toString() {
        return currencyCode +" "+ descCurrency +" "+ isoCodeCurrency;
    }
    
}
