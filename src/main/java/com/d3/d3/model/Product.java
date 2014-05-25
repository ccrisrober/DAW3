/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model;

import com.d3.d3.model.others.ProductAux;
import com.d3.d3.repository.CategoryRepository;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.Resource;
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
@Table(name = "PRODUCT")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROD")
    private Integer idProd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOCK")
    private int stock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<Item> itemCollection;
    @OneToMany(mappedBy = "idProd")
    private Collection<Image> imageCollection;
    @JoinColumn(name = "ID_CAT", referencedColumnName = "ID_CAT")
    @ManyToOne
    private Category idCat;

    public Product() {
    }

    public Product(Integer idProd) {
        this.idProd = idProd;
    }

    public Product(Integer idProd, String name, String description, double price, int stock) {
        this.idProd = idProd;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product(ProductAux product, Category cat) {
        this.description = product.getDescription();
        this.idCat = cat;
        this.idProd = product.getIdProd();
        this.imageCollection = null;
        this.itemCollection = null;
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }

    public Category getIdCat() {
        return idCat;
    }

    public void setIdCat(Category idCat) {
        this.idCat = idCat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProd != null ? idProd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.idProd == null && other.idProd != null) || (this.idProd != null && !this.idProd.equals(other.idProd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.d3.d3.model.Product[ idProd=" + idProd + " ]";
    }

    public void update(Product product) {
        this.setDescription(product.getDescription());
        this.setIdCat(product.getIdCat());
        this.setImageCollection(product.getImageCollection());
        this.setItemCollection(product.getItemCollection());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setStock(product.getStock());
    }
    
}
