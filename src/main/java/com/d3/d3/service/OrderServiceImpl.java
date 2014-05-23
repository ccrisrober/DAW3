/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Card;
import com.d3.d3.model.Order1;
import com.d3.d3.model.User;
import com.d3.d3.model.others.ItemProduct;
import com.d3.d3.model.others.OrderReceipt;
import com.d3.d3.repository.OrderRepository;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;




// VIGILAR AND Y OR DE CUANDO COMPRUEBO DATOS EN TODOS LOS SERVICIOS





@Service
public class OrderServiceImpl implements OrderService {
    
    @Resource
    private OrderRepository orderRepository;
    
    @Override
    public boolean createOrder(Collection<ItemProduct> sp, OrderReceipt receipt, Integer id_user, double plus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getTotalPrice(Collection<ItemProduct> products) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateStatus(Integer idOrd, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order1> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order1 findById(Integer idOrd) {
        return orderRepository.findOne(idOrd);
    }

    @Override
    public boolean checkAccessUser(Integer idOrd, Integer idUser) {
        Order1 ord = orderRepository.findOne(idOrd);
        if(ord == null || ord.getIdOrd() <= 0) {
            return false;
        }
        if(ord.getIdUsu().getIdUsu() != idUser) {
            System.out.println("ERROR");
            return false;
        }
        return true;
    }
    /*
    
    @Resource
    private ProductService productService;
    
    @Resource
    private CardService cardService;

    @Resource
    private UserService userService;
    
    @Resource
    private ItemService itemService;
    
    @Override
    public boolean createOrder(Collection<ItemProduct> sp, OrderReceipt receipt, Integer id_user, double plus) {
        // Creo un Order vacío
        Order1 emptyOrder = new Order1();
        emptyOrder.setDirection(receipt.getDirection());
        if(receipt.getPayment().equals("card")) {
            Card cardAux = new Card();
            cardAux.setDateCatd(receipt.getDate());
            String numCard = receipt.getCard1() + "-" + receipt.getCard2() + "-" +
                    receipt.getCard3() + "-" + receipt.getCard4();
            cardAux.setNumCard(numCard);
            Card create = cardService.create(cardAux);
            if(create == null || create.getIdCard() < 0) {
                //ERROR XD
            return false;
            }
            emptyOrder.setIdCard(create.getIdCard());
        }
        User usu = userService.findById(id_user);
        if(usu == null || usu.getIdUsu() <= 0) {
            // ERROR XD
            return false;
        }
        emptyOrder.setIdUsu(usu);
        emptyOrder.setNameRec(receipt.getName());
        emptyOrder.setPrice(0);
        emptyOrder.setStatus("en preparación");
        emptyOrder.setSurnameRec(receipt.getSurname());
        emptyOrder.setTelephone(receipt.getPhone());
        
        // Guardamos el pedido para sacar su id
        Order1 order = orderRepository.save(emptyOrder);
    
        if(order == null || order.getIdOrd() <= 0) {
            //ERROR XD
            return false;
        }
        
        // Ahora cremos la tabla intermedia
        boolean create = itemService.create(sp, order.getIdOrd());
    
        if(!create) {
            //ERROR XD
            return false;
        }
    
        double price = this.getTotalPrice(sp);
        
        order.setPrice(price);
        
        Order1 save = orderRepository.save(order);
        
        if(save == null || save.getIdOrd() <= 0) {
            //ERROR XD
            System.out.println("hola");
            return false;
        }
        return true;
    }

    @Override
    public double getTotalPrice(Collection<ItemProduct> products) {
        double total = 0.0;
        for(ItemProduct p: products) {
            total += productService.findPriceById(p.getId());
        }
        return total;
    }
    
    @Override
    public boolean updateStatus(Integer idOrd, String status) {
        Order1 findById = this.findById(idOrd);
        if(findById == null || findById.getIdOrd() <= 0) {
            return false;
        }
        findById.setStatus(status);
        Order1 upd = orderRepository.save(findById);
        return upd != null;
    }

    */
}
