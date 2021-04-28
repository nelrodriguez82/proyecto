/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "resultadocomponente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultadocomponente.findAll", query = "SELECT r FROM Resultadocomponente r")
    , @NamedQuery(name = "Resultadocomponente.findByIdResultadoComponente", query = "SELECT r FROM Resultadocomponente r WHERE r.idResultadoComponente = :idResultadoComponente")})
public class Resultadocomponente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResultadoComponente")
    private Integer idResultadoComponente;
    @JoinColumn(name = "idComponente", referencedColumnName = "idComponente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Componente idComponente;
    @JoinColumn(name = "idResultadoAprendizaje", referencedColumnName = "idResultadoAprendizaje")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Resultadoaprendizaje idResultadoAprendizaje;

    public Resultadocomponente() {
    }

    public Resultadocomponente(Integer idResultadoComponente) {
        this.idResultadoComponente = idResultadoComponente;
    }

    public Integer getIdResultadoComponente() {
        return idResultadoComponente;
    }

    public void setIdResultadoComponente(Integer idResultadoComponente) {
        this.idResultadoComponente = idResultadoComponente;
    }

    public Componente getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Componente idComponente) {
        this.idComponente = idComponente;
    }

    public Resultadoaprendizaje getIdResultadoAprendizaje() {
        return idResultadoAprendizaje;
    }

    public void setIdResultadoAprendizaje(Resultadoaprendizaje idResultadoAprendizaje) {
        this.idResultadoAprendizaje = idResultadoAprendizaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultadoComponente != null ? idResultadoComponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadocomponente)) {
            return false;
        }
        Resultadocomponente other = (Resultadocomponente) object;
        if ((this.idResultadoComponente == null && other.idResultadoComponente != null) || (this.idResultadoComponente != null && !this.idResultadoComponente.equals(other.idResultadoComponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idResultadoComponente.toString();
    }
    
}
