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
@Table(name = "stop_words")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StopWords.findAll", query = "SELECT s FROM StopWords s"),
    @NamedQuery(name = "StopWords.findByDe", query = "SELECT s FROM StopWords s WHERE s.de = :de")})
public class StopWords implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "de")
    private String de;

    public StopWords() {
    }

    public StopWords(String de) {
        this.de = de;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (de != null ? de.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StopWords)) {
            return false;
        }
        StopWords other = (StopWords) object;
        if ((this.de == null && other.de != null) || (this.de != null && !this.de.equals(other.de))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StopWords[ de=" + de + " ]";
    }
    
}
