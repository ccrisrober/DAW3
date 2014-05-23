/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d3.d3.validation.others;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Cristian
 */
public class Functions {

    public static int getID_USER(HttpSession session) {
        /*Object id_ = session.getAttribute("ID_USER");
         int id = -1;
         if(id_ != null && id_ instanceof ()) {
         try {
                
         }
         }*/
        return 0;
    }

    public static int getInt(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (str.length() > 1) {
                i++;
            } else {
                return -1;
            }
        }
        for (; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return -1;
            }
        }
        return Integer.parseInt(str);
    }
    
}
