/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Item;
import com.d3.d3.model.ItemPK;
import com.d3.d3.model.Order1;
import com.d3.d3.model.Product;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.repository.ItemRepository;
import com.d3.d3.repository.ProductRepository;
import java.util.Collection;
import javax.annotation.Resource;


public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemRepository itemRepository;
    @Resource
    private ProductRepository productRepository;

    @Resource
    private ProductService productService;
    @Resource
    private ItemService itemService;
    
    private void init() {
        if(productService == null) {
            productService = new ProductServiceImpl();
        }
        if(itemService == null) {
            itemService = new ItemServiceImpl();
        }
    }
    
    @Override
    public boolean create(ItemProduct ip, Order1 order) {
        init();
        productService.setRepository(productRepository);
        Product p = productService.findById(ip.getId(), false);
        if(p == null || p.getIdProd() <= 0) {
            //ERROR XD
        }
        Item item = new Item();
        ItemPK pk = new ItemPK();
        pk.setIdOrd(order.getIdOrd());
        pk.setIdProd(ip.getId());
        item.setItemPK(pk);
        item.setQuantity(ip.getQuantity());
        item.setProduct(p);
        item.setOrder1(order);
        Item create = itemRepository.save(item);
        if(create == null) {
            // ERROR XD
            return false;
        }
        //Ahora borro el stock asociado al objeto
        if(!productService.removeStock(ip.getId(), ip.getQuantity())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Collection<ItemProduct> ip, Order1 order) {
        init();
        boolean ret_ = true;
        for(ItemProduct itp: ip) {
            if(!this.create(itp, order)) {
                ret_ = false;
                break;
            }
        }
        return ret_;
    }

    @Override
    public void setRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void setRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean delete(int idOrd) {
        Collection<Item> items = itemRepository.findByIdOrd(idOrd);
        for(Item i: items) {
            itemRepository.delete(i);
        }
        return true;
    }
}
