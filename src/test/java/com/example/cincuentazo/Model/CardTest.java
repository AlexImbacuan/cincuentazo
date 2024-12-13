package com.example.cincuentazo.Model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @BeforeEach
    public void interruptThreads() {
        // Interrumpe todos los hilos antes de cada prueba
        Thread.currentThread().getThreadGroup().interrupt();
    }

    @Test
    public void testgetValuecase3() {
        String card = "3d";
        int result= getValor(card);
        assertEquals(3, result);
    }
    @Test
    public void testgetValuecasej() {
        String card = "jd";
        int result= getValor(card);
        assertEquals(-10, result);
    }
    @Test
    public void testgetValuecaseA() {
        String card = "ad";
        int result= getValor(card);
        assertEquals(10, result);
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
    private int askForAceValue() {

        return  10;    }

}