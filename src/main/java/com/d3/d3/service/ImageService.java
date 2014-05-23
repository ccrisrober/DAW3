/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Image;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface ImageService {
    public boolean create(Image m);
    public boolean create(List<Image> imgs);
    public boolean create(Image[] imgs);
}
