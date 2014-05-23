/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Product;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface ProductService {

    public boolean create(Product product);

    public boolean update(Product product);

    public boolean delete(int id_);

    public Product findById(int id_);

    public List<Product> findAll();

    public List<Product> findBySearchName(String search);

    public Integer findStockById(Integer id);
    
    public Double findPriceById(Integer id);
    
}
