/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d3.d3.controller;

import com.d3.d3.model.Order1;
import com.d3.d3.model.User;
import com.d3.d3.service.UserService;
import com.d3.d3.validation.others.Functions;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final String INDEX_USER = "user";
    private final String USER_ALL = INDEX_USER + "/index";
    private final String INDEX_ORDER = "order";
    private final String ORDER_ALL = INDEX_ORDER + "/showAllOrders";
    private final String USER_ORDER_ALL = INDEX_ORDER + "/showAllOrders";
    private final String ADMIN_INDEX = "admin/admin";

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String admin(Model model, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            redir = ADMIN_INDEX;
        }
        return redir;
    }

    // Esto solo visible para "admin"
    @RequestMapping(value = {"/user", "/user/all", "/user/index"}, method = RequestMethod.GET)
    public String allUsers(Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            List<User> users = userService.findAll();
            if (users == null) {
                users = new LinkedList<User>();
            }
            m.addAttribute("users", users);
            redir = USER_ALL;
        }
        return redir;
    }

    @RequestMapping(value = "/user/order/{id}", method = RequestMethod.GET)
    public String allOrderuser(@PathVariable String id, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            int id_ = Functions.getInt(id);
            if (id_ <= 0) {
                m.addAttribute("error", "Producto no encontrado");
            } else {
                User u = userService.findById(id_);
                List<Order1> orders;
                if (u == null || u.getOrder1Collection() == null) {
                    orders = new LinkedList<Order1>();
                } else {
                    orders = (List<Order1>) u.getOrder1Collection();
                }
                m.addAttribute("orders", orders);
            }
            redir = USER_ORDER_ALL;
        }
        return redir;
    }
}
