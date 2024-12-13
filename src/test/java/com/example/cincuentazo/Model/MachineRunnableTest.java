package com.example.cincuentazo.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MachineRunnableTest {
    private String[] posiblecards;
    int currentvalue;

    @BeforeEach
    void setUp() {
        this.posiblecards = new String[4];
        currentvalue = 0;

    }
    @Test
    void testgetHighestPossibleCardcaseA() {
        posiblecards = new String[]{"3d", "jd", "ad", "kd"};
        getHighestPossibleCard();
        assertEquals("ad", getHighestPossibleCard());
    }
    @Test
    void testgetHighestPossibleCardcase9() {
        posiblecards = new String[]{"jd", "jd", "kd", "9d"};
        getHighestPossibleCard();
        assertEquals("9d", getHighestPossibleCard());
    }
    @Test
    void testgetHighestPossibleCardcaseAcurrentvalue49() {
        currentvalue = 49;
        posiblecards = new String[]{"ad", "kd", "2d", "9d"};
        getHighestPossibleCard();
        assertEquals("2d", getHighestPossibleCard());
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
}