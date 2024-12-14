// GameObserver.java
package com.example.cincuentazo.Model;

import javafx.scene.control.Label;

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