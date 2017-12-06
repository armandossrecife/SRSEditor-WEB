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
@Table(name = "elemento_de_interface")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElementoDeInterface.findAll", query = "SELECT e FROM ElementoDeInterface e"),
    @NamedQuery(name = "ElementoDeInterface.findById", query = "SELECT e FROM ElementoDeInterface e WHERE e.id = :id"),
    @NamedQuery(name = "ElementoDeInterface.findByDe", query = "SELECT e FROM ElementoDeInterface e WHERE e.de = :de")})
public class ElementoDeInterface implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "de")
    private String de;
    @JoinColumn(name = "tipo", referencedColumnName = "id")
    @ManyToOne
    private TipoElementoInterface tipo;

    public ElementoDeInterface() {
    }

    public ElementoDeInterface(Integer id) {
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

    public TipoElementoInterface getTipo() {
        return tipo;
    }

    public void setTipo(TipoElementoInterface tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof ElementoDeInterface)) {
            return false;
        }
        ElementoDeInterface other = (ElementoDeInterface) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ElementoDeInterface[ id=" + id + " ]";
    }
    
}
