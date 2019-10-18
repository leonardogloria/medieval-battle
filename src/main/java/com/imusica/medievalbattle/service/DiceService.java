package com.imusica.medievalbattle.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiceService {
    public int rollDice(int diceMax){
        Random r = new Random();
        return r.nextInt((diceMax - 1 ) + 1) + 1;
    }
    public int rollD10(){
        return rollDice(10);
    }
    public int rollD4(){
        return rollDice(4);
    }
}
