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
@Table(name = "gramatica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gramatica.findAll", query = "SELECT g FROM Gramatica g"),
    @NamedQuery(name = "Gramatica.findById", query = "SELECT g FROM Gramatica g WHERE g.id = :id"),
    @NamedQuery(name = "Gramatica.findByNome", query = "SELECT g FROM Gramatica g WHERE g.nome = :nome"),
    @NamedQuery(name = "Gramatica.findByDe", query = "SELECT g FROM Gramatica g WHERE g.de = :de")})
public class Gramatica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "de")
    private String de;
//    @OneToMany(mappedBy = "idGramatica")
//    private Collection<MensagemErro> mensagemErroCollection;

    public Gramatica() {
    }

    public Gramatica(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

//    @XmlTransient
//    public Collection<MensagemErro> getMensagemErroCollection() {
//        return mensagemErroCollection;
//    }
//
//    public void setMensagemErroCollection(Collection<MensagemErro> mensagemErroCollection) {
//        this.mensagemErroCollection = mensagemErroCollection;
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
        if (!(object instanceof Gramatica)) {
            return false;
        }
        Gramatica other = (Gramatica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.tools.Gramatica[ id=" + id + " ]";
    }
    
}
