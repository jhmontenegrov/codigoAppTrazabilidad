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
@Table(name = "actividades_perfil_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadesPerfilUsuario.findAll", query = "SELECT a FROM ActividadesPerfilUsuario a")
    , @NamedQuery(name = "ActividadesPerfilUsuario.findByActividades", query = "SELECT a FROM ActividadesPerfilUsuario a WHERE a.actividades = :actividades")
    , @NamedQuery(name = "ActividadesPerfilUsuario.findByActividadesperfilusuarioID", query = "SELECT a FROM ActividadesPerfilUsuario a WHERE a.actividadesperfilusuarioID = :actividadesperfilusuarioID")})
public class ActividadesPerfilUsuario implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Actividades")
    private String actividades;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Posicion_menu")
    private String posicionmenu;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Actividades_perfil_usuarioID")
    private Integer actividadesperfilusuarioID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadesperfilusuarioID")
    private Collection<AsignadaPerfilUsuario> asignadaPerfilUsuarioCollection;

    public ActividadesPerfilUsuario() {
    }

    public ActividadesPerfilUsuario(Integer actividadesperfilusuarioID) {
        this.actividadesperfilusuarioID = actividadesperfilusuarioID;
    }

    public ActividadesPerfilUsuario(Integer actividadesperfilusuarioID, String actividades) {
        this.actividadesperfilusuarioID = actividadesperfilusuarioID;
        this.actividades = actividades;
    }


    public Integer getActividadesperfilusuarioID() {
        return actividadesperfilusuarioID;
    }

    public void setActividadesperfilusuarioID(Integer actividadesperfilusuarioID) {
        this.actividadesperfilusuarioID = actividadesperfilusuarioID;
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
        hash += (actividadesperfilusuarioID != null ? actividadesperfilusuarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadesPerfilUsuario)) {
            return false;
        }
        ActividadesPerfilUsuario other = (ActividadesPerfilUsuario) object;
        if ((this.actividadesperfilusuarioID == null && other.actividadesperfilusuarioID != null) || (this.actividadesperfilusuarioID != null && !this.actividadesperfilusuarioID.equals(other.actividadesperfilusuarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ActividadesPerfilUsuario[ actividadesperfilusuarioID=" + actividadesperfilusuarioID + " ]";
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPosicionmenu() {
        return posicionmenu;
    }

    public void setPosicionmenu(String posicionmenu) {
        this.posicionmenu = posicionmenu;
    }
    
}
