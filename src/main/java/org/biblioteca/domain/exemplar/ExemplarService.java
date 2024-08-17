package org.biblioteca.domain.exemplar;

import java.util.List;
import java.util.Optional;

public interface ExemplarService {
    Exemplar adicionarExemplar(Exemplar livro);

    Optional<Exemplar> buscarExemplarPorCodigo(String codigo);

    List<Exemplar> listarTodosExemplares();

    Exemplar atualizarExemplar(Exemplar exemplar);

    void removerExemplar(Exemplar livro);
}
