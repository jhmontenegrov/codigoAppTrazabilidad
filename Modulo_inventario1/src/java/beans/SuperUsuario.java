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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhona
 */
@Entity
@Table(name = "super_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuperUsuario.findAll", query = "SELECT s FROM SuperUsuario s")
    , @NamedQuery(name = "SuperUsuario.findByNombre", query = "SELECT s FROM SuperUsuario s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SuperUsuario.findByApellido", query = "SELECT s FROM SuperUsuario s WHERE s.apellido = :apellido")
    , @NamedQuery(name = "SuperUsuario.findByUser", query = "SELECT s FROM SuperUsuario s WHERE s.user = :user")
    , @NamedQuery(name = "SuperUsuario.findByPassword", query = "SELECT s FROM SuperUsuario s WHERE s.password = :password")
    , @NamedQuery(name = "SuperUsuario.findBySuperusuarioID", query = "SELECT s FROM SuperUsuario s WHERE s.superusuarioID = :superusuarioID")})
public class SuperUsuario implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "User")
    private String user;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Password")
    private String password;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Super_usuarioID")
    private Integer superusuarioID;

    public SuperUsuario() {
    }

    public SuperUsuario(Integer superusuarioID) {
        this.superusuarioID = superusuarioID;
    }

    public SuperUsuario(Integer superusuarioID, String nombre, String apellido, String user, String password) {
        this.superusuarioID = superusuarioID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
        this.password = password;
    }


    public Integer getSuperusuarioID() {
        return superusuarioID;
    }

    public void setSuperusuarioID(Integer superusuarioID) {
        this.superusuarioID = superusuarioID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (superusuarioID != null ? superusuarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuperUsuario)) {
            return false;
        }
        SuperUsuario other = (SuperUsuario) object;
        if ((this.superusuarioID == null && other.superusuarioID != null) || (this.superusuarioID != null && !this.superusuarioID.equals(other.superusuarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SuperUsuario[ superusuarioID=" + superusuarioID + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
