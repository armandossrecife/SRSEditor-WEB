/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "referencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Referencia.findAll", query = "SELECT r FROM Referencia r"),
    @NamedQuery(name = "Referencia.findById", query = "SELECT r FROM Referencia r WHERE r.id = :id"),
    @NamedQuery(name = "Referencia.findByDe", query = "SELECT r FROM Referencia r WHERE r.de = :de"),
    @NamedQuery(name = "Referencia.findByQtd", query = "SELECT r FROM Referencia r WHERE r.qtd = :qtd"),
    @NamedQuery(name = "Referencia.findByQtdRelevante", query = "SELECT r FROM Referencia r WHERE r.qtdRelevante = :qtdRelevante"),
    @NamedQuery(name = "Referencia.findByPercRelevante", query = "SELECT r FROM Referencia r WHERE r.percRelevante = :percRelevante"),
    @NamedQuery(name = "Referencia.findByQtdRadical", query = "SELECT r FROM Referencia r WHERE r.qtdRadical = :qtdRadical"),
    @NamedQuery(name = "Referencia.findByNomeArquivo", query = "SELECT r FROM Referencia r WHERE r.nomeArquivo = :nomeArquivo")})
public class Referencia implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "de")
    private String de;
    @Column(name = "qtd")
    private Integer qtd;
    @Column(name = "qtd_relevante")
    private Integer qtdRelevante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "perc_relevante")
    private Float percRelevante;
    @Column(name = "qtd_radical")
    private Integer qtdRadical;
    @Column(name = "nome_arquivo")
    private String nomeArquivo;
    @JoinColumn(name = "id_projeto", referencedColumnName = "id")
    @ManyToOne
    private Projeto idProjeto;

    public Referencia() {
    }

    public Referencia(Integer id) {
        this.id = id;
    }

    public Referencia(Integer id, String de) {
        this.id = id;
        this.de = de;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        String oldDe = this.de;
        this.de = de;
        changeSupport.firePropertyChange("de", oldDe, de);
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        Integer oldQtd = this.qtd;
        this.qtd = qtd;
        changeSupport.firePropertyChange("qtd", oldQtd, qtd);
    }

    public Integer getQtdRelevante() {
        return qtdRelevante;
    }

    public void setQtdRelevante(Integer qtdRelevante) {
        Integer oldQtdRelevante = this.qtdRelevante;
        this.qtdRelevante = qtdRelevante;
        changeSupport.firePropertyChange("qtdRelevante", oldQtdRelevante, qtdRelevante);
    }

    public Float getPercRelevante() {
        return percRelevante;
    }

    public void setPercRelevante(Float percRelevante) {
        Float oldPercRelevante = this.percRelevante;
        this.percRelevante = percRelevante;
        changeSupport.firePropertyChange("percRelevante", oldPercRelevante, percRelevante);
    }

    public Integer getQtdRadical() {
        return qtdRadical;
    }

    public void setQtdRadical(Integer qtdRadical) {
        Integer oldQtdRadical = this.qtdRadical;
        this.qtdRadical = qtdRadical;
        changeSupport.firePropertyChange("qtdRadical", oldQtdRadical, qtdRadical);
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        String oldNomeArquivo = this.nomeArquivo;
        this.nomeArquivo = nomeArquivo;
        changeSupport.firePropertyChange("nomeArquivo", oldNomeArquivo, nomeArquivo);
    }

    public Projeto getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Projeto idProjeto) {
        Projeto oldIdProjeto = this.idProjeto;
        this.idProjeto = idProjeto;
        changeSupport.firePropertyChange("idProjeto", oldIdProjeto, idProjeto);
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
        if (!(object instanceof Referencia)) {
            return false;
        }
        Referencia other = (Referencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Referencia[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
