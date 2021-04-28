/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author usuario
 */
@Entity
@Table(name = "itemevaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemevaluacion.findAll", query = "SELECT i FROM Itemevaluacion i")
    , @NamedQuery(name = "Itemevaluacion.findByIdItemEvaluacion", query = "SELECT i FROM Itemevaluacion i WHERE i.idItemEvaluacion = :idItemEvaluacion")
    , @NamedQuery(name = "Itemevaluacion.findByDescripcion", query = "SELECT i FROM Itemevaluacion i WHERE i.descripcion = :descripcion")})
public class Itemevaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idItemEvaluacion")
    private Integer idItemEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idItem", fetch = FetchType.EAGER)
    private Collection<Evaluacionitem> evaluacionitemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItem", fetch = FetchType.EAGER)
    private Collection<Listachequeo> listachequeoCollection;

    public Itemevaluacion() {
    }

    public Itemevaluacion(Integer idItemEvaluacion) {
        this.idItemEvaluacion = idItemEvaluacion;
    }

    public Itemevaluacion(Integer idItemEvaluacion, String descripcion) {
        this.idItemEvaluacion = idItemEvaluacion;
        this.descripcion = descripcion;
    }

    public Integer getIdItemEvaluacion() {
        return idItemEvaluacion;
    }

    public void setIdItemEvaluacion(Integer idItemEvaluacion) {
        this.idItemEvaluacion = idItemEvaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Evaluacionitem> getEvaluacionitemCollection() {
        return evaluacionitemCollection;
    }

    public void setEvaluacionitemCollection(Collection<Evaluacionitem> evaluacionitemCollection) {
        this.evaluacionitemCollection = evaluacionitemCollection;
    }

    @XmlTransient
    public Collection<Listachequeo> getListachequeoCollection() {
        return listachequeoCollection;
    }

    public void setListachequeoCollection(Collection<Listachequeo> listachequeoCollection) {
        this.listachequeoCollection = listachequeoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemEvaluacion != null ? idItemEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemevaluacion)) {
            return false;
        }
        Itemevaluacion other = (Itemevaluacion) object;
        if ((this.idItemEvaluacion == null && other.idItemEvaluacion != null) || (this.idItemEvaluacion != null && !this.idItemEvaluacion.equals(other.idItemEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
