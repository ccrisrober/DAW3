/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d3.d3.controller;

import com.d3.d3.model.Image;
import com.d3.d3.model.Product;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.service.ImageService;
import com.d3.d3.service.ProductService;
import com.d3.d3.validation.ItemProductValidator;
import com.d3.d3.validation.ProductValidator;
import com.d3.d3.validation.others.Functions;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Cristian
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;
    @Resource
    private ImageService imageService;

    // Determino la vista
    @ModelAttribute("page")
    public String module() {
        return "product";
    }
    
    @InitBinder(value = "itemproduct")
    protected void initBinderItemProduct(WebDataBinder binder) {
        binder.setValidator(new ItemProductValidator());
    }

    private final String URL = "product";
    private final String INDEX = URL + "/index";
    private final String CREATE = URL + "/create";
    private final String EDIT = URL + "/edit";
    private final String DELETE = URL + "/delete";
    private final String SHOW = URL + "/show";
    private final String SHOW_ALL = URL + "/showAll";
    private final String PRODJS = URL + "/productjs";

    @InitBinder(value = "product")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ProductValidator());
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return CREATE;
    }

    private String uploadImage(MultipartFile image, Product prod) {
        String message;
        try {
            byte[] bytes = image.getBytes();

            // Creating the directory to store file
            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "tmpFiles");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + image.getName());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            System.err.println("Server File Location=" + serverFile.getAbsolutePath());
            System.err.println(dir.getAbsolutePath() + File.separator + image.getName());

            message = "You successfully uploaded file=" + image.getName() + "<br />";

            Image img = new Image();
            img.setImage(serverFile.getAbsolutePath());
            img.setIdProd(prod);
            imageService.create(img);

        } catch (IOException e) {
            return "You failed to upload " + image.getName() + " => " + e.getMessage();
        }
        return message;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create_post(@ModelAttribute(value = "product") @Valid Product product,
            BindingResult errors, Model m, @RequestParam("image") MultipartFile[] images) {
        if (errors.hasErrors()) {
            System.out.println("Error validación");
            return CREATE;
        }
        boolean insert = productService.create(product);
        if (!insert) {
            m.addAttribute("error", "No se ha podido insertar");
        } else {
            // Si todo ha ido bien, subo las fotos
            String message = "";
            for (MultipartFile image : images) {
                message += uploadImage(image, product);
            }
            m.addAttribute("ok", "Producto " + product.getName() + " insertado");
        }
        return PRODJS;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        if (id_ <= 0) {
            m.addAttribute("error", "Producto no encontrado");
        } else {
            Product p = productService.findById(id_);
            if (p != null) {
                m.addAttribute("product", p);
                m.addAttribute("itemproduct", new ItemProduct(id_));
                return SHOW;
            } else {
                m.addAttribute("error", "Producto no encontrado");
            }
        }
        return PRODJS;
    }

    @RequestMapping(value = {"/all", "", "/index"}, method = RequestMethod.GET)
    public String showAll(@RequestParam(value = "error", defaultValue = "",
            required = true) String error, @RequestParam(value = "ok", defaultValue = "",
                    required = true) String ok, ModelMap model) {
        List<Product> lp = productService.findAll();
        if (!ok.isEmpty()) {
            model.addAttribute("ok", ok);
        }
        if (!error.isEmpty()) {
            model.addAttribute("error", error);
        }
        model.addAttribute("products", lp);
        return SHOW_ALL;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit_get(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        if (id_ <= 0) {
            m.addAttribute("error", "Producto no encontrado");
        } else {
            Product p = productService.findById(id_);
            if (p == null) {
                m.addAttribute("error", "Producto no encontrado");
            } else {
                m.addAttribute("product", p);
                return EDIT;
            }
        }
        return PRODJS;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id,
            Model m) {
        int id_ = Functions.getInt(id);
        if (id_ <= 0) {
            m.addAttribute("error", "Producto no encontrado");
        } else {
            boolean delete = productService.delete(id_);
            if (!delete) {
                m.addAttribute("error", "Producto no encontrado");
            } else {
                m.addAttribute("ok", "Producto borrado");
            }
        }
        return PRODJS;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit_post(@ModelAttribute(value = "product") @Valid Product product,
            BindingResult errors, Model m) {
        if (errors.hasErrors()) {
            System.out.println("ERRORES");
            return EDIT;
        }
        boolean edit = productService.update(product);
        if (!edit) {
            m.addAttribute("error", "product.edit.error");
        } else {
            m.addAttribute("ok", "product.edit.ok");
        }
        return PRODJS;
    }

}
