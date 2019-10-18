package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.MortoVivo;
import com.imusica.medievalbattle.model.Paladino;

public class PaladinoFactory extends CharacterFactory {
    @Override
    public Character create() {
        Paladino paladino = new Paladino();
        paladino.setPontosDeVida(25);
        paladino.setForca(4);
        paladino.setDefesa(0);
        paladino.setAgilidade(1);
        paladino.setFatorDeDano(4);
        paladino.setQuantidadeDeDano(2);
        return paladino;
    }
}
