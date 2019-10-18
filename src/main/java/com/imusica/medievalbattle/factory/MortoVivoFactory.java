package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.MortoVivo;
import com.imusica.medievalbattle.model.Orc;

public class MortoVivoFactory extends CharacterFactory {

    @Override
    public Character create() {
        MortoVivo mortoVivo = new MortoVivo();
        mortoVivo.setPontosDeVida(25);
        mortoVivo.setForca(4);
        mortoVivo.setDefesa(0);
        mortoVivo.setAgilidade(1);
        mortoVivo.setFatorDeDano(4);
        mortoVivo.setQuantidadeDeDano(2);
        return mortoVivo;
    }
}
