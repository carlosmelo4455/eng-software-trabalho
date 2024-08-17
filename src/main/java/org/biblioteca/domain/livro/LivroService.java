package org.biblioteca.domain.livro;

import java.util.List;
import java.util.Optional;

public interface LivroService {
    Livro adicionarLivro(Livro livro);

    Optional<Livro> buscarLivroPorCodigo(String codigo);

    List<Livro> listarTodosLivros();

    Livro atualizarLivro(Livro livroAtualizado);

    void removerLivro(Livro livro);
}
