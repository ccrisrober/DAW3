/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.User;
import com.d3.d3.model.others.UserLogin;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface UserService {
    public boolean create(User u);
    public boolean delete(Integer id);// throws UserNotFoundException;
    public List<User> findAll();
    public int chekLogin(UserLogin ul);
    public User findById(Integer id);
    public boolean update(User u);// throws UserNotFoundException;
}
