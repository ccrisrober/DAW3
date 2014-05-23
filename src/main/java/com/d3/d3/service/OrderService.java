/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.model.others.OrderReceipt;
import com.d3.d3.model.others.ShopCart;
import java.util.Collection;

/**
 *
 * @author Cristian
 */
public interface OrderService {

    public boolean createOrder(ShopCart sp, OrderReceipt receipt);

    public double getTotalPrice(Collection<ItemProduct> products);
    
}
