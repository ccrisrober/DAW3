/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Product;
import com.d3.d3.repository.ImageRepository;
import com.d3.d3.repository.ProductRepository;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface ProductService {

    public boolean create(Product product);

    public boolean update(Product product) throws ProductNotFoundException;

    public boolean delete(int id_) throws ProductNotFoundException;

    public Product findById(int id_);

    public List<Product> findAll();

    public List<Product> findBySearchName(String search);

    public Integer findStockById(Integer id);
    
    public Double findPriceById(Integer id);

    public boolean removeStock(Integer id, Integer quantity) throws ProductNotFoundException;

    public void setRepository(ProductRepository productRepositoryMock);
    
    public List<Product> findByIdCat(Integer idCat);
    
    public void setRepository(ImageRepository imageRepository);

    public Product findById(Integer id, boolean b);
    
}
