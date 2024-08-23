package org.biblioteca.domain.historico;

import org.biblioteca.config.model.Identity;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.usuario.Usuario;

import java.time.LocalDate;

public class Historico implements Identity<Long> {
    private final Usuario usuario;
    private final Livro livro;
    private final LocalDate dataReserva;
    private Long id;

    public Historico(Usuario usuario, Livro Livro) {
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

    public LocalDate getDataReserva() {
        return dataReserva;
    }
}
