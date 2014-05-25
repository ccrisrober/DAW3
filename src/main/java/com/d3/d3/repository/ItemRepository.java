/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.repository;

import com.d3.d3.model.Item;
import com.d3.d3.model.ItemPK;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cristian
 */
public interface ItemRepository extends JpaRepository<Item, ItemPK>{

    @Query("select i from Item i where i.order1.idOrd = :idOrd")
    public Collection<Item> findByIdOrd(@Param("idOrd") Integer idOrd);
    
}