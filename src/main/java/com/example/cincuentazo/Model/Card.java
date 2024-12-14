package com.example.cincuentazo.Model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 * Represents a card in the game with a value.
 */
public class Card implements CardInterface{//numero y palo
    private int valor;

    /**
     * Sets the value of the card.
     *
     * @param valor the value to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }


    /**
     * Gets the value of the card based on its name.
     *
     * @param name the name of the card
     * @return the value of the card
     */
    @Override
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
                    if (!Thread.currentThread().isInterrupted()) {
                       valor = 1;
                    } else {
                        valor = askForAceValue();
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

    /**
     * Asks the user to choose a value for the Ace card.
     *
     * @return the chosen value for the Ace card
     */
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
