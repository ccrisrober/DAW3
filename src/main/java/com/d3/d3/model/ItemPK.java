/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cristian
 */
@Embeddable
public class ItemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ORD")
    private int idOrd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROD")
    private int idProd;

    public ItemPK() {
    }

    public ItemPK(int idOrd, int idProd) {
        this.idOrd = idOrd;
        this.idProd = idProd;
    }

    public int getIdOrd() {
        return idOrd;
    }

    public void setIdOrd(int idOrd) {
        this.idOrd = idOrd;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOrd;
        hash += (int) idProd;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPK)) {
            return false;
        }
        ItemPK other = (ItemPK) object;
        if (this.idOrd != other.idOrd) {
            return false;
        }
        if (this.idProd != other.idProd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.d3.d3.model.ItemPK[ idOrd=" + idOrd + ", idProd=" + idProd + " ]";
    }
    
}
