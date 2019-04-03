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
@Table(name = "establecimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Establecimiento.findAll", query = "SELECT e FROM Establecimiento e")
    , @NamedQuery(name = "Establecimiento.findByUbicaciontienda", query = "SELECT e FROM Establecimiento e WHERE e.ubicaciontienda = :ubicaciontienda")
    , @NamedQuery(name = "Establecimiento.findByEstablecimientoID", query = "SELECT e FROM Establecimiento e WHERE e.establecimientoID = :establecimientoID")})
public class Establecimiento implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Ubicaciontienda")
    private String ubicaciontienda;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EstablecimientoID")
    private Integer establecimientoID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimientoID")
    private Collection<Venta> ventaCollection;
    @JoinColumn(name = "CiudadID", referencedColumnName = "CiudadID")
    @ManyToOne
    private Ciudad ciudadID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimientoID")
    private Collection<Almacen> almacenCollection;

    public Establecimiento() {
    }

    public Establecimiento(Integer establecimientoID) {
        this.establecimientoID = establecimientoID;
    }

    public Establecimiento(Integer establecimientoID, String ubicaciontienda) {
        this.establecimientoID = establecimientoID;
        this.ubicaciontienda = ubicaciontienda;
    }


    public Integer getEstablecimientoID() {
        return establecimientoID;
    }

    public void setEstablecimientoID(Integer establecimientoID) {
        this.establecimientoID = establecimientoID;
    }

    @XmlTransient
    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
    }

    public Ciudad getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(Ciudad ciudadID) {
        this.ciudadID = ciudadID;
    }

    public Usuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuario usuarioID) {
        this.usuarioID = usuarioID;
    }

    @XmlTransient
    public Collection<Almacen> getAlmacenCollection() {
        return almacenCollection;
    }

    public void setAlmacenCollection(Collection<Almacen> almacenCollection) {
        this.almacenCollection = almacenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (establecimientoID != null ? establecimientoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Establecimiento)) {
            return false;
        }
        Establecimiento other = (Establecimiento) object;
        if ((this.establecimientoID == null && other.establecimientoID != null) || (this.establecimientoID != null && !this.establecimientoID.equals(other.establecimientoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Establecimiento[ establecimientoID=" + establecimientoID + " ]";
    }

    public String getUbicaciontienda() {
        return ubicaciontienda;
    }

    public void setUbicaciontienda(String ubicaciontienda) {
        this.ubicaciontienda = ubicaciontienda;
    }
    
}
