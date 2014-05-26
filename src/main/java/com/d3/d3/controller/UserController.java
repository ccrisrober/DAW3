/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.User;
import com.d3.d3.repository.UserRepository;
import com.d3.d3.service.UserNotFoundException;
import com.d3.d3.service.UserService;
import com.d3.d3.validation.others.Functions;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cristian
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    private static final String URL = "user";
    private static final String INDEX = URL + "/index";
    private static final String EDIT = URL + "/edit";
    private static final String EDIT_OK = EDIT + "Success";
    private static final String EDIT_ERROR = EDIT + "Error";
    private static final String ORDER = URL + "/order";
    
    /*@InitBinder(value = "user")
    protected void initBinderItemProduct(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }*/
    
    @Resource
    private UserService userService;
    @Resource
    private UserRepository userRepository;
    
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(HttpSession session, Model m) {
        String redir = Functions.goUser(session);
        if (redir.isEmpty()) {
            redir = INDEX;
            // Si entro aquí, es que el usuario existe
            userService.setRepository(userRepository);
            User user = userRepository.findOne(Functions.getID_USER(session));
            user.setNickname(" " + user.getNickname());
            m.addAttribute("user", user);
        }
        return redir;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit_get(HttpSession session, Model m) {
        String redir = Functions.goUser(session);
        if (redir.isEmpty()) {
            userService.setRepository(userRepository);
            User u = userService.findById(Functions.getID_USER(session));
            m.addAttribute("user", u);
            redir = EDIT;
        }
        return redir;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit_post(@ModelAttribute(value = "user") @Valid User user,
            BindingResult errors, Model m, HttpSession session) {
        String redir = Functions.goUser(session);
        if (redir.isEmpty()) {
            if(errors.hasErrors()) {
                return EDIT;
            }
            userService.setRepository(userRepository);
            boolean update = false;
            try {
                update = userService.update(user);
            } catch (UserNotFoundException ex) {
            }
            redir = update ? EDIT_OK : EDIT_ERROR;
        }
        return redir;
    }
}
