/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.validation;

import com.d3.d3.model.Order1;
import com.d3.d3.model.others.OrderReceipt;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Cristian
 */
public class OrderReceiptValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return OrderReceipt.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // Los errores de anotaciones corren a cargo de la entidad y el messages.properties
        OrderReceipt or = (OrderReceipt) o;
        if(!Order1.payment.contains(or.getPayment())) {
            errors.rejectValue("payment", "ErrorPayment.orderreceipt.payment");
        }
        // Si es pago por tarjeta, validamos los cuatro campos con dígitos
        if(or.getPayment().compareTo("card")==0) {
            //if(or.getCard1())
        }
    }
    
}
