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
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name = "cash_closing", indexes = {@Index(name = "IDX_idCashClosing",
                                       unique = true,
                                       columnList = "id_cash_closing" )})
@NamedNativeQueries({
                    @NamedNativeQuery(name = "CashClosing.findCashClosing", query = "select * from findCashClosing(?,?)", resultClass = CashClosing.class)
                   ,@NamedNativeQuery(name = "CashClosing.countCashClosing", query = "select * from countCashClosing(?,?)", resultSetMapping = "scalarCashClosing")

})
@SqlResultSetMapping(name = "scalarCashClosing", columns = @ColumnResult(name = "id_cash_closing"))
public class CashClosing implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_cash_closing")
    private Integer idCashClosing;
     @Basic(optional = false)
    @NotNull
    @Column(name = "cash_total")
    private BigDecimal cashTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "card_total")
    private BigDecimal cardTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dollar_total")
    private BigDecimal dollarTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "refund_total")
    private BigDecimal refundTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "withdraw_total")
    private BigDecimal withdrawTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dollar_count")
    private BigDecimal dollarCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cash_count")
    private BigDecimal cashCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grand_total")
    private BigDecimal grandTotal;
    @Basic(optional = true)
    @Column(name = "cash_difference")
    private BigDecimal difCash;
    @Basic(optional = true)
    @Column(name = "dollar_difference")
    private BigDecimal difDollar;
    @Basic(optional = true)
    @Column(name = "debit_total")
    private BigDecimal debitTotal;
    
    
    @Basic(optional = false)
    @Size(max = 45)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_mod_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModDate;
    @Basic(optional = true)
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = true)
    @Column(name = "count_rows")
    private Long countRows;
    
    public CashClosing() {
    }

    public CashClosing(Integer idCashClosing) {
        this.idCashClosing = idCashClosing;
    }

    public CashClosing(Integer idCashClosing, BigDecimal cashTotal, BigDecimal cardTotal, BigDecimal dollarTotal, BigDecimal refundTotal, BigDecimal withdrawTotal, BigDecimal dollarCount, BigDecimal cashCount, BigDecimal grandTotal, BigDecimal difCash, BigDecimal difDollar, BigDecimal debitTotal, String modifiedBy, Date lastModDate, Date creationDate, Long countRows) {
        this.idCashClosing = idCashClosing;
        this.cashTotal = cashTotal;
        this.cardTotal = cardTotal;
        this.dollarTotal = dollarTotal;
        this.refundTotal = refundTotal;
        this.withdrawTotal = withdrawTotal;
        this.dollarCount = dollarCount;
        this.cashCount = cashCount;
        this.grandTotal = grandTotal;
        this.difCash = difCash;
        this.difDollar = difDollar;
        this.debitTotal = debitTotal;
        this.modifiedBy = modifiedBy;
        this.lastModDate = lastModDate;
        this.creationDate = creationDate;
        this.countRows = countRows;
    }

    public CashClosing(BigDecimal cashTotal, BigDecimal cardTotal, BigDecimal dollarTotal, BigDecimal refundTotal, BigDecimal withdrawTotal, BigDecimal dollarCount, BigDecimal cashCount, BigDecimal grandTotal, BigDecimal difCash, BigDecimal difDollar, BigDecimal debitTotal, String modifiedBy, Date lastModDate, Date creationDate, Long countRows) {
        this.cashTotal = cashTotal;
        this.cardTotal = cardTotal;
        this.dollarTotal = dollarTotal;
        this.refundTotal = refundTotal;
        this.withdrawTotal = withdrawTotal;
        this.dollarCount = dollarCount;
        this.cashCount = cashCount;
        this.grandTotal = grandTotal;
        this.difCash = difCash;
        this.difDollar = difDollar;
        this.debitTotal = debitTotal;
        this.modifiedBy = modifiedBy;
        this.lastModDate = lastModDate;
        this.creationDate = creationDate;
        this.countRows = countRows;
    }

   
    public Integer getIdCashClosing() {
        return idCashClosing;
    }

    public void setIdCashClosing(Integer idCashClosing) {
        this.idCashClosing = idCashClosing;
    }

    public BigDecimal getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(BigDecimal cashTotal) {
        this.cashTotal = cashTotal;
    }

    public BigDecimal getDollarTotal() {
        return dollarTotal;
    }

    public void setDollarTotal(BigDecimal dollarTotal) {
        this.dollarTotal = dollarTotal;
    }

    public BigDecimal getWithdrawTotal() {
        return withdrawTotal;
    }

    public void setWithdrawTotal(BigDecimal withdrawTotal) {
        this.withdrawTotal = withdrawTotal;
    }

    public BigDecimal getDollarCount() {
        return dollarCount;
    }

    public void setDollarCount(BigDecimal dollarCount) {
        this.dollarCount = dollarCount;
    }

    public BigDecimal getCashCount() {
        return cashCount;
    }

    public void setCashCount(BigDecimal cashCount) {
        this.cashCount = cashCount;
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

    public BigDecimal getCardTotal() {
        return cardTotal;
    }

    public void setCardTotal(BigDecimal cardTotal) {
        this.cardTotal = cardTotal;
    }

    public BigDecimal getRefundTotal() {
        return refundTotal;
    }

    public void setRefundTotal(BigDecimal refundTotal) {
        this.refundTotal = refundTotal;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public BigDecimal getDifCash() {
        return difCash;
    }

    public void setDifCash(BigDecimal difCash) {
        this.difCash = difCash;
    }

    public BigDecimal getDifDollar() {
        return difDollar;
    }

    public void setDifDollar(BigDecimal difDollar) {
        this.difDollar = difDollar;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(BigDecimal debitTotal) {
        this.debitTotal = debitTotal;
    }

    public Long getCountRows() {
        return countRows;
    }

    public void setCountRows(Long countRows) {
        this.countRows = countRows;
    }

  
    
    
}
