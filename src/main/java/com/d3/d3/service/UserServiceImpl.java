/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.repository.UserRepository;
import javax.annotation.Resource;


public class UserServiceImpl implements UserService {
    @Resource
    UserRepository userRepository;
}
