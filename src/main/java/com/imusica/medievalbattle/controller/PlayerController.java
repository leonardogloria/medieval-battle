package com.imusica.medievalbattle.controller;

import com.imusica.medievalbattle.model.Player;
import com.imusica.medievalbattle.repository.PlayerRepository;
import com.mongodb.MongoWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;
    @PostMapping("/player")
    public ResponseEntity create(@RequestBody Player player){
        UUID uuid = UUID.randomUUID();
        player.setId(uuid.toString());
        try{
            playerRepository.save(player);

        }catch (org.springframework.dao.DuplicateKeyException e){
            return ResponseEntity.badRequest().body("Esse username já existe, escolha outro");
        }
        return ResponseEntity.ok().body(player);

    }
    @GetMapping("/player/{name}")
    public ResponseEntity getById(@PathVariable String name){
        Player player = playerRepository.findByName(name);
        if(player != null){
            return ResponseEntity.ok().body(player);
        }else {
            return  ResponseEntity.notFound().build();
        }

    }
}
