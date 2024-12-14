package com.example.cincuentazo.Model;

// AceValueCardDecorator.java

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
