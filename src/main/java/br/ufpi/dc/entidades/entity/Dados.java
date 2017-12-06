/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;

import java.io.Serializable;
import java.util.Objects;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helcio.soares
 */
@Entity
@Table(name = "dados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dados.findAll", query = "SELECT d FROM Dados d"),
    @NamedQuery(name = "Dados.findById", query = "SELECT d FROM Dados d WHERE d.id = :id"),
    @NamedQuery(name = "Dados.findByF", query = "SELECT d FROM Dados d WHERE d.f = :f"),
    @NamedQuery(name = "Dados.findByRf", query = "SELECT d FROM Dados d WHERE d.rf = :rf"),
    @NamedQuery(name = "Dados.findByIdf", query = "SELECT d FROM Dados d WHERE d.idf = :idf")})
public class Dados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "f")
    private Integer f;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rf")
    private Float rf;
    @Column(name = "idf")
    private Float idf;
    @JoinColumn(name = "id_projeto", referencedColumnName = "id")
    @ManyToOne
    private Projeto idProjeto;
    @JoinColumn(name = "id_palavra", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Palavra idPalavra;
    @JoinColumn(name = "id_radical", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Radical idRadical;
    @JoinColumn(name = "id_referencia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Referencia idReferencia;
    @Basic(optional = false)
    @Column(name = "qtd_doc")
    private int qtdDoc;

    public Dados() {
    }

    public int getQtdDoc() {
        return qtdDoc;
    }

    public void setQtdDoc(int qtdDoc) {
        this.qtdDoc = qtdDoc;
    }

    public Dados(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public Float getRf() {
        return rf;
    }

    public void setRf(Float rf) {
        this.rf = rf;
    }

    public Float getIdf() {
        return idf;
    }

    public void setIdf(Float idf) {
        this.idf = idf;
    }

    public Projeto getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Projeto idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Palavra getIdPalavra() {
        return idPalavra;
    }

    public void setIdPalavra(Palavra idPalavra) {
        this.idPalavra = idPalavra;
    }

    public Radical getIdRadical() {
        return idRadical;
    }

    public void setIdRadical(Radical idRadical) {
        this.idRadical = idRadical;
    }

    public Referencia getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Referencia idReferencia) {
        this.idReferencia = idReferencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dados other = (Dados) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public boolean igual(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dados other = (Dados) obj;
        if (!Objects.equals(this.f, other.f)) {
            return false;
        }
        if (!Objects.equals(this.rf, other.rf)) {
            return false;
        }
        if (!Objects.equals(this.idf, other.idf)) {
            return false;
        }
        if (this.qtdDoc != other.qtdDoc) {
            return false;
        }
        return true;
    }

    public Dados merge(Dados obj) {
        final Dados other = (Dados) obj;
        this.f = other.f;
        this.rf = other.rf;
        this.idf = other.idf;
        this.qtdDoc = other.qtdDoc;
        return this;
    }

    @Override
    public String toString() {
        return "id=" + id + "palavra " + idPalavra.getDe();
    }

}
