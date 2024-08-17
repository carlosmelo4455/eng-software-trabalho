package org.biblioteca.domain.exemplar;

import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;
import java.util.Optional;

@Singleton
public class ExemplarServiceImpl implements ExemplarService {

    private final ExemplarRepository exemplarRepository = ExemplarRepositoryImpl.getInstance();

    private ExemplarServiceImpl() {
    }

    public static ExemplarServiceImpl getInstance() {
        return SingletonManager.getInstance(ExemplarServiceImpl.class);
    }

    @Override
    public Exemplar adicionarExemplar(Exemplar exemplar) {
        return exemplarRepository.save(exemplar);
    }

    @Override
    public Optional<Exemplar> buscarExemplarPorCodigo(String codigo) {
        return exemplarRepository.findById(codigo);
    }

    @Override
    public List<Exemplar> listarTodosExemplares() {
        return exemplarRepository.findAll();
    }

    @Override
    public Exemplar atualizarExemplar(Exemplar exemplar) {
        return exemplarRepository.update(exemplar);
    }

    @Override
    public void removerExemplar(Exemplar exemplar) {
        exemplarRepository.delete(exemplar);
    }
}