/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.repository;

import com.d3.d3.model.Product;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cristian
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{
    
    @Query("SELECT p FROM Product p WHERE p.name like :searchTerm")
    public List<Product> findByName(@Param("searchTerm") String searchTerm);

    @Query("SELECT p.stock FROM Product p WHERE p.idProd = :id")
    public Integer findStockById(@Param("id") Integer id);

    @Query("SELECT p.price FROM Product p WHERE p.idProd = :id")
    public Double findPriceById(@Param("id") Integer id);
    
    @Query("select p from Product p where p.idCat.idCat = :idCat")
    public List<Product> findByIdCategory(@Param("idCat") Integer idCat);
    
    /*@Modifying
    @Query("update p set p.stock = :stock where p.idProd = :idProd")
    public boolean editStock(@Param("idProd") Integer idProd, @Param("stock") Integer stock);*/
    
}