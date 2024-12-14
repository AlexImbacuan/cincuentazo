package com.example.cincuentazo.Model;

// CardDecorator.java
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
