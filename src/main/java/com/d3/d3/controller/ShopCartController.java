/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.others.OrderReceipt;
import com.d3.d3.validation.OrderReceiptValidator;
import com.google.common.base.Functions;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cristian
 */
@Controller
@RequestMapping("/shopcart")
public class ShopCartController {
    
    /*private final static String SHOPCART = "shopcart";
    
    // Determino la vista
    @ModelAttribute("page")
    public String module() {
        return SHOPCART;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute(value = "itemproduct") @Valid ItemProduct item,
            BindingResult errors, HttpServletRequest request, 
            HttpSession session) {
        
        String referer = request.getHeader("Referer");  // Obtenemos la página de dónde venimos : D
        
        // Comprobamos que existe el id_user en session
        int id_user = Functions.getID_USER(session);
        if(id_user <= 1) {
            return "redirect:login.html";   // Creo que así valdría : D
        }
        
        // Vemos si existe el shopcart
        Object spp = session.getAttribute(SHOPCART);
        ShopCart sp;
        if((spp == null) || ! (spp instanceof ShopCart)) {
            // Cargamos el shopcart en "sp"
            sp = new ShopCart(id_user);
        } else {
            sp = (ShopCart) spp;
            if(!sp.valid(id_user)) {
                //error
            }
        }
        // Añadimos el producto
        
        
        
        
        // HAY QUE MIRAR LO DEL STOOOOOCK
        
        
        
        
        
        
        sp.annadirProducto(item.getId_prod(), item.getQuantity());
        session.setAttribute(SHOPCART, sp);
        return "redirect:"+ referer;
    }
    
    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String main(HttpSession session) {
        return "shopcart/index";
    }
    
    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public String finish(HttpSession session, ModelMap model, 
            @ModelAttribute(value = "orderreceipt") @Valid OrderReceipt receipt,
            BindingResult errors) {
        if (errors.hasErrors()) {
            System.out.println("Error validación");
            return "???";   //error
        }
        // Comprobamos que existe el id_user en session
        int id_user = Functions.getID_USER(session);
        if(id_user <= 1) {
            return "redirect:login.html";   // Creo que así valdría : D
        }
        // Vemos si existe el shopcart
        Object spp = session.getAttribute(SHOPCART);
        if((spp == null) || ! (spp instanceof ShopCart)) {
            //Error
        }
        ShopCart sp = (ShopCart) spp;
        if(!sp.valid(id_user)) {
            //error
        }
        
        //Lógica base de datos de creación del pedido total : D
        boolean error = false;
        
        
        if (error) {
            model.addAttribute("error", "Se ha producido un error");
            return "shopcart/error";
        }
        session.setAttribute(SHOPCART, null);   // Con esto eliminamos el "shopcart" de session
        return "shopcart/finish";
    }
    
    @RequestMapping(value = {"/add", "/finish"}, method = RequestMethod.GET)
    public String error(HttpServletRequest request) {
        String referer = request.getHeader("Referer");  // Obtenemos la página de dónde venimos : D
        return "redirect:"+ referer;
    }
    
    @RequestMapping(value = "/receipt", method = RequestMethod.POST)
    public String showReceipt(HttpSession session, ModelMap m) {
        // Comprobamos que existe el id_user en session
        int id_user = Functions.getID_USER(session);
        if(id_user <= 1) {
            return "redirect:login.html";   // Creo que así valdría : D
        }
        // Vemos si existe el shopcart
        Object spp = session.getAttribute(SHOPCART);
        if((spp == null) || ! (spp instanceof ShopCart)) {
            //Error
        }
        ShopCart sp = (ShopCart) spp;
        if(!sp.valid(id_user)) {
            //error
        }
        
        Map<Integer, ItemProduct> prodsM = sp.getProductos();
        Set<Entry<Integer, ItemProduct>> item = prodsM.entrySet();
        //Creo el servicio de consulta : D
        float total = 0;
        List<ItemProduct> prods = new LinkedList<ItemProduct>();
        for(Entry<Integer, ItemProduct> prod: item) {
            prods.add(prod.getValue());
            // Extraigo el precio del service (5 es el valor simulado xD)
            total += prod.getKey() * 5;
        }
        
        total = (float) (Math.rint(total*100)/100);   //Redondeo a dos decimales, en plan euros
        m.addAttribute("total", total);
        return "shopcart/receipt";
    }
    
    @InitBinder(value = "orderreceipt")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new OrderReceiptValidator());
    }
    */
}
