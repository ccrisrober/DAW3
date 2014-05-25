/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.validation;

import com.d3.d3.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Cristian
 */
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // Compruebo argumentos vacíos
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "surname.required");*/
        // Compruebo rangos
        
        // Trolololo
    }
    
}
