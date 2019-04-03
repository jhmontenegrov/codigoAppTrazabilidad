/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.*;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhona
 */
@Entity
@Table(name = "asignada_perfil_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignadaPerfilUsuario.findAll", query = "SELECT a FROM AsignadaPerfilUsuario a")
    , @NamedQuery(name = "AsignadaPerfilUsuario.findByIdusuarioasignado", query = "SELECT a FROM AsignadaPerfilUsuario a WHERE a.idusuarioasignado = :idusuarioasignado")
    , @NamedQuery(name = "AsignadaPerfilUsuario.findByAsignadaperfilusuarioID", query = "SELECT a FROM AsignadaPerfilUsuario a WHERE a.asignadaperfilusuarioID = :asignadaperfilusuarioID")})
public class AsignadaPerfilUsuario implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Usuario_Asigna")
    private int usuarioAsigna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UsuarioID")
    private int usuarioID;


    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_usuario_asignado")
    private int idusuarioasignado;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Asignada_perfil_usuarioID")
    private Integer asignadaperfilusuarioID;
    @JoinColumn(name = "ActivacionID", referencedColumnName = "ActivacionID")
    @ManyToOne(optional = false)
    private int activacionID;
    @JoinColumn(name = "Actividades_perfil_usuarioID", referencedColumnName = "Actividades_perfil_usuarioID")
    @ManyToOne(optional = false)
    private int actividadesperfilusuarioID;

    public AsignadaPerfilUsuario() {
    }

    public AsignadaPerfilUsuario(Integer asignadaperfilusuarioID) {
        this.asignadaperfilusuarioID = asignadaperfilusuarioID;
    }

    public AsignadaPerfilUsuario(Integer idusuarioasignado, Integer asignada_perfil_usuarioID, Integer usuarioID, Integer activacionID, Integer actividades_perfil_usuarioID) {
        this.idusuarioasignado = idusuarioasignado;
        this.asignadaperfilusuarioID=asignada_perfil_usuarioID;
        this.usuarioID = usuarioID;
        this.activacionID=activacionID;
        this.actividadesperfilusuarioID=actividades_perfil_usuarioID;
    }

    public int getIdusuarioasignado() {
        return idusuarioasignado;
    }

    public void setIdusuarioasignado(int idusuarioasignado) {
        this.idusuarioasignado = idusuarioasignado;
    }

    public Integer getAsignadaperfilusuarioID() {
        return asignadaperfilusuarioID;
    }

    public void setAsignadaperfilusuarioID(Integer asignadaperfilusuarioID) {
        this.asignadaperfilusuarioID = asignadaperfilusuarioID;
    }

    public int getActivacionID() {
        return activacionID;
    }

    public void setActivacionID(int activacionID) {
        this.activacionID = activacionID;
    }

    public int getActividadesperfilusuarioID() {
        return actividadesperfilusuarioID;
    }

    public void setActividadesperfilusuarioID(int actividadesperfilusuarioID) {
        this.actividadesperfilusuarioID = actividadesperfilusuarioID;
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
        hash += (asignadaperfilusuarioID != null ? asignadaperfilusuarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignadaPerfilUsuario)) {
            return false;
        }
        AsignadaPerfilUsuario other = (AsignadaPerfilUsuario) object;
        if ((this.asignadaperfilusuarioID == null && other.asignadaperfilusuarioID != null) || (this.asignadaperfilusuarioID != null && !this.asignadaperfilusuarioID.equals(other.asignadaperfilusuarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AsignadaPerfilUsuario[ asignadaperfilusuarioID=" + asignadaperfilusuarioID + " ]";
    }

    public int getUsuarioAsigna() {
        return usuarioAsigna;
    }

    public void setUsuarioAsigna(int usuarioAsigna) {
        this.usuarioAsigna = usuarioAsigna;
    }
}
