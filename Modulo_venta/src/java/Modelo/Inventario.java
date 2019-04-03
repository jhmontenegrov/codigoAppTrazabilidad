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
    @Column(name = "Cantidad")
    private int cantidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioID")
    private Collection<InformacionVenta> informacionVentaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "InventarioID")
    private Integer inventarioID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioID")
    private Collection<Venta> ventaCollection;
    @JoinColumn(name = "AlmacenID", referencedColumnName = "AlmacenID")
    @ManyToOne(optional = false)
    private Almacen almacenID;
    @JoinColumn(name = "ProductoID", referencedColumnName = "ProductoID")
    @ManyToOne(optional = false)
    private Producto productoID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;

    public Inventario() {
    }

    public Inventario(Integer inventarioID) {
        this.inventarioID = inventarioID;
    }

    public Inventario(Integer inventarioID, String observacion, int cantidad) {
        this.inventarioID = inventarioID;
        this.observacion = observacion;
        this.cantidad = cantidad;
    }


    public Integer getInventarioID() {
        return inventarioID;
    }

    public void setInventarioID(Integer inventarioID) {
        this.inventarioID = inventarioID;
    }

    @XmlTransient
    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
    }

    public Almacen getAlmacenID() {
        return almacenID;
    }

    public void setAlmacenID(Almacen almacenID) {
        this.almacenID = almacenID;
    }

    public Producto getProductoID() {
        return productoID;
    }

    public void setProductoID(Producto productoID) {
        this.productoID = productoID;
    }

    public Usuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuario usuarioID) {
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
        if ((this.inventarioID == null && other.inventarioID != null) || (this.inventarioID != null && !this.inventarioID.equals(other.inventarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Inventario[ inventarioID=" + inventarioID + " ]";
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @XmlTransient
    public Collection<InformacionVenta> getInformacionVentaCollection() {
        return informacionVentaCollection;
    }

    public void setInformacionVentaCollection(Collection<InformacionVenta> informacionVentaCollection) {
        this.informacionVentaCollection = informacionVentaCollection;
    }
    
}
