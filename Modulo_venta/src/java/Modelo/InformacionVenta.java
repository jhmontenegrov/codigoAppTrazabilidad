/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhona
 */
@Entity
@Table(name = "informacion_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformacionVenta.findAll", query = "SELECT i FROM InformacionVenta i")
    , @NamedQuery(name = "InformacionVenta.findByCantidad", query = "SELECT i FROM InformacionVenta i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "InformacionVenta.findByInformacionventaID", query = "SELECT i FROM InformacionVenta i WHERE i.informacionventaID = :informacionventaID")})
public class InformacionVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private int cantidad;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Informacion_ventaID")
    private Integer informacionventaID;
    @JoinColumn(name = "InventarioID", referencedColumnName = "InventarioID")
    @ManyToOne(optional = false)
    private Integer inventarioID;
    @JoinColumn(name = "ProductoID", referencedColumnName = "ProductoID")
    @ManyToOne(optional = false)
    private String productoID;
    @JoinColumn(name = "VentaID", referencedColumnName = "VentaID")
    @ManyToOne(optional = false)
    private Integer ventaID;

    public InformacionVenta() {
    }

    public InformacionVenta(Integer informacionventaID) {
        this.informacionventaID = informacionventaID;
    }

    public InformacionVenta(int cantidad, Integer informacionventaID, Integer inventarioID, String productoID, Integer ventaID) {
        this.cantidad = cantidad;
        this.informacionventaID = informacionventaID;
        this.inventarioID = inventarioID;
        this.productoID = productoID;
        this.ventaID = ventaID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getInformacionventaID() {
        return informacionventaID;
    }

    public void setInformacionventaID(Integer informacionventaID) {
        this.informacionventaID = informacionventaID;
    }

    public Integer getInventarioID() {
        return inventarioID;
    }

    public void setInventarioID(Integer inventarioID) {
        this.inventarioID = inventarioID;
    }

    public String getProductoID() {
        return productoID;
    }

    public void setProductoID(String productoID) {
        this.productoID = productoID;
    }

    public Integer getVentaID() {
        return ventaID;
    }

    public void setVentaID(Integer ventaID) {
        this.ventaID = ventaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (informacionventaID != null ? informacionventaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformacionVenta)) {
            return false;
        }
        InformacionVenta other = (InformacionVenta) object;
        return !((this.informacionventaID == null && other.informacionventaID != null) || (this.informacionventaID != null && !this.informacionventaID.equals(other.informacionventaID)));
    }

    @Override
    public String toString() {
        return "Modelo.InformacionVenta[ informacionventaID=" + informacionventaID + " ]";
    }
    
}
