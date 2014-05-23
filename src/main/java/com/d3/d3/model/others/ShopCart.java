/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model.others;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cristian
 */
public class ShopCart implements Serializable {
    protected Map<Integer, ItemProduct> products;  //id_prod, ItemProduct(Producto y cantidad)
    protected int id_user;
    
    public ShopCart(int id_user) {
        products = new HashMap<Integer, ItemProduct>();
        this.id_user = id_user;
    }
    
    synchronized public void addItem(int[] id_producto, int[] quantity) {
        for(int i = 0; i < id_producto.length; i++) {
            ShopCart.this.addItem(id_producto[i], quantity[i]);
        }
    }
    
    synchronized public void addItem(int id_producto, int quantity) {
        if(products.containsKey(id_producto)) {
            //quantity += productos.get(id_producto);
        }
        //productos.put(id_producto, quantity);
    }

    public Collection<ItemProduct> getProducts() {
        return products.values();
    }

    public int getId_user() {
        return id_user;
    }
    
    public boolean valid(int id_user) {
        return id_user == this.getId_user();
    }
    
}
