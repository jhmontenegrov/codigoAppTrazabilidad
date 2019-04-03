/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhona
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByCantvta", query = "SELECT v FROM Venta v WHERE v.cantvta = :cantvta")
    , @NamedQuery(name = "Venta.findByFechventa", query = "SELECT v FROM Venta v WHERE v.fechventa = :fechventa")
    , @NamedQuery(name = "Venta.findByVentaID", query = "SELECT v FROM Venta v WHERE v.ventaID = :ventaID")})
public class Venta implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fech_venta")
    @Temporal(TemporalType.DATE)
    private Date fechventa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaID")
    private Collection<InformacionVenta> informacionVentaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VentaID")
    private Integer ventaID;
    @JoinColumn(name = "EstablecimientoID", referencedColumnName = "EstablecimientoID")
    @ManyToOne(optional = false)
    private Integer establecimientoID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Integer usuarioID;

    public Venta() {
    }

    public Venta(Integer ventaID) {
        this.ventaID = ventaID;
    }

    public Venta(Date fechventa, Integer ventaID, Integer establecimientoID, Integer usuarioID) {
        this.fechventa = fechventa;
        this.ventaID = ventaID;
        this.establecimientoID = establecimientoID;
        this.usuarioID = usuarioID;
    }


    public Date getFechventa() {
        return fechventa;
    }

    public void setFechventa(Date fechventa) {
        this.fechventa = fechventa;
    }

    public Integer getVentaID() {
        return ventaID;
    }

    public void setVentaID(Integer ventaID) {
        this.ventaID = ventaID;
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
        hash += (ventaID != null ? ventaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        return !((this.ventaID == null && other.ventaID != null) || (this.ventaID != null && !this.ventaID.equals(other.ventaID)));
    }

    @Override
    public String toString() {
        return "Modelo.Venta[ ventaID=" + ventaID + " ]";
    }

    @XmlTransient
    public Collection<InformacionVenta> getInformacionVentaCollection() {
        return informacionVentaCollection;
    }

    public void setInformacionVentaCollection(Collection<InformacionVenta> informacionVentaCollection) {
        this.informacionVentaCollection = informacionVentaCollection;
    }
    
}
