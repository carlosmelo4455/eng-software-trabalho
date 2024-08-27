package org.biblioteca.domain.transacao;

import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.usuario.Usuario;

import java.time.LocalDate;

public interface Transacao {
    Long getId();
    void setId(Long id);
    Usuario getUsuario();
    Livro getLivro();
    LocalDate getDataTransacao();
}