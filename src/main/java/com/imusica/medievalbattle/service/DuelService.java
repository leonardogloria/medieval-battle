package com.imusica.medievalbattle.service;

import com.imusica.medievalbattle.exception.HeroNotFoundException;
import com.imusica.medievalbattle.factory.*;
import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.DuelVO;
import com.imusica.medievalbattle.model.Player;
import com.imusica.medievalbattle.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class DuelService {
    Logger logger = LoggerFactory.getLogger(DuelService.class);
    public static final int KOBOLD = 3;
    public static final int ORC = 2;
    public static final int MORTO_VIVO = 1;

    @Autowired
    DiceService diceService;
    @Autowired
    PlayerRepository playerRepository;

    public int calculateInitiative(Character hero, Character monster){
        Integer monsterIniciative = monster.getAgilidade() + diceService.rollD10();
        Integer heroIniciative = hero.getAgilidade() + diceService.rollD10();
        while(monsterIniciative == heroIniciative){
            monsterIniciative = monster.getAgilidade() + diceService.rollD10();
            heroIniciative = hero.getAgilidade() + diceService.rollD10();
        }
        if(heroIniciative > monsterIniciative){
            return 1;
        } else{
            return -1;
        }
    }
    public DuelVO duel(Player player, Character hero, Character monster){

        logger.info("Iniciando Duelo");
        logger.info("Calculando iniciativa");
        DuelVO duelVO = new DuelVO();
        UUID uuid = UUID.randomUUID();
        duelVO.setUuid(uuid);
        Integer iniciative = calculateInitiative(hero,monster);
        List<Character> battle;
        if(iniciative == 1){
            logger.info("Hero Win");
            battle = Arrays.asList(hero,monster);
        }else {
            logger.info("Monster Win");
            battle = Arrays.asList(monster,hero);
        }

        int round = 0;
        while (continueDuel(hero,monster)){
            logger.info("Round " + (round + 1));
            Character striker;
            Character defender;
            if(round % 2 == 0){
                striker = battle.get(0);
                defender = battle.get(1);
            }else {
                striker = battle.get(1);
                defender = battle.get(0);

            }
            if(attack(striker,defender)){
                logger.info(striker + " conseguiu atacar");
                int dano = calculateDano(striker);
                logger.info("Dano: " + dano);
                defender.removeDano(dano);
            }else {
                logger.info(striker + " não conseguiu o ataque");
            }
            round++;

        }
        logger.info("Pontos de vida do heroi: " + hero.getPontosDeVida() );
        logger.info("Pontos de vida do monstro: " + monster.getPontosDeVida() );
        if(heroWon(hero,monster)){
            int xp = calculateXP(round);
            duelVO.setRounds(round);
            duelVO.setWinner(hero);
            duelVO.setXp(xp);
            duelVO.setStatus("Won");
            playerRepository.incrementXP(player,duelVO.getXp());

            logger.info(hero + " ganhou! E ganhou " + xp + " de XP ");
        }else {
            duelVO.setWinner(monster);
            duelVO.setRounds(round);
            duelVO.setStatus("Lost");
            logger.info(monster +  " ganhou");
        }
        return duelVO;


    }
    public Character getMonster() {
        Character monster = null;
        Random r = new Random();
        int character = r.nextInt((3 - 1) + 1) + 1;
        switch (character){
            case 1: monster =  new MortoVivoFactory().create();
                break;
            case 2: monster =  new OrcFactory().create();
                break;
            case 3: monster =  new KoboldFactory().create();
                break;
        }
        return monster;
    }
    public Character getHero(String heroPayload) throws HeroNotFoundException {
        heroPayload = heroPayload.toUpperCase();
        Character hero = null;
        switch (heroPayload){
            case "GUERREIRO": hero = new GuerreiroFactory().create();
                break;
            case "BARBARO": hero = new BarbaroFactory().create();
                break;
            case "PALADINO": hero = new PaladinoFactory().create();
                break;
            default: throw new HeroNotFoundException("Herói não localizado");

        }
        return hero;
    }
    private int calculateDano(Character striker){
        int dano = 0;
        logger.info("Lançando " + striker.getQuantidadeDeDano() + "d" + striker.getFatorDeDano());
        for(int i = 0; i< striker.getQuantidadeDeDano(); i++ ){
            dano += diceService.rollDice(striker.getFatorDeDano());
        }
        return dano;
    }

    public Boolean attack(Character striker, Character defender){
        Integer attackFactor = calculateAtack(striker);
        Integer defenseFactor = calculateDefense(defender);
        if(attackFactor > defenseFactor) {
            return true;
        }else {
            return false;
        }
    }
    private Boolean continueDuel(Character hero, Character monster){
        if(hero.getPontosDeVida() <= 0 || monster.getPontosDeVida() <= 0){
            return false;
        }else {
            return true;
        }
    }
    private boolean heroWon(Character hero, Character monster){
        if(hero.getPontosDeVida() > monster.getPontosDeVida()){
            return true;
        }
        else {
           return false;
        }
    }
    private int calculateXP(int turns){
        return 100 - turns;
    }
    private Integer calculateAtack(Character striker){
        return diceService.rollD10() + striker.getAgilidade() + striker.getForca();
    }
    private Integer calculateDefense(Character defender){
        return diceService.rollD10() + defender.getAgilidade() + defender.getDefesa();
    }


}

