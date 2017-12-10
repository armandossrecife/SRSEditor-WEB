/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity.tools;

import br.ufpi.dc.entidades.entity.Palavra;
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
@Table(name = "classe_gramatical")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasseGramatical.findAll", query = "SELECT c FROM ClasseGramatical c"),
    @NamedQuery(name = "ClasseGramatical.findById", query = "SELECT c FROM ClasseGramatical c WHERE c.id = :id"),
    @NamedQuery(name = "ClasseGramatical.findByDe", query = "SELECT c FROM ClasseGramatical c WHERE c.de = :de")})
public class ClasseGramatical implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "de")
    private String de;
//    @OneToMany(mappedBy = "idClasseGramatical")
//    private Collection<Palavra> palavraCollection;

    public ClasseGramatical() {
    }

    public ClasseGramatical(Integer id) {
        this.id = id;
    }

    public ClasseGramatical(Integer id, String de) {
        this.id = id;
        this.de = de;
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

//    @XmlTransient
//    public Collection<Palavra> getPalavraCollection() {
//        return palavraCollection;
//    }
//
//    public void setPalavraCollection(Collection<Palavra> palavraCollection) {
//        this.palavraCollection = palavraCollection;
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
        if (!(object instanceof ClasseGramatical)) {
            return false;
        }
        ClasseGramatical other = (ClasseGramatical) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return de;
    }
    
}
