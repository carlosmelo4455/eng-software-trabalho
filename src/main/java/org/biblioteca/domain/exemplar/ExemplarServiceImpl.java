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
    public Exemplar adicionar(Exemplar exemplar) {
        return exemplarRepository.save(exemplar);
    }

    @Override
    public Optional<Exemplar> buscarPorId(String codigo) {
        return exemplarRepository.findById(codigo);
    }

    @Override
    public List<Exemplar> listarTodos() {
        return exemplarRepository.findAll();
    }

    @Override
    public Exemplar atualizar(Exemplar exemplar) {
        return exemplarRepository.update(exemplar);
    }

    @Override
    public void remover(Exemplar exemplar) {
        exemplarRepository.delete(exemplar);
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
    public void atualizarStatusExemplar(String codigoExemplar, boolean disponivel) {
        exemplarRepository.findById(codigoExemplar).ifPresent(exemplar -> {
            exemplar.setDisponivel(disponivel);
            exemplarRepository.update(exemplar);
        });
    }

    @Override
    public boolean existeLivro(String codigoLivro) {
        return !exemplarRepository.findExemplaresPorCodigoLivro(codigoLivro).isEmpty();
    }

    @Override
    public List<Exemplar> buscarExemplaresDisponiveisPorCodigoLivro(String codigoLivro) {
        return exemplarRepository.findExemplaresDisponiveisPorCodigoLivro(codigoLivro);
    }
}