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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "instructor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instructor.findAll", query = "SELECT i FROM Instructor i")
    , @NamedQuery(name = "Instructor.findByIdInstructor", query = "SELECT i FROM Instructor i WHERE i.idInstructor = :idInstructor")
    , @NamedQuery(name = "Instructor.findByTipoVinculacion", query = "SELECT i FROM Instructor i WHERE i.tipoVinculacion = :tipoVinculacion")
    , @NamedQuery(name = "Instructor.findByProfesion", query = "SELECT i FROM Instructor i WHERE i.profesion = :profesion")})
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idInstructor")
    private Integer idInstructor;
    @Size(max = 45)
    @Column(name = "tipoVinculacion")
    private String tipoVinculacion;
    @Size(max = 45)
    @Column(name = "profesion")
    private String profesion;
    @OneToMany(mappedBy = "idInstructor", fetch = FetchType.EAGER)
    private Collection<Evaluacion> evaluacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJurado", fetch = FetchType.EAGER)
    private Collection<Programacionevaluacion> programacionevaluacionCollection;
    @JoinColumn(name = "idInstructor", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstructor", fetch = FetchType.EAGER)
    private Collection<Fichacomponente> fichacomponenteCollection;

    public Instructor() {
    }

    public Instructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
    }

    public Integer getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getTipoVinculacion() {
        return tipoVinculacion;
    }

    public void setTipoVinculacion(String tipoVinculacion) {
        this.tipoVinculacion = tipoVinculacion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @XmlTransient
    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }

    @XmlTransient
    public Collection<Programacionevaluacion> getProgramacionevaluacionCollection() {
        return programacionevaluacionCollection;
    }

    public void setProgramacionevaluacionCollection(Collection<Programacionevaluacion> programacionevaluacionCollection) {
        this.programacionevaluacionCollection = programacionevaluacionCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Fichacomponente> getFichacomponenteCollection() {
        return fichacomponenteCollection;
    }

    public void setFichacomponenteCollection(Collection<Fichacomponente> fichacomponenteCollection) {
        this.fichacomponenteCollection = fichacomponenteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstructor != null ? idInstructor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instructor)) {
            return false;
        }
        Instructor other = (Instructor) object;
        if ((this.idInstructor == null && other.idInstructor != null) || (this.idInstructor != null && !this.idInstructor.equals(other.idInstructor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return usuario.getNombres() + " "+usuario.getApellidos();
    }
    
}
