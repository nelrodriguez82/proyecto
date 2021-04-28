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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "evaluacionitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacionitem.findAll", query = "SELECT e FROM Evaluacionitem e")
    , @NamedQuery(name = "Evaluacionitem.findByIdEvaluacionItem", query = "SELECT e FROM Evaluacionitem e WHERE e.idEvaluacionItem = :idEvaluacionItem")
    , @NamedQuery(name = "Evaluacionitem.findByResultado", query = "SELECT e FROM Evaluacionitem e WHERE e.resultado = :resultado")
    , @NamedQuery(name = "Evaluacionitem.findByObservacion", query = "SELECT e FROM Evaluacionitem e WHERE e.observacion = :observacion")})
public class Evaluacionitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEvaluacionItem")
    private Integer idEvaluacionItem;
    @Size(max = 20)
    @Column(name = "resultado")
    private String resultado;
    @Size(max = 255)
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "idEvaluacion", referencedColumnName = "idEvaluacion")
    @ManyToOne(fetch = FetchType.EAGER)
    private Evaluacion idEvaluacion;
    @JoinColumn(name = "idItem", referencedColumnName = "idItemEvaluacion")
    @ManyToOne(fetch = FetchType.EAGER)
    private Itemevaluacion idItem;

    public Evaluacionitem() {
    }

    public Evaluacionitem(Integer idEvaluacionItem) {
        this.idEvaluacionItem = idEvaluacionItem;
    }

    public Integer getIdEvaluacionItem() {
        return idEvaluacionItem;
    }

    public void setIdEvaluacionItem(Integer idEvaluacionItem) {
        this.idEvaluacionItem = idEvaluacionItem;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Evaluacion getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Evaluacion idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Itemevaluacion getIdItem() {
        return idItem;
    }

    public void setIdItem(Itemevaluacion idItem) {
        this.idItem = idItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacionItem != null ? idEvaluacionItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacionitem)) {
            return false;
        }
        Evaluacionitem other = (Evaluacionitem) object;
        if ((this.idEvaluacionItem == null && other.idEvaluacionItem != null) || (this.idEvaluacionItem != null && !this.idEvaluacionItem.equals(other.idEvaluacionItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evaluacionitem[ idEvaluacionItem=" + idEvaluacionItem + " ]";
    }
    
}
