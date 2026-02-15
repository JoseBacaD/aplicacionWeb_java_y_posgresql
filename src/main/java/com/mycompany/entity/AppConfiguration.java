/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
@Table(name = "app_config", indexes = {@Index(name = "IDX_idDivition_idSubdivition",
                                       unique = true,
                                       columnList = "id_divition,id_subdivition" )})

@NamedNativeQueries({
                    @NamedNativeQuery(name = "appConfig.findAppConfig", query = "select * from findAppConfig(?,?)", resultClass = AppConfiguration.class)
                   
})

public class AppConfiguration implements Serializable {
      private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_config")
    private Integer idConfig;
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
    @Column(name = "tax")
    private BigDecimal tax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sale_rate")
    private BigDecimal rateSale;
    @Basic(optional = false)
    @Column(name = "print_ticket")
    private Boolean printTicket;
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
    @Basic(optional = true)
    @Size(min = 1, max = 100)
    @Column(name = "varchar_wildcard")
    private String strWildCard;
    @Basic(optional = true)
    @Column(name = "int_wildcard")
    private Integer intWildCard;
    @Basic(optional = true)
    @Column(name = "boolean_wildcard")
    private Boolean boolWildCard;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "currency_code")
    @Basic(optional = false)
    @NotNull
    private Integer currencyCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "store_address")
    private String storeAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "store_phone")
    private String storePhone;
    @Column(name = "print_copies")
    @Basic(optional = false)
    @NotNull
    private Integer printCopies;
    @Basic(optional = true)
    @Size(min = 1, max = 50)
    @Column(name = "date_format")
    private String dateFormat;
    @Basic(optional = true)
    @Column(name = "refund_percentage")
    private BigDecimal refundPercentage;
    @Column(name = "print_label_adjustment")
    @Basic(optional = true)
    private Integer printLabelAdjustment;
    @Column(name = "gap_label_adjustment")
    @Basic(optional = true)
    private Integer gapLabelAdjustment;
    
    public AppConfiguration() {
    }

    public AppConfiguration(Integer idConfig) {
        this.idConfig = idConfig;
    }

    public AppConfiguration(Integer idConfig, Integer idDivition, Integer idSubdivition, BigDecimal tax, BigDecimal rateSale, Boolean printTicket, Date lastModDate, String modifiedBy, String strWildCard, Integer intWildCard, Boolean boolWildCard, String companyName, Integer currencyCode, String storeAddress, String storePhone, Integer printCopies, String dateFormat, BigDecimal refundPercentage, Integer printLabelAdjustment, Integer gapLabelAdjustment) {
        this.idConfig = idConfig;
        this.idDivition = idDivition;
        this.idSubdivition = idSubdivition;
        this.tax = tax;
        this.rateSale = rateSale;
        this.printTicket = printTicket;
        this.lastModDate = lastModDate;
        this.modifiedBy = modifiedBy;
        this.strWildCard = strWildCard;
        this.intWildCard = intWildCard;
        this.boolWildCard = boolWildCard;
        this.companyName = companyName;
        this.currencyCode = currencyCode;
        this.storeAddress = storeAddress;
        this.storePhone = storePhone;
        this.printCopies = printCopies;
        this.dateFormat = dateFormat;
        this.refundPercentage = refundPercentage;
        this.printLabelAdjustment = printLabelAdjustment;
        this.gapLabelAdjustment = gapLabelAdjustment;
    }

    public AppConfiguration(Integer idDivition, Integer idSubdivition, BigDecimal tax, BigDecimal rateSale, Boolean printTicket, Date lastModDate, String modifiedBy, String strWildCard, Integer intWildCard, Boolean boolWildCard, String companyName, Integer currencyCode, String storeAddress, String storePhone, Integer printCopies, String dateFormat, BigDecimal refundPercentage, Integer printLabelAdjustment, Integer gapLabelAdjustment) {
        this.idDivition = idDivition;
        this.idSubdivition = idSubdivition;
        this.tax = tax;
        this.rateSale = rateSale;
        this.printTicket = printTicket;
        this.lastModDate = lastModDate;
        this.modifiedBy = modifiedBy;
        this.strWildCard = strWildCard;
        this.intWildCard = intWildCard;
        this.boolWildCard = boolWildCard;
        this.companyName = companyName;
        this.currencyCode = currencyCode;
        this.storeAddress = storeAddress;
        this.storePhone = storePhone;
        this.printCopies = printCopies;
        this.dateFormat = dateFormat;
        this.refundPercentage = refundPercentage;
        this.printLabelAdjustment = printLabelAdjustment;
        this.gapLabelAdjustment = gapLabelAdjustment;
    }
    
    public Integer getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Integer idConfig) {
        this.idConfig = idConfig;
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

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getRateSale() {
        return rateSale;
    }

    public void setRateSale(BigDecimal rateSale) {
        this.rateSale = rateSale;
    }

    public Boolean getPrintTicket() {
        return printTicket;
    }

    public void setPrintTicket(Boolean printTicket) {
        this.printTicket = printTicket;
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

    public String getStrWildCard() {
        return strWildCard;
    }

    public void setStrWildCard(String strWildCard) {
        this.strWildCard = strWildCard;
    }

    public Integer getIntWildCard() {
        return intWildCard;
    }

    public void setIntWildCard(Integer intWildCard) {
        this.intWildCard = intWildCard;
    }

    public Boolean getBoolWildCard() {
        return boolWildCard;
    }

    public void setBoolWildCard(Boolean boolWildCard) {
        this.boolWildCard = boolWildCard;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public Integer getPrintCopies() {
        return printCopies;
    }

    public void setPrintCopies(Integer printCopies) {
        this.printCopies = printCopies;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public BigDecimal getRefundPercentage() {
        return refundPercentage;
    }

    public void setRefundPercentage(BigDecimal refundPercentage) {
        this.refundPercentage = refundPercentage;
    }

    public Integer getPrintLabelAdjustment() {
        return printLabelAdjustment;
    }

    public void setPrintLabelAdjustment(Integer printLabelAdjustment) {
        this.printLabelAdjustment = printLabelAdjustment;
    }

    public Integer getGapLabelAdjustment() {
        return gapLabelAdjustment;
    }

    public void setGapLabelAdjustment(Integer gapLabelAdjustment) {
        this.gapLabelAdjustment = gapLabelAdjustment;
    }
    
    
    
}
