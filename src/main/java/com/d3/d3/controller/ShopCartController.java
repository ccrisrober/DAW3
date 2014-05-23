/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.controller;

import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.model.others.OrderReceipt;
import com.d3.d3.model.others.ShopCart;
import com.d3.d3.service.OrderService;
import com.d3.d3.service.ProductService;
import com.d3.d3.validation.ItemProductValidator;
import com.d3.d3.validation.OrderReceiptValidator;
import com.d3.d3.validation.others.Functions;
import java.util.Collection;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    @InitBinder(value = "orderreceipt")
    protected void initBinderOrderReceipt(WebDataBinder binder) {
        binder.setValidator(new OrderReceiptValidator());
    }
    
    @InitBinder(value = "itemproduct")
    protected void initBinderItemProduct(WebDataBinder binder) {
        binder.setValidator(new ItemProductValidator());
    }
    
    private final String SHOPCART = "shopcart";
    private final String URL = "shopcart";
    private final String INDEX = URL + "/index";
    private final String RECEIPT = URL + "/receipt";
    private final String PAYMENT = URL + "/payment";
    private final String FINISH = URL + "/finish";
    private final String REDIR_LOGIN = "redirect: login.html";
    
    @ModelAttribute("page")
    public String module() {
        return SHOPCART;
    }
    
    @Resource
    private OrderService orderService;
    
    @Resource
    private ProductService productService;
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute(value = "itemproduct") @Valid ItemProduct item,
            BindingResult errors, HttpServletRequest request, 
            HttpSession session) {
        
        String referer = request.getHeader("Referer");  // Obtenemos la página de dónde venimos : D
        
        // Comprobamos que existe el id_user en session
        int id_user = Functions.getID_USER(session);
        if(id_user <= 1) {
            return REDIR_LOGIN;   // Creo que así valdría : D
        }
        
        // Vemos si existe el shopcart
        Object spp = session.getAttribute(SHOPCART);
        ShopCart sp;
        if((spp == null) || ! (spp instanceof ShopCart)) {
            sp = new ShopCart(id_user); // Cargamos el shopcart en "sp"
        } else {
            sp = (ShopCart) spp;
            if(!sp.valid(id_user)) {
                return REDIR_LOGIN;
            }
        }
        // Si llegamos aquí ya sabemos que tenemos un Carrito en perfecto estado
        //      Vamos a mirar si existe stock
        Integer stock = productService.findStockById(item.getId());
        if(item.getQuantity() > stock) {
            //Error, no hay espacio D:
        }
        //      Si hay stock, insertamos en el carrito : D
        sp.addItem(item.getId(), item.getQuantity());
        
        session.setAttribute(SHOPCART, sp);
        return "redirect:"+ referer;
    }
    
    @RequestMapping(value = "/receipt", method = RequestMethod.GET)
    public String receipt(Model m, HttpSession session) {
        // Comprobamos que existe el id_user en session
        int id_user = Functions.getID_USER(session);
        if(id_user <= 1) {
            return REDIR_LOGIN;   // Creo que así valdría : D
        }
        
        // Vemos si existe el shopcart
        Object spp = session.getAttribute(SHOPCART);
        ShopCart sp;
        if((spp == null) || ! (spp instanceof ShopCart)) {
            return REDIR_LOGIN;
        } else {
            sp = (ShopCart) spp;
            if(!sp.valid(id_user)) {
                return REDIR_LOGIN;
            }
        }
        
        // Llegados aquí, ya tenemos un carrito en perfecto estado de "revista"
        Collection<ItemProduct> products = sp.getProducts();
        double total = orderService.getTotalPrice(products);
        m.addAttribute("products", products);
        m.addAttribute("total", total);
        return RECEIPT;
    }
    
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String payment(Model m) {
        m.addAttribute("orderreceipt", new OrderReceipt());
        return PAYMENT;
    }
    
    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public String finish(ModelMap model, 
            @ModelAttribute(value = "orderreceipt") @Valid OrderReceipt receipt,
            BindingResult errors, HttpSession session) {
        if (errors.hasErrors()) {
            System.out.println("Error validación");
            return PAYMENT;   //error
        }
        // Comprobamos que existe el id_user en session
        int id_user = Functions.getID_USER(session);
        if(id_user <= 1) {
            return REDIR_LOGIN;   // Creo que así valdría : D
        }
        
        // Vemos si existe el shopcart
        Object spp = session.getAttribute(SHOPCART);
        ShopCart sp;
        if((spp == null) || ! (spp instanceof ShopCart)) {
            return REDIR_LOGIN;
        } else {
            sp = (ShopCart) spp;
            if(!sp.valid(id_user)) {
                return REDIR_LOGIN;
            }
        }
        boolean create = orderService.createOrder(sp, receipt);
        if(!create) {
            //ERROR ¿A DÓNDE MANDO?
        }
        return FINISH;
    }
    
    @RequestMapping(value = {"/add", "/payment", "/finish"}, method = RequestMethod.GET)
    public String error(HttpServletRequest request) {
        String referer = request.getHeader("Referer");  // Obtenemos la página de dónde venimos : D
        return "redirect:"+ referer;
    }
    
    /*
    
    
    
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
