package com.imusica.medievalbattle.controller;

import com.imusica.medievalbattle.factory.GuerreiroFactory;
import com.imusica.medievalbattle.factory.KoboldFactory;
import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.service.DiceService;
import com.imusica.medievalbattle.service.DuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    DiceService diceService;
    @Autowired
    DuelService duelService;
    @GetMapping("/teste")
    public void teste(){
        Character hero = new GuerreiroFactory().create();
        Character monster = new KoboldFactory().create();

       // duelService.duel(hero,monster);


    }
}
