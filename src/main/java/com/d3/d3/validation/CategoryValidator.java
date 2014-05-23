/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.validation;

import com.d3.d3.model.Category;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Cristian
 */
public class CategoryValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Category.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
    }

}
