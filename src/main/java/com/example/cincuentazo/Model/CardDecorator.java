package com.example.cincuentazo.Model;

// CardDecorator.java
/**
 * Abstract decorator class implementing the CardInterface.
 * This class is used as a decorator in the Decorator pattern.
 * The Decorator pattern allows for dynamically adding functionality to a card without altering its structure.
 */
public abstract class CardDecorator implements CardInterface {
    protected CardInterface decoratedCard;

    public CardDecorator(CardInterface decoratedCard) {
        this.decoratedCard = decoratedCard;
    }

    @Override
    public int getValor(String name) {
        return decoratedCard.getValor(name);
    }
}
