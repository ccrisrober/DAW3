/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.d3.d3.service;

import com.d3.d3.exceptions.CardNotFoundException;
import com.d3.d3.model.Card;
import com.d3.d3.repository.CardRepository;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardService {
    @Resource
    CardRepository cardRepository;

    @Transactional
    @Override
    public Card create(Card c) {
        return cardRepository.save(c);
    }

    @Transactional(rollbackFor = CardNotFoundException.class)
    @Override
    public Card update(Card c) throws CardNotFoundException {
        // Buscamos la tarjeta vieja con el id de "c"
        Card old = cardRepository.findOne(c.getIdCard());
        // Si es null, devolvemos excepción
        if(old == null) {
            throw new CardNotFoundException();
        }
        old.update(c);
        cardRepository.save(old);
        return old;
    }

    @Transactional(rollbackFor = CardNotFoundException.class)
    @Override
    public boolean delete(Integer id) throws CardNotFoundException {
        // Buscamos la "card" con el id = "id"
        Card del = cardRepository.findOne(id);
        // Si es null, devolvemos excepción
        if(del == null) {
            throw new CardNotFoundException();
        }
        cardRepository.delete(del);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Card findById(Integer id) {
        return cardRepository.findOne(id);
    }

    @Override
    public void setRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
}
