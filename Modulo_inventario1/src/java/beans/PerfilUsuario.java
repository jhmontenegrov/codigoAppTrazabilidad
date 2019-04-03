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
@Table(name = "perfil_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilUsuario.findAll", query = "SELECT p FROM PerfilUsuario p")
    , @NamedQuery(name = "PerfilUsuario.findByNomperfilusuario", query = "SELECT p FROM PerfilUsuario p WHERE p.nomperfilusuario = :nomperfilusuario")
    , @NamedQuery(name = "PerfilUsuario.findByPerfilusuarioID", query = "SELECT p FROM PerfilUsuario p WHERE p.perfilusuarioID = :perfilusuarioID")})
public class PerfilUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nom_perfilusuario")
    private String nomperfilusuario;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Perfil_usuarioID")
    private Integer perfilusuarioID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfilusuarioID")
    private Collection<Usuario> usuarioCollection;

    public PerfilUsuario() {
    }

    public PerfilUsuario(Integer perfilusuarioID) {
        this.perfilusuarioID = perfilusuarioID;
    }

    public PerfilUsuario(Integer perfilusuarioID, String nomperfilusuario) {
        this.perfilusuarioID = perfilusuarioID;
        this.nomperfilusuario = nomperfilusuario;
    }

    public String getNomperfilusuario() {
        return nomperfilusuario;
    }

    public void setNomperfilusuario(String nomperfilusuario) {
        this.nomperfilusuario = nomperfilusuario;
    }

    public Integer getPerfilusuarioID() {
        return perfilusuarioID;
    }

    public void setPerfilusuarioID(Integer perfilusuarioID) {
        this.perfilusuarioID = perfilusuarioID;
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
        hash += (perfilusuarioID != null ? perfilusuarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilUsuario)) {
            return false;
        }
        PerfilUsuario other = (PerfilUsuario) object;
        if ((this.perfilusuarioID == null && other.perfilusuarioID != null) || (this.perfilusuarioID != null && !this.perfilusuarioID.equals(other.perfilusuarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.PerfilUsuario[ perfilusuarioID=" + perfilusuarioID + " ]";
    }
    
}
 