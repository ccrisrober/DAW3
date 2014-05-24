/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Order1;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.repository.ItemRepository;
import com.d3.d3.repository.ProductRepository;
import java.util.Collection;

/**
 *
 * @author Cristian
 */
public interface ItemService {
    public boolean create(ItemProduct ip, Order1 order);
    public boolean create(Collection<ItemProduct> ip, Order1 order);

    public void setRepository(ItemRepository itemRepository);

    public void setRepository(ProductRepository productRepository);
}
