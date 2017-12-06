/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;
//CUIDADO AO ATUALIZAR ESSA CLASSE, ELA TEM O MÉTODO EQUALS CONFIGURADO!

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helcio.soares
 */
@Entity
@Table(name = "tabela",
    indexes = {
        @Index(name = "IUNIQUE", columnList = "id_projeto,nome_lemma,id_tipo_tabela", unique = true)}
)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tabela.findAll", query = "SELECT t FROM Tabela t"),
    @NamedQuery(name = "Tabela.findById", query = "SELECT t FROM Tabela t WHERE t.id = :id"),
    @NamedQuery(name = "Tabela.findByNome", query = "SELECT t FROM Tabela t WHERE t.nome = :nome"),
    @NamedQuery(name = "Tabela.findByDe", query = "SELECT t FROM Tabela t WHERE t.de = :de"),
    @NamedQuery(name = "Tabela.findByIdTipoTabela", query = "SELECT t FROM Tabela t WHERE t.idTipoTabela = :idTipoTabela")})
public class Tabela implements Serializable {
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
    @Column(name = "de")
    private String de;
    @Column(name = "nome_lemma")
    private String nomeLemma;
    @Column(name = "id_tipo_tabela")
    private Integer idTipoTabela;
    @JoinColumn(name = "id_projeto", referencedColumnName = "id")
    @ManyToOne
    private Projeto idProjeto;

    public Tabela() {
    }

    public Tabela(Integer id) {
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

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        String oldDe = this.de;
        this.de = de;
        changeSupport.firePropertyChange("de", oldDe, de);
    }

    public Integer getIdTipoTabela() {
        return idTipoTabela;
    }

    public void setIdTipoTabela(Integer idTipoTabela) {
        Integer oldIdTipoTabela = this.idTipoTabela;
        this.idTipoTabela = idTipoTabela;
        changeSupport.firePropertyChange("idTipoTabela", oldIdTipoTabela, idTipoTabela);
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
        if (!(object instanceof Tabela)) {
            return false;
        }
        Tabela other = (Tabela) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        Integer thisId  = this.id  != null ? this.id  : -1;
        Integer otherId = other.id != null ? other.id : -1;

        Integer thisIdProjeto  = this.idProjeto  != null ? this.id  : -1;
        Integer otherIdProjeto = other.idProjeto != null ? other.id : -1;

        Integer thisIdTipoTabela   = this.idTipoTabela  != null ? this.idTipoTabela  : -1;
        Integer otherIdTipoTabela  = other.idTipoTabela != null ? other.idTipoTabela : -1;

        String thisNomeLemma  = this.nomeLemma  != null  ? this.nomeLemma  : "";
        String otherNomeLemma = other.nomeLemma != null  ? other.nomeLemma : "";
        
        
        
        if ( thisId.equals(otherId) && thisIdProjeto.equals(otherIdProjeto)
             && thisNomeLemma.equals(otherNomeLemma)  
             && thisIdTipoTabela.equals(otherIdTipoTabela)
            ){
            return true;
        }else{
            return false;
        }
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
    
}
