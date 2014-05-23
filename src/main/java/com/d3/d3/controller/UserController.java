/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.User;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cristian
 */
@Controller
public class UserController {
    /*@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userIndex(Model model) {
        return "user/index";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public String userEdit_get(Model model, HttpSession session) {
        model.addAttribute("user", new User());     // Se debería devolver el valor del usuario con el ID de la sesión
        return "user/edit";
    }
    
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String userEdit_post(
            @ModelAttribute(value = "user") @Valid User user,
            BindingResult errors, ModelMap modelMap, HttpSession session) {
        if(session.getAttribute("id_user") == null) {
            return "redirect:../index.html";
        }
        else if (errors.hasErrors()) {
            System.out.println("some errors occur");
            return "user/edit";
        }
        return "user/editSuccess";
    }
    */
    
    
    // Esto solo visible para "admin"
    @RequestMapping(value = {"/user", "/user/all", "/user/index"}, method = RequestMethod.GET)
    public String allUsers(Model m) {
        List<User> users = new LinkedList<User>();  // Esto es una llamada a un DAO
        m.addAttribute("users", users);
        return "user/users";
    }
}
