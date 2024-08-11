package org.biblioteca.services.interfaces;

import org.biblioteca.model.Livro;

import java.util.List;

public interface LivroService {
    Livro adicionarLivro(Livro livro);

    Livro buscarLivroPorCodigo(String codigo);

    List<Livro> listarTodosLivros();

    Livro atualizarLivro(String codigo, Livro livroAtualizado);

    boolean removerLivro(String codigo);
}
