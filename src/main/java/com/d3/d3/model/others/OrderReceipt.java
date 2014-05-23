/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model.others;

import com.d3.d3.annotation.Phone;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
public class OrderReceipt {
    @NotNull
    String payment;
    @NotNull
    @Size(min=5)
    String name;
    @NotNull
    @Size(min=5)
    String surname;
    @NotNull
    @Size(min=5)
    String direction;
    @Phone
    String phone;

    // Esto es solo si usas tarjeta : D
    Integer card1;
    Integer card2;
    Integer card3;
    Integer card4;

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

    public Integer getCard1() {
        return card1;
    }

    public void setCard1(Integer card1) {
        this.card1 = card1;
    }

    public Integer getCard2() {
        return card2;
    }

    public void setCard2(Integer card2) {
        this.card2 = card2;
    }

    public Integer getCard3() {
        return card3;
    }

    public void setCard3(Integer card3) {
        this.card3 = card3;
    }

    public Integer getCard4() {
        return card4;
    }

    public void setCard4(Integer card4) {
        this.card4 = card4;
    }
    
}
