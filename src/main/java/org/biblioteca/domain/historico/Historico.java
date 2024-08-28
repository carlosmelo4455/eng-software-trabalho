package org.biblioteca.domain.historico;

import org.biblioteca.config.model.Identity;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.transacao.Transacao;
import org.biblioteca.domain.usuario.Usuario;

import java.time.LocalDate;

public class Historico implements Identity<Long>, Transacao {
    private final Usuario usuario;
    private final Livro livro;
    private final LocalDate dataTransacao;
    private final Acao acao;
    private Long id;

    public Historico(Transacao transacao, Acao acao) {
        this.usuario = transacao.getUsuario();
        this.livro = transacao.getLivro();
        this.dataTransacao = transacao.getDataTransacao();
        this.acao = acao;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public Acao getAcao() {
        return acao;
    }
}
