/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model;

import com.d3.d3.annotation.Phone;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "USER_")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USU")
    private Integer idUsu;
    @NotNull
    @Size(min = 4,max = 20)
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Size(min = 4,max = 20)
    @Column(name = "SURNAME")
    private String surname;
    @NotNull
    @Size(min = 4,max = 20)
    @Column(name = "SURNAME2")
    private String surname2;
    @NotNull
    @Size(min = 4,max = 50)
    @Column(name = "DIRECTION")
    private String direction;
    @NotNull
    @Phone
    @Size(min = 9,max = 9)
    @Column(name = "TELEPHONE")
    private String telephone;
    @NotNull
    @Size(min = 4,max = 50)
    @Column(name = "PASSWORD")
    private String password;
    @NotNull
    @Size(min = 4, max = 20)
    @Column(name = "NICKNAME")
    private String nickname;
    @OneToMany(mappedBy = "idUsu")
    private Collection<Order1> order1Collection;

    public User() {
        this.idUsu = 0;
    }

    public User(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public Integer getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Collection<Order1> getOrder1Collection() {
        return order1Collection;
    }

    public void setOrder1Collection(Collection<Order1> order1Collection) {
        this.order1Collection = order1Collection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsu != null ? idUsu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUsu == null && other.idUsu != null) || (this.idUsu != null && !this.idUsu.equals(other.idUsu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.d3.d3.model.User[ idUsu=" + idUsu + " ]";
    }

    public void update(User u) {
        this.setDirection(u.getDirection());
        this.setName(u.getName());
        this.setNickname(u.getNickname());
        this.setOrder1Collection(u.getOrder1Collection());
        this.setPassword(u.getPassword());
        this.setSurname(u.getSurname());
        this.setSurname2(u.getSurname2());
        this.setTelephone(u.getTelephone());
    }
    
}
