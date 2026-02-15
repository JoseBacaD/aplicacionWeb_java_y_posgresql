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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;



@Entity
@Table(name = "frecuent_sale_item",indexes = {@Index(name = "IDX_itemStandardCode",
                                       unique = true,
                                       columnList = "standard_code_item" )})



@NamedNativeQueries({
                    @NamedNativeQuery(name = "FrecuentSaleItem.findFrecItem", query = "select * from findFrecItem()", resultClass = FrecuentSaleItem.class)
                   , @NamedNativeQuery(name = "FrecuentSaleItem.findFrecItemByCode", query = "select * from findFrecItemByCode(?)", resultClass = FrecuentSaleItem.class)
              
                
})
public class FrecuentSaleItem implements Serializable{
     private static final long serialVersionUID = 1L;
     
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_frecuent_sale_item")
     private Integer idFrecuentSaleItem;
     @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @NotNull
    @OneToOne
    @JoinColumn(name="standard_code_item",referencedColumnName = "standard_code_item", foreignKey = @ForeignKey(name = "fk_saleItem_item"))
    private Item standardCodeItem;
    @Basic(optional = false)
    @Column(name = "is_frecuent_promo")
    private Boolean isFrecuentPromo;
    @Basic(optional = false)
    @Column(name = "is_frecuent_extra")
    private Boolean isFrecuentExtra;
    @Basic(optional = false)
    @Column(name = "is_frecuent_other")
    private Boolean isFrecuentOther;
    
     public FrecuentSaleItem() {
      
    }

    
    public FrecuentSaleItem(Integer idFrecuentSaleItem) {
        this.idFrecuentSaleItem = idFrecuentSaleItem;
    }

    public FrecuentSaleItem(Integer idFrecuentSaleItem, Date creationDate, Item standardCodeItem, Boolean isFrecuentPromo, Boolean isFrecuentExtra, Boolean isFrecuentOther) {
        this.idFrecuentSaleItem = idFrecuentSaleItem;
        this.creationDate = creationDate;
        this.standardCodeItem = standardCodeItem;
        this.isFrecuentPromo = isFrecuentPromo;
        this.isFrecuentExtra = isFrecuentExtra;
        this.isFrecuentOther = isFrecuentOther;
    }

    public FrecuentSaleItem(Date creationDate, Item standardCodeItem, Boolean isFrecuentPromo, Boolean isFrecuentExtra, Boolean isFrecuentOther) {
        this.creationDate = creationDate;
        this.standardCodeItem = standardCodeItem;
        this.isFrecuentPromo = isFrecuentPromo;
        this.isFrecuentExtra = isFrecuentExtra;
        this.isFrecuentOther = isFrecuentOther;
    }

    

  
   

    public Integer getIdFrecuentSaleItem() {
        return idFrecuentSaleItem;
    }

    public void setIdFrecuentSaleItem(Integer idFrecuentSaleItem) {
        this.idFrecuentSaleItem = idFrecuentSaleItem;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Item getStandardCodeItem() {
        return standardCodeItem;
    }

    public void setStandardCodeItem(Item standardCodeItem) {
        this.standardCodeItem = standardCodeItem;
    }

    public Boolean getIsFrecuentPromo() {
        return isFrecuentPromo;
    }

    public void setIsFrecuentPromo(Boolean isFrecuentPromo) {
        this.isFrecuentPromo = isFrecuentPromo;
    }

    public Boolean getIsFrecuentExtra() {
        return isFrecuentExtra;
    }

    public void setIsFrecuentExtra(Boolean isFrecuentExtra) {
        this.isFrecuentExtra = isFrecuentExtra;
    }

    public Boolean getIsFrecuentOther() {
        return isFrecuentOther;
    }

    public void setIsFrecuentOther(Boolean isFrecuentOther) {
        this.isFrecuentOther = isFrecuentOther;
    }

    


    
}
