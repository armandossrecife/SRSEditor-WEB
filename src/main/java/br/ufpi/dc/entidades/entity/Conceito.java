/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name = "conceito",
    indexes = {
        @Index(name = "IUNIQUE", columnList = "id_projeto, nome_lemma, id_tipo_conceito", unique = true)}
)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceito.findAll", query = "SELECT c FROM Conceito c"),
    @NamedQuery(name = "Conceito.findById", query = "SELECT c FROM Conceito c WHERE c.id = :id"),
    @NamedQuery(name = "Conceito.findByDe", query = "SELECT c FROM Conceito c WHERE c.de = :de"),
    @NamedQuery(name = "Conceito.findByNomeLemma", query = "SELECT c FROM Conceito c WHERE c.nomeLemma = :nomeLemma"),
    @NamedQuery(name = "Conceito.findByF", query = "SELECT c FROM Conceito c WHERE c.f = :f"),
    @NamedQuery(name = "Conceito.findByIdProjeto", query = "SELECT c FROM Conceito c WHERE c.idProjeto = :idProjeto"),
    @NamedQuery(name = "Conceito.findByIdTipoConceito", query = "SELECT c FROM Conceito c WHERE c.idTipoConceito = :idTipoConceito"),
    @NamedQuery(name = "Conceito.findByUtilizado", query = "SELECT c FROM Conceito c WHERE c.utilizado = :utilizado")})
public class Conceito implements Serializable {
//    @OneToMany(mappedBy = "conceitoChave")
//    private Collection<SinonimoDominio> sinonimoDominioCollection;
//    @OneToMany(mappedBy = "conceitoSinonimo")
//    private Collection<SinonimoDominio> sinonimoDominioCollection1;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "de")
    private String de;
    @Column(name = "nome_lemma")
    private String nomeLemma;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "f")
    private Double f;
    @Column(name = "id_projeto")
    private Integer idProjeto;
    @Column(name = "id_tipo_conceito")
    private Integer idTipoConceito;
    @Column(name = "utilizado")
    private Integer utilizado;

    public Conceito() {
    }

    public Conceito(Integer id) {
        this.id = id;
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

    public String getNomeLemma() {
        return nomeLemma;
    }

    public void setNomeLemma(String nomeLemma) {
        this.nomeLemma = nomeLemma;
    }

    public Double getF() {
        return f;
    }

    public void setF(Double f) {
        this.f = f;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Integer getIdTipoConceito() {
        return idTipoConceito;
    }

    public void setIdTipoConceito(Integer idTipoConceito) {
        this.idTipoConceito = idTipoConceito;
    }

    public Integer getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(Integer utilizado) {
        this.utilizado = utilizado;
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
        if (!(object instanceof Conceito)) {
            return false;
        }
        Conceito other = (Conceito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (this.idProjeto == other.idProjeto && this.nomeLemma == other.nomeLemma){
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: "+ id +" "+ de;
    }

//    @XmlTransient
//    public Collection<SinonimoDominio> getSinonimoDominioCollection() {
//        return sinonimoDominioCollection;
//    }
//
//    public void setSinonimoDominioCollection(Collection<SinonimoDominio> sinonimoDominioCollection) {
//        this.sinonimoDominioCollection = sinonimoDominioCollection;
//    }

//    @XmlTransient
//    public Collection<SinonimoDominio> getSinonimoDominioCollection1() {
//        return sinonimoDominioCollection1;
//    }
//
//    public void setSinonimoDominioCollection1(Collection<SinonimoDominio> sinonimoDominioCollection1) {
//        this.sinonimoDominioCollection1 = sinonimoDominioCollection1;
//    }
//    
}
