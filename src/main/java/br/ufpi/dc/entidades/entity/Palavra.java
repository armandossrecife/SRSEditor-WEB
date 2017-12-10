/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;

import br.ufpi.dc.entidades.entity.tools.ClasseGramatical;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "palavra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Palavra.findAll", query = "SELECT p FROM Palavra p"),
    @NamedQuery(name = "Palavra.findById", query = "SELECT p FROM Palavra p WHERE p.id = :id"),
    @NamedQuery(name = "Palavra.findByDe", query = "SELECT p FROM Palavra p WHERE p.de = :de")})
public class Palavra implements Serializable {
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPalavra")
//    private Collection<Dados> dadosCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "de")
    private String de;

    @JoinColumn(name = "id_radical", referencedColumnName = "id")
    @ManyToOne
    private Radical idRadical;
    @JoinColumn(name = "id_classe_gramatical", referencedColumnName = "id")
    @ManyToOne
    private ClasseGramatical idClasseGramatical;
    @JoinColumn(name = "id_lemma", referencedColumnName = "id")
    @ManyToOne
    private Lemma idLemma;

    public Palavra() {
    }

    public Palavra(Integer id, String de, Lemma idLemma, Radical radical) {
        this.id = id;
        this.de = de;
        this.idLemma = idLemma;
        this.idRadical = radical;
    }

    public Palavra(Integer id) {
        this.id = id;
    }

    public Palavra(Integer id, String de) {
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

     public Radical getIdRadical() {
        return idRadical;
    }

    public void setIdRadical(Radical idRadical) {
        this.idRadical = idRadical;
    }

    public ClasseGramatical getIdClasseGramatical() {
        return idClasseGramatical;
    }

    public void setIdClasseGramatical(ClasseGramatical idClasseGramatical) {
        this.idClasseGramatical = idClasseGramatical;
    }

    public Lemma getIdLemma() {
        return idLemma;
    }

    public void setIdLemma(Lemma idLemma) {
        this.idLemma = idLemma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Palavra)) {
            return false;
        }
        Palavra other = (Palavra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: "+ id +" "+ de;
    }

//    @XmlTransient
//    public Collection<Dados> getDadosCollection() {
//        return dadosCollection;
//    }
//
//    public void setDadosCollection(Collection<Dados> dadosCollection) {
//        this.dadosCollection = dadosCollection;
//    }
    
}
