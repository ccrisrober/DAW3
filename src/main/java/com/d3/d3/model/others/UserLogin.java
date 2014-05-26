/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model.others;

import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
public class UserLogin implements Serializable {
    public final int MIN_USER = 4;
    public final int MAX_USER = 30;
    public final int MIN_PASS = 6;
    public final int MAX_PASS = 20;
    
    @Size(min = MIN_USER, max = MAX_USER)
    private String username;
    
    @Size(min = MIN_PASS, max = MAX_PASS)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
