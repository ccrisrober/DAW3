/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.Product;
import com.d3.d3.service.ProductService;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
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
    
    @Resource
    private ProductService productService;
    
    private final String URL = "index";
    private final String INDEX = URL + "/index";
    private final String ABOUT = URL + "/about";
    private final String CONTACT = URL + "/contact";
    private final String SEARCH = URL + "/search";
    private final String REDIR = "redirect:index.html";
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model m) {
        m.addAttribute("page", "index");
        return INDEX;
    }
    
    @RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
    public String aboutUs(Model m) {
        m.addAttribute("page", "aboutUs");
        return ABOUT;
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model m) {
        m.addAttribute("page", "contact");
        return CONTACT;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search_get() {
        return REDIR;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search (@RequestParam(value = "search", defaultValue = "",
            required = false) String search, Model m) {
        List<Product> products;
        if(search.isEmpty()) {
            products = new LinkedList<Product>();
        } else {
            products = productService.findBySearchName(search);
        }
        m.addAttribute("products", products);
        m.addAttribute("search", search);
        return SEARCH;
    }
}
