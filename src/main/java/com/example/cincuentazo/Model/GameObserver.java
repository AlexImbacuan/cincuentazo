// GameObserver.java
package com.example.cincuentazo.Model;

import javafx.scene.control.Label;

/**
 * Represents an observer in the Observer pattern for the game.
 * Updates a label with the current count value when notified.
 */
public class GameObserver implements Observer {
    private final Label counterLabel;

    public GameObserver(Label counterLabel) {
        this.counterLabel = counterLabel;
    }

    @Override
    public void update(int count) {
        counterLabel.setText(String.valueOf(count));
    }
}