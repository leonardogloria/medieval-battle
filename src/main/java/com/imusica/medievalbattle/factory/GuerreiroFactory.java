package com.imusica.medievalbattle.factory;
import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.Guerreiro;

public class GuerreiroFactory extends CharacterFactory {

    @Override
    public Character create() {
        Guerreiro guerreiro = new Guerreiro();
        guerreiro.setAgilidade(3);
        guerreiro.setPontosDeVida(12);
        guerreiro.setForca(4);
        guerreiro.setDefesa(3);
        guerreiro.setQuantidadeDeDano(2);
        guerreiro.setFatorDeDano(4);
        return guerreiro;
    }
}
