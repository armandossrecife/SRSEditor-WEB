/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.entidades.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author helcio.soares
 */
@Entity
@Table(name = "projeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projeto.findAll", query = "SELECT p FROM Projeto p"),
    @NamedQuery(name = "Projeto.findById", query = "SELECT p FROM Projeto p WHERE p.id = :id"),
    @NamedQuery(name = "Projeto.findByDe", query = "SELECT p FROM Projeto p WHERE p.de = :de"),
    @NamedQuery(name = "Projeto.findByPath", query = "SELECT p FROM Projeto p WHERE p.path = :path")})
public class Projeto implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
//    @OneToMany(mappedBy = "idProjeto")
//    private Collection<SinonimoDominio> sinonimoDominioCollection;
//    @OneToMany(mappedBy = "idProjeto")
//    private Collection<Atributo> atributoCollection;
//    @OneToMany(mappedBy = "idProjeto")
//    private Collection<CasoDeUso> casoDeUsoCollection;
//    @OneToMany(mappedBy = "idProjeto")
//    private Collection<Acao> acaoCollection;
//    @OneToMany(mappedBy = "idProjeto")
//    private Collection<Isr> isrCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "de")
    private String de;
    @Column(name = "path")
    private String path;
//    @OneToMany(mappedBy = "idProjeto")
//    private Collection<Tabela> tabelaCollection;

    public Projeto() {
    }

    public Projeto(Integer id) {
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

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        String oldDe = this.de;
        this.de = de;
        changeSupport.firePropertyChange("de", oldDe, de);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        String oldPath = this.path;
        this.path = path;
        changeSupport.firePropertyChange("path", oldPath, path);
    }

//    @XmlTransient
//    public Collection<Tabela> getTabelaCollection() {
//        return tabelaCollection;
//    }
//
//    public void setTabelaCollection(Collection<Tabela> tabelaCollection) {
//        this.tabelaCollection = tabelaCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Projeto[ id=" + id + " ]";
    }

//    @XmlTransient
//    public Collection<Isr> getIsrCollection() {
//        return isrCollection;
//    }
//
//    public void setIsrCollection(Collection<Isr> isrCollection) {
//        this.isrCollection = isrCollection;
//    }
//
//    @XmlTransient
//    public Collection<Acao> getAcaoCollection() {
//        return acaoCollection;
//    }
//
//    public void setAcaoCollection(Collection<Acao> acaoCollection) {
//        this.acaoCollection = acaoCollection;
//    }
//
//    @XmlTransient
//    public Collection<CasoDeUso> getCasoDeUsoCollection() {
//        return casoDeUsoCollection;
//    }
//
//    public void setCasoDeUsoCollection(Collection<CasoDeUso> casoDeUsoCollection) {
//        this.casoDeUsoCollection = casoDeUsoCollection;
//    }
//
//    @XmlTransient
//    public Collection<Atributo> getAtributoCollection() {
//        return atributoCollection;
//    }
//
//    public void setAtributoCollection(Collection<Atributo> atributoCollection) {
//        this.atributoCollection = atributoCollection;
//    }
//
//    @XmlTransient
//    public Collection<SinonimoDominio> getSinonimoDominioCollection() {
//        return sinonimoDominioCollection;
//    }
//
//    public void setSinonimoDominioCollection(Collection<SinonimoDominio> sinonimoDominioCollection) {
//        this.sinonimoDominioCollection = sinonimoDominioCollection;
//    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
