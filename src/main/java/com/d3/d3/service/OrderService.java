/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Order1;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.model.others.OrderReceipt;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface OrderService {

    public boolean createOrder(Collection<ItemProduct> sp, OrderReceipt receipt, Integer id_user, double plus);

    public double getTotalPrice(Collection<ItemProduct> products);
    
    public boolean updateStatus(Integer idOrd, String status);
    
    public List<Order1> findAll();
    
    public Order1 findById(Integer idOrd);
    
    public boolean checkAccessUser(Integer idOrd, Integer idUser);
}
