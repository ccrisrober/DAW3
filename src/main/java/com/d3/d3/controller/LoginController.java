/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d3.d3.controller;

import com.d3.d3.model.others.UserLogin;
import com.d3.d3.service.UserService;
import com.d3.d3.validation.others.Functions;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;
    
    private static final String REDIR_INDEX = "redirect:" + Functions.URL;
    private static final String LOGIN = "/login/login";

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        //if (Functions.isAdmin(session) || Functions.isLogin(session)) {
            session.setAttribute(Functions.ID_USER, null);
            session.setAttribute(Functions.ID_ADMIN, null);
            session.setAttribute(ShopCartController.SHOPCART, null);
        //}
        return REDIR_INDEX;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login(Model m, HttpServletRequest request, HttpSession session) {
        if (Functions.isAdmin(session) || Functions.isLogin(session)) {
            return REDIR_INDEX;
        }
        m.addAttribute("userlogin", new UserLogin());
        m.addAttribute("referer", request.getHeader("Referer"));
        return LOGIN;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String loginpost(@ModelAttribute(value = "userlogin") @Valid UserLogin login,
            BindingResult errors, Model m, HttpSession session,
            @ModelAttribute(value = "referer") String referer) {
        if (errors.hasErrors()) {
            m.addAttribute("error", "Datos incorrectos");
            return LOGIN;
        }
        /*if(Functions.isAdmin(login)) {
         return "redirect:"+ referer;
         }*/
        if (Functions.isAdmin(login)) {
            session.setAttribute(Functions.ID_ADMIN, Functions.ADMIN_SESSION);
        } else {
            int id_user = userService.chekLogin(login);
            if (id_user <= 0) {
                m.addAttribute("error", "No se encuentra un usuario con esos datos");
                return LOGIN;
            }
            session.setAttribute(Functions.ID_USER, id_user);
        }
        return "redirect:" + referer;
    }

    /*@RequestMapping("/login-error")
     public String loginError(Model model) {
     model.addAttribute("loginError", true);
     return "login/login";
     }*/
}
