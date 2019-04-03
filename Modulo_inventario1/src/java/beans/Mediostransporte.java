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
@Table(name = "mediostransporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mediostransporte.findAll", query = "SELECT m FROM Mediostransporte m")
    , @NamedQuery(name = "Mediostransporte.findByMarca", query = "SELECT m FROM Mediostransporte m WHERE m.marca = :marca")
    , @NamedQuery(name = "Mediostransporte.findByPlaca", query = "SELECT m FROM Mediostransporte m WHERE m.placa = :placa")
    , @NamedQuery(name = "Mediostransporte.findByModelo", query = "SELECT m FROM Mediostransporte m WHERE m.modelo = :modelo")
    , @NamedQuery(name = "Mediostransporte.findByMediostransporteID", query = "SELECT m FROM Mediostransporte m WHERE m.mediostransporteID = :mediostransporteID")})
public class Mediostransporte implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Modelo")
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Placa")
    private String placa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mediostransporteID")
    private Collection<SalidaProducto> salidaProductoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MediostransporteID")
    private Integer mediostransporteID;

    public Mediostransporte() {
    }

    public Mediostransporte(Integer mediostransporteID) {
        this.mediostransporteID = mediostransporteID;
    }

    public Mediostransporte(Integer mediostransporteID, String marca, String placa, String modelo) {
        this.mediostransporteID = mediostransporteID;
        this.marca = marca;
        this.placa = placa;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getMediostransporteID() {
        return mediostransporteID;
    }

    public void setMediostransporteID(Integer mediostransporteID) {
        this.mediostransporteID = mediostransporteID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mediostransporteID != null ? mediostransporteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mediostransporte)) {
            return false;
        }
        Mediostransporte other = (Mediostransporte) object;
        if ((this.mediostransporteID == null && other.mediostransporteID != null) || (this.mediostransporteID != null && !this.mediostransporteID.equals(other.mediostransporteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Mediostransporte[ mediostransporteID=" + mediostransporteID + " ]";
    }

    @XmlTransient
    public Collection<SalidaProducto> getSalidaProductoCollection() {
        return salidaProductoCollection;
    }

    public void setSalidaProductoCollection(Collection<SalidaProducto> salidaProductoCollection) {
        this.salidaProductoCollection = salidaProductoCollection;
    }

}
