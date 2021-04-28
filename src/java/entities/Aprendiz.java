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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "aprendiz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aprendiz.findAll", query = "SELECT a FROM Aprendiz a")
    , @NamedQuery(name = "Aprendiz.findByIdAprendiz", query = "SELECT a FROM Aprendiz a WHERE a.idAprendiz = :idAprendiz")
    , @NamedQuery(name = "Aprendiz.findByEstrato", query = "SELECT a FROM Aprendiz a WHERE a.estrato = :estrato")
    , @NamedQuery(name = "Aprendiz.findByDiscapacidad", query = "SELECT a FROM Aprendiz a WHERE a.discapacidad = :discapacidad")})
public class Aprendiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAprendiz")
    private Integer idAprendiz;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estrato")
    private int estrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "discapacidad")
    private String discapacidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAprendiz", fetch = FetchType.EAGER)
    private Collection<Grupoaprendiz> grupoaprendizCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAprendiz", fetch = FetchType.EAGER)
    private Collection<Matricula> matriculaCollection;
    @JoinColumn(name = "idAprendiz", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    public Aprendiz() {
    }

    public Aprendiz(Integer idAprendiz) {
        this.idAprendiz = idAprendiz;
    }

    public Aprendiz(Integer idAprendiz, int estrato, String discapacidad) {
        this.idAprendiz = idAprendiz;
        this.estrato = estrato;
        this.discapacidad = discapacidad;
    }

    public Integer getIdAprendiz() {
        return idAprendiz;
    }

    public void setIdAprendiz(Integer idAprendiz) {
        this.idAprendiz = idAprendiz;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    @XmlTransient
    public Collection<Grupoaprendiz> getGrupoaprendizCollection() {
        return grupoaprendizCollection;
    }

    public void setGrupoaprendizCollection(Collection<Grupoaprendiz> grupoaprendizCollection) {
        this.grupoaprendizCollection = grupoaprendizCollection;
    }

    @XmlTransient
    public Collection<Matricula> getMatriculaCollection() {
        return matriculaCollection;
    }

    public void setMatriculaCollection(Collection<Matricula> matriculaCollection) {
        this.matriculaCollection = matriculaCollection;
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
        hash += (idAprendiz != null ? idAprendiz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aprendiz)) {
            return false;
        }
        Aprendiz other = (Aprendiz) object;
        if ((this.idAprendiz == null && other.idAprendiz != null) || (this.idAprendiz != null && !this.idAprendiz.equals(other.idAprendiz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return usuario.getNombres() + " "+ usuario.getApellidos();
    }
    
}
