/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.Product;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Cristian
 */
@Controller
public class IndexController {
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model m) {
        m.addAttribute("page", "index");
        return "index/index";
    }
    
    @RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
    public String aboutUs(Model m) {
        m.addAttribute("page", "aboutUs");
        return "index/aboutUs";
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model m) {
        m.addAttribute("page", "contact");
        return "index/contact";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search_get() {
        return "redirect:index.html";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search (@RequestParam(value = "search", defaultValue = "",
            required = false) String search, Model m) {
        List<Product> products;
        if(search.isEmpty()) {
            products = new LinkedList<Product>();
        } else {
            products = new LinkedList<Product>();//Esto va con DAO
        }
        m.addAttribute("products", products);
        m.addAttribute("search", search);
        return "index/search";
    }
    /*// Determino la vista
    @ModelAttribute("page")
    public String module() {
        return "index";
    }*/
}
