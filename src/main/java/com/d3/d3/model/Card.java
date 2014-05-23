/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "CARD")
@NamedQueries({
    @NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c")})
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "NUM_CARD")
    private String numCard;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CATD")
    @Temporal(TemporalType.DATE)
    private Date dateCatd;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CARD")
    private Integer idCard;

    public Card() {
    }

    public Card(Integer idCard) {
        this.idCard = idCard;
    }

    public Card(Integer idCard, String numCard, Date dateCatd) {
        this.idCard = idCard;
        this.numCard = numCard;
        this.dateCatd = dateCatd;
    }

    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public Date getDateCatd() {
        return dateCatd;
    }

    public void setDateCatd(Date dateCatd) {
        this.dateCatd = dateCatd;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCard != null ? idCard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.idCard == null && other.idCard != null) || (this.idCard != null && !this.idCard.equals(other.idCard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.d3.d3.model.Card[ idCard=" + idCard + " ]";
    }

    public void update(Card c) {
        this.setDateCatd(c.getDateCatd());
        this.setNumCard(c.getNumCard());
    }
    
}
