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
 * @author Trabajo
 */
@Entity
@Table(name = "ciudad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c")
    , @NamedQuery(name = "Ciudad.findByCiudadnombre", query = "SELECT c FROM Ciudad c WHERE c.ciudadnombre = :ciudadnombre")
    , @NamedQuery(name = "Ciudad.findByPais", query = "SELECT c FROM Ciudad c WHERE c.pais = :pais")
    , @NamedQuery(name = "Ciudad.findByCiudadID", query = "SELECT c FROM Ciudad c WHERE c.ciudadID = :ciudadID")})
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Ciudad_nombre")
    private String ciudadnombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Pais")
    private String pais;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CiudadID")
    private Integer ciudadID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadID")
    private Collection<Usuario> usuarioCollection;

    public Ciudad() {
    }
  
    public Ciudad(Integer ciudadID) {
        this.ciudadID = ciudadID;
    }

    public Ciudad(Integer ciudadID, String ciudadnombre, String pais) {
        this.ciudadID = ciudadID;
        this.ciudadnombre = ciudadnombre;
        this.pais = pais;
    }

    public String getCiudadnombre() {
        return ciudadnombre;
    }

    public void setCiudadnombre(String ciudadnombre) {
        this.ciudadnombre = ciudadnombre;
    }

    public String getPais() {
        return pais; 
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(Integer ciudadID) {
        this.ciudadID = ciudadID;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciudadID != null ? ciudadID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        if ((this.ciudadID == null && other.ciudadID != null) || (this.ciudadID != null && !this.ciudadID.equals(other.ciudadID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Ciudad[ ciudadID=" + ciudadID + " ]";
    }
    
}
