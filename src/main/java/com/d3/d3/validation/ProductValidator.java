/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.validation;

import com.d3.d3.model.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Cristian
 */
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Product.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // Compruebo argumentos vacíos
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "nameProduct.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "priceProduct.required");
        // Compruebo rangos
        Product u = (Product) o;
        // Trolololo
    }
}
