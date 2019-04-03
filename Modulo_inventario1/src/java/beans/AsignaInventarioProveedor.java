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
@Table(name = "asigna_inventario_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignaInventarioProveedor.findAll", query = "SELECT a FROM AsignaInventarioProveedor a")
    , @NamedQuery(name = "AsignaInventarioProveedor.findByAsignainventarioproveedorID", query = "SELECT a FROM AsignaInventarioProveedor a WHERE a.asignainventarioproveedorID = :asignainventarioproveedorID")})
public class AsignaInventarioProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Asigna_inventario_proveedorID")
    private Integer asignainventarioproveedorID;
    @JoinColumn(name = "InventarioID", referencedColumnName = "InventarioID")
    @ManyToOne(optional = false)
    private int inventarioID;
    @JoinColumn(name = "ProveedorID", referencedColumnName = "ProveedorID")
    @ManyToOne(optional = false)
    private int proveedorID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private int usuarioID;

    public AsignaInventarioProveedor() {
    }

    public void setAsignainventarioproveedorID (Integer asignainventarioproveedorID) {
        this.asignainventarioproveedorID = asignainventarioproveedorID;
    }

    public Integer getAsignainventarioproveedorID() {
        return asignainventarioproveedorID;
    }

    public AsignaInventarioProveedor(Integer asignainventarioproveedorID, Integer inventarioID, Integer proveedorID, Integer usuarioID) {
        this.asignainventarioproveedorID = asignainventarioproveedorID;
        this.inventarioID = inventarioID;
        this.proveedorID= proveedorID;
        this.usuarioID= usuarioID;
    }

    public int getInventarioID() {
        return inventarioID;
    }

    public void setInventarioID(int inventarioID) {
        this.inventarioID = inventarioID;
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
        hash += (asignainventarioproveedorID != null ? asignainventarioproveedorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignaInventarioProveedor)) {
            return false;
        }
        AsignaInventarioProveedor other = (AsignaInventarioProveedor) object;
        if ((this.asignainventarioproveedorID == null && other.asignainventarioproveedorID != null) || (this.asignainventarioproveedorID != null && !this.asignainventarioproveedorID.equals(other.asignainventarioproveedorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.AsignaInventarioProveedor[ asignainventarioproveedorID=" + asignainventarioproveedorID + " ]";
    }
    
}
