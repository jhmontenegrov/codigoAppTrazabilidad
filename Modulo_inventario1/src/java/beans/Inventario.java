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
@Table(name = "inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i")
    , @NamedQuery(name = "Inventario.findByObservacion", query = "SELECT i FROM Inventario i WHERE i.observacion = :observacion")
    , @NamedQuery(name = "Inventario.findByCantidad", query = "SELECT i FROM Inventario i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "Inventario.findByInventarioID", query = "SELECT i FROM Inventario i WHERE i.inventarioID = :inventarioID")})
public class Inventario implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Zona")
    private String zona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Estante")
    private String estante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private int cantidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioID")
    private Collection<AsignaInventarioProveedor> asignaInventarioProveedorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioID")
    private Collection<SalidaProducto> salidaProductoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioID")
    private Collection<Devolucion> devolucionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "InventarioID")
    private Integer inventarioID;
    @JoinColumn(name = "AlmacenID", referencedColumnName = "AlmacenID")
    @ManyToOne(optional = false)
    private Integer almacenID;
    @JoinColumn(name = "ProductoID", referencedColumnName = "ProductoID")
    @ManyToOne(optional = false)
    private String productoID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Integer usuarioID;

    public Inventario() {
    }

    public Inventario(Integer inventarioID) {
        this.inventarioID = inventarioID;
    }

    public Inventario(Integer inventarioID, String observacion, int cantidad, String zona, String estante,int almacenID, String productoID, int usuarioID) {
        this.inventarioID = inventarioID;
        this.observacion = observacion;
        this.cantidad = cantidad;
        this.zona= zona;
        this.estante = estante;
        this.almacenID = almacenID;
        this.productoID = productoID;
        this.usuarioID = usuarioID;
    }


    public Integer getInventarioID() {
        return inventarioID;
    }

    public void setInventarioID(Integer inventarioID) {
        this.inventarioID = inventarioID;
    }

    public Integer getAlmacenID() {
        return almacenID;
    }

    public void setAlmacenID(Integer almacenID) {
        this.almacenID = almacenID;
    }

    public String getProductoID() {
        return productoID;
    }

    public void setProductoID(String productoID) {
        this.productoID = productoID;
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
        hash += (inventarioID != null ? inventarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        return !((this.inventarioID == null && other.inventarioID != null) || (this.inventarioID != null && !this.inventarioID.equals(other.inventarioID)));
    }

    @Override
    public String toString() {
        return "Beans.Inventario[ inventarioID=" + inventarioID + " ]";
    }


    @XmlTransient
    public Collection<AsignaInventarioProveedor> getAsignaInventarioProveedorCollection() {
        return asignaInventarioProveedorCollection;
    }

    public void setAsignaInventarioProveedorCollection(Collection<AsignaInventarioProveedor> asignaInventarioProveedorCollection) {
        this.asignaInventarioProveedorCollection = asignaInventarioProveedorCollection;
    }

    @XmlTransient
    public Collection<SalidaProducto> getSalidaProductoCollection() {
        return salidaProductoCollection;
    }

    public void setSalidaProductoCollection(Collection<SalidaProducto> salidaProductoCollection) {
        this.salidaProductoCollection = salidaProductoCollection;
    }

    @XmlTransient
    public Collection<Devolucion> getDevolucionCollection() {
        return devolucionCollection;
    }

    public void setDevolucionCollection(Collection<Devolucion> devolucionCollection) {
        this.devolucionCollection = devolucionCollection;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
