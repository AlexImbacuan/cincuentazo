package com.example.cincuentazo.Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;

public class MachineRunnable implements Runnable {
    private String name;
    private int time;
    private boolean winner;
    private boolean loser;
    private boolean turn;
    private String[] hand;
    private String[] posiblecards;
    private int currentvalue;
    private final Object lock = new Object();
    private Deck deck;
    private ImageView mesaImageView;

    public MachineRunnable(String name, Deck deck) {
        this.name = name;
        this.time = 1000;
        this.winner = false;
        this.loser = false;
        this.turn = false;
        this.hand = new String[4];
        this.posiblecards = new String[4];
        this.currentvalue = 0;
        this.deck = deck;
        this.mesaImageView = mesaImageView;
    }
    public void takeCard(String card) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = card;
                System.out.println(name + " took a card " + card);
                break;
            }
        }
    }
    public Image showCards(int position) {
        if (hand[position] != null) {
            String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + hand[position] + ".jpg").toExternalForm();
            Image ima = new Image(imageUrl);
            return ima;
        }

        return null;
    }


    public void passTurn() {
        turn = false;
    }


    public Image setTurn(int valuemesa) throws InterruptedException {

        synchronized (lock) {
            while (!turn) {
                try {
                    lock.wait(); // Wait until it's this machine's turn
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            currentvalue = valuemesa;
            posiblecards = new String[4];
            boolean canPlay = false;
            for (int i = 0; i < hand.length; i++) {
                if (hand[i] != null) {
                    int valor = getValor(hand[i]);
                    if (valor + currentvalue < 50) {
                        posiblecards[i] = hand[i];
                        canPlay = true;
                    }
                }
            }

            if(!canPlay){
                loser = true;
                return null;
            }
            turn = false;
            Thread.sleep(0000);// Reset turn after processing
            return throwCard(getHighestPossibleCard());
        }

    }
    public void notifyTurn() {
        turn = true;
        synchronized (lock) {
            lock.notify();
        }
    }


    public String getHighestPossibleCard() {
        int highestValue = Integer.MIN_VALUE;
        String highestCard = null;
        for (String card : posiblecards) {
            if (card != null) {
                int cardValue = getValor(card);
                if (cardValue > highestValue) {
                    highestValue = cardValue;
                    highestCard = card;
                }
            }
        }
        return highestCard;
    }
    public Image throwCard(String card) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] != null && hand[i].equals(card)) {
                hand[i] = null;
                break;
            }
        }
        deck.addPlayedCard(card);
        takeCard(deck.getCard());
        URL resourceurl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + card + ".jpg");
        if (resourceurl == null) {
            throw new RuntimeException("Resource not found: /com/example/cincuentazo/Images/cards/" + card + ".jpg");
        }
        String imageUrl = resourceurl.toExternalForm();
        Image ima = new Image(imageUrl);
        return ima;


    }

    public int getValor(String name) {
        char firstChar = name.charAt(0);
        int valor;

        if (Character.isDigit(firstChar)) {
            if (firstChar == '1') {
                valor = 10;
            } else if (firstChar == '9') {
                valor = 0;
            } else
                valor = Character.getNumericValue(firstChar);
        } else {
            switch (firstChar) {
                case 'a':
                    if(currentvalue + 10 < 50){
                        valor = 10;
                    }else {
                        valor = 1;
                    }
                    break;
                case 'j':
                case 'q':
                case 'k':
                    valor = -10;
                    break;
                default:
                    valor = 0; // Default value if the character is not recognized
                    break;
            }
        }

        return valor;
    }


    @Override
    public void run() {
        while (!winner && !loser) {
            if (turn) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean getHand()
    {
        return hand != null;
    }

    public boolean isLoser() {
        return loser;
    }
}
