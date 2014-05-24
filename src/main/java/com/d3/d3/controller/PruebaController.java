/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.Image;
import com.d3.d3.model.Product;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.model.others.OrderReceipt;
import com.d3.d3.service.ImageService;
import com.d3.d3.service.OrderService;
import com.d3.d3.service.ProductService;
import com.d3.d3.service.UserService;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cristian
 */
@Controller
@RequestMapping("/prueba")
public class PruebaController {
    @Resource
    private OrderService orderService;
    @Resource
    private ProductService productService;
    @Resource
    private UserService userService;
    @Resource
    private ImageService imageService;
    
    private final String RUTA = "prueba";
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model m) {
        m.addAttribute("findById", userService.findById(1).getNickname());
        m.addAttribute("", userService.findAll().get(0).getNickname());
        return RUTA;
    }
    
    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public String stock(Model m) {
        m.addAttribute("error", !productService.removeStock(1, -20));
        return "index2";
    }
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll(Model m) {
        List<Product> findAll = productService.findAll();
        String salida = "";
        for(Product p: findAll) {
            salida += p.getName() + "\n";
        }
        m.addAttribute("error", salida);
        return "index2";
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String hola(Model m) {
        Product p = productService.findById(1);
        String aux = "";
        for(Image im: p.getImageCollection()) {
            aux += im.getImage() + "    ";
        }
        m.addAttribute("error", aux);
        /*Product findById = productService.findById(1);
        List<Image> li = new LinkedList<Image>();
        for (int i = 0; i < 5; i++) {
            Image im = new Image();
            im.setIdProd(findById);
            im.setImage("hola " + i + ".jpg");
            li.add(im);
        }
        m.addAttribute("error", !imageService.create(li));*/
        /*List<Image> list = imageService.findByProductId(1);
        String aux = "";
        for(Image mm: list) {
            aux += mm.getImage() + "  \n";
        }
        m.addAttribute("error", aux);*/
        return "index2";
    }
    
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String prueba(Model m) {
        Collection<ItemProduct> cir = new LinkedList<ItemProduct>();
        for (int i = 0; i < 5; i++) {
            ItemProduct ip = new ItemProduct();
            ip.setId((i%2) + 1);
            ip.setQuantity(10);
            cir.add(ip);
        }
        OrderReceipt or = new OrderReceipt();
        or.setCard1("");
        or.setCard2("");
        or.setCard3("");
        or.setCard4("");
        or.setDirection("Mi casa o q ase");
        or.setName("Mi nombre o k ase");
        or.setPayment("delivery");
        or.setSurname("Mi apellido o k ase");
        or.setPhone("916651199");
        System.out.println("hola");
        boolean error = orderService.createOrder(cir, or, 1, 5);
        m.addAttribute("error", orderService.text());
        return "index2";
    }
}
