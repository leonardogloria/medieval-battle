package com.imusica.medievalbattle.repository;

import com.imusica.medievalbattle.model.Player;

import java.util.List;

public interface CustomPlayerRepository {
    void incrementXP(Player player, int xp);
    List<Player> getRanking();
}
