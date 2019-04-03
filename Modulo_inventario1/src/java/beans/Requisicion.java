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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhona
 */
@Entity
@Table(name = "requisicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requisicion.findAll", query = "SELECT r FROM Requisicion r")
    , @NamedQuery(name = "Requisicion.findByNombreproducto", query = "SELECT r FROM Requisicion r WHERE r.nombreproducto = :nombreproducto")
    , @NamedQuery(name = "Requisicion.findByFechasolicutud", query = "SELECT r FROM Requisicion r WHERE r.fechasolicutud = :fechasolicutud")
    , @NamedQuery(name = "Requisicion.findByFechaentrega", query = "SELECT r FROM Requisicion r WHERE r.fechaentrega = :fechaentrega")
    , @NamedQuery(name = "Requisicion.findByObservacion", query = "SELECT r FROM Requisicion r WHERE r.observacion = :observacion")
    , @NamedQuery(name = "Requisicion.findByCantidad", query = "SELECT r FROM Requisicion r WHERE r.cantidad = :cantidad")
    , @NamedQuery(name = "Requisicion.findByRequisicionID", query = "SELECT r FROM Requisicion r WHERE r.requisicionID = :requisicionID")})
public class Requisicion implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre_producto")
    private String nombreproducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasolicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private int cantidad;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RequisicionID")
    private Integer requisicionID;
    @JoinColumn(name = "EstadoID", referencedColumnName = "EstadoID")
    @ManyToOne(optional = false)
    private int estadoID;
    @JoinColumn(name = "ProveedorID", referencedColumnName = "ProveedorID")
    @ManyToOne(optional = false)
    private int proveedorID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private int usuarioID;

    public Requisicion() {
    }

    public Requisicion(Integer requisicionID) {
        this.requisicionID = requisicionID;
    }

    public Requisicion(Integer requisicionID, String nombreproducto, Date fechasolicitud, String observacion, int cantidad, Integer estadoID, Integer proveedorID, Integer usuarioID) {
        this.requisicionID = requisicionID;
        this.nombreproducto = nombreproducto;
        this.fechasolicitud = fechasolicitud;
        this.observacion = observacion;
        this.cantidad = cantidad;
        this.estadoID = estadoID;
        this.proveedorID = proveedorID;
        this.usuarioID = usuarioID;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public Date getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicutud(Date fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }


    public Integer getRequisicionID() {
        return requisicionID;
    }

    public void setRequisicionID(Integer requisicionID) {
        this.requisicionID = requisicionID;
    }

    public int getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(int estadoID) {
        this.estadoID = estadoID;
    }

    public int getProveedorID() {
        return proveedorID;
    }

    public void setProveedorID(int proveedorID) {
        this.proveedorID = proveedorID;
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
        hash += (requisicionID != null ? requisicionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requisicion)) {
            return false;
        }
        Requisicion other = (Requisicion) object;
        return !((this.requisicionID == null && other.requisicionID != null) || (this.requisicionID != null && !this.requisicionID.equals(other.requisicionID)));
    }

    @Override
    public String toString() {
        return "Beans.Requisicion[ requisicionID=" + requisicionID + " ]";
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
    
}
