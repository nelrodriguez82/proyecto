/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "ficha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ficha.findAll", query = "SELECT f FROM Ficha f")
    , @NamedQuery(name = "Ficha.findByIdFicha", query = "SELECT f FROM Ficha f WHERE f.idFicha = :idFicha")
    , @NamedQuery(name = "Ficha.findByFechaInicio", query = "SELECT f FROM Ficha f WHERE f.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Ficha.findByFechaFin", query = "SELECT f FROM Ficha f WHERE f.fechaFin = :fechaFin")
    , @NamedQuery(name = "Ficha.findByCupo", query = "SELECT f FROM Ficha f WHERE f.cupo = :cupo")})
public class Ficha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFicha")
    private Integer idFicha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cupo")
    private int cupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFicha", fetch = FetchType.EAGER)
    private Collection<Programacionevaluacion> programacionevaluacionCollection;
    @JoinColumn(name = "idPrograma", referencedColumnName = "idProgramaFormacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Programaformacion idPrograma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFicha", fetch = FetchType.EAGER)
    private Collection<Fichacomponente> fichacomponenteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFicha", fetch = FetchType.EAGER)
    private Collection<Matricula> matriculaCollection;

    public Ficha() {
    }

    public Ficha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Ficha(Integer idFicha, Date fechaInicio, Date fechaFin, int cupo) {
        this.idFicha = idFicha;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cupo = cupo;
    }

    public Integer getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    @XmlTransient
    public Collection<Programacionevaluacion> getProgramacionevaluacionCollection() {
        return programacionevaluacionCollection;
    }

    public void setProgramacionevaluacionCollection(Collection<Programacionevaluacion> programacionevaluacionCollection) {
        this.programacionevaluacionCollection = programacionevaluacionCollection;
    }

    public Programaformacion getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Programaformacion idPrograma) {
        this.idPrograma = idPrograma;
    }

    @XmlTransient
    public Collection<Fichacomponente> getFichacomponenteCollection() {
        return fichacomponenteCollection;
    }

    public void setFichacomponenteCollection(Collection<Fichacomponente> fichacomponenteCollection) {
        this.fichacomponenteCollection = fichacomponenteCollection;
    }

    @XmlTransient
    public Collection<Matricula> getMatriculaCollection() {
        return matriculaCollection;
    }

    public void setMatriculaCollection(Collection<Matricula> matriculaCollection) {
        this.matriculaCollection = matriculaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFicha != null ? idFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ficha)) {
            return false;
        }
        Ficha other = (Ficha) object;
        if ((this.idFicha == null && other.idFicha != null) || (this.idFicha != null && !this.idFicha.equals(other.idFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  idFicha.toString();
    }
    
}
