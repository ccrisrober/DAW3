/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d3.d3.controller;

import com.d3.d3.model.Order1;
import com.d3.d3.repository.OrderRepository;
import com.d3.d3.service.OrderService;
import com.d3.d3.validation.others.Functions;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// ESTÄ MAL HECHO EL EDIT POR POST LOS VALORES QUE PASO, REVISAR EN TOOOOODOOOOOOS
/**
 *
 * @author Cristian
 */
@Controller
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderRepository orderRepository;

    @RequestMapping(value = {"/admin/order", "/admin/order/index"}, method = RequestMethod.GET)
    public String showOrders(Model model) {
        orderService.setRepository(orderRepository);
        List<Order1> orders = orderService.findAll();
        if (orders == null) {
            orders = new LinkedList<Order1>();
        }
        model.addAttribute("orders", orders);
        return "order/showAllOrders";
    }

    //Así se hace un buen crud : D http://fruzenshtein.com/spring-mvc-hibernate-maven-crud/
    @RequestMapping(value = "/admin/order/delete/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        if (id_ <= 0) {
            m.addAttribute("error", "Producto no encontrado");
            return "redirect:../order.html";
        }
        orderService.setRepository(orderRepository);
        boolean delete = orderService.delete(id_);
        if (!delete) {
            m.addAttribute("error", "Pedido no encontrado");
        } else {
            m.addAttribute("ok", "Pedido borrado");
        }
        return "redirect:../../order.html";
    }

    @RequestMapping(value = "/admin/order/show/{id}", method = RequestMethod.GET)
    public String showOrderAdmin(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        if (id_ <= 0) {
            m.addAttribute("error", "Producto no encontrado");
            return "redirect:../order.html";
        }
        orderService.setRepository(orderRepository);
        Order1 order = orderService.findById(id_);
        if (order == null) {
            m.addAttribute("error", "Pedido no encontrado");
            return "redirect:../order.html";
        }
        m.addAttribute("order", order);
        return "order/_view";
    }

    @RequestMapping(value = "/admin/order/status/{id}", method = RequestMethod.GET)
    public String changeStatusOrder_get(@PathVariable String id, Model m) {
        int id_ = Functions.getInt(id);
        if (id_ <= 0) {
            m.addAttribute("error", "Producto no encontrado");
            return "redirect:../order.html";
        }
        orderService.setRepository(orderRepository);
        Order1 order = orderService.findById(id_);
        if (order == null) {
            m.addAttribute("error", "Pedido no encontrado");
            return "redirect:../order.html";
        }
        m.addAttribute("status", "listo");//order.getStatus());
        m.addAttribute("all_status", Order1.allStatus);
        return "order/status";
    }

    // Igual aquí el id me lo puedo quitar xD
    @RequestMapping(value = "/admin/order/status/{id}", method = RequestMethod.POST)
    public String changeStatusOrder_post(@PathVariable String status, @PathVariable String id,
            BindingResult errors, Model m) {
        // Compruebo si el status es de los buenos
        if (!Order1.allStatus.contains(status)) {
            ObjectError err_status = new ObjectError("status", "orderForm.status.incorrect");
            errors.addError(err_status);
        }
        // Compruebo si el id es correcto
        if (Functions.getInt(id) < 0) {
            ObjectError err_id = new ObjectError("id", "orderForm.id.incorrect");
            errors.addError(err_id);
        }

        if (errors.hasErrors()) {
            System.out.println("Error validación");
            return "order/status";
        }
        orderService.setRepository(orderRepository);
        boolean update = orderService.updateStatus(Functions.getInt(id), status);
        if (!update) {
            m.addAttribute("error", "No se ha podido actualizar");
            return "order/status";
        }
        m.addAttribute("ok", "Pedido actualizado");// + order.getID() + " (" + order.getUser().getUsername() + ") actualizado");
        return "order/status";
    }

    @RequestMapping(value = "/user/order/{id}", method = RequestMethod.GET)
    public String showOrderUser(@PathVariable String id,
            Model m, HttpSession session) {
        //Comprobamos si el pedido es del usuario
        int idUser = Functions.getID_USER(session);
        if (idUser <= 0) {
            m.addAttribute("error", "Producto no encontrado");  // esto no es el error xD
        } else {
            int idOrd = Functions.getInt(id);
            if (idOrd <= 0) {
                m.addAttribute("error", "Producto no encontrado");  // esto no es el error xD
            } else {
                orderService.setRepository(orderRepository);
                boolean checkAccess = orderService.checkAccessUser(idOrd, idUser);
                if (!checkAccess) {
                    m.addAttribute("error", "Producto no encontrado");  // esto no es el error xD
                }
                Order1 o = orderService.findById(idOrd);    // No hace falta comprobar porque si ha llegado aquí,
                //  es que al menos existe y se tiene acceso
                System.out.println(o.getItemCollection() == null);
                m.addAttribute("order", o);
            }
        }
        return "order/_view";
    }

    @RequestMapping(value = {"/user/order", "/user/order/index", "/user/order/"}, method = RequestMethod.GET)
    public String showAllOrderUser(Model m, HttpSession session) {
        orderService.setRepository(orderRepository);
        List<Order1> order = orderService.findAll();
        if (order == null) {
            order = new LinkedList<Order1>();
        }
        m.addAttribute("orders", order);
        return "order/ordersUser";
    }

}
