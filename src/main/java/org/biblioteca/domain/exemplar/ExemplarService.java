package org.biblioteca.domain.exemplar;

import java.util.List;
import java.util.Optional;

public interface ExemplarService {
    Exemplar adicionar(Exemplar livro);

    Optional<Exemplar> buscarPorId(String codigo);

    List<Exemplar> listarTodos();

    Exemplar atualizar(Exemplar exemplar);

    void remover(Exemplar livro);

    List<Exemplar> buscarExemplaresPorCodigoLivro(String codigoLivro);

    Optional<Livro> buscarLivroPorCodigo(String codigoLivro);

    void atualizarStatusExemplar(String codigoExemplar, boolean disponivel);

    boolean existeLivro(String codigoLivro);

    List<Exemplar> buscarExemplaresDisponiveisPorCodigoLivro(String codigoLivro);

}
