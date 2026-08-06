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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
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
@Table(name = "item", indexes = {@Index(name = "IDX_itemCode_div_subdiv_currency",
                                       unique = true,
                                       columnList = "id_divition,id_subdivition,standard_code_item,id_currency" )})
@NamedNativeQueries({
                    @NamedNativeQuery(name = "Item.findItem", query = "select * from findItem(?,?,?,?,?,?,?,?,?)", resultClass = Item.class)
//                 ,@NamedNativeQuery(name = "Item.findCountItem", query = "select * from findCountItem(?,?,?,?,?,?,?,?,?)", resultSetMapping = "scalarItem")
                   ,@NamedNativeQuery(name = "Item.discountItemStock", query = "select * from discountItemStock(?)", resultSetMapping = "scalarItem")
                   ,@NamedNativeQuery(name = "Item.discountItemStockOneByOne", query = "select * from discountItemStockOneByOne(?,?)", resultSetMapping = "scalarItem")
                   ,@NamedNativeQuery(name = "Item.finditemByCode", query = "select * from findItemByCode(?,?,?)", resultClass = Item.class)
                   ,@NamedNativeQuery(name = "Item.findItemByDesc", query = "select * from findItemByDesc(?,?,?)", resultClass = Item.class)
                   ,@NamedNativeQuery(name = "Item.findLowStock", query = "select * from findLowStock(?,?)", resultClass = Item.class)

})
@SqlResultSetMapping(name = "scalarItem", columns = @ColumnResult(name = "id_item"))

