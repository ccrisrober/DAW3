/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.User;
import com.d3.d3.model.others.UserLogin;
import com.d3.d3.repository.UserRepository;
import com.d3.d3.validation.others.Functions;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserRepository userRepository;

    @Override
    public boolean create(User u) {
        if(userRepository.getUser(u.getNickname())== null) {
            try {
                u.setPassword(Functions.sha1(u.getPassword()));
            } catch (NoSuchAlgorithmException ex) {
                return false;
            }
            User us = userRepository.save(u);
            return us != null && us.getIdUsu() > 0;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        User del = userRepository.findOne(id);
        if(del == null) {
            return false;
        }
        userRepository.delete(id);
        return true;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public int chekLogin(UserLogin ul) {
        try {
            ul.setPassword(Functions.sha1(ul.getPassword()));
        } catch (NoSuchAlgorithmException ex) {
            return -1;
        }
        User usu = userRepository.getUserLogin(ul.getUsername(), ul.getPassword());
        return (usu == null) ? -1 : usu.getIdUsu();
        //return ((usu != null) && (usu.getIdUsu()>=1))? usu.getIdUsu():-1;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public boolean update(User u) {
        User old = userRepository.findOne(u.getIdUsu());
        if(old == null) {
            return false;
        }
        old.update(u);
        old = userRepository.save(u);
        return old != null && old.getIdUsu() > 0;
    }

    @Override
    public void setRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
