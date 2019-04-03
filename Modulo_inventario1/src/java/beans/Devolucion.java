/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhona
 */
@Entity
@Table(name = "devolucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devolucion.findAll", query = "SELECT d FROM Devolucion d")
    , @NamedQuery(name = "Devolucion.findByCantdevolucion", query = "SELECT d FROM Devolucion d WHERE d.cantdevolucion = :cantdevolucion")
    , @NamedQuery(name = "Devolucion.findByFechdevolucion", query = "SELECT d FROM Devolucion d WHERE d.fechdevolucion = :fechdevolucion")
    , @NamedQuery(name = "Devolucion.findByDevolucionID", query = "SELECT d FROM Devolucion d WHERE d.devolucionID = :devolucionID")})
public class Devolucion implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Cant_devolucion")
    private int cantdevolucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fech_devolucion")
    @Temporal(TemporalType.DATE)
    private Date fechdevolucion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DevolucionID")
    private Integer devolucionID;
    @JoinColumn(name = "EstablecimientoID", referencedColumnName = "EstablecimientoID")
    @ManyToOne(optional = false)
    private int establecimientoID;
    @JoinColumn(name = "EstadoID", referencedColumnName = "EstadoID")
    @ManyToOne(optional = false)
    private int estadoID;
    @JoinColumn(name = "InventarioID", referencedColumnName = "InventarioID")
    @ManyToOne(optional = false)
    private int inventarioID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private int usuarioID;

    public Devolucion() {
    }

    public Devolucion(Integer devolucionID) {
        this.devolucionID = devolucionID;
    }

    public Devolucion(Integer devolucionID, int cantdevolucion, Date fechdevolucion, Integer usuarioID, Integer inventarioID, Integer estadoID) {
        this.devolucionID = devolucionID;
        this.cantdevolucion = cantdevolucion;
        this.fechdevolucion = fechdevolucion;
        this.usuarioID = usuarioID;
        this.inventarioID = inventarioID;
        this.estadoID = estadoID;
    }

    public int getCantdevolucion() {
        return cantdevolucion;
    }

    public void setCantdevolucion(int cantdevolucion) {
        this.cantdevolucion = cantdevolucion;
    }

    public Date getFechdevolucion() {
        return fechdevolucion;
    }

    public void setFechdevolucion(Date fechdevolucion) {
        this.fechdevolucion = fechdevolucion;
    }

    public Integer getDevolucionID() {
        return devolucionID;
    }

    public void setDevolucionID(Integer devolucionID) {
        this.devolucionID = devolucionID;
    }

    public int getEstablecimientoID() {
        return establecimientoID;
    }

    public void setEstablecimientoID(int establecimientoID) {
        this.establecimientoID = establecimientoID;
    }

    public int getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(int estadoID) {
        this.estadoID = estadoID;
    }

    public int getInventarioID() {
        return inventarioID;
    }

    public void setInventarioID(int inventarioID) {
        this.inventarioID = inventarioID;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (devolucionID != null ? devolucionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devolucion)) {
            return false;
        }
        Devolucion other = (Devolucion) object;
        return !((this.devolucionID == null && other.devolucionID != null) || (this.devolucionID != null && !this.devolucionID.equals(other.devolucionID)));
    }

    @Override
    public String toString() {
        return "Beans.Devolucion[ devolucionID=" + devolucionID + " ]";
    }
}
