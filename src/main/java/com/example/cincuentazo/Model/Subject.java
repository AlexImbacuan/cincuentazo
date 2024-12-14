// Subject.java
package com.example.cincuentazo.Model;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}