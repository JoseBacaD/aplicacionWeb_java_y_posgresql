/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author bacajos
 */
@Entity
@Table(name = "sale_header", indexes = {@Index(name = "IDX_folio_idSaleHeader",
                                       unique = true,
                                       columnList = "id_sale_header,folio")})


@NamedNativeQueries({
                    @NamedNativeQuery(name = "SaleHeader.findSaleHeader", query = "select * from findSaleHeader(?,?,?,?,?)", resultClass = SaleHeader.class)
                   ,@NamedNativeQuery(name = "SaleHeader.countSaleHeader", query = "select * from countSaleHeader(?,?,?,?,?)", resultSetMapping = "scalarSaleHeader")
                   ,@NamedNativeQuery(name = "SaleHeader.findTotalSale", query = "select * from findTotalSale(?,?,?,?)", resultSetMapping = "SaleHeaderTotalMapping")
                   ,@NamedNativeQuery(name = "SaleHeader.findTotalInvoiced", query = "select * from findTotalInvoiced(?,?,?,?)", resultSetMapping = "invoicedTotalMapping")

})
//@SqlResultSetMapping(name = "scalarSaleHeader", columns = @ColumnResult(name = "id_sale_header"))

@SqlResultSetMappings({
                       @SqlResultSetMapping(name="SaleHeaderTotalMapping",
                              
                               columns = {
                                   @ColumnResult(name = "tot_cash")
                                  ,@ColumnResult(name = "tot_card")
                                  ,@ColumnResult(name = "tot_other")
                                  ,@ColumnResult(name = "grand_total")
                                  ,@ColumnResult(name = "tot_withdraw")
                                  ,@ColumnResult(name = "tot_refund")
                                  ,@ColumnResult(name = "tot_debit")
                               })
                       ,@SqlResultSetMapping(name = "scalarSaleHeader", columns = @ColumnResult(name = "id_sale_header"))
                       ,@SqlResultSetMapping(name="invoicedTotalMapping",columns = {@ColumnResult(name = "tot_invoiced")})

})