public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_item")
    private Integer idItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "desc_item")
    private String descItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "short_desc_item")
    private String shortDescItem;
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
    @Column(name = "last_mod_date")
    @Temporal(TemporalType.DATE)
    private Date lastModDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "min_quantity")
    private Integer minQuantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_sale")
    private BigDecimal priceSale;
    @Column(name = "standard_code_item")
    @NotNull
    @Basic(optional = false)
    private String standardCodeItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wholesale_price")
    private BigDecimal wholesalePrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "item_location")
    private String itemLocation;
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
    @Column(name = "sale_by_fraction")
    private Boolean saleByFraction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_stock")
    private BigDecimal itemStock;
    @Basic(optional = false)
    @JoinColumn(name = "id_department", referencedColumnName = "id_department", foreignKey = @ForeignKey(name = "fk_item_department"))
    @ManyToOne
    @NotNull
    private Department idDepartment;
    @Basic(optional = false)
    @JoinColumn(name = "id_uom", referencedColumnName = "id_uom", foreignKey = @ForeignKey(name = "fk_item_uom"))
    @ManyToOne
    @NotNull
    private UnitOfMeasureUom idUom;
    @Basic(optional = false)
    @JoinColumn(name = "id_currency", referencedColumnName = "id_currency", foreignKey = @ForeignKey(name = "fk_item_currency"))
    @ManyToOne
    @NotNull
    private Currency idCurrency;
    @Size(min = 1, max = 50)
    @Column(name = "icon_item_name")
    private String iconItemName;
    @Basic(optional = false)
    @JoinColumn(name = "id_provider", referencedColumnName = "id_provider", foreignKey = @ForeignKey(name = "fk_item_provider"))
    @ManyToOne
    @NotNull
    private Provider idProvider;
    
    @Basic(optional = true)
    @JoinColumn(name = "id_provider_2", referencedColumnName = "id_provider", foreignKey = @ForeignKey(name = "fk_item_provider"))
    @ManyToOne
    private Provider idProvider2;
    @Basic(optional = true)
    @JoinColumn(name = "id_provider_3", referencedColumnName = "id_provider", foreignKey = @ForeignKey(name = "fk_item_provider"))
    @ManyToOne
    private Provider idProvider3;
    
    
    @Basic(optional = false)
    @JoinColumn(name = "id_brand",  referencedColumnName = "id_brand", foreignKey = @ForeignKey(name = "fk_item_brand"))
    @ManyToOne 
    @NotNull
    private Brand idBrand;
    @Basic(optional = false)
    @JoinColumn(name = "id_location",  referencedColumnName = "id_location", foreignKey = @ForeignKey(name = "fk_item_location"))
    @ManyToOne 
    @NotNull
    private Location idLocation;
    @Basic(optional = true  )
    @Column(name = "min_stock_allowed")
    private Integer minStockAllowed;
    
    @Basic(optional = true)
    @Column(name = "price_provider")
    private BigDecimal priceProvider;
    
    @Basic(optional = true)
    @Column(name = "price_provider2")
    private BigDecimal priceProvider2;
    
    @Basic(optional = true)
    @Column(name = "price_provider3")
    private BigDecimal priceProvider3;
    
    
    @Basic(optional = true)
    @Column(name = "count_rows")
    private Long countRows;
    
    @Transient
    private BigDecimal priceWithTax;
 
    public Item() {

    }

    public Item(Integer idItem) {
        this.idItem = idItem;
    }

    public Item(Integer idItem, Date creationDate, String descItem, String shortDescItem, Integer idDivition, Integer idSubdivition, Date lastModDate, Integer minQuantity, String modifiedBy, BigDecimal priceSale, String standardCodeItem, BigDecimal wholesalePrice, String itemLocation, String strWildCard, Integer intWildCard, Boolean boolWildCard, Boolean saleByFraction, BigDecimal itemStock, Department idDepartment, UnitOfMeasureUom idUom, Currency idCurrency, String iconItemName, Provider idProvider, Provider idProvider2, Provider idProvider3, Brand idBrand, Location idLocation, Integer minStockAllowed, BigDecimal priceProvider, BigDecimal priceProvider2, BigDecimal priceProvider3, String codeProvider, String codeProvider2, String codeProvider3, Long countRows, BigDecimal priceWithTax) {
        this.idItem = idItem;
        this.creationDate = creationDate;
        this.descItem = descItem;
        this.shortDescItem = shortDescItem;
        this.idDivition = idDivition;
        this.idSubdivition = idSubdivition;
        this.lastModDate = lastModDate;
        this.minQuantity = minQuantity;
        this.modifiedBy = modifiedBy;
        this.priceSale = priceSale;
        this.standardCodeItem = standardCodeItem;
        this.wholesalePrice = wholesalePrice;
        this.itemLocation = itemLocation;
        this.strWildCard = strWildCard;
        this.intWildCard = intWildCard;
        this.boolWildCard = boolWildCard;
        this.saleByFraction = saleByFraction;
        this.itemStock = itemStock;
        this.idDepartment = idDepartment;
        this.idUom = idUom;
        this.idCurrency = idCurrency;
        this.iconItemName = iconItemName;
        this.idProvider = idProvider;
        this.idProvider2 = idProvider2;
        this.idProvider3 = idProvider3;
        this.idBrand = idBrand;
        this.idLocation = idLocation;
        this.minStockAllowed = minStockAllowed;
        this.priceProvider = priceProvider;
        this.priceProvider2 = priceProvider2;
        this.priceProvider3 = priceProvider3;
       
        this.countRows = countRows;
        this.priceWithTax = priceWithTax;
    }

    public Item(Date creationDate, String descItem, String shortDescItem, Integer idDivition, Integer idSubdivition, Date lastModDate, Integer minQuantity, String modifiedBy, BigDecimal priceSale, String standardCodeItem, BigDecimal wholesalePrice, String itemLocation, String strWildCard, Integer intWildCard, Boolean boolWildCard, Boolean saleByFraction, BigDecimal itemStock, Department idDepartment, UnitOfMeasureUom idUom, Currency idCurrency, String iconItemName, Provider idProvider, Provider idProvider2, Provider idProvider3, Brand idBrand, Location idLocation, Integer minStockAllowed, BigDecimal priceProvider, BigDecimal priceProvider2, BigDecimal priceProvider3, String codeProvider, String codeProvider2, String codeProvider3, Long countRows, BigDecimal priceWithTax) {
        this.creationDate = creationDate;
        this.descItem = descItem;
        this.shortDescItem = shortDescItem;
        this.idDivition = idDivition;
        this.idSubdivition = idSubdivition;
        this.lastModDate = lastModDate;
        this.minQuantity = minQuantity;
        this.modifiedBy = modifiedBy;
        this.priceSale = priceSale;
        this.standardCodeItem = standardCodeItem;
        this.wholesalePrice = wholesalePrice;
        this.itemLocation = itemLocation;
        this.strWildCard = strWildCard;
        this.intWildCard = intWildCard;
        this.boolWildCard = boolWildCard;
        this.saleByFraction = saleByFraction;
        this.itemStock = itemStock;
        this.idDepartment = idDepartment;
        this.idUom = idUom;
        this.idCurrency = idCurrency;
        this.iconItemName = iconItemName;
        this.idProvider = idProvider;
        this.idProvider2 = idProvider2;
        this.idProvider3 = idProvider3;
        this.idBrand = idBrand;
        this.idLocation = idLocation;
        this.minStockAllowed = minStockAllowed;
        this.priceProvider = priceProvider;
        this.priceProvider2 = priceProvider2;
        this.priceProvider3 = priceProvider3;
        
        this.countRows = countRows;
        this.priceWithTax = priceWithTax;
    }

   

    

    

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getShortDescItem() {
        return shortDescItem;
    }

    public void setShortDescItem(String shortDescItem) {
        this.shortDescItem = shortDescItem;
    }

   
    public String getDescItem() {
        return descItem;
    }

    public void setDescItem(String descItem) {
        this.descItem = descItem;
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

    public Date getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public BigDecimal getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(BigDecimal priceSale) {
        this.priceSale = priceSale;
    }

    public String getStandardCodeItem() {
        return standardCodeItem;
    }

    public void setStandardCodeItem(String standardCodeItem) {
        this.standardCodeItem = standardCodeItem;
    }

 

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public Department getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Department idDepartment) {
        this.idDepartment = idDepartment;
    }

    public UnitOfMeasureUom getIdUom() {
        return idUom;
    }

    public void setIdUom(UnitOfMeasureUom idUom) {
        this.idUom = idUom;
    }

    public Currency getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(Currency idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getIconItemName() {
        return iconItemName;
    }

    public void setIconItemName(String iconItemName) {
        this.iconItemName = iconItemName;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
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

    public Boolean getSaleByFraction() {
        return saleByFraction;
    }

    public void setSaleByFraction(Boolean saleByFraction) {
        this.saleByFraction = saleByFraction;
    }

    public BigDecimal getItemStock() {
        return itemStock;
    }

    public void setItemStock(BigDecimal itemStock) {
        this.itemStock = itemStock;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Item[ idItem=" + idItem + " ]";
    }

    public BigDecimal getPriceWithTax() {
        return priceWithTax;
    }

    public void setPriceWithTax(BigDecimal priceWithTax) {
        this.priceWithTax = priceWithTax;
    }

    public Provider getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Provider idProvider) {
        this.idProvider = idProvider;
    }

    public Integer getMinStockAllowed() {
        return minStockAllowed;
    }

    public void setMinStockAllowed(Integer minStockAllowed) {
        this.minStockAllowed = minStockAllowed;
    }

    public Brand getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Brand idBrand) {
        this.idBrand = idBrand;
    }

    public Location getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Location idLocation) {
        this.idLocation = idLocation;
    }

    public Long getCountRows() {
        return countRows;
    }

    public void setCountRows(Long countRows) {
        this.countRows = countRows;
    }

   
    public BigDecimal getPriceProvider() {
        return priceProvider;
    }

    public void setPriceProvider(BigDecimal priceProvider) {
        this.priceProvider = priceProvider;
    }

    public Provider getIdProvider2() {
        return idProvider2;
    }

    public void setIdProvider2(Provider idProvider2) {
        this.idProvider2 = idProvider2;
    }

    public Provider getIdProvider3() {
        return idProvider3;
    }

    public void setIdProvider3(Provider idProvider3) {
        this.idProvider3 = idProvider3;
    }

    public BigDecimal getPriceProvider2() {
        return priceProvider2;
    }

    public void setPriceProvider2(BigDecimal priceProvider2) {
        this.priceProvider2 = priceProvider2;
    }

    public BigDecimal getPriceProvider3() {
        return priceProvider3;
    }

    public void setPriceProvider3(BigDecimal priceProvider3) {
        this.priceProvider3 = priceProvider3;
    }

    
}
