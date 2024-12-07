package com.example.cincuentazo.Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {//generar un maso, enviar una carta,recolver el mazo

    private Queue<String> taskQueue;
    private String[] palos;
    private String[] valores;

    public Deck() {
        this.taskQueue = new LinkedList<>();
        this.palos = new String[]{"c", "d", "p", "t"};
        this.valores = new String[]{"a", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k"};

    }

    public void generateDeck() {
        for (String palo : palos) {
            for (String valor : valores) {
                taskQueue.add(valor + palo);
            }
        }
        shuffleDeck();
    }

    public void shuffleDeck() {
        List<String> list = new LinkedList<>(taskQueue);
        Collections.shuffle(list);
        taskQueue = new LinkedList<>(list);
    }
    public List<String> getDeck() {
        return new LinkedList<>(taskQueue);
    }
    public String getCard() {
        return taskQueue.poll();
    }

}
