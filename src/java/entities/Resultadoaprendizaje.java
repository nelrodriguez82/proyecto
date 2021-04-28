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
import javax.persistence.Lob;
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
@Table(name = "resultadoaprendizaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultadoaprendizaje.findAll", query = "SELECT r FROM Resultadoaprendizaje r")
    , @NamedQuery(name = "Resultadoaprendizaje.findByIdResultadoAprendizaje", query = "SELECT r FROM Resultadoaprendizaje r WHERE r.idResultadoAprendizaje = :idResultadoAprendizaje")})
public class Resultadoaprendizaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "idResultadoAprendizaje")
    private String idResultadoAprendizaje;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "nombreResultado")
    private String nombreResultado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResultadoAprendizaje", fetch = FetchType.EAGER)
    private Collection<Resultadocomponente> resultadocomponenteCollection;
    @JoinColumn(name = "idProgramaFormacion", referencedColumnName = "idProgramaFormacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Programaformacion idProgramaFormacion;

    public Resultadoaprendizaje() {
    }

    public Resultadoaprendizaje(String idResultadoAprendizaje) {
        this.idResultadoAprendizaje = idResultadoAprendizaje;
    }

    public Resultadoaprendizaje(String idResultadoAprendizaje, String nombreResultado) {
        this.idResultadoAprendizaje = idResultadoAprendizaje;
        this.nombreResultado = nombreResultado;
    }

    public String getIdResultadoAprendizaje() {
        return idResultadoAprendizaje;
    }

    public void setIdResultadoAprendizaje(String idResultadoAprendizaje) {
        this.idResultadoAprendizaje = idResultadoAprendizaje;
    }

    public String getNombreResultado() {
        return nombreResultado;
    }

    public void setNombreResultado(String nombreResultado) {
        this.nombreResultado = nombreResultado;
    }

    @XmlTransient
    public Collection<Resultadocomponente> getResultadocomponenteCollection() {
        return resultadocomponenteCollection;
    }

    public void setResultadocomponenteCollection(Collection<Resultadocomponente> resultadocomponenteCollection) {
        this.resultadocomponenteCollection = resultadocomponenteCollection;
    }

    public Programaformacion getIdProgramaFormacion() {
        return idProgramaFormacion;
    }

    public void setIdProgramaFormacion(Programaformacion idProgramaFormacion) {
        this.idProgramaFormacion = idProgramaFormacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultadoAprendizaje != null ? idResultadoAprendizaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadoaprendizaje)) {
            return false;
        }
        Resultadoaprendizaje other = (Resultadoaprendizaje) object;
        if ((this.idResultadoAprendizaje == null && other.idResultadoAprendizaje != null) || (this.idResultadoAprendizaje != null && !this.idResultadoAprendizaje.equals(other.idResultadoAprendizaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreResultado;
    }
    
}
