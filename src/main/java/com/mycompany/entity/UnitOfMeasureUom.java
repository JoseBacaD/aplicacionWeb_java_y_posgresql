/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author bacajos
 */
@Entity
@Table(name = "unit_of_measure_uom", indexes = {@Index(name = "IDX_codeUom",
                                       unique = true,
                                       columnList = "code_uom" )})

@NamedNativeQueries({
                    @NamedNativeQuery(name = "UnitOfMeasureUom.findUOM", query = "select * from findUOM(?,?,?)", resultClass = UnitOfMeasureUom.class)
                   ,@NamedNativeQuery(name = "UnitOfMeasureUom.findCountUOM", query = "select * from findCountUOM(?,?,?)", resultSetMapping = "scalarUOM")

})
@SqlResultSetMapping(name = "scalarUOM", columns = @ColumnResult(name = "id_uom"))


public class UnitOfMeasureUom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_uom")
    private Integer idUom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "code_uom")
    private Integer codeUom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "short_name")
    private String shortName;
    @Size(max = 45)
    @Column(name = "desc_uom")
    private String descUom;
//    @OneToMany(mappedBy = "unit_of_measure_uom", cascade = CascadeType.ALL)
//    private List<Item> items = new ArrayList<>();
     @OneToMany(mappedBy = "idUom")
     private Set<Item> itemCollection;
    @Basic(optional = true)
    @Column(name = "count_rows")
    private Long countRows;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "modified_by")
    private String modifiedBy;
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

    public UnitOfMeasureUom() {
    }

    public UnitOfMeasureUom(Integer idUom) {
        this.idUom = idUom;
    }

    public UnitOfMeasureUom(Integer idUom, Integer codeUom, String shortName, String descUom, Set<Item> itemCollection, Long countRows, String modifiedBy, Date creationDate, Date lastModDate) {
        this.idUom = idUom;
        this.codeUom = codeUom;
        this.shortName = shortName;
        this.descUom = descUom;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
        this.modifiedBy = modifiedBy;
        this.creationDate = creationDate;
        this.lastModDate = lastModDate;
    }

    public UnitOfMeasureUom(Integer codeUom, String shortName, String descUom, Set<Item> itemCollection, Long countRows, String modifiedBy, Date creationDate, Date lastModDate) {
        this.codeUom = codeUom;
        this.shortName = shortName;
        this.descUom = descUom;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
        this.modifiedBy = modifiedBy;
        this.creationDate = creationDate;
        this.lastModDate = lastModDate;
    }

    public Integer getIdUom() {
        return idUom;
    }

    public void setIdUom(Integer idUom) {
        this.idUom = idUom;
    }

    public Integer getCodeUom() {
        return codeUom;
    }

    public void setCodeUom(Integer codeUom) {
        this.codeUom = codeUom;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescUom() {
        return descUom;
    }

    public void setDescUom(String descUom) {
        this.descUom = descUom;
    }

    
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public Set<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Set<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUom != null ? idUom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnitOfMeasureUom)) {
            return false;
        }
        UnitOfMeasureUom other = (UnitOfMeasureUom) object;
        if ((this.idUom == null && other.idUom != null) || (this.idUom != null && !this.idUom.equals(other.idUom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codeUom +" " + descUom;
    }

    public Long getCountRows() {
        return countRows;
    }

    public void setCountRows(Long countRows) {
        this.countRows = countRows;
    }

   
    
}
