/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helcio.soares
 */
@Entity
@Table(name = "atributo",       
        indexes = {
         @Index(name = "IUNIQUE", columnList = "id_projeto,nome_lemma,id_isr", unique = true)
                }
)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atributo.findAll", query = "SELECT a FROM Atributo a"),
    @NamedQuery(name = "Atributo.findById", query = "SELECT a FROM Atributo a WHERE a.id = :id"),
    @NamedQuery(name = "Atributo.findByNome", query = "SELECT a FROM Atributo a WHERE a.nome = :nome"),
    @NamedQuery(name = "Atributo.findByNomeLemma", query = "SELECT a FROM Atributo a WHERE a.nomeLemma = :nomeLemma"),
    @NamedQuery(name = "Atributo.findByValidado", query = "SELECT a FROM Atributo a WHERE a.validado = :validado")})

public class Atributo extends BaseEntity implements Serializable {
    
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
    @Column(name = "validado")
    private Integer validado;

    @JoinColumn(name = "id_projeto", referencedColumnName = "id")
    @ManyToOne
    private Projeto idProjeto;
    @JoinColumn(name = "id_isr", referencedColumnName = "id")
    @ManyToOne
    private Isr idIsr;
    @JoinColumn(name = "id_isr_origem", referencedColumnName = "id")
    @ManyToOne
    private Isr idIsrOrigem;

    public Atributo() {
    }

    public Atributo(Integer id) {
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

    public String getNomeLemma() {
        return nomeLemma;
    }

    public void setNomeLemma(String nomeLemma) {
        this.nomeLemma = nomeLemma;
    }

    public Integer getValidado() {
        return validado;
    }

    public void setValidado(Integer validado) {
        this.validado = validado;
    }

    public Projeto getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Projeto idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Isr getIdIsr() {
        return idIsr;
    }

    public void setIdIsr(Isr idIsr) {
        this.idIsr = idIsr;
    }

    public Isr getIdIsrOrigem() {
        return idIsrOrigem;
    }

    public void setIdIsrOrigem(Isr idIsrOrigem) {
        this.idIsrOrigem = idIsrOrigem;
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
        if (!(object instanceof Atributo)) {
            return false;
        }
        Atributo other = (Atributo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Atributo[ id=" + id + " ]";
    }

}
