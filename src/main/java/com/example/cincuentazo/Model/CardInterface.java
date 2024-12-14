package com.example.cincuentazo.Model;

/**
 * Interface representing a card in the game.
 * This interface is used as a component in the Decorator pattern.
 * The Decorator pattern allows for dynamically adding functionality to a card without altering its structure.
 */
public interface CardInterface {
    int getValor(String name);
}
