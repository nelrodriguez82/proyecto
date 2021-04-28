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
@Table(name = "grupotrabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupotrabajo.findAll", query = "SELECT g FROM Grupotrabajo g")
    , @NamedQuery(name = "Grupotrabajo.findByIdGrupoTrabajo", query = "SELECT g FROM Grupotrabajo g WHERE g.idGrupoTrabajo = :idGrupoTrabajo")
    , @NamedQuery(name = "Grupotrabajo.findByNombreGrupo", query = "SELECT g FROM Grupotrabajo g WHERE g.nombreGrupo = :nombreGrupo")})
public class Grupotrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupoTrabajo")
    private Integer idGrupoTrabajo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreGrupo")
    private String nombreGrupo;
    @OneToMany(mappedBy = "idGrupoTrabajo", fetch = FetchType.EAGER)
    private Collection<Evaluacion> evaluacionCollection;
    @JoinColumn(name = "idEntregable", referencedColumnName = "idEntregable")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Entregable idEntregable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo", fetch = FetchType.EAGER)
    private Collection<Grupoaprendiz> grupoaprendizCollection;

    public Grupotrabajo() {
    }

    public Grupotrabajo(Integer idGrupoTrabajo) {
        this.idGrupoTrabajo = idGrupoTrabajo;
    }

    public Grupotrabajo(Integer idGrupoTrabajo, String nombreGrupo) {
        this.idGrupoTrabajo = idGrupoTrabajo;
        this.nombreGrupo = nombreGrupo;
    }

    public Integer getIdGrupoTrabajo() {
        return idGrupoTrabajo;
    }

    public void setIdGrupoTrabajo(Integer idGrupoTrabajo) {
        this.idGrupoTrabajo = idGrupoTrabajo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    @XmlTransient
    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }

    public Entregable getIdEntregable() {
        return idEntregable;
    }

    public void setIdEntregable(Entregable idEntregable) {
        this.idEntregable = idEntregable;
    }

    @XmlTransient
    public Collection<Grupoaprendiz> getGrupoaprendizCollection() {
        return grupoaprendizCollection;
    }

    public void setGrupoaprendizCollection(Collection<Grupoaprendiz> grupoaprendizCollection) {
        this.grupoaprendizCollection = grupoaprendizCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoTrabajo != null ? idGrupoTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupotrabajo)) {
            return false;
        }
        Grupotrabajo other = (Grupotrabajo) object;
        if ((this.idGrupoTrabajo == null && other.idGrupoTrabajo != null) || (this.idGrupoTrabajo != null && !this.idGrupoTrabajo.equals(other.idGrupoTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreGrupo;
    }
    
}
