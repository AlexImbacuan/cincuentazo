package com.example.cincuentazo.Model;

public class Player {//4 cartas, un turno, estado(ganador o perdedor), tomar una carta, lanzar una carta, robar una carta, pasar el turno

    private int topcard;
    private boolean winner;
    private boolean turn;
    private Card[] hand;


    public Player(String name) {

        this.topcard = 0;
        this.winner = false;
        this.turn = false;
        this.hand = new Card[4];

    }

    public void takeCard(Card card) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = card;
                break;
            }
        }
    }

    public void throwCard(Card card) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == card) {
                hand[i] = null;
                break;
            }
        }
    }


    public void passTurn() {
        turn = false;
    }

    public void setTurn() {
        turn = true;
    }

    public void setWinner() {
        winner = true;
    }


    public boolean isWinner() {
        return winner;
    }

    public boolean isTurn() {
        return turn;
    }

    public Card[] getHand() {
        return hand;
    }


}
