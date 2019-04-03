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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimientoID")
    private Collection<Venta> ventaCollection;
    @JoinColumn(name = "CiudadID", referencedColumnName = "CiudadID")
    @ManyToOne
    private int ciudadID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimientoID")
    private Collection<Devolucion> devolucionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimientoID")
    private Collection<Almacen> almacenCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimientoID")
    private Collection<SalidaProducto> salidaProductoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EstablecimientoID")
    private Integer establecimientoID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Integer usuarioID;

    public Establecimiento() {
    }

    public Establecimiento(Integer establecimientoID) {
        this.establecimientoID = establecimientoID;
    }

    public Establecimiento(String ubicaciontienda, Integer establecimientoID, Integer usuarioID, Integer ciudadID) {
        this.establecimientoID = establecimientoID;
        this.ubicaciontienda = ubicaciontienda;
        this.usuarioID = usuarioID;
        this.ciudadID=ciudadID;
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
        return "Beans.Establecimiento[ establecimientoID=" + establecimientoID + " ]";
    }

    @XmlTransient
    public Collection<SalidaProducto> getSalidaProductoCollection() {
        return salidaProductoCollection;
    }

    public void setSalidaProductoCollection(Collection<SalidaProducto> salidaProductoCollection) {
        this.salidaProductoCollection = salidaProductoCollection;
    }

    @XmlTransient
    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
    }

    public int getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(int ciudadID) {
        this.ciudadID = ciudadID;
    }

    @XmlTransient
    public Collection<Devolucion> getDevolucionCollection() {
        return devolucionCollection;
    }

    public void setDevolucionCollection(Collection<Devolucion> devolucionCollection) {
        this.devolucionCollection = devolucionCollection;
    }

    @XmlTransient
    public Collection<Almacen> getAlmacenCollection() {
        return almacenCollection;
    }

    public void setAlmacenCollection(Collection<Almacen> almacenCollection) {
        this.almacenCollection = almacenCollection;
    }

    public String getUbicaciontienda() {
        return ubicaciontienda;
    }

    public void setUbicaciontienda(String ubicaciontienda) {
        this.ubicaciontienda = ubicaciontienda;
    }
    
}
