/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author helcio.soares
 */
@Entity
@Table(name = "isr",
    indexes = {
        @Index(name = "IUNIQUE", columnList = "id_projeto, nome_lemma", unique = true)}
)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Isr.findAll", query = "SELECT i FROM Isr i"),
    @NamedQuery(name = "Isr.findById", query = "SELECT i FROM Isr i WHERE i.id = :id"),
    @NamedQuery(name = "Isr.findByProposito", query = "SELECT i FROM Isr i WHERE i.proposito = :proposito"),
    @NamedQuery(name = "Isr.findByDe", query = "SELECT i FROM Isr i WHERE i.de = :de"),
    @NamedQuery(name = "Isr.findByDadosArmazenados", query = "SELECT i FROM Isr i WHERE i.dadosArmazenados = :dadosArmazenados")})
public class Isr extends BaseEntity implements Serializable {
//    @OneToMany(mappedBy = "idIsr")
//    private Collection<Atributo> atributoCollection;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "nome_lemma")
    private String nomeLemma;
    @Lob
    @Column(name = "proposito")
    private String proposito;
    @Column(name = "de")
    private String de;
    @Lob
    @Column(name = "dados_armazenados")
    private String dadosArmazenados;
    @JoinColumn(name = "id_projeto", referencedColumnName = "id")
    @ManyToOne
    private Projeto idProjeto;

    public Isr() {
    }

    public Isr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

 
    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        String oldProposito = this.proposito;
        this.proposito = proposito;
        changeSupport.firePropertyChange("proposito", oldProposito, proposito);
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        String oldDe = this.de;
        this.de = de;
        changeSupport.firePropertyChange("de", oldDe, de);
    }

    public String getDadosArmazenados() {
        return dadosArmazenados;
    }

    public void setDadosArmazenados(String dadosArmazenados) {
        String oldDadosArmazenados = this.dadosArmazenados;
        this.dadosArmazenados = dadosArmazenados;
        changeSupport.firePropertyChange("dadosArmazenados", oldDadosArmazenados, dadosArmazenados);
    }

    public Projeto getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Projeto idProjeto) {
        Projeto oldIdProjeto = this.idProjeto;
        this.idProjeto = idProjeto;
        changeSupport.firePropertyChange("idProjeto", oldIdProjeto, idProjeto);
    }

    public PropertyChangeSupport getChangeSupport() {
        return changeSupport;
    }

    public void setChangeSupport(PropertyChangeSupport changeSupport) {
        this.changeSupport = changeSupport;
    }

    public String getNomeLemma() {
        return nomeLemma;
    }

    public void setNomeLemma(String nomeLemma) {
        String oldNomeLemma = this.nomeLemma;
        this.nomeLemma = nomeLemma;
        changeSupport.firePropertyChange("nomeLemma", oldNomeLemma, nomeLemma);
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
        if (!(object instanceof Isr)) {
            return false;
        }
        Isr other = (Isr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

//    @XmlTransient
//    public Collection<Atributo> getAtributoCollection() {
//        return atributoCollection;
//    }
//
//    public void setAtributoCollection(Collection<Atributo> atributoCollection) {
//        this.atributoCollection = atributoCollection;
//    }
    
}
