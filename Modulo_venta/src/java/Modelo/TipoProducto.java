/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
@Table(name = "tipo_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProducto.findAll", query = "SELECT t FROM TipoProducto t")
    , @NamedQuery(name = "TipoProducto.findByNomtipoproducto", query = "SELECT t FROM TipoProducto t WHERE t.nomtipoproducto = :nomtipoproducto")
    , @NamedQuery(name = "TipoProducto.findByDescripcion", query = "SELECT t FROM TipoProducto t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoProducto.findByTipoproductoID", query = "SELECT t FROM TipoProducto t WHERE t.tipoproductoID = :tipoproductoID")})
public class TipoProducto implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nom_tipoproducto")
    private String nomtipoproducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descripcion")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_productoID")
    private Integer tipoproductoID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoproductoID")
    private Collection<Producto> productoCollection;

    public TipoProducto() {
    }

    public TipoProducto(Integer tipoproductoID) {
        this.tipoproductoID = tipoproductoID;
    }

    public TipoProducto(Integer tipoproductoID, String nomtipoproducto, String descripcion) {
        this.tipoproductoID = tipoproductoID;
        this.nomtipoproducto = nomtipoproducto;
        this.descripcion = descripcion;
    }

    public String getNomtipoproducto() {
        return nomtipoproducto;
    }

    public void setNomtipoproducto(String nomtipoproducto) {
        this.nomtipoproducto = nomtipoproducto;
    }


    public Integer getTipoproductoID() {
        return tipoproductoID;
    }

    public void setTipoproductoID(Integer tipoproductoID) {
        this.tipoproductoID = tipoproductoID;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoproductoID != null ? tipoproductoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProducto)) {
            return false;
        }
        TipoProducto other = (TipoProducto) object;
        if ((this.tipoproductoID == null && other.tipoproductoID != null) || (this.tipoproductoID != null && !this.tipoproductoID.equals(other.tipoproductoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.TipoProducto[ tipoproductoID=" + tipoproductoID + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
