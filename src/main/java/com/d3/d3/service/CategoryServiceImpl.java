/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Category;
import com.d3.d3.model.Product;
import com.d3.d3.repository.CategoryRepository;
import com.d3.d3.repository.ImageRepository;
import com.d3.d3.repository.ProductRepository;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Resource
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;

    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(int id_) {
        return categoryRepository.findOne(id_);
    }

    @Transactional(rollbackFor = CategoryNotFoundException.class)
    @Override
    public boolean update(Category c) throws CategoryNotFoundException{
        // Buscamos la categoría con el id de "c"
        Category old = categoryRepository.findOne(c.getIdCat());
        // Si es null, devolvemos excepción
        if(old == null) {
            throw new CategoryNotFoundException();
        }
        old.update(c);
        old = categoryRepository.save(old);
        return old != null && old.getIdCat() > 0;
    }

    @Transactional(rollbackFor = CategoryNotFoundException.class)
    @Override
    public boolean delete(int id) throws CategoryNotFoundException{
        // Buscamos la "categoría" con el id = "id"
        Category del = categoryRepository.findOne(id);
        // Si es null, devolvemos excepción
        if(del == null) {
            return false;
            //throw new CategoryNotFoundException();
        }
        categoryRepository.delete(id);
        return true;
    }

    /**
     * Si es null, significa que no existe la categoría : D
     * @param id
     * @return 
     */
    @Transactional(readOnly = true)
    @Override
    public List<Product> findProductWithIdCategory(Integer id) {
        List<Product> categories = null;
        if(categoryRepository.exists(id)) {
            categories = productRepository.findByIdCategory(id);
        }
        if(categories == null) {
            categories = new LinkedList<Product>();
        } else {
            for(Product p: categories) {
                p.setImageCollection(imageRepository.findByProductId(p.getIdProd()));
            }
        }
        return categories;
    }
}
