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
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "Brand", indexes = {@Index(name = "IDX_idBrand",
                                       unique = true,
                                       columnList = "id_brand" )})
@NamedNativeQueries({
                    @NamedNativeQuery(name = "Brand.findBrand", query = "select * from findBrand(?,?,?)", resultClass = Brand.class)
                   ,@NamedNativeQuery(name = "Brand.findCountBrand", query = "select * from findCountBrand(?,?,?)", resultSetMapping = "scalarBrand")

})
@SqlResultSetMapping(name = "scalarBrand", columns = @ColumnResult(name = "id_brand"))

public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_brand")
    private Integer idBrand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name_brand")
    private String nameBrand;
    @Basic(optional = false)
    @Size(max = 45)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_mod_date")
    @Temporal(TemporalType.DATE)
    private Date lastModDate;
    @OneToMany(mappedBy = "idBrand")
    private Set<Item> itemCollection;
    @Basic(optional = true)
    @Column(name = "count_rows")
    private Long countRows;

    public Brand() {
    }

    public Brand(Integer idBrand) {
        this.idBrand = idBrand;
    }

    public Brand(Integer idBrand, String nameBrand, String modifiedBy, Date lastModDate, Set<Item> itemCollection, Long countRows) {
        this.idBrand = idBrand;
        this.nameBrand = nameBrand;
        this.modifiedBy = modifiedBy;
        this.lastModDate = lastModDate;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
    }

    public Brand(String nameBrand, String modifiedBy, Date lastModDate, Set<Item> itemCollection, Long countRows) {
        this.nameBrand = nameBrand;
        this.modifiedBy = modifiedBy;
        this.lastModDate = lastModDate;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
    }

    

    public Integer getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Integer idBrand) {
        this.idBrand = idBrand;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
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
    
    @Override
    public String toString() {
        return "" + nameBrand ;
    }

    public Long getCountRows() {
        return countRows;
    }

    public void setCountRows(Long countRows) {
        this.countRows = countRows;
    }

}
