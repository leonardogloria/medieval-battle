package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Barbaro;
import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.Guerreiro;

public class BarbaroFactory extends CharacterFactory {

    @Override
    public Character create() {
        Barbaro barbaro = new Barbaro();
        barbaro.setAgilidade(3);
        barbaro.setPontosDeVida(13);
        barbaro.setForca(6);
        barbaro.setDefesa(1);
        barbaro.setQuantidadeDeDano(6);
        barbaro.setFatorDeDano(2);
        return barbaro;
    }
}
