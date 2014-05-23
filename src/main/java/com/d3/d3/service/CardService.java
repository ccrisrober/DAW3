/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.model.Card;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface CardService {
    public Card create(Card c);
    public Card update(Card c) throws CardNotFoundException;
    public boolean delete(Integer id) throws CardNotFoundException;
    public List<Card> findAll();
    public Card findById(Integer id);
}
