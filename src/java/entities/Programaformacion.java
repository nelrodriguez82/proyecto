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
@Table(name = "programaformacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programaformacion.findAll", query = "SELECT p FROM Programaformacion p")
    , @NamedQuery(name = "Programaformacion.findByIdProgramaFormacion", query = "SELECT p FROM Programaformacion p WHERE p.idProgramaFormacion = :idProgramaFormacion")
    , @NamedQuery(name = "Programaformacion.findByNombrePrograma", query = "SELECT p FROM Programaformacion p WHERE p.nombrePrograma = :nombrePrograma")
    , @NamedQuery(name = "Programaformacion.findByDuracion", query = "SELECT p FROM Programaformacion p WHERE p.duracion = :duracion")})
public class Programaformacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProgramaFormacion")
    private Integer idProgramaFormacion;
    @Size(max = 50)
    @Column(name = "nombrePrograma")
    private String nombrePrograma;
    @Column(name = "duracion")
    private Integer duracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProgramaFormacion", fetch = FetchType.EAGER)
    private Collection<Resultadoaprendizaje> resultadoaprendizajeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrograma", fetch = FetchType.EAGER)
    private Collection<Ficha> fichaCollection;

    public Programaformacion() {
    }

    public Programaformacion(Integer idProgramaFormacion) {
        this.idProgramaFormacion = idProgramaFormacion;
    }

    public Integer getIdProgramaFormacion() {
        return idProgramaFormacion;
    }

    public void setIdProgramaFormacion(Integer idProgramaFormacion) {
        this.idProgramaFormacion = idProgramaFormacion;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    @XmlTransient
    public Collection<Resultadoaprendizaje> getResultadoaprendizajeCollection() {
        return resultadoaprendizajeCollection;
    }

    public void setResultadoaprendizajeCollection(Collection<Resultadoaprendizaje> resultadoaprendizajeCollection) {
        this.resultadoaprendizajeCollection = resultadoaprendizajeCollection;
    }

    @XmlTransient
    public Collection<Ficha> getFichaCollection() {
        return fichaCollection;
    }

    public void setFichaCollection(Collection<Ficha> fichaCollection) {
        this.fichaCollection = fichaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramaFormacion != null ? idProgramaFormacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programaformacion)) {
            return false;
        }
        Programaformacion other = (Programaformacion) object;
        if ((this.idProgramaFormacion == null && other.idProgramaFormacion != null) || (this.idProgramaFormacion != null && !this.idProgramaFormacion.equals(other.idProgramaFormacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombrePrograma;
    }
    
}
