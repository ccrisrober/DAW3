/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Category;
import com.d3.d3.model.Product;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface CategoryService {

    public List<Category> findAll();

    public Category create(Category category);

    public Category findById(int id_);

    public boolean update(Category category);

    public boolean delete(int id_) throws CategoryNotFoundException;

    public List<Product> findProductWithIdCategory(Integer id);
    
}
