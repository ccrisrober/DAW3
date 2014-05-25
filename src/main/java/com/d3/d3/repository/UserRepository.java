/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.repository;

import com.d3.d3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cristian
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query ("select u from User u where u.nickname = :nick and u.password = :pass")
    User getUserLogin (@Param("nick") String nick, @Param("pass") String pass);
    @Query("select u from User u where u.nickname = :nick")
    User getUser(@Param("nick") String nick);
}
