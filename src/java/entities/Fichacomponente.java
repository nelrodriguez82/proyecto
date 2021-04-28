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
@Table(name = "fichacomponente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fichacomponente.findAll", query = "SELECT f FROM Fichacomponente f")
    , @NamedQuery(name = "Fichacomponente.findByIdFichaComponente", query = "SELECT f FROM Fichacomponente f WHERE f.idFichaComponente = :idFichaComponente")})
public class Fichacomponente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFichaComponente")
    private Integer idFichaComponente;
    @JoinColumn(name = "idComponente", referencedColumnName = "idComponente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Componente idComponente;
    @JoinColumn(name = "idFicha", referencedColumnName = "idFicha")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ficha idFicha;
    @JoinColumn(name = "idInstructor", referencedColumnName = "idInstructor")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Instructor idInstructor;

    public Fichacomponente() {
    }

    public Fichacomponente(Integer idFichaComponente) {
        this.idFichaComponente = idFichaComponente;
    }

    public Integer getIdFichaComponente() {
        return idFichaComponente;
    }

    public void setIdFichaComponente(Integer idFichaComponente) {
        this.idFichaComponente = idFichaComponente;
    }

    public Componente getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Componente idComponente) {
        this.idComponente = idComponente;
    }

    public Ficha getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Ficha idFicha) {
        this.idFicha = idFicha;
    }

    public Instructor getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Instructor idInstructor) {
        this.idInstructor = idInstructor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFichaComponente != null ? idFichaComponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fichacomponente)) {
            return false;
        }
        Fichacomponente other = (Fichacomponente) object;
        if ((this.idFichaComponente == null && other.idFichaComponente != null) || (this.idFichaComponente != null && !this.idFichaComponente.equals(other.idFichaComponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  idFichaComponente.toString();
    }
    
}
