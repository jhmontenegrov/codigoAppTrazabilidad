/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.*;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhona
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByNomproveedor", query = "SELECT p FROM Proveedor p WHERE p.nomproveedor = :nomproveedor")
    , @NamedQuery(name = "Proveedor.findByDirproveedor", query = "SELECT p FROM Proveedor p WHERE p.dirproveedor = :dirproveedor")
    , @NamedQuery(name = "Proveedor.findByTelproveedor", query = "SELECT p FROM Proveedor p WHERE p.telproveedor = :telproveedor")
    , @NamedQuery(name = "Proveedor.findByMailproveedor", query = "SELECT p FROM Proveedor p WHERE p.mailproveedor = :mailproveedor")
    , @NamedQuery(name = "Proveedor.findByProveedorID", query = "SELECT p FROM Proveedor p WHERE p.proveedorID = :proveedorID")})
public class Proveedor implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nom_proveedor")
    private String nomproveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Dir_proveedor")
    private String dirproveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Tel_proveedor")
    private String telproveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Mail_proveedor")
    private String mailproveedor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorID")
    private Collection<AsignaInventarioProveedor> asignaInventarioProveedorCollection;
    @JoinColumn(name = "CiudadID", referencedColumnName = "CiudadID")
    @ManyToOne(optional = false)
    private int ciudadID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorID")
    private Collection<Requisicion> requisicionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProveedorID")
    private Integer proveedorID;

    public Proveedor() {
    }

    public Proveedor(Integer proveedorID) {
        this.proveedorID = proveedorID;
    }

    public Proveedor(Integer proveedorID, String nomproveedor, String dirproveedor, String telproveedor, String mailproveedor, Integer ciudadID) {
        this.proveedorID = proveedorID;
        this.nomproveedor = nomproveedor;
        this.dirproveedor = dirproveedor;
        this.telproveedor = telproveedor;
        this.mailproveedor = mailproveedor;
        this.ciudadID=ciudadID;
    }

    public String getNomproveedor() {
        return nomproveedor;
    }

    public void setNomproveedor(String nomproveedor) {
        this.nomproveedor = nomproveedor;
    }

    public String getDirproveedor() {
        return dirproveedor;
    }

    public void setDirproveedor(String dirproveedor) {
        this.dirproveedor = dirproveedor;
    }

    public String getTelproveedor() {
        return telproveedor;
    }

    public void setTelproveedor(String telproveedor) {
        this.telproveedor = telproveedor;
    }

    public String getMailproveedor() {
        return mailproveedor;
    }

    public void setMailproveedor(String mailproveedor) {
        this.mailproveedor = mailproveedor;
    }

    public Integer getProveedorID() {
        return proveedorID;
    }

    public void setProveedorID(Integer proveedorID) {
        this.proveedorID = proveedorID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorID != null ? proveedorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        return !((this.proveedorID == null && other.proveedorID != null) || (this.proveedorID != null && !this.proveedorID.equals(other.proveedorID)));
    }

    @Override
    public String toString() {
        return "Beans.Proveedor[ proveedorID=" + proveedorID + " ]";
    }


    @XmlTransient
    public Collection<AsignaInventarioProveedor> getAsignaInventarioProveedorCollection() {
        return asignaInventarioProveedorCollection;
    }

    public void setAsignaInventarioProveedorCollection(Collection<AsignaInventarioProveedor> asignaInventarioProveedorCollection) {
        this.asignaInventarioProveedorCollection = asignaInventarioProveedorCollection;
    }

    public int getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(int ciudadID) {
        this.ciudadID = ciudadID;
    }

    @XmlTransient
    public Collection<Requisicion> getRequisicionCollection() {
        return requisicionCollection;
    }

    public void setRequisicionCollection(Collection<Requisicion> requisicionCollection) {
        this.requisicionCollection = requisicionCollection;
    }

}
