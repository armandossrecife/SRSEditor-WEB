/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufpi.dc.entidades.entity.tools;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helcio.soares
 */
@Entity
@Table(name = "mensagem_erro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MensagemErro.findAll", query = "SELECT m FROM MensagemErro m"),
    @NamedQuery(name = "MensagemErro.findById", query = "SELECT m FROM MensagemErro m WHERE m.id = :id"),
    @NamedQuery(name = "MensagemErro.findByErro", query = "SELECT m FROM MensagemErro m WHERE m.erro = :erro"),
    @NamedQuery(name = "MensagemErro.findByMensagem", query = "SELECT m FROM MensagemErro m WHERE m.mensagem = :mensagem")})
public class MensagemErro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "erro")
    private String erro;
    @Column(name = "mensagem")
    private String mensagem;
    @JoinColumn(name = "id_gramatica", referencedColumnName = "id")
    @ManyToOne
    private Gramatica idGramatica;

    public MensagemErro() {
    }

    public MensagemErro(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Gramatica getIdGramatica() {
        return idGramatica;
    }

    public void setIdGramatica(Gramatica idGramatica) {
        this.idGramatica = idGramatica;
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
        if (!(object instanceof MensagemErro)) {
            return false;
        }
        MensagemErro other = (MensagemErro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.tools.MensagemErro[ id=" + id + " ]";
    }
    
}
