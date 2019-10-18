package com.imusica.medievalbattle.repository.impl;

import com.imusica.medievalbattle.model.Player;
import com.imusica.medievalbattle.repository.CustomPlayerRepository;
import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class CustomPlayerRepositoryImpl implements CustomPlayerRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public void incrementXP(Player player, int xp) {
        Query query = new Query(Criteria.where("id").is(player.getId()));
        Update update = new Update();
        update.inc("xp",xp);
        UpdateResult result = mongoTemplate.updateFirst(query,update,Player.class);




    }

    @Override
    public List<Player> getRanking() {
       return null;
    }
}
