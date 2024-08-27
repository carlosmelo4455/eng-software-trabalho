package org.biblioteca.domain.transacao.reserva;

import org.biblioteca.config.model.Identity;
import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.transacao.Transacao;
import org.biblioteca.domain.usuario.Usuario;

import java.time.LocalDate;

public class Reserva implements Identity<Long>, Transacao {
    private final Usuario usuario;
    private final Livro livro;
    private final LocalDate dataReserva;
    private Long id;

    public Reserva(Usuario usuario, Livro Livro) {
        this.usuario = usuario;
        this.livro = Livro;
        this.dataReserva = LocalDate.now();
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

    @Override
    public LocalDate getDataTransacao() {
        return dataReserva;
    }
}