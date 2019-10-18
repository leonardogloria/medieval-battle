package com.imusica.medievalbattle.exception;

public class HeroNotFoundException extends Exception {
    public HeroNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
