/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Trabajo
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

    public InformacionVenta() {
    }

    public InformacionVenta(Integer informacionventaID) {
        this.informacionventaID = informacionventaID;
    }

    public InformacionVenta(Integer informacionventaID, int cantidad) {
        this.informacionventaID = informacionventaID;
        this.cantidad = cantidad;
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
        if ((this.informacionventaID == null && other.informacionventaID != null) || (this.informacionventaID != null && !this.informacionventaID.equals(other.informacionventaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.InformacionVenta[ informacionventaID=" + informacionventaID + " ]";
    }
    
}
