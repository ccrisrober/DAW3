/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Image;
import com.d3.d3.repository.ImageRepository;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ImageServiceImpl implements ImageService {
    @Resource
    ImageRepository imageRepository;

    @Transactional
    @Override
    public boolean create(Image m) {
        Image new_ = imageRepository.save(m);
        return new_ != null && new_.getIdImg() > 0;
    }

    @Transactional
    @Override
    public boolean create(List<Image> imgs) {
        boolean ret_ = true;
        for(Image i: imgs) {
            if(!(ret_ = create(i))) {
                break;
            }
        }
        return ret_;
    }

    @Transactional
    @Override
    public boolean create(Image[] imgs) {
        boolean ret_ = true;
        for(Image i: imgs) {
            if(!(ret_ = create(i))) {
                break;
            }
        }
        return ret_;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Image> findByProductId(int id) {
        return imageRepository.findByProductId(id);
    }
}
