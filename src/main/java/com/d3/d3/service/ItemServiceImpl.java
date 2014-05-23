/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Item;
import com.d3.d3.model.ItemPK;
import com.d3.d3.model.Product;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.repository.ItemRepository;
import java.util.Collection;
import javax.annotation.Resource;


public class ItemServiceImpl implements ItemService {
    @Resource
    ItemRepository itemRepository;

    @Resource
    private ProductService productService;
    
    @Override
    public boolean create(ItemProduct ip, Integer idOrder) {
        Product p = productService.findById(ip.getId());
        if(p == null || p.getIdProd() <= 0) {
            //ERROR XD
        }
        Item item = new Item();
        ItemPK pk = new ItemPK();
        pk.setIdOrd(idOrder);
        pk.setIdProd(ip.getId());
        item.setItemPK(pk);
        item.setQuantity(ip.getQuantity());
        Item create = itemRepository.save(item);
        if(create != null) {
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
    public boolean create(Collection<ItemProduct> ip, Integer idOrder) {
        boolean ret_ = true;
        for(ItemProduct itp: ip) {
            if(!this.create(itp, idOrder)) {
                ret_ = false;
                break;
            }
        }
        return ret_;
    }
}
