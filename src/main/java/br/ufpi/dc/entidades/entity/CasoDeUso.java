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
import javax.persistence.Lob;
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
@Table(name = "caso_de_uso",
    indexes = {
        @Index(name = "IUNIQUE", columnList = "id_projeto,nome_lemma", unique = true)}
)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasoDeUso.findAll", query = "SELECT c FROM CasoDeUso c"),
    @NamedQuery(name = "CasoDeUso.findById", query = "SELECT c FROM CasoDeUso c WHERE c.id = :id"),
    @NamedQuery(name = "CasoDeUso.findByNome", query = "SELECT c FROM CasoDeUso c WHERE c.nome = :nome"),
    @NamedQuery(name = "CasoDeUso.findByProposito", query = "SELECT c FROM CasoDeUso c WHERE c.proposito = :proposito"),
    @NamedQuery(name = "CasoDeUso.findByDescricao", query = "SELECT c FROM CasoDeUso c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "CasoDeUso.findByAtor", query = "SELECT c FROM CasoDeUso c WHERE c.ator = :ator"),
    @NamedQuery(name = "CasoDeUso.findByPreCondicao", query = "SELECT c FROM CasoDeUso c WHERE c.preCondicao = :preCondicao"),
    @NamedQuery(name = "CasoDeUso.findByFluxoPrincipal", query = "SELECT c FROM CasoDeUso c WHERE c.fluxoPrincipal = :fluxoPrincipal"),
    @NamedQuery(name = "CasoDeUso.findByNomeLemma", query = "SELECT c FROM CasoDeUso c WHERE c.nomeLemma = :nomeLemma")})
public class CasoDeUso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "proposito")
    private String proposito;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ator")
    private String ator;
    @Column(name = "pre_condicao")
    private String preCondicao;
    @Basic(optional = false)
    @Column(name = "fluxo_principal")
    @Lob
    private String fluxoPrincipal;
    @Basic(optional = false)
    @Lob
    @Column(name = "pos_condicao")
    private String posCondicao;
    @Lob
    @Column(name = "fluxo_alternativo")
    private String fluxoAlternativo;
    @Lob
    @Column(name = "fluxo_excecao")
    private String fluxoExcecao;
    @Column(name = "nome_lemma")
    private String nomeLemma;
    @JoinColumn(name = "id_projeto", referencedColumnName = "id")
    @ManyToOne
    private Projeto idProjeto;

    public CasoDeUso() {
    }

    public CasoDeUso(Integer id) {
        this.id = id;
    }

    public CasoDeUso(Integer id, String nome, String proposito, String descricao, String ator, String fluxoPrincipal, String posCondicao) {
        this.id = id;
        this.nome = nome;
        this.proposito = proposito;
        this.descricao = descricao;
        this.ator = ator;
        this.fluxoPrincipal = fluxoPrincipal;
        this.posCondicao = posCondicao;
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

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAtor() {
        return ator;
    }

    public void setAtor(String ator) {
        this.ator = ator;
    }

    public String getPreCondicao() {
        return preCondicao;
    }

    public void setPreCondicao(String preCondicao) {
        this.preCondicao = preCondicao;
    }

    public String getFluxoPrincipal() {
        return fluxoPrincipal;
    }

    public void setFluxoPrincipal(String fluxoPrincipal) {
        this.fluxoPrincipal = fluxoPrincipal;
    }

    public String getPosCondicao() {
        return posCondicao;
    }

    public void setPosCondicao(String posCondicao) {
        this.posCondicao = posCondicao;
    }

    public String getFluxoAlternativo() {
        return fluxoAlternativo;
    }

    public void setFluxoAlternativo(String fluxoAlternativo) {
        this.fluxoAlternativo = fluxoAlternativo;
    }

    public String getFluxoExcecao() {
        return fluxoExcecao;
    }

    public void setFluxoExcecao(String fluxoExcecao) {
        this.fluxoExcecao = fluxoExcecao;
    }

    public String getNomeLemma() {
        return nomeLemma;
    }

    public void setNomeLemma(String nomeLemma) {
        this.nomeLemma = nomeLemma;
    }

    public Projeto getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Projeto idProjeto) {
        this.idProjeto = idProjeto;
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
        if (!(object instanceof CasoDeUso)) {
            return false;
        }
        CasoDeUso other = (CasoDeUso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: "+ id +" "+ nome;
    }
    
}
