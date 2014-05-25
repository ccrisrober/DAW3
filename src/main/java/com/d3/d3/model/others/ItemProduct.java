/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model.others;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Cristian
 */
public class ItemProduct {
    @NotNull
    Integer quantity;
    @NotNull
    Integer id;

    public ItemProduct() {
    }

    public ItemProduct(Integer quantity, Integer id) {
        this.quantity = quantity;
        this.id = id;
    }
    
    public ItemProduct(int id) {
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
    
}
