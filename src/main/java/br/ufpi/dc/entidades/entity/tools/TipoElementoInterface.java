/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufpi.dc.entidades.entity.tools;

import br.ufpi.dc.entidades.entity.tools.ElementoDeInterface;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "tipo_elemento_interface")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoElementoInterface.findAll", query = "SELECT t FROM TipoElementoInterface t"),
    @NamedQuery(name = "TipoElementoInterface.findById", query = "SELECT t FROM TipoElementoInterface t WHERE t.id = :id"),
    @NamedQuery(name = "TipoElementoInterface.findByDe", query = "SELECT t FROM TipoElementoInterface t WHERE t.de = :de")})
public class TipoElementoInterface implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "de")
    private String de;
//    @OneToMany(mappedBy = "tipo")
//    private Collection<ElementoDeInterface> elementoDeInterfaceCollection;

    public TipoElementoInterface() {
    }

    public TipoElementoInterface(Integer id) {
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

//    @XmlTransient
//    public Collection<ElementoDeInterface> getElementoDeInterfaceCollection() {
//        return elementoDeInterfaceCollection;
//    }
//
//    public void setElementoDeInterfaceCollection(Collection<ElementoDeInterface> elementoDeInterfaceCollection) {
//        this.elementoDeInterfaceCollection = elementoDeInterfaceCollection;
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
        if (!(object instanceof TipoElementoInterface)) {
            return false;
        }
        TipoElementoInterface other = (TipoElementoInterface) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TipoElementoInterface[ id=" + id + " ]";
    }
    
}
