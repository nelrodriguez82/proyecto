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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "coordinador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coordinador.findAll", query = "SELECT c FROM Coordinador c")
    , @NamedQuery(name = "Coordinador.findByIdCoordinador", query = "SELECT c FROM Coordinador c WHERE c.idCoordinador = :idCoordinador")
    , @NamedQuery(name = "Coordinador.findByCoordinacion", query = "SELECT c FROM Coordinador c WHERE c.coordinacion = :coordinacion")
    , @NamedQuery(name = "Coordinador.findByProfesion", query = "SELECT c FROM Coordinador c WHERE c.profesion = :profesion")})
public class Coordinador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCoordinador")
    private Integer idCoordinador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "coordinacion")
    private String coordinacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "profesion")
    private String profesion;
    @JoinColumn(name = "idCoordinador", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    public Coordinador() {
    }

    public Coordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public Coordinador(Integer idCoordinador, String coordinacion, String profesion) {
        this.idCoordinador = idCoordinador;
        this.coordinacion = coordinacion;
        this.profesion = profesion;
    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public String getCoordinacion() {
        return coordinacion;
    }

    public void setCoordinacion(String coordinacion) {
        this.coordinacion = coordinacion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoordinador != null ? idCoordinador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coordinador)) {
            return false;
        }
        Coordinador other = (Coordinador) object;
        if ((this.idCoordinador == null && other.idCoordinador != null) || (this.idCoordinador != null && !this.idCoordinador.equals(other.idCoordinador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return usuario.getNombres() + " "+ usuario.getApellidos();
    }
    
}