public class SaleHeader implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_sale_header")
    private Integer idSaleHeader;
    
    @Column(name = "folio")
    @NotNull
    @Basic(optional = false)
    private Long folio;
    
    @Column(name = "consecutive")
    @NotNull
    @Basic(optional = false)
    private Integer consecutive;
     
    @Basic(optional = false)
    @NotNull
    @Column(name = "sale_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "sale_rate")
    private BigDecimal rateSale;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_sale")
    private BigDecimal totalSale;
    
      @Basic(optional = false)
    @NotNull
    @Column(name = "cash_payment")
    private BigDecimal cashPayment;
      
      @Basic(optional = false)
    @NotNull
    @Column(name = "card_payment")
    private BigDecimal cardPayment;
      
      @Basic(optional = true)
    
    @Column(name = "debit_payment")
    private BigDecimal debitPayment;
      
      @Basic(optional = false)
    @NotNull
    @Column(name = "other_payment")
    private BigDecimal otherPayment;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tax_total")
    private BigDecimal taxTotal;
    
    @OneToMany(mappedBy = "folio", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<SaleDetail> folioCollection;
    
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
    @Column(name = "modified_by")
    private String modifiedBy;
    
    
    @Transient
    @Column(name = "tot_cash")
    private BigDecimal totCash;
    @Transient
    @Column(name = "tot_card")
    private BigDecimal totCard;
    @Transient
    @Column(name = "tot_other")
    private BigDecimal totOther;
    @Transient
    @Column(name = "grand_total")
    private BigDecimal grandTotal;
    @Transient
    @Column(name = "tot_refund")
    private BigDecimal totRefund;
    @Transient
    @Column(name = "tot_withdraw")
    private BigDecimal totWithdraw;
    @Transient
    @Column(name = "tot_invoiced")
    private BigDecimal totInvoiced;
    @Transient
    @Column(name = "payment_type")
    private BigDecimal paymentType;
    @Transient
    @Column(name = "tot_debit")
    private BigDecimal totDebit;
    
    @Basic(optional = false)
    @Column(name = "is_withdraw")
    private Boolean isWithdraw;
    @Basic(optional = false)
    @Column(name = "is_refund")
    private Boolean isrefund;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cash_withdraw")
    private BigDecimal cashWithdraw;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cash_refund")
    private BigDecimal cashRefund;
    @Basic(optional = true)
    @Size(min = 1, max = 100)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = true)
    @Column(name = "has_isr_invoice")
    private Boolean hasISRInvoice;
    @Basic(optional = true)
    @Column(name = "count_rows")
    private Long countRows;

    public SaleHeader() {
    }

    public SaleHeader(Integer idSaleHeader, Long folio, Integer consecutive, Date saleDate, Date creationDate, BigDecimal rateSale, BigDecimal totalSale, BigDecimal cashPayment, BigDecimal cardPayment, BigDecimal debitPayment, BigDecimal otherPayment, BigDecimal taxTotal, List<SaleDetail> folioCollection, Integer idDivition, Integer idSubdivition, String modifiedBy, BigDecimal totCash, BigDecimal totCard, BigDecimal totOther, BigDecimal grandTotal, BigDecimal totRefund, BigDecimal totWithdraw, BigDecimal totInvoiced, BigDecimal paymentType, BigDecimal totDebit, Boolean isWithdraw, Boolean isrefund, BigDecimal cashWithdraw, BigDecimal cashRefund, String comment, Boolean hasISRInvoice, Long countRows) {
        this.idSaleHeader = idSaleHeader;
        this.folio = folio;
        this.consecutive = consecutive;
        this.saleDate = saleDate;
        this.creationDate = creationDate;
        this.rateSale = rateSale;
        this.totalSale = totalSale;
        this.cashPayment = cashPayment;
        this.cardPayment = cardPayment;
        this.debitPayment = debitPayment;
        this.otherPayment = otherPayment;
        this.taxTotal = taxTotal;
        this.folioCollection = folioCollection;
        this.idDivition = idDivition;
        this.idSubdivition = idSubdivition;
        this.modifiedBy = modifiedBy;
        this.totCash = totCash;
        this.totCard = totCard;
        this.totOther = totOther;
        this.grandTotal = grandTotal;
        this.totRefund = totRefund;
        this.totWithdraw = totWithdraw;
        this.totInvoiced = totInvoiced;
        this.paymentType = paymentType;
        this.totDebit = totDebit;
        this.isWithdraw = isWithdraw;
        this.isrefund = isrefund;
        this.cashWithdraw = cashWithdraw;
        this.cashRefund = cashRefund;
        this.comment = comment;
        this.hasISRInvoice = hasISRInvoice;
        this.countRows = countRows;
    }

    public SaleHeader(Long folio, Integer consecutive, Date saleDate, Date creationDate, BigDecimal rateSale, BigDecimal totalSale, BigDecimal cashPayment, BigDecimal cardPayment, BigDecimal debitPayment, BigDecimal otherPayment, BigDecimal taxTotal, List<SaleDetail> folioCollection, Integer idDivition, Integer idSubdivition, String modifiedBy, BigDecimal totCash, BigDecimal totCard, BigDecimal totOther, BigDecimal grandTotal, BigDecimal totRefund, BigDecimal totWithdraw, BigDecimal totInvoiced, BigDecimal paymentType, BigDecimal totDebit, Boolean isWithdraw, Boolean isrefund, BigDecimal cashWithdraw, BigDecimal cashRefund, String comment, Boolean hasISRInvoice, Long countRows) {
        this.folio = folio;
        this.consecutive = consecutive;
        this.saleDate = saleDate;
        this.creationDate = creationDate;
        this.rateSale = rateSale;
        this.totalSale = totalSale;
        this.cashPayment = cashPayment;
        this.cardPayment = cardPayment;
        this.debitPayment = debitPayment;
        this.otherPayment = otherPayment;
        this.taxTotal = taxTotal;
        this.folioCollection = folioCollection;
        this.idDivition = idDivition;
        this.idSubdivition = idSubdivition;
        this.modifiedBy = modifiedBy;
        this.totCash = totCash;
        this.totCard = totCard;
        this.totOther = totOther;
        this.grandTotal = grandTotal;
        this.totRefund = totRefund;
        this.totWithdraw = totWithdraw;
        this.totInvoiced = totInvoiced;
        this.paymentType = paymentType;
        this.totDebit = totDebit;
        this.isWithdraw = isWithdraw;
        this.isrefund = isrefund;
        this.cashWithdraw = cashWithdraw;
        this.cashRefund = cashRefund;
        this.comment = comment;
        this.hasISRInvoice = hasISRInvoice;
        this.countRows = countRows;
    }

   

    public Integer getIdSaleHeader() {
        return idSaleHeader;
    }

    public void setIdSaleHeader(Integer idSaleHeader) {
        this.idSaleHeader = idSaleHeader;
    }

    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getRateSale() {
        return rateSale;
    }

    public void setRateSale(BigDecimal rateSale) {
        this.rateSale = rateSale;
    }

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(BigDecimal totalSale) {
        this.totalSale = totalSale;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public Integer getConsecutive() {
        return consecutive;
    }

    public void setConsecutive(Integer consecutive) {
        this.consecutive = consecutive;
    }

    public List<SaleDetail> getFolioCollection() {
        return folioCollection;
    }

    public void setFolioCollection(List<SaleDetail> folioCollection) {
        this.folioCollection = folioCollection;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(BigDecimal cashPayment) {
        this.cashPayment = cashPayment;
    }

    public BigDecimal getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(BigDecimal cardPayment) {
        this.cardPayment = cardPayment;
    }

    public BigDecimal getOtherPayment() {
        return otherPayment;
    }

    public void setOtherPayment(BigDecimal otherPayment) {
        this.otherPayment = otherPayment;
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
    
    

    @Override
    public String toString() {
        return folio.toString();
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public BigDecimal getTotCash() {
        return totCash;
    }

    public void setTotCash(BigDecimal totCash) {
        this.totCash = totCash;
    }

    public BigDecimal getTotCard() {
        return totCard;
    }

    public void setTotCard(BigDecimal totCard) {
        this.totCard = totCard;
    }

    public BigDecimal getTotOther() {
        return totOther;
    }

    public void setTotOther(BigDecimal totOther) {
        this.totOther = totOther;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Boolean getIsWithdraw() {
        return isWithdraw;
    }

    public void setIsWithdraw(Boolean isWithdraw) {
        this.isWithdraw = isWithdraw;
    }

    public BigDecimal getCashWithdraw() {
        return cashWithdraw;
    }

    public void setCashWithdraw(BigDecimal cashWithdraw) {
        this.cashWithdraw = cashWithdraw;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getTotRefund() {
        return totRefund;
    }

    public void setTotRefund(BigDecimal totRefund) {
        this.totRefund = totRefund;
    }

    public BigDecimal getTotWithdraw() {
        return totWithdraw;
    }

    public void setTotWithdraw(BigDecimal totWithdraw) {
        this.totWithdraw = totWithdraw;
    }

    public BigDecimal getCashRefund() {
        return cashRefund;
    }

    public void setCashRefund(BigDecimal cashRefund) {
        this.cashRefund = cashRefund;
    }

    public Boolean getIsrefund() {
        return isrefund;
    }

    public void setIsrefund(Boolean isrefund) {
        this.isrefund = isrefund;
    }

    public Boolean getHasISRInvoice() {
        return hasISRInvoice;
    }

    public void setHasISRInvoice(Boolean hasISRInvoice) {
        this.hasISRInvoice = hasISRInvoice;
    }

    public BigDecimal getTotInvoiced() {
        return totInvoiced;
    }

    public void setTotInvoiced(BigDecimal totInvoiced) {
        this.totInvoiced = totInvoiced;
    }

    public BigDecimal getDebitPayment() {
        return debitPayment;
    }

    public void setDebitPayment(BigDecimal debitPayment) {
        this.debitPayment = debitPayment;
    }

    public BigDecimal getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(BigDecimal paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getTotDebit() {
        return totDebit;
    }

    public void setTotDebit(BigDecimal totDebit) {
        this.totDebit = totDebit;
    }

    public Long getCountRows() {
        return countRows;
    }

    public void setCountRows(Long countRows) {
        this.countRows = countRows;
    }

}
