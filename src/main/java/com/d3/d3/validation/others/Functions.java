/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d3.d3.validation.others;

import com.d3.d3.model.others.UserLogin;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;

/**
 *
 * @author Cristian
 */
public class Functions {

    public static final String ID_USER = "id_user";
    public static final String ID_ADMIN = "id_admin";
    public static final Integer ADMIN_SESSION = 666;
    public static final String ADMIN_USER = "POXMANIA_USER";
    public static final String ADMIN_PASS = "POXMANIA_PASS";
    public static final String LOGIN = "login.html";
    public static final String URL = "http://localhost:8080/caca/";

    
    public static String goAdmin(HttpSession session) {
        String redir = "";
        int id_user = Functions.getID_USER(session);
        if(id_user > 0) {
            redir = "admin/error";   // No tienes derecho a ver la página
        } else  {
            if(!Functions.isAdmin(session)) {
                if(id_user < 0) {
                    redir = "redirect:" + URL + "login.html";
                }
            }
        }
        return redir;
    }
    
    public static boolean isAdmin(HttpSession session) {
        int id_admin = Functions.getID_ADMIN(session);
        boolean ret_ = true;
        if(id_admin != Functions.ADMIN_SESSION) {
            ret_ = false;
        }
        return ret_;
    }

    public static boolean isLogin(HttpSession session) {
        boolean ret_ = true;
        if (!Functions.isAdmin(session)) {
            int id_user = Functions.getID_USER(session);
            if (id_user < 1) {
                ret_ = false;
            }
        }
        return ret_;
    }

    public static boolean isAdmin(UserLogin userlogin) {
        boolean ret_ = false;
        if (userlogin.getUsername().compareToIgnoreCase(ADMIN_USER) == 0
                && userlogin.getPassword().compareToIgnoreCase(ADMIN_PASS) == 0) {
            ret_ = true;
        }
        return ret_;
    }

    public static int getID_USER(HttpSession session) {
        Object id_ = session.getAttribute(Functions.ID_USER);
        try {
            return (Integer) id_;
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getID_ADMIN(HttpSession session) {
        Object id_ = session.getAttribute(Functions.ID_ADMIN);
        try {
            return (Integer) id_;
        } catch (Exception e) {
            return -1;
        }
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
