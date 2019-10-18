package com.imusica.medievalbattle.controller;

import com.imusica.medievalbattle.exception.HeroNotFoundException;
import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.DuelVO;
import com.imusica.medievalbattle.model.Player;
import com.imusica.medievalbattle.model.RequestVO;
import com.imusica.medievalbattle.repository.PlayerRepository;
import com.imusica.medievalbattle.service.DuelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class GameController {
    Logger logger = LoggerFactory.getLogger(DuelService.class);

    @Autowired
    DuelService duelService;
    @Autowired
    PlayerRepository playerRepository;


    @PostMapping
    public ResponseEntity startGame(@RequestBody RequestVO payload){
        Character hero;
        Player player = playerRepository.findByName(payload.getUsername());
        if(playerNotFound(payload.getUsername())){
            return ResponseEntity.badRequest().body("Player Not Found");
        }
        logger.info("Iniciando o jogo de: " + payload.getUsername());
        String heroPayload = payload.getCharacter();
        try{
           hero = duelService.getHero(heroPayload);
            logger.info("Com o heroi: " + hero);
            Character monster = duelService.getMonster();
            logger.info("Jogando contra: " + monster);
            DuelVO duelVO = duelService.duel(player,hero, monster);
            return ResponseEntity.ok().body(duelVO);

        }catch (HeroNotFoundException ex){
            logger.info("Hero not found");
            return ResponseEntity.badRequest().body(heroNotFound());
        }

    }
    @GetMapping("/ranking")
    public ResponseEntity ranking(){
        Sort sort = new Sort(Sort.Direction.DESC,"xp");
        List<Player> allRanked = playerRepository.findAll(sort);
        return ResponseEntity.ok().body(allRanked);
    }
    private HashMap<String,String> heroNotFound(){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Heroi não encontrado");
        return map;
    }
    private boolean playerNotFound(String playerName){
        Player player = playerRepository.findByName(playerName);
        if(player == null){
            return true;
        }else return false;

    }
}
