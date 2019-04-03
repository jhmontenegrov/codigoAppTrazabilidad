/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Trabajo
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByNomproducto", query = "SELECT p FROM Producto p WHERE p.nomproducto = :nomproducto")
    , @NamedQuery(name = "Producto.findByDescproducto", query = "SELECT p FROM Producto p WHERE p.descproducto = :descproducto")
    , @NamedQuery(name = "Producto.findByFechlaboracion", query = "SELECT p FROM Producto p WHERE p.fechlaboracion = :fechlaboracion")
    , @NamedQuery(name = "Producto.findByFechvencimiento", query = "SELECT p FROM Producto p WHERE p.fechvencimiento = :fechvencimiento")
    , @NamedQuery(name = "Producto.findByFechnotificacion", query = "SELECT p FROM Producto p WHERE p.fechnotificacion = :fechnotificacion")
    , @NamedQuery(name = "Producto.findByMarca", query = "SELECT p FROM Producto p WHERE p.marca = :marca")
    , @NamedQuery(name = "Producto.findByLote", query = "SELECT p FROM Producto p WHERE p.lote = :lote")
    , @NamedQuery(name = "Producto.findByVlorproducto", query = "SELECT p FROM Producto p WHERE p.vlorproducto = :vlorproducto")
    , @NamedQuery(name = "Producto.findByProducNo", query = "SELECT p FROM Producto p WHERE p.producNo = :producNo")
    , @NamedQuery(name = "Producto.findByProductoID", query = "SELECT p FROM Producto p WHERE p.productoID = :productoID")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nom_producto")
    private String nomproducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Desc_producto")
    private String descproducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fech_laboracion")
    @Temporal(TemporalType.DATE)
    private Date fechlaboracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fech_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechvencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fech_notificacion")
    @Temporal(TemporalType.DATE)
    private Date fechnotificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lote")
    private String lote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Vlor_producto")
    private double vlorproducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "producNo")
    private int producNo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ProductoID")
    private String productoID;
    @JoinColumn(name = "Tipo_productoID", referencedColumnName = "Tipo_productoID")
    @ManyToOne(optional = false)
    private int tipoproductoID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private int usuarioID;

    public Producto() {
    }

    public Producto(String productoID) {
        this.productoID = productoID;
    }

    public Producto(String nomproducto, String descproducto, Date fechlaboracion, Date fechvencimiento, Date fechnotificacion, String marca, String lote, double vlorproducto, int producNo, String productoID, int tipoproductoID, int usuarioID) {
        this.nomproducto = nomproducto;
        this.descproducto = descproducto;
        this.fechlaboracion = fechlaboracion;
        this.fechvencimiento = fechvencimiento;
        this.fechnotificacion = fechnotificacion;
        this.marca = marca;
        this.lote = lote;
        this.vlorproducto = vlorproducto;
        this.producNo = producNo;
        this.productoID = productoID;
        this.tipoproductoID = tipoproductoID;
        this.usuarioID = usuarioID;
    }

    public String getNomproducto() {
        return nomproducto;
    }

    public void setNomproducto(String nomproducto) {
        this.nomproducto = nomproducto;
    }

    public String getDescproducto() {
        return descproducto;
    }

    public void setDescproducto(String descproducto) {
        this.descproducto = descproducto;
    }

    public Date getFechlaboracion() {
        return fechlaboracion;
    }

    public void setFechlaboracion(Date fechlaboracion) {
        this.fechlaboracion = fechlaboracion;
    }

    public Date getFechvencimiento() {
        return fechvencimiento;
    }

    public void setFechvencimiento(Date fechvencimiento) {
        this.fechvencimiento = fechvencimiento;
    }

    public Date getFechnotificacion() {
        return fechnotificacion;
    }

    public void setFechnotificacion(Date fechnotificacion) {
        this.fechnotificacion = fechnotificacion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public double getVlorproducto() {
        return vlorproducto;
    }

    public void setVlorproducto(double vlorproducto) {
        this.vlorproducto = vlorproducto;
    }
    public int getProducNo() {
        return producNo;
    }

    public void setProducNo(int producNo) {
        this.producNo = producNo;
    }

    public String getProductoID() {
        return productoID;
    }

    public void setProductoID(String productoID) {
        this.productoID = productoID;
    }

    public int getTipoproductoID() {
        return tipoproductoID;
    }

    public void setTipoproductoID(int tipoproductoID) {
        this.tipoproductoID = tipoproductoID;
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
        hash += (productoID != null ? productoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        return !((this.productoID == null && other.productoID != null) || (this.productoID != null && !this.productoID.equals(other.productoID)));
    }

    @Override
    public String toString() {
        return "beans.Producto[ productoID=" + productoID + " ]";
    }

}
