/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.validation;

import com.d3.d3.model.others.UserLogin;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Cristian
 */
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return UserLogin.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // Compruebo argumentos vacíos
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        UserLogin user = (UserLogin) o;
        // Compruebo rangos
        if (user.getUsername().length() < user.MIN_USER) {
            errors.rejectValue("username", "username.min");
        } 
        if (user.getUsername().length() > user.MAX_USER) {
            errors.rejectValue("username", "username.max");
        }
        if (user.getPassword().length() < user.MIN_PASS) {
            errors.rejectValue("password", "password.min");
        }
        if (user.getPassword().length() > user.MAX_PASS) {
            errors.rejectValue("password", "password.max");
        }
    }
    
}
