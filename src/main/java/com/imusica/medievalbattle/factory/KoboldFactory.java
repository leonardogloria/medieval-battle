package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.Kobold;

public class KoboldFactory  extends CharacterFactory {


    @Override
    public Character create() {
        Kobold kobold = new Kobold();
        kobold.setPontosDeVida(12);
        kobold.setForca(4);
        kobold.setDefesa(3);
        kobold.setAgilidade(4);
        kobold.setFatorDeDano(2);
        kobold.setQuantidadeDeDano(3);
        return kobold;
    }
}
