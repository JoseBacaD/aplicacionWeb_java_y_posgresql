/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author bacajos
 */

@Entity
@Table(name = "sale_detail", indexes = {@Index(name = "IDX_folio_idItem",
                                       unique = true,
                                       columnList = "id_detail" ),
                                       @Index(name = "idx_creation_date",
                                               unique = false,
                                               columnList = "creation_date DESC")})
@NamedNativeQueries({
                    @NamedNativeQuery(name = "SaleDetail.findSaleDetail", query = "select * from findSaleDetail(?,?,?,?)", resultClass = SaleDetail.class)
                   ,@NamedNativeQuery(name = "SaleDetail.countSaleDetail", query = "select * from countSaleDetail(?,?,?,?)", resultSetMapping = "scalar")
                   ,@NamedNativeQuery(name = "SaleDetail.findSaleDetailByFolio", query = "select * from findSaleDetailByFolio(?,?,?)", resultClass = SaleDetail.class)

})
@SqlResultSetMapping(name = "scalar", columns = @ColumnResult(name = "totalCount"))

public class SaleDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_detail")
    private Integer idDetail;
        
    
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="folio",referencedColumnName = "folio", foreignKey = @ForeignKey(name = "fk_saleHeader_saleDetail"))
    private SaleHeader folio;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="standard_code_item",referencedColumnName = "standard_code_item", foreignKey = @ForeignKey(name = "fk_saleItem_item"))
    private Item standardCodeItem;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity_item")
    private BigDecimal quantity;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_detail")
    private BigDecimal priceDetail;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "sale_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_tax")
    private BigDecimal itemTax;
    
    @Basic(optional = true)
    @Column(name = "count_rows")
    private Long countRows;

    public SaleDetail() {
    }

    public SaleDetail(Integer idDetail, SaleHeader folio, Item standardCodeItem, BigDecimal quantity, BigDecimal priceDetail, Date creationDate, Date saleDate, BigDecimal itemTax, Long countRows) {
        this.idDetail = idDetail;
        this.folio = folio;
        this.standardCodeItem = standardCodeItem;
        this.quantity = quantity;
        this.priceDetail = priceDetail;
        this.creationDate = creationDate;
        this.saleDate = saleDate;
        this.itemTax = itemTax;
        this.countRows = countRows;
    }

    public SaleDetail(SaleHeader folio, Item standardCodeItem, BigDecimal quantity, BigDecimal priceDetail, Date creationDate, Date saleDate, BigDecimal itemTax, Long countRows) {
        this.folio = folio;
        this.standardCodeItem = standardCodeItem;
        this.quantity = quantity;
        this.priceDetail = priceDetail;
        this.creationDate = creationDate;
        this.saleDate = saleDate;
        this.itemTax = itemTax;
        this.countRows = countRows;
    }
    

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public SaleHeader getFolio() {
        return folio;
    }

    public void setFolio(SaleHeader folio) {
        this.folio = folio;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

   
   
    public BigDecimal getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(BigDecimal priceDetail) {
        this.priceDetail = priceDetail;
    }

    public Item getStandardCodeItem() {
        return standardCodeItem;
    }

    public void setStandardCodeItem(Item standardCodeItem) {
        this.standardCodeItem = standardCodeItem;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getItemTax() {
        return itemTax;
    }

    public void setItemTax(BigDecimal itemTax) {
        this.itemTax = itemTax;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Long getCountRows() {
        return countRows;
    }

    public void setCountRows(Long countRows) {
        this.countRows = countRows;
    }
    
}
