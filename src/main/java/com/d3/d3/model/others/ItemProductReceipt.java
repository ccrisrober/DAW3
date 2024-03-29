/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model.others;

import com.d3.d3.model.Product;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cristian
 */
public class ItemProductReceipt implements Serializable {
    @NotNull
    Integer quantity;
    @NotNull
    Integer id;
    @NotNull
    Product product;
    @NotNull
    Double price;

    public ItemProductReceipt() {
    }
    
    public ItemProductReceipt(ItemProduct ip, Product p) {
        this.id = ip.getId();
        this.product = p;
        this.quantity = ip.getQuantity();
        this.price = ip.getQuantity() * p.getPrice();
    }
    
    public ItemProductReceipt(int id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
}
