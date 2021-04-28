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
@Table(name = "grupoaprendiz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupoaprendiz.findAll", query = "SELECT g FROM Grupoaprendiz g")
    , @NamedQuery(name = "Grupoaprendiz.findByIdGrupoAprendiz", query = "SELECT g FROM Grupoaprendiz g WHERE g.idGrupoAprendiz = :idGrupoAprendiz")})
public class Grupoaprendiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupoAprendiz")
    private Integer idGrupoAprendiz;
    @JoinColumn(name = "idAprendiz", referencedColumnName = "idAprendiz")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Aprendiz idAprendiz;
    @JoinColumn(name = "idGrupo", referencedColumnName = "idGrupoTrabajo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Grupotrabajo idGrupo;

    public Grupoaprendiz() {
    }

    public Grupoaprendiz(Integer idGrupoAprendiz) {
        this.idGrupoAprendiz = idGrupoAprendiz;
    }

    public Integer getIdGrupoAprendiz() {
        return idGrupoAprendiz;
    }

    public void setIdGrupoAprendiz(Integer idGrupoAprendiz) {
        this.idGrupoAprendiz = idGrupoAprendiz;
    }

    public Aprendiz getIdAprendiz() {
        return idAprendiz;
    }

    public void setIdAprendiz(Aprendiz idAprendiz) {
        this.idAprendiz = idAprendiz;
    }

    public Grupotrabajo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupotrabajo idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoAprendiz != null ? idGrupoAprendiz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoaprendiz)) {
            return false;
        }
        Grupoaprendiz other = (Grupoaprendiz) object;
        if ((this.idGrupoAprendiz == null && other.idGrupoAprendiz != null) || (this.idGrupoAprendiz != null && !this.idGrupoAprendiz.equals(other.idGrupoAprendiz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  idGrupoAprendiz.toString();
    }
    
}
