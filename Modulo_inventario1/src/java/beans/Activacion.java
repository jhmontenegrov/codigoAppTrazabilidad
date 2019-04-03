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
@Table(name = "activacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activacion.findAll", query = "SELECT a FROM Activacion a")
    , @NamedQuery(name = "Activacion.findByNombreactivacion", query = "SELECT a FROM Activacion a WHERE a.nombreactivacion = :nombreactivacion")
    , @NamedQuery(name = "Activacion.findByActivacionID", query = "SELECT a FROM Activacion a WHERE a.activacionID = :activacionID")})
public class Activacion implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre_activacion")
    private String nombreactivacion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ActivacionID")
    private Integer activacionID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activacionID")
    private Collection<AsignadaPerfilUsuario> asignadaPerfilUsuarioCollection;

    public Activacion() {
    }

    public Activacion(Integer activacionID) {
        this.activacionID = activacionID;
    }

    public Activacion(Integer activacionID, String nombreactivacion) {
        this.activacionID = activacionID;
        this.nombreactivacion = nombreactivacion;
    }

    public String getNombreactivacion() {
        return nombreactivacion;
    }

    public void setNombreactivacion(String nombreactivacion) {
        this.nombreactivacion = nombreactivacion;
    }

    public Integer getActivacionID() {
        return activacionID;
    }

    public void setActivacionID(Integer activacionID) {
        this.activacionID = activacionID;
    }

    @XmlTransient
    public Collection<AsignadaPerfilUsuario> getAsignadaPerfilUsuarioCollection() {
        return asignadaPerfilUsuarioCollection;
    }

    public void setAsignadaPerfilUsuarioCollection(Collection<AsignadaPerfilUsuario> asignadaPerfilUsuarioCollection) {
        this.asignadaPerfilUsuarioCollection = asignadaPerfilUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activacionID != null ? activacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activacion)) {
            return false;
        }
        Activacion other = (Activacion) object;
        return !((this.activacionID == null && other.activacionID != null) || (this.activacionID != null && !this.activacionID.equals(other.activacionID)));
    }

    @Override
    public String toString() {
        return "modelo.Activacion[ activacionID=" + activacionID + " ]";
    }

}
