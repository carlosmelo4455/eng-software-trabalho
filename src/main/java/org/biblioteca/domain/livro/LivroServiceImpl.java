package org.biblioteca.domain.livro;

import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;
import java.util.Optional;

@Singleton
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository = LivroRepositoryImpl.getInstance();

    private LivroServiceImpl() {
    }

    public static LivroServiceImpl getInstance() {
        return SingletonManager.getInstance(LivroServiceImpl.class);
    }

    @Override
    public Livro adicionar(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public Optional<Livro> buscarPorId(String codigo) {
        return livroRepository.findById(codigo);
    }

    @Override
    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    @Override
    public Livro atualizar(Livro livroAtualizado) {
        return livroRepository.update(livroAtualizado);
    }

    @Override
    public void remover(Livro livro) {
        livroRepository.delete(livro);
    }
}