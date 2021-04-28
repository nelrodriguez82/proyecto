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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "entregable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entregable.findAll", query = "SELECT e FROM Entregable e")
    , @NamedQuery(name = "Entregable.findByIdEntregable", query = "SELECT e FROM Entregable e WHERE e.idEntregable = :idEntregable")
    , @NamedQuery(name = "Entregable.findByNombre", query = "SELECT e FROM Entregable e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Entregable.findByDescripcion", query = "SELECT e FROM Entregable e WHERE e.descripcion = :descripcion")})
public class Entregable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEntregable")
    private Integer idEntregable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "idComponente", referencedColumnName = "idComponente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Componente idComponente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntregable", fetch = FetchType.EAGER)
    private Collection<Grupotrabajo> grupotrabajoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntregable", fetch = FetchType.EAGER)
    private Collection<Listachequeo> listachequeoCollection;

    public Entregable() {
    }

    public Entregable(Integer idEntregable) {
        this.idEntregable = idEntregable;
    }

    public Entregable(Integer idEntregable, String nombre, String descripcion) {
        this.idEntregable = idEntregable;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdEntregable() {
        return idEntregable;
    }

    public void setIdEntregable(Integer idEntregable) {
        this.idEntregable = idEntregable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Componente getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Componente idComponente) {
        this.idComponente = idComponente;
    }

    @XmlTransient
    public Collection<Grupotrabajo> getGrupotrabajoCollection() {
        return grupotrabajoCollection;
    }

    public void setGrupotrabajoCollection(Collection<Grupotrabajo> grupotrabajoCollection) {
        this.grupotrabajoCollection = grupotrabajoCollection;
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
        hash += (idEntregable != null ? idEntregable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entregable)) {
            return false;
        }
        Entregable other = (Entregable) object;
        if ((this.idEntregable == null && other.idEntregable != null) || (this.idEntregable != null && !this.idEntregable.equals(other.idEntregable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
