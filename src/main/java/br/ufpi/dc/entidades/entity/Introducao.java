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
@Table(name = "introducao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Introducao.findAll", query = "SELECT i FROM Introducao i"),
    @NamedQuery(name = "Introducao.findById", query = "SELECT i FROM Introducao i WHERE i.id = :id"),
    @NamedQuery(name = "Introducao.findByObjetivo", query = "SELECT i FROM Introducao i WHERE i.objetivo = :objetivo"),
    @NamedQuery(name = "Introducao.findByEscopo", query = "SELECT i FROM Introducao i WHERE i.escopo = :escopo"),
    @NamedQuery(name = "Introducao.findByVisaoGeral", query = "SELECT i FROM Introducao i WHERE i.visaoGeral = :visaoGeral")})
public class Introducao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "objetivo")
    private String objetivo;
    @Basic(optional = false)
    @Lob
    @Column(name = "escopo")
    private String escopo;
    @Basic(optional = false)
    @Lob
    @Column(name = "visao_geral")
    private String visaoGeral;
    @JoinColumn(name = "id_projeto", referencedColumnName = "id")
    @ManyToOne
    private Projeto idProjeto;

    public Introducao() {
    }

    public Introducao(Integer id) {
        this.id = id;
    }

    public Introducao(Integer id, String objetivo, String escopo, String visaoGeral) {
        this.id = id;
        this.objetivo = objetivo;
        this.escopo = escopo;
        this.visaoGeral = visaoGeral;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public String getVisaoGeral() {
        return visaoGeral;
    }

    public void setVisaoGeral(String visaoGeral) {
        this.visaoGeral = visaoGeral;
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
        if (!(object instanceof Introducao)) {
            return false;
        }
        Introducao other = (Introducao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Introducao[ id=" + id + " ]";
    }
    
}
