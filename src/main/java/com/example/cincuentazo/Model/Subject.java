// Subject.java
package com.example.cincuentazo.Model;


/**
 * Interface representing a subject in the Observer pattern.
 * Subjects can add, remove, and notify observers.
 */
public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}