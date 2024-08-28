package org.biblioteca.domain.exemplar;

import org.biblioteca.config.service.BaseService;

import java.util.List;
import java.util.Optional;

public interface ExemplarService extends BaseService<Exemplar, String> {

    List<Exemplar> buscarExemplaresPorCodigoLivro(String codigoLivro);

    Optional<Livro> buscarLivroPorCodigo(String codigoLivro);

    void atualizarStatusExemplar(String codigoExemplar, boolean disponivel);

    boolean existeLivro(String codigoLivro);

    List<Exemplar> buscarExemplaresDisponiveisPorCodigoLivro(String codigoLivro);

}
