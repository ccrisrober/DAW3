/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.User;
import com.d3.d3.service.UserService;
import com.d3.d3.validation.UserValidator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cristian
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private UserService userService;
    
    private final String URL = "register";
    private final String REGISTER = URL + "/register";
    private final String REGISTER_SUCCESS = URL + "/registerSuccess";
    
    /*@InitBinder(value = "user")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }*/

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return REGISTER;
    }

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.POST)
    public String sendLogin (
            @ModelAttribute(value = "user") @Valid User user,
            BindingResult errors, ModelMap modelMap) {
        String redir = REGISTER_SUCCESS;
        
        
        
        List<ObjectError> allErrors = errors.getAllErrors();
        for(ObjectError oe: allErrors) {
            System.out.println(oe);
        }
        
        
        
        if (errors.hasErrors()) {
            redir = REGISTER;
        } else {
            if(!userService.create(user)) {
                redir = REGISTER;
            }
            modelMap.addAttribute("username", user.getNickname());
        }
        return redir;
    }
    
}
