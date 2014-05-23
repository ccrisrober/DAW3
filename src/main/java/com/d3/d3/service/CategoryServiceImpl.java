/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Category;
import com.d3.d3.model.Product;
import com.d3.d3.repository.CategoryRepository;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Resource
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(int id_) {
        return categoryRepository.findOne(id_);
    }

    @Override
    public boolean update(Category c) {
        // Buscamos la categoría con el id de "c"
        Category old = categoryRepository.findOne(c.getIdCat());
        // Si es null, devolvemos excepción
        if(old == null) {
            return false;
            //throw new CategoryNotFoundException();
        }
        old.update(c);
        categoryRepository.save(old);
        return old != null && old.getIdCat() > 0;
    }

    @Override
    public boolean delete(int id) throws CategoryNotFoundException{
        // Buscamos la "categoría" con el id = "id"
        Category del = categoryRepository.findOne(id);
        // Si es null, devolvemos excepción
        if(del == null) {
            throw new CategoryNotFoundException();
        }
        categoryRepository.delete(del);
        return true;
    }

    @Override
    public List<Product> findProductWithIdCategory(Integer id) {
        return categoryRepository.getProductWithIdCat(id);
    }
}
