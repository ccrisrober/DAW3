/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Order1;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.model.others.ItemProductReceipt;
import com.d3.d3.model.others.OrderReceipt;
import com.d3.d3.repository.CardRepository;
import com.d3.d3.repository.ItemRepository;
import com.d3.d3.repository.OrderRepository;
import com.d3.d3.repository.ProductRepository;
import com.d3.d3.repository.UserRepository;
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
    
    public List<Order1> findAllUser(Integer idUser);
    
    public Order1 findById(Integer idOrd);
    
    public boolean checkAccessUser(Integer idOrd, Integer idUser);
    
    public String text();

    public Collection<ItemProductReceipt> generateReceipt(Collection<ItemProduct> products);

    //Setters 
    public void setRepository(ProductRepository productRepository);
    public void setRepository(UserRepository userRepository);
    public void setRepository(OrderRepository orderRepository);
    public void setRepository(CardRepository cardRepository);
    public void setRepository(ItemRepository itemRepository);

    public boolean delete(int id_);

}
