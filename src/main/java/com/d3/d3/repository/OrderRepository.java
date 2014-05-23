/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.repository;

import com.d3.d3.model.Order1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cristian
 */
public interface OrderRepository extends JpaRepository<Order1, Integer>{
    /*
    @Modifying
    @Query("UPDATE Order o SET o.status = :status WHERE o.idOrd = :idOrd")
    public boolean changeStatus(@Param("status") String status,
            @Param("idOrd") Integer idOrd);*/
    
}