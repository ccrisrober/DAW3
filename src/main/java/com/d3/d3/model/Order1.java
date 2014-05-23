/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "ORDER_")
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o")})
public class Order1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ORD")
    private Integer idOrd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAME_REC")
    private String nameRec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SURNAME_REC")
    private String surnameRec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DIRECTION")
    private String direction;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "ID_CARD")
    private Integer idCard;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order1")
    private Collection<Item> itemCollection;
    @JoinColumn(name = "ID_USU", referencedColumnName = "ID_USU")
    @ManyToOne
    private User idUsu;

    public Order1() {
    }

    public Order1(Integer idOrd) {
        this.idOrd = idOrd;
    }

    public Order1(Integer idOrd, double price, String nameRec, String surnameRec, String direction, String telephone) {
        this.idOrd = idOrd;
        this.price = price;
        this.nameRec = nameRec;
        this.surnameRec = surnameRec;
        this.direction = direction;
        this.telephone = telephone;
    }

    public Integer getIdOrd() {
        return idOrd;
    }

    public void setIdOrd(Integer idOrd) {
        this.idOrd = idOrd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNameRec() {
        return nameRec;
    }

    public void setNameRec(String nameRec) {
        this.nameRec = nameRec;
    }

    public String getSurnameRec() {
        return surnameRec;
    }

    public void setSurnameRec(String surnameRec) {
        this.surnameRec = surnameRec;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public User getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(User idUsu) {
        this.idUsu = idUsu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrd != null ? idOrd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.idOrd == null && other.idOrd != null) || (this.idOrd != null && !this.idOrd.equals(other.idOrd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.d3.d3.model.Order1[ idOrd=" + idOrd + " ]";
    }
    
    
    
    public static Set<String> allStatus = new HashSet<String>();
    public static Set<String> payment = new HashSet<String>();
    static {
        allStatus.add("nuevo");
        allStatus.add("preparación");
        allStatus.add("listo");
        allStatus.add("camino");
        payment.add("delivery");
        payment.add("card");
    }

    public static Set<String> getAllStatus() {
        return allStatus;
    }

    public static Set<String> getPayment() {
        return payment;
    }
    
    
}
