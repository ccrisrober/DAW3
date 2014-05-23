/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.User;
import com.d3.d3.service.UserService;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cristian
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Resource
    private UserService userService;
    
    
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin/admin";
    }
    // Esto solo visible para "admin"
    @RequestMapping(value = {"/user", "/user/all", "/user/index"}, method = RequestMethod.GET)
    public String allUsers(Model m) {
        List<User> users = userService.findAll();
        if(users == null) {
            users = new LinkedList<User>();
        }
        m.addAttribute("users", users);
        return "user/users";
    }
    
}
