/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model.others;

import com.d3.d3.model.Product;

/**
 *
 * @author Cristian
 */
public class ProductAux {
    private Integer idProd;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Integer idCat;

    public ProductAux() {
        idProd = 0;
    }

    public ProductAux(Product p) {
        this.idProd = p.getIdProd();
        this.description = p.getDescription();
        this.idCat = p.getIdCat().getIdCat();
        this.idProd = p.getIdProd();
        this.name = p.getName();
        this.price = p.getPrice();
        this.stock = p.getStock();
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

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }
    
}
