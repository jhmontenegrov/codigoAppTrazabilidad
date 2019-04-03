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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacenID")
    private Collection<Inventario> inventarioCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AlmacenID")
    private Integer almacenID;
    @JoinColumn(name = "CiudadID", referencedColumnName = "CiudadID")
    @ManyToOne(optional = false)
    private Integer ciudadID;
    @JoinColumn(name = "EstablecimientoID", referencedColumnName = "EstablecimientoID")
    @ManyToOne(optional = false)
    private Integer establecimientoID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Integer usuarioID;

    public Almacen() {
    }

    public Almacen(Integer almacenID) {
        this.almacenID = almacenID;
    }

    public Almacen(String ubicacion, Integer almacenID, Integer ciudadID, Integer establecimientoID, Integer usuarioID) {
        this.ubicacion = ubicacion;
        this.almacenID = almacenID;
        this.ciudadID = ciudadID;
        this.establecimientoID = establecimientoID;
        this.usuarioID = usuarioID;
    }

    


    public Integer getAlmacenID() {
        return almacenID;
    }

    public void setAlmacenID(Integer almacenID) {
        this.almacenID = almacenID;
    }

    public Integer getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(Integer ciudadID) {
        this.ciudadID = ciudadID;
    }

    public Integer getEstablecimientoID() {
        return establecimientoID;
    }

    public void setEstablecimientoID(Integer establecimientoID) {
        this.establecimientoID = establecimientoID;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
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
        return "Beans.Almacen[ almacenID=" + almacenID + " ]";
    }

    @XmlTransient
    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}
