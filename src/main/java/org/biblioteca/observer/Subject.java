package org.biblioteca.observer;

public interface Subject {
    void registrarObserver(Observer observer);
    void notificarObserver();
}
