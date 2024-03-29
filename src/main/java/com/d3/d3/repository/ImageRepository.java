/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.repository;

import com.d3.d3.model.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cristian
 */
public interface ImageRepository extends JpaRepository<Image, Integer>{
    
    @Query("SELECT i FROM Image i WHERE i.idProd.idProd = :id")
    public List<Image> findByProductId(@Param("id") Integer id);
    
}