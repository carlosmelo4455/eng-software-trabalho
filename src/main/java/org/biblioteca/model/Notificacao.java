package org.biblioteca.model;

public class Notificacao {
    private final Livro livro;
    private int quantidadeNotificacoes;

    public Notificacao(Livro livro) {
        this.livro = livro;
        this.quantidadeNotificacoes = 0;
    }

    public Livro getLivro() {
        return livro;
    }

    public int getQuantidadeNotificacoes() {
        return quantidadeNotificacoes;
    }

    public void notificar() {
        quantidadeNotificacoes++;
    }
}