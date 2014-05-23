/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d3.d3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cristian
 */
@Controller
public class LoginController {

    /*@InitBinder(value = "userlogin")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new LoginValidator());
    }*/
    
    /** Login form.
     * @return  */
    @RequestMapping("/login")
    public String login() {
        return "login/login";
    }
    
    /** Login form with error.
     * @param model
     * @return  */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login/login";
    }
    
}
