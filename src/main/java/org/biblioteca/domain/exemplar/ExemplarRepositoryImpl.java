package org.biblioteca.domain.exemplar;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;

@Singleton
public class ExemplarRepositoryImpl extends CacheRepositoryImpl<Exemplar, String> implements ExemplarRepository {

    private ExemplarRepositoryImpl() {
    }

    public static ExemplarRepositoryImpl getInstance() {
        return SingletonManager.getInstance(ExemplarRepositoryImpl.class);
    }

    @Override
    public List<Exemplar> findExemplaresPorCodigoLivro(String codigoLivro) {
        return this.store.values().stream()
                .filter(exemplar -> exemplar.getLivro().getCodigo().equals(codigoLivro))
                .toList();
    }

    @Override
    public Livro findLivroPorCodigo(String codigoLivro) {
        return this.store.values().stream()
                .map(Exemplar::getLivro)
                .filter(livro -> livro.getCodigo().equals(codigoLivro))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Exemplar> findExemplaresDisponiveisPorCodigoLivro(String codigoLivro) {
        return this.store.values().stream()
                .filter(exemplar -> exemplar.getLivro().getCodigo().equals(codigoLivro))
                .filter(Exemplar::isDisponivel)
                .toList();
    }
}