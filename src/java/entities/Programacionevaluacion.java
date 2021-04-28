/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "programacionevaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programacionevaluacion.findAll", query = "SELECT p FROM Programacionevaluacion p")
    , @NamedQuery(name = "Programacionevaluacion.findByIdProgramacionEvaluacion", query = "SELECT p FROM Programacionevaluacion p WHERE p.idProgramacionEvaluacion = :idProgramacionEvaluacion")
    , @NamedQuery(name = "Programacionevaluacion.findByFecha", query = "SELECT p FROM Programacionevaluacion p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Programacionevaluacion.findByHora", query = "SELECT p FROM Programacionevaluacion p WHERE p.hora = :hora")})
public class Programacionevaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProgramacionEvaluacion")
    private Integer idProgramacionEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "hora")
    private String hora;
    @JoinColumn(name = "idFicha", referencedColumnName = "idFicha")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ficha idFicha;
    @JoinColumn(name = "idcomponente", referencedColumnName = "idComponente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Componente idcomponente;
    @JoinColumn(name = "idJurado", referencedColumnName = "idInstructor")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Instructor idJurado;

    public Programacionevaluacion() {
    }

    public Programacionevaluacion(Integer idProgramacionEvaluacion) {
        this.idProgramacionEvaluacion = idProgramacionEvaluacion;
    }

    public Programacionevaluacion(Integer idProgramacionEvaluacion, Date fecha, String hora) {
        this.idProgramacionEvaluacion = idProgramacionEvaluacion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getIdProgramacionEvaluacion() {
        return idProgramacionEvaluacion;
    }

    public void setIdProgramacionEvaluacion(Integer idProgramacionEvaluacion) {
        this.idProgramacionEvaluacion = idProgramacionEvaluacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Ficha getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Ficha idFicha) {
        this.idFicha = idFicha;
    }

    public Componente getIdcomponente() {
        return idcomponente;
    }

    public void setIdcomponente(Componente idcomponente) {
        this.idcomponente = idcomponente;
    }

    public Instructor getIdJurado() {
        return idJurado;
    }

    public void setIdJurado(Instructor idJurado) {
        this.idJurado = idJurado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramacionEvaluacion != null ? idProgramacionEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacionevaluacion)) {
            return false;
        }
        Programacionevaluacion other = (Programacionevaluacion) object;
        if ((this.idProgramacionEvaluacion == null && other.idProgramacionEvaluacion != null) || (this.idProgramacionEvaluacion != null && !this.idProgramacionEvaluacion.equals(other.idProgramacionEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idProgramacionEvaluacion.toString();
    }
    
}
