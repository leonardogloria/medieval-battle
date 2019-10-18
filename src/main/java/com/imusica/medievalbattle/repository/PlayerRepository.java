package com.imusica.medievalbattle.repository;

import com.imusica.medievalbattle.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface PlayerRepository extends MongoRepository<Player,Long>, CustomPlayerRepository {
    Player findByName(String name);
}
