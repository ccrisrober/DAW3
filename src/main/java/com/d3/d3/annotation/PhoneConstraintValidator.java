/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Cristian
 */
public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {
 
    public final static String MATCH = "[0-9()-\\.]*";
    
    @Override
    public void initialize(Phone phone) { }
 
    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext cxt) {
        if(phoneField == null) {
            return false;
        }
        return phoneField.matches(MATCH);
    }
 
}