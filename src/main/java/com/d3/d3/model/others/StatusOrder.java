/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.model.others;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cristian
 */
public class StatusOrder implements Serializable {
    @NotNull
    private Integer idOrd;
    @NotNull
    private String status;

    public Integer getIdOrd() {
        return idOrd;
    }

    public void setIdOrd(Integer idOrd) {
        this.idOrd = idOrd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
