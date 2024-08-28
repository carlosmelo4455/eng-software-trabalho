package org.biblioteca.domain.exemplar;

import org.biblioteca.config.service.AbstractService;

import java.util.List;
import java.util.Optional;

public class ExemplarServiceImpl extends AbstractService<Exemplar, String> implements ExemplarService {

    private final ExemplarRepository exemplarRepository;

    public ExemplarServiceImpl(ExemplarRepository exemplarRepository) {
        super(exemplarRepository);
        this.exemplarRepository = exemplarRepository;
    }

    @Override
    public List<Exemplar> buscarExemplaresPorCodigoLivro(String codigoLivro) {
        return exemplarRepository.findExemplaresPorCodigoLivro(codigoLivro);
    }

    @Override
    public Optional<Livro> buscarLivroPorCodigo(String codigoLivro) {
        return Optional.ofNullable(exemplarRepository.findLivroPorCodigo(codigoLivro));
    }

    @Override
    public List<Exemplar> buscarExemplaresDisponiveisPorCodigoLivro(String codigoLivro) {
        List<Exemplar> exemplares = exemplarRepository.findExemplaresDisponiveisPorCodigoLivro(codigoLivro);
        if (exemplares.isEmpty()) {
            throw new RuntimeException("Não há exemplares disponíveis do livro.");
        }
        return exemplares;
    }
}