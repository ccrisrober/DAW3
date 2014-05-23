/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.validation.CategoryValidator;
import com.d3.d3.model.Category;
import com.d3.d3.model.Product;
import com.d3.d3.service.CategoryService;
import com.d3.d3.validation.others.Functions;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cristian
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    
    @Resource
    public CategoryService categoryService;
    
    private final String URL = "category";
    private final String INDEX = URL + "/index";
    private final String CATJS = URL + "/categoryjs";
    private final String CREATE = URL + "/create";
    private final String EDIT = URL + "/edit";
    private final String SHOW = URL + "/show";
    
    @InitBinder(value = "category")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CategoryValidator());
    }
    
    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model m) {
        List<Category> categories = categoryService.findAll();
        if(categories == null) {
            categories = new LinkedList<Category>();
        }
        m.addAttribute("categories", categories);
        return INDEX;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create_get(Model m) {
        m.addAttribute("category", new Category());
        return CREATE;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create_post(@ModelAttribute(value = "category") @Valid Category category,
            BindingResult errors, Model m) {
        if(errors.hasErrors()) {
            return CREATE;
        }
        
        Category c = categoryService.create(category);
        if(c == null) {
            m.addAttribute("error", "category.create.error");
        } else {
            m.addAttribute("ok", "category.create.ok");
        }
        return CATJS;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit_get(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        if (id_ <= 0) {
            m.addAttribute("error", "category.notfound");
        } else {
            Category c = categoryService.findById(id_);
            if (c == null) {
                m.addAttribute("error", "category.notfound");
            } else {
                m.addAttribute("category", c);
                return EDIT;
            }
        }
        return CATJS;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit_post(@ModelAttribute(value = "category") @Valid Category category,
            BindingResult errors, Model m) {
        if(errors.hasErrors()) {
            System.out.println("ERRORES");
            return EDIT;
        }
        boolean edit = categoryService.update(category);
        if(!edit) {
            m.addAttribute("error", "category.edit.error");
        } else {
            m.addAttribute("ok", "category.edit.ok");
        }
        return CATJS;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        if(id_ <= 0) {
            m.addAttribute("error", "category.notfound");
        }
        boolean delete = categoryService.delete(id_);
        if(!delete) {
            m.addAttribute("error", "category.delete.error");
        } else {
            m.addAttribute("ok", "category.delete.ok");
        }
        return CATJS;
    }
    
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showProductWithCatgID(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        if(id_ <= 0) {
            m.addAttribute("error", "category.notfound");
        }
        List<Product> products = categoryService.findProductWithIdCategory(id_);
        if(products == null) {
            products = new LinkedList<Product>();
        }
        m.addAttribute("products", products);
        return SHOW;
    }
}
