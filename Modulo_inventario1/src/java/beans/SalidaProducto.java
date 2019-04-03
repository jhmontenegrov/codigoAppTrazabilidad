/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
@Table(name = "salida_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaProducto.findAll", query = "SELECT s FROM SalidaProducto s")
    , @NamedQuery(name = "SalidaProducto.findByCanttraslado", query = "SELECT s FROM SalidaProducto s WHERE s.canttraslado = :canttraslado")
    , @NamedQuery(name = "SalidaProducto.findByFechtraslado", query = "SELECT s FROM SalidaProducto s WHERE s.fechtraslado = :fechtraslado")
    , @NamedQuery(name = "SalidaProducto.findBySalidaproductoID", query = "SELECT s FROM SalidaProducto s WHERE s.salidaproductoID = :salidaproductoID")})
public class SalidaProducto implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Cant_salidap")
    private int cantsalidap;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fech_salidap")
    @Temporal(TemporalType.DATE)
    private Date fechsalidap;
    @JoinColumn(name = "InventarioID", referencedColumnName = "InventarioID")
    @ManyToOne(optional = false)
    private int inventarioID;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cant_traslado")
    private int canttraslado;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Salida_productoID")
    private Integer salidaproductoID;
    @JoinColumn(name = "EstadoID", referencedColumnName = "EstadoID")
    @ManyToOne(optional = false)
    private Integer estadoID;
    @JoinColumn(name = "MediostransporteID", referencedColumnName = "MediostransporteID")
    @ManyToOne(optional = false)
    private Integer mediostransporteID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Integer usuarioID;

    public SalidaProducto() {
    }

    public SalidaProducto(Integer salidaproductoID) {
        this.salidaproductoID = salidaproductoID;
    }

    public SalidaProducto(Integer salidaproductoID, int canttraslado, Date fechsalidap, Integer estadoID, Integer mediostransporteID, Integer inventarioID,Integer usuarioID) {
        this.salidaproductoID = salidaproductoID;
        this.canttraslado = canttraslado;
        this.fechsalidap = fechsalidap;
        this.estadoID=estadoID;
        this.mediostransporteID=mediostransporteID;

        this.inventarioID=inventarioID;
        this.usuarioID=usuarioID;
    }

    public int getCanttraslado() {
        return canttraslado;
    }

    public void setCanttraslado(int canttraslado) {
        this.canttraslado = canttraslado;
    }

    public Integer getSalidaproductoID() {
        return salidaproductoID;
    }

    public void setSalidaproductoID(Integer salidaproductoID) {
        this.salidaproductoID = salidaproductoID;
    }

    public Integer getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(Integer estadoID) {
        this.estadoID = estadoID;
    }

    public Integer getMediostransporteID() {
        return mediostransporteID;
    }

    public void setMediostransporteID(Integer mediostransporteID) {
        this.mediostransporteID = mediostransporteID;
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
        hash += (salidaproductoID != null ? salidaproductoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidaProducto)) {
            return false;
        }
        SalidaProducto other = (SalidaProducto) object;
        return !((this.salidaproductoID == null && other.salidaproductoID != null) || (this.salidaproductoID != null && !this.salidaproductoID.equals(other.salidaproductoID)));
    }

    @Override
    public String toString() {
        return "Beans.SalidaProducto[ salidaproductoID=" + salidaproductoID + " ]";
    }

    public int getCantsalidap() {
        return cantsalidap;
    }

    public void setCantsalidap(int cantsalidap) {
        this.cantsalidap = cantsalidap;
    }

    public Date getFechsalidap() {
        return fechsalidap;
    }

    public void setFechsalidap(Date fechsalidap) {
        this.fechsalidap = fechsalidap;
    }

    public Integer getInventarioID() {
        return inventarioID;
    }

    public void setInventarioID(Integer inventarioID) {
        this.inventarioID = inventarioID;
    }    
}
