package com.example.cincuentazo.Model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Card {//numero y palo
    private int valor;

    public void setValor(int valor) {
        this.valor = valor;
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
                    valor = askForAceValue();
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
