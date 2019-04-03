/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
@Table(name = "almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a")
    , @NamedQuery(name = "Almacen.findByUbicacion", query = "SELECT a FROM Almacen a WHERE a.ubicacion = :ubicacion")
    , @NamedQuery(name = "Almacen.findByAlmacenID", query = "SELECT a FROM Almacen a WHERE a.almacenID = :almacenID")})
public class Almacen implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Ubicacion")
    private String ubicacion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AlmacenID")
    private Integer almacenID;
    @JoinColumn(name = "CiudadID", referencedColumnName = "CiudadID")
    @ManyToOne(optional = false)
    private Ciudad ciudadID;
    @JoinColumn(name = "EstablecimientoID", referencedColumnName = "EstablecimientoID")
    @ManyToOne(optional = false)
    private Establecimiento establecimientoID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacenID")
    private Collection<Inventario> inventarioCollection;

    public Almacen() {
    }

    public Almacen(Integer almacenID) {
        this.almacenID = almacenID;
    }

    public Almacen(Integer almacenID, String ubicacion) {
        this.almacenID = almacenID;
        this.ubicacion = ubicacion;
    }


    public Integer getAlmacenID() {
        return almacenID;
    }

    public void setAlmacenID(Integer almacenID) {
        this.almacenID = almacenID;
    }

    public Ciudad getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(Ciudad ciudadID) {
        this.ciudadID = ciudadID;
    }

    public Establecimiento getEstablecimientoID() {
        return establecimientoID;
    }

    public void setEstablecimientoID(Establecimiento establecimientoID) {
        this.establecimientoID = establecimientoID;
    }

    public Usuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuario usuarioID) {
        this.usuarioID = usuarioID;
    }

    @XmlTransient
    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (almacenID != null ? almacenID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.almacenID == null && other.almacenID != null) || (this.almacenID != null && !this.almacenID.equals(other.almacenID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Almacen[ almacenID=" + almacenID + " ]";
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}
