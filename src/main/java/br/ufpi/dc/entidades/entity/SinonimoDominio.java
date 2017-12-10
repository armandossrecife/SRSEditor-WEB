/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;

import br.ufpi.dc.entidades.entity.Projeto;
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
@Table(name = "sinonimo_dominio",
    indexes = {
        @Index(name = "IUNIQUE", columnList = "id_projeto,chave,sinonimo", unique = true)}
)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SinonimoDominio.findAll", query = "SELECT s FROM SinonimoDominio s"),
    @NamedQuery(name = "SinonimoDominio.findById", query = "SELECT s FROM SinonimoDominio s WHERE s.id = :id"),
    @NamedQuery(name = "SinonimoDominio.findByChave", query = "SELECT s FROM SinonimoDominio s WHERE s.chave = :chave"),
    @NamedQuery(name = "SinonimoDominio.findBySinonimo", query = "SELECT s FROM SinonimoDominio s WHERE s.sinonimo = :sinonimo")})

public class SinonimoDominio implements Serializable {
    @JoinColumn(name = "conceito_chave", referencedColumnName = "id")
    @ManyToOne
    private Conceito conceitoChave;
    @JoinColumn(name = "conceito_sinonimo", referencedColumnName = "id")
    @ManyToOne
    private Conceito conceitoSinonimo;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "chave")
    private String chave;
    @Basic(optional = false)
    @Column(name = "sinonimo")
    private String sinonimo;
    @JoinColumn(name = "id_projeto", referencedColumnName = "id")
    @ManyToOne
    private Projeto idProjeto;

    public SinonimoDominio() {
    }

    public SinonimoDominio(Integer id) {
        this.id = id;
    }

    public SinonimoDominio(Integer id, String chave, String sinonimo) {
        this.id = id;
        this.chave = chave;
        this.sinonimo = sinonimo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        String oldChave = this.chave;
        this.chave = chave;
        changeSupport.firePropertyChange("chave", oldChave, chave);
    }

    public String getSinonimo() {
        return sinonimo;
    }

    public void setSinonimo(String sinonimo) {
        String oldSinonimo = this.sinonimo;
        this.sinonimo = sinonimo;
        changeSupport.firePropertyChange("sinonimo", oldSinonimo, sinonimo);
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
        if (!(object instanceof SinonimoDominio)) {
            return false;
        }
        SinonimoDominio other = (SinonimoDominio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: "+ id+ " chave: " + chave + " sinonimo: "+ sinonimo;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public Conceito getConceitoChave() {
        return conceitoChave;
    }

    public void setConceitoChave(Conceito conceitoChave) {
        this.conceitoChave = conceitoChave;
    }

    public Conceito getConceitoSinonimo() {
        return conceitoSinonimo;
    }

    public void setConceitoSinonimo(Conceito conceitoSinonimo) {
        this.conceitoSinonimo = conceitoSinonimo;
    }
    
}
