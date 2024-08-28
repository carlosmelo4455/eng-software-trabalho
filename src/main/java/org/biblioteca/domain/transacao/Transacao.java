package org.biblioteca.domain.transacao;

import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.usuario.Usuario;

import java.time.LocalDate;

public interface Transacao {
    Long getId();
    Usuario getUsuario();
    Livro getLivro();
    LocalDate getDataTransacao();
}