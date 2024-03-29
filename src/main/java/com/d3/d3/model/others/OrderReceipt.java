/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model.others;

import com.d3.d3.annotation.Phone;
import com.d3.d3.model.User;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
public class OrderReceipt implements Serializable {
    public static final int MIN = 5;
    @NotNull
    String payment;
    @NotNull
    @Size(min=MIN)
    String name;
    @NotNull
    @Size(min=MIN)
    String surname;
    @NotNull
    @Size(min=MIN)
    String direction;
    @Phone
    String phone;

    // Esto es solo si usas tarjeta : D
    //@NotNull
    //@Size(min = 4, max = 4)
    String card1;
    //@NotNull
    //@Size(min = 4, max = 4)
    String card2;
    //@NotNull
    //@Size(min = 4, max = 4)
    String card3;
    //@NotNull
    //@Size(min = 4, max = 4)
    String card4;
    //@Date
    private Date date;
    Integer cc_exp_mo;
    Integer cc_exp_yr;

    public Integer getCc_exp_mo() {
        return cc_exp_mo;
    }

    public void setCc_exp_mo(Integer cc_exp_mo) {
        this.cc_exp_mo = cc_exp_mo;
    }

    public Integer getCc_exp_yr() {
        return cc_exp_yr;
    }

    public void setCc_exp_yr(Integer cc_exp_yr) {
        this.cc_exp_yr = cc_exp_yr;
    }
    
    public OrderReceipt() {
        
    }
    
    public OrderReceipt(User user) {
        this.direction = user.getDirection();
        this.name = user.getName();
        this.phone = user.getTelephone();
        this.surname = user.getSurname();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard1() {
        return card1;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public String getCard2() {
        return card2;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }

    public String getCard3() {
        return card3;
    }

    public void setCard3(String card3) {
        this.card3 = card3;
    }

    public String getCard4() {
        return card4;
    }

    public void setCard4(String card4) {
        this.card4 = card4;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
