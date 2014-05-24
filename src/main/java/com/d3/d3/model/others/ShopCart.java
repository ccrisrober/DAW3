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
    
    synchronized public void addItem(int[] id_product, int[] quantity) {
        for(int i = 0; i < id_product.length; i++) {
            ShopCart.this.addItem(id_product[i], quantity[i]);
        }
    }
    
    synchronized public void addItem(int id_product, int quantity) {
        ItemProduct item = products.get(id_product);
        if(item == null) {
            item = new ItemProduct();
            item.setId(id_product);
            item.setQuantity(quantity);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }
        products.put(id_product, item);
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

    @Override
    public String toString() {
        String aux = "";
        for(ItemProduct ip: this.products.values()) {
            aux += ("Objeto: " + ip.id + ". Cantidad: " + ip.quantity) + "\n";
        }
        return aux;
    }
    
}
