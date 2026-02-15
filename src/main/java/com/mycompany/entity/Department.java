/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author bacajos
 */
@Entity
@Table(name = "Department", indexes = {@Index(name = "IDX_codeDepartment",
                                       unique = true,
                                       columnList = "code_department" )})
@NamedNativeQueries({
                    @NamedNativeQuery(name = "Department.findDepartment", query = "select * from findDepartment(?,?,?)", resultClass = Department.class)
                   ,@NamedNativeQuery(name = "Department.findCountDepartment", query = "select * from findCountDepartment(?,?,?)", resultSetMapping = "scalarDepartment")

})
@SqlResultSetMapping(name = "scalarDepartment", columns = @ColumnResult(name = "id_department"))

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_department")
    private Integer idDepartment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "code_department")
    private Integer codeDepartment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "desc_department")
    private String descDepartment;
    @Basic(optional = false)
    @Size(max = 45)
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
    @OneToMany(mappedBy = "idDepartment")
    private Set<Item> itemCollection;
    @Basic(optional = true)
    @Column(name = "count_rows")
    private Long countRows;

    public Department() {
    }

    public Department(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Department(Integer idDepartment, Integer codeDepartment, String descDepartment, String modifiedBy, Date creationDate, Date lastModDate, Set<Item> itemCollection, Long countRows) {
        this.idDepartment = idDepartment;
        this.codeDepartment = codeDepartment;
        this.descDepartment = descDepartment;
        this.modifiedBy = modifiedBy;
        this.creationDate = creationDate;
        this.lastModDate = lastModDate;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
    }

    public Department(Integer codeDepartment, String descDepartment, String modifiedBy, Date creationDate, Date lastModDate, Set<Item> itemCollection, Long countRows) {
        this.codeDepartment = codeDepartment;
        this.descDepartment = descDepartment;
        this.modifiedBy = modifiedBy;
        this.creationDate = creationDate;
        this.lastModDate = lastModDate;
        this.itemCollection = itemCollection;
        this.countRows = countRows;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Integer getCodeDepartment() {
        return codeDepartment;
    }

    public void setCodeDepartment(Integer codeDepartment) {
        this.codeDepartment = codeDepartment;
    }

    public String getDescDepartment() {
        return descDepartment;
    }

    public void setDescDepartment(String descDepartment) {
        this.descDepartment = descDepartment;
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
        hash += (idDepartment != null ? idDepartment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.idDepartment == null && other.idDepartment != null) || (this.idDepartment != null && !this.idDepartment.equals(other.idDepartment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + codeDepartment + " " + descDepartment ;
    }

    public Long getCountRows() {
        return countRows;
    }

    public void setCountRows(Long countRows) {
        this.countRows = countRows;
    }

}
