/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d3.d3.controller;

import com.d3.d3.model.Category;
import com.d3.d3.model.Product;
import com.d3.d3.service.CategoryNotFoundException;
import com.d3.d3.service.CategoryService;
import com.d3.d3.validation.others.Functions;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cristian
 */
@Controller
//@RequestMapping("/admin/category")
public class CategoryController {

    @Resource
    public CategoryService categoryService;

    private final String URL = "category";
    private final String INDEX = URL + "/index";
    private final String CATJS = URL + "/categoryjs";
    private final String CREATE = URL + "/create";
    private final String EDIT = URL + "/edit";
    private final String SHOW = URL + "/show";

    /*@InitBinder(value = "category")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CategoryValidator());
    }*/

    @RequestMapping(value = {"/admin/category", "/admin/category/index"}, method = RequestMethod.GET)
    public String index(HttpSession session, Model m) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            List<Category> categories = categoryService.findAll();
            if (categories == null) {
                categories = new LinkedList<Category>();
            }
            m.addAttribute("categories", categories);
            redir = INDEX;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/category/create", method = RequestMethod.GET)
    public String create_get(Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            m.addAttribute("category", new Category());
            redir = CREATE;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/category/create", method = RequestMethod.POST)
    public String create_post(@ModelAttribute(value = "category") @Valid Category category,
            BindingResult errors, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            if (errors.hasErrors()) {
                return CREATE;
            }

            Category c = categoryService.create(category);
            if (c == null) {
                m.addAttribute("error", "No se ha podido crear la categoría");
            } else {
                m.addAttribute("ok", "Categoría creada");
            }
            redir = CATJS;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/category/edit/{id}", method = RequestMethod.GET)
    public String edit_get(@PathVariable String id, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            int id_ = Functions.getInt(id);
            if (id_ <= 0) {
                m.addAttribute("error", "Categoría no encontrada");
            } else {
                Category c = categoryService.findById(id_);
                if (c == null) {
                    m.addAttribute("error", "Categoría no encontrada");
                } else {
                    m.addAttribute("category", c);
                    return EDIT;
                }
            }
            redir = CATJS;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/category/edit/{id}", method = RequestMethod.POST)
    public String edit_post(@ModelAttribute(value = "category") @Valid Category category,
            BindingResult errors, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            if (errors.hasErrors()) {
                System.out.println("ERRORES");
                return EDIT;
            }
            boolean edit = false;
            try {
                edit = categoryService.update(category);
            } catch (CategoryNotFoundException ex) {
                m.addAttribute("error", "No se ha podido actualizar");
            }
            if (!edit) {
                m.addAttribute("error", "No se ha podido actualizar");
            } else {
                m.addAttribute("ok", "Categoría actualizada");
            }
            redir = CATJS;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/category/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id, Model m, HttpSession session) {
        String redir = Functions.goAdmin(session);
        if (redir.isEmpty()) {
            int id_ = Functions.getInt(id);
            if (id_ <= 0) {
                m.addAttribute("error", "Categoría no encontrada");
            }
            boolean delete = false;
            try {
                delete = categoryService.delete(id_);
            } catch (CategoryNotFoundException ex) {
                m.addAttribute("error", "No se ha podido borrar");
            }
            if (!delete) {
                m.addAttribute("error", "No se ha podido borrar");
            } else {
                m.addAttribute("ok", "Categoría borrada");
            }
            redir = CATJS;
        }
        return redir;
    }

    @RequestMapping(value = "/admin/category/show/{id}", method = RequestMethod.GET)
    public String showProductWithCatgID(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        List<Product> products = new LinkedList<Product>();
        if (id_ > 0) {
            products = categoryService.findProductWithIdCategory(id_);
            if (products == null) {
                products = new LinkedList<Product>();
            }
        }
        m.addAttribute("products", products);
        return SHOW;
    }
}
