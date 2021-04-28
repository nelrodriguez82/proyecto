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
@Table(name = "componente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Componente.findAll", query = "SELECT c FROM Componente c")
    , @NamedQuery(name = "Componente.findByIdComponente", query = "SELECT c FROM Componente c WHERE c.idComponente = :idComponente")
    , @NamedQuery(name = "Componente.findByNombreComponente", query = "SELECT c FROM Componente c WHERE c.nombreComponente = :nombreComponente")
    , @NamedQuery(name = "Componente.findByFase", query = "SELECT c FROM Componente c WHERE c.fase = :fase")})
public class Componente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idComponente")
    private Integer idComponente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombreComponente")
    private String nombreComponente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "fase")
    private String fase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComponente", fetch = FetchType.EAGER)
    private Collection<Entregable> entregableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComponente", fetch = FetchType.EAGER)
    private Collection<Resultadocomponente> resultadocomponenteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcomponente", fetch = FetchType.EAGER)
    private Collection<Programacionevaluacion> programacionevaluacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComponente", fetch = FetchType.EAGER)
    private Collection<Tema> temaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComponente", fetch = FetchType.EAGER)
    private Collection<Fichacomponente> fichacomponenteCollection;

    public Componente() {
    }

    public Componente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public Componente(Integer idComponente, String nombreComponente, String fase) {
        this.idComponente = idComponente;
        this.nombreComponente = nombreComponente;
        this.fase = fase;
    }

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public void setNombreComponente(String nombreComponente) {
        this.nombreComponente = nombreComponente;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    @XmlTransient
    public Collection<Entregable> getEntregableCollection() {
        return entregableCollection;
    }

    public void setEntregableCollection(Collection<Entregable> entregableCollection) {
        this.entregableCollection = entregableCollection;
    }

    @XmlTransient
    public Collection<Resultadocomponente> getResultadocomponenteCollection() {
        return resultadocomponenteCollection;
    }

    public void setResultadocomponenteCollection(Collection<Resultadocomponente> resultadocomponenteCollection) {
        this.resultadocomponenteCollection = resultadocomponenteCollection;
    }

    @XmlTransient
    public Collection<Programacionevaluacion> getProgramacionevaluacionCollection() {
        return programacionevaluacionCollection;
    }

    public void setProgramacionevaluacionCollection(Collection<Programacionevaluacion> programacionevaluacionCollection) {
        this.programacionevaluacionCollection = programacionevaluacionCollection;
    }

    @XmlTransient
    public Collection<Tema> getTemaCollection() {
        return temaCollection;
    }

    public void setTemaCollection(Collection<Tema> temaCollection) {
        this.temaCollection = temaCollection;
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
        hash += (idComponente != null ? idComponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componente)) {
            return false;
        }
        Componente other = (Componente) object;
        if ((this.idComponente == null && other.idComponente != null) || (this.idComponente != null && !this.idComponente.equals(other.idComponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreComponente;
    }
    
}
