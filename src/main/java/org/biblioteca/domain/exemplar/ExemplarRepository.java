package org.biblioteca.domain.exemplar;

import org.biblioteca.config.repository.CacheRepository;

import java.util.List;

public interface ExemplarRepository extends CacheRepository<Exemplar, String> {
    List<Exemplar> findExemplaresPorCodigoLivro(String codigoLivro);
    Livro findLivroPorCodigo(String codigoLivro);
    List<Exemplar> findExemplaresDisponiveisPorCodigoLivro(String codigoLivro);
}
