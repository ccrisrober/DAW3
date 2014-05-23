/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Product;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.model.others.OrderReceipt;
import com.d3.d3.model.others.ShopCart;
import com.d3.d3.repository.OrderRepository;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;
    
    @Resource
    private ProductService productService;

    @Override
    public boolean createOrder(ShopCart sp, OrderReceipt receipt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getTotalPrice(Collection<ItemProduct> products) {
        double total = 0.0;
        for(ItemProduct p: products) {
            total += productService.findPriceById(p.getId());
        }
        return total;
    }
}
