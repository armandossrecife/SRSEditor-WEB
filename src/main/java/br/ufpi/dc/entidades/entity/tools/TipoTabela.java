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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author helcio.soares
 */
@Entity
@Table(name = "tipo_tabela")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTabela.findAll", query = "SELECT t FROM TipoTabela t"),
    @NamedQuery(name = "TipoTabela.findById", query = "SELECT t FROM TipoTabela t WHERE t.id = :id"),
    @NamedQuery(name = "TipoTabela.findByDe", query = "SELECT t FROM TipoTabela t WHERE t.de = :de"),
    @NamedQuery(name = "TipoTabela.findByCarregaArvore", query = "SELECT t FROM TipoTabela t WHERE t.carregaArvore = :carregaArvore")})
public class TipoTabela implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "de")
    private String de;
    @Column(name = "carrega_arvore")
    private Integer carregaArvore;

    public TipoTabela() {
    }

    public TipoTabela(Integer id) {
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

    public Integer getCarregaArvore() {
        return carregaArvore;
    }

    public void setCarregaArvore(Integer carregaArvore) {
        this.carregaArvore = carregaArvore;
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
        if (!(object instanceof TipoTabela)) {
            return false;
        }
        TipoTabela other = (TipoTabela) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDe();
    }
    
}
