package com.example.cincuentazo.Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Represents a deck of cards used in the game.
 */
public class Deck {//generar un maso, enviar una carta,recolver el mazo

    private Queue<String> taskQueue;
    private List<String> playedCards;
    private String[] palos;
    private String[] valores;
    private String lastPlayedCard;


    /**
     * Constructs a new Deck object and initializes the card suits and values.
     */
    public Deck() {
        this.taskQueue = new LinkedList<>();
        this.playedCards = new LinkedList<>();
        this.palos = new String[]{"c", "d", "p", "t"};
        this.valores = new String[]{"a", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k"};

    }

    /**
     * Generates a new deck of cards by combining suits and values, and shuffles the deck.
     */
    public void generateDeck() {
        for (String palo : palos) {
            for (String valor : valores) {
                taskQueue.add(valor + palo);
            }
        }
        shuffleDeck();
    }

    /**
     * Shuffles the deck of cards.
     */
    public void shuffleDeck() {
        List<String> list = new LinkedList<>(taskQueue);
        Collections.shuffle(list);
        taskQueue = new LinkedList<>(list);
    }

    /**
     * Returns a list of cards currently in the deck.
     *
     * @return a list of cards in the deck
     */
    public List<String> getDeck()
    {
        return new LinkedList<>(taskQueue);
    }

    /**
     * Retrieves a card from the deck. If the deck is empty, it reshuffles the played cards back into the deck.
     *
     * @return the card drawn from the deck
     */
    public String getCard()
    {
        if(taskQueue.isEmpty())
        {
            reshuffleDeck();
        }
        return taskQueue.poll();
    }

    /**
     * Adds a card to the list of played cards.
     *
     * @param card the card to add to the played cards
     */
    public void addPlayedCard(String card)
    {
        lastPlayedCard = card;
        playedCards.add(card);
        System.out.println("Played cards: " + playedCards);
    }

    /**
     * Reshuffles the played cards back into the deck, excluding the last played card.
     */
    private void reshuffleDeck()
    {
        if (lastPlayedCard != null)
        {
            playedCards.remove(lastPlayedCard);
        }
        taskQueue.addAll(playedCards);
        playedCards.clear();
        shuffleDeck();
        System.out.println("Deck reshuffled" + taskQueue);
    }

}
