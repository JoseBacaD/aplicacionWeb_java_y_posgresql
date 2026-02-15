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
@Table(name = "location", indexes = {@Index(name = "IDX_idLocation",
                                       unique = true,
                                       columnList = "id_location" )})
@NamedNativeQueries({
                    @NamedNativeQuery(name = "location.findLocation", query = "select * from findLocation(?,?,?)", resultClass = Location.class)
                   ,@NamedNativeQuery(name = "location.findCountLocation", query = "select * from findCountLocation(?,?,?)", resultSetMapping = "scalarLocation")

})
@SqlResultSetMapping(name = "scalarLocation", columns = @ColumnResult(name = "id_location"))

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_location")
    private Integer idLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name_location")
    private String nameLocation;
    @Basic(optional = false)
    @Size(max = 45)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_mod_date")
    @Temporal(TemporalType.DATE)
    private Date lastModDate;
    @OneToMany(mappedBy = "idLocation")
    private Set<Item> itemCollection;
    @Basic(optional = true)
    @Column(name = "count_rows")
    private Long countRows;


    public Location() {
    }

    public Location(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public Location(Integer idLocation, String nameLocation, String modifiedBy, Date lastModDate, Set<Item> itemCollection, Long countRows) {
        this.idLocation = idLocation;
        this.nameLocation = nameLocation;
        this.modifiedBy = modifiedBy;
        this.lastModDate = lastModDate;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
    }

    public Location(String nameLocation, String modifiedBy, Date lastModDate, Set<Item> itemCollection, Long countRows) {
        this.nameLocation = nameLocation;
        this.modifiedBy = modifiedBy;
        this.lastModDate = lastModDate;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
    }


    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
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
        return "" + nameLocation;
    }

    public Long getCountRows() {
        return countRows;
    }

    public void setCountRows(Long countRows) {
        this.countRows = countRows;
    }

   
}