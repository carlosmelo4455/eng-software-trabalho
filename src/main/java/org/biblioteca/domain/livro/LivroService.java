package org.biblioteca.domain.livro;

import java.util.List;
import java.util.Optional;

public interface LivroService {
    Livro adicionar(Livro livro);

    Optional<Livro> buscarPorId(String codigo);

    List<Livro> listarTodos();

    Livro atualizar(Livro livroAtualizado);

    void remover(Livro livro);
}
