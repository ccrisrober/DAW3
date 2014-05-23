/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.others.ItemProduct;
import java.util.Collection;

/**
 *
 * @author Cristian
 */
public interface ItemService {
    public boolean create(ItemProduct ip, Integer idOrder);
    public boolean create(Collection<ItemProduct> ip, Integer idOrder);
}
