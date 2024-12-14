package com.example.cincuentazo.Model;

// AceValueCardDecorator.java

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Concrete decorator class that adds functionality to handle Ace card values.
 * This class is used as a decorator in the Decorator pattern.
 * The Decorator pattern allows for dynamically adding functionality to a card without altering its structure.
 */
public class AceValueCardDecorator extends CardDecorator {
    public AceValueCardDecorator(CardInterface decoratedCard) {
        super(decoratedCard);
    }

    @Override
    public int getValor(String name) {
        char firstChar = name.charAt(0);
        if (firstChar == 'a') {
            return askForAceValue();
        }
        return super.getValor(name);
    }

    private int askForAceValue() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choose Ace Value");
        alert.setHeaderText("Ace Value Selection");
        alert.setContentText("Choose the value for Ace:");

        ButtonType buttonTypeOne = new ButtonType("1");
        ButtonType buttonTypeTen = new ButtonType("10");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTen);

        ButtonType result = alert.showAndWait().orElse(buttonTypeOne);
        return result == buttonTypeOne ? 1 : 10;
    }
}
