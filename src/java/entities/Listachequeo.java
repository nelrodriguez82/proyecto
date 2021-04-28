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
@Table(name = "listachequeo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listachequeo.findAll", query = "SELECT l FROM Listachequeo l")
    , @NamedQuery(name = "Listachequeo.findByIdListaChequeo", query = "SELECT l FROM Listachequeo l WHERE l.idListaChequeo = :idListaChequeo")})
public class Listachequeo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idListaChequeo")
    private Integer idListaChequeo;
    @JoinColumn(name = "idEntregable", referencedColumnName = "idEntregable")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Entregable idEntregable;
    @JoinColumn(name = "idItem", referencedColumnName = "idItemEvaluacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Itemevaluacion idItem;

    public Listachequeo() {
    }

    public Listachequeo(Integer idListaChequeo) {
        this.idListaChequeo = idListaChequeo;
    }

    public Integer getIdListaChequeo() {
        return idListaChequeo;
    }

    public void setIdListaChequeo(Integer idListaChequeo) {
        this.idListaChequeo = idListaChequeo;
    }

    public Entregable getIdEntregable() {
        return idEntregable;
    }

    public void setIdEntregable(Entregable idEntregable) {
        this.idEntregable = idEntregable;
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
        hash += (idListaChequeo != null ? idListaChequeo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listachequeo)) {
            return false;
        }
        Listachequeo other = (Listachequeo) object;
        if ((this.idListaChequeo == null && other.idListaChequeo != null) || (this.idListaChequeo != null && !this.idListaChequeo.equals(other.idListaChequeo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idListaChequeo.toString() ;
    }
    
}
