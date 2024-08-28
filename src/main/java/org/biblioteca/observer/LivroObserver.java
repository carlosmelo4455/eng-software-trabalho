package org.biblioteca.observer;

public class LivroObserver implements Observer {
    private final String codigoUsuario;
    private int notificacoes = 0;

    public LivroObserver(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public void update() {
        notificacoes++;
    }

    public int getNotificacoes() {
        return notificacoes;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }
}
