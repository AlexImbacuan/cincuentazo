// GameSubject.java
package com.example.cincuentazo.Model;

import com.example.cincuentazo.Model.Observer;

import java.util.ArrayList;
import java.util.List;

public class GameSubject implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int count;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(count);
        }
    }

    public void setCount(int count) {
        this.count = count;
        notifyObservers();
    }

    public int getCount() {
        return count;
    }
}