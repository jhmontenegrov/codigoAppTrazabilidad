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
@Table(name = "estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")
    , @NamedQuery(name = "Estado.findByEstado", query = "SELECT e FROM Estado e WHERE e.estado = :estado")
    , @NamedQuery(name = "Estado.findByDescripcion", query = "SELECT e FROM Estado e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Estado.findByEstadoID", query = "SELECT e FROM Estado e WHERE e.estadoID = :estadoID")})
public class Estado implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Id_estado")
    private Integer idestado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoID")
    private Collection<SalidaProducto> salidaProductoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoID")
    private Collection<Devolucion> devolucionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoID")
    private Collection<Requisicion> requisicionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EstadoID")
    private Integer estadoID;

    public Estado() {
    }

    public Estado(Integer estadoID) {
        this.estadoID = estadoID;
    }

    public Estado(Integer estadoID, String estado, String descripcion) {
        this.estadoID = estadoID;
        this.estado = estado;
        this.descripcion = descripcion;
    }


    public Integer getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(Integer estadoID) {
        this.estadoID = estadoID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoID != null ? estadoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.estadoID == null && other.estadoID != null) || (this.estadoID != null && !this.estadoID.equals(other.estadoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Estado[ estadoID=" + estadoID + " ]";
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
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

    @XmlTransient
    public Collection<Requisicion> getRequisicionCollection() {
        return requisicionCollection;
    }

    public void setRequisicionCollection(Collection<Requisicion> requisicionCollection) {
        this.requisicionCollection = requisicionCollection;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
