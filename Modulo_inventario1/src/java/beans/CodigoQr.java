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
import javax.persistence.Lob;
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
@Table(name = "codigo_qr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CodigoQr.findAll", query = "SELECT c FROM CodigoQr c")
    , @NamedQuery(name = "CodigoQr.findByCodigoqrID", query = "SELECT c FROM CodigoQr c WHERE c.codigoqrID = :codigoqrID")})
public class CodigoQr implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "Contenido")
    private byte[] contenido;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Codigo_qrID")
    private Integer codigoqrID;
    @JoinColumn(name = "ProductoID", referencedColumnName = "ProductoID")
    @ManyToOne(optional = false)
    private String productoID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Integer usuarioID;

    public CodigoQr() {
    }

    public CodigoQr(Integer codigoqrID) {
        this.codigoqrID = codigoqrID;
    }

    public CodigoQr(Integer codigoqrID, byte[] contenido,String productoID, Integer usuarioID) {
        this.codigoqrID = codigoqrID;
        this.contenido = contenido;
        this.productoID = productoID;
        this.usuarioID = usuarioID;
    }


    public Integer getCodigoqrID() {
        return codigoqrID;
    }

    public void setCodigoqrID(Integer codigoqrID) {
        this.codigoqrID = codigoqrID;
    }

    public String getProductoID() {
        return productoID;
    }

    public void setProductoID(String productoID) {
        this.productoID = productoID;
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
        hash += (codigoqrID != null ? codigoqrID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodigoQr)) {
            return false;
        }
        CodigoQr other = (CodigoQr) object;
        if ((this.codigoqrID == null && other.codigoqrID != null) || (this.codigoqrID != null && !this.codigoqrID.equals(other.codigoqrID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.CodigoQr[ codigoqrID=" + codigoqrID + " ]";
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
    
}
