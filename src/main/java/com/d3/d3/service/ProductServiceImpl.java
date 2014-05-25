/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Image;
import com.d3.d3.model.Product;
import com.d3.d3.repository.ImageRepository;
import com.d3.d3.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageRepository imageRepository;

    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public boolean create(Product product) {
        Product p = productRepository.save(product);
        return p != null && p.getIdProd() > 0;
    }

    @Override
    public boolean update(Product product) {
        Product old = productRepository.findOne(product.getIdProd());
        if(old == null) {
            return false;
        }
        old.update(product);
        old = productRepository.save(old);
        return old != null && old.getIdProd() > 0;
    }

    @Override
    public boolean delete(int id) {
        Product del = productRepository.findOne(id);
        if(del == null) {
            return false;
            //throw new ProductNotFoundException();
        }
        productRepository.delete(id);
        return true;
    }

    @Override
    public Product findById(int id) {
        Product p = productRepository.findOne(id);
        if(p != null) {
            List<Image> images = this.imageRepository.findByProductId(id);
            p.setImageCollection(images);
        }
        return p;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findBySearchName(String search) {
        return productRepository.findByName("%"+search+"%");
    }

    @Override
    public Integer findStockById(Integer id) {
        Integer stock = productRepository.findStockById(id);
        if(stock < 0) {
            stock = 0;
        }
        return stock;
    }

    @Override
    public Double findPriceById(Integer id) {
        Double price = productRepository.findPriceById(id);
        if(price < 0) {
            price = 0.0;
        }
        return price;
    }

    @Override
    public boolean removeStock(Integer id, Integer quantity) {
        Product prod = this.findById(id);
        Integer stock = prod.getStock();
        stock -= quantity;
        if(stock < 0) {
            return false;
        }
        prod.setStock(stock);
        return this.update(prod);
    }

    @Override
    public void setRepository(ProductRepository productRepositoryMock) {
        this.productRepository = productRepositoryMock;
    }

    @Override
    public List<Product> findByIdCat(Integer idCat) {
        return productRepository.findByIdCategory(idCat);
    }
}
