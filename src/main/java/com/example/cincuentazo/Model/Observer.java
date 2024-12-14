// Observer.java
package com.example.cincuentazo.Model;

/**
 * Interface representing an observer in the Observer pattern.
 * Observers are notified of changes in the subject they are observing.
 */
public interface Observer {
    void update(int count);
}