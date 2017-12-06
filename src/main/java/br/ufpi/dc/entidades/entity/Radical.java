/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "radical")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Radical.findAll", query = "SELECT r FROM Radical r"),
    @NamedQuery(name = "Radical.findById", query = "SELECT r FROM Radical r WHERE r.id = :id"),
    @NamedQuery(name = "Radical.findByDe", query = "SELECT r FROM Radical r WHERE r.de = :de")})
public class Radical implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "de")
    private String de;
//    @OneToMany(mappedBy = "idRadical")
//    private Collection<Palavra> palavraCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRadical")
//    private Collection<Dados> dadosCollection;
//    @OneToMany(mappedBy = "idRadical")
//    private Collection<DadoRadical> dadoRadicalCollection;

    public Radical() {
    }

    public Radical(Integer id) {
        this.id = id;
    }

    public Radical(Integer id, String de) {
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
//
//    @XmlTransient
//    public Collection<Dados> getDadosCollection() {
//        return dadosCollection;
//    }
//
//    public void setDadosCollection(Collection<Dados> dadosCollection) {
//        this.dadosCollection = dadosCollection;
//    }
//
//    @XmlTransient
//    public Collection<DadoRadical> getDadoRadicalCollection() {
//        return dadoRadicalCollection;
//    }
//
//    public void setDadoRadicalCollection(Collection<DadoRadical> dadoRadicalCollection) {
//        this.dadoRadicalCollection = dadoRadicalCollection;
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
        if (!(object instanceof Radical)) {
            return false;
        }
        Radical other = (Radical) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Radical[ id=" + id + " ]";
    }
    
}
