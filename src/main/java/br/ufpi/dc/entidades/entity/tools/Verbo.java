/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufpi.dc.entidades.entity.tools;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author helcio.soares
 */
@Entity
@Table(name = "verbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Verbo.findAll", query = "SELECT v FROM Verbo v"),
    @NamedQuery(name = "Verbo.findById", query = "SELECT v FROM Verbo v WHERE v.id = :id"),
    @NamedQuery(name = "Verbo.findByDe", query = "SELECT v FROM Verbo v WHERE v.de = :de"),
    @NamedQuery(name = "Verbo.findByProx", query = "SELECT v FROM Verbo v WHERE v.prox = :prox")})
public class Verbo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "de")
    private String de;
    @Column(name = "prox")
    private String prox;
//    @OneToMany(mappedBy = "idVerbo")
//    private Collection<Proximo> proximoCollection;
//    @OneToMany(mappedBy = "idVerbo")
//    private Collection<Sinonimo> sinonimoCollection;

    public Verbo() {
    }

    public Verbo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getProx() {
        return prox;
    }

    public void setProx(String prox) {
        this.prox = prox;
    }

//    @XmlTransient
//    public Collection<Proximo> getProximoCollection() {
//        return proximoCollection;
//    }
//
//    public void setProximoCollection(Collection<Proximo> proximoCollection) {
//        this.proximoCollection = proximoCollection;
//    }
//
//    @XmlTransient
//    public Collection<Sinonimo> getSinonimoCollection() {
//        return sinonimoCollection;
//    }
//
//    public void setSinonimoCollection(Collection<Sinonimo> sinonimoCollection) {
//        this.sinonimoCollection = sinonimoCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Verbo)) {
            return false;
        }
        Verbo other = (Verbo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.tools.Verbo[ id=" + id + " ]";
    }
    
}
