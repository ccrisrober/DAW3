/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.User;
import com.d3.d3.validation.UserValidator;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
public class RegisterController {

    @InitBinder(value = "user")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "register/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String sendLogin (
            @ModelAttribute(value = "user") @Valid User user,
            BindingResult errors, ModelMap modelMap, HttpSession session) {
        if (errors.hasErrors()) {
            System.out.println("some errors occur");
            return "register/register";
        }
        
        System.out.println("Conectado con éxito");
        modelMap.addAttribute("username", user.getNickname());
        session.setAttribute("id_user", user.getIdUsu());
        return "register/registerSuccess";
    }
    
}
