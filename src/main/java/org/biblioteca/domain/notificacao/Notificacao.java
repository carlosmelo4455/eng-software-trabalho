package org.biblioteca.domain.notificacao;

import org.biblioteca.config.model.Identity;
import org.biblioteca.domain.livro.Livro;

public class Notificacao implements Identity<Long> {

    private final Livro livro;
    private Long id;
    private int quantidadeNotificacoes;

    public Notificacao(Livro livro) {
        this.livro = livro;
        this.quantidadeNotificacoes = 0;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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