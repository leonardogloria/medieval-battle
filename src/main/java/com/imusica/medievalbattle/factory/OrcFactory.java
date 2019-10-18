package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.Kobold;
import com.imusica.medievalbattle.model.Orc;

public class OrcFactory extends CharacterFactory {

    @Override
    public Character create() {
        Orc orc = new Orc();
        orc.setPontosDeVida(20);
        orc.setForca(6);
        orc.setDefesa(2);
        orc.setAgilidade(2);
        orc.setFatorDeDano(8);
        orc.setQuantidadeDeDano(1);
        return orc;
    }
}
